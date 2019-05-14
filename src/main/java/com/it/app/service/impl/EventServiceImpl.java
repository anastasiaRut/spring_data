package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Event;
import com.it.app.model.Student;
import com.it.app.repository.EventRepository;
import com.it.app.service.EventService;
import com.it.app.service.StudentService;
import com.it.app.service.TutorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The class represents a Implementation of EventService interface
 *
 * @author A. Rutkouskaya
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    private final LocalizedMessageSource localizedMessageSource;

    private final TutorService tutorService;

    private final StudentService studentService;

    public EventServiceImpl(EventRepository eventRepository, LocalizedMessageSource localizedMessageSource, TutorService tutorService, StudentService studentService) {
        this.eventRepository = eventRepository;
        this.localizedMessageSource = localizedMessageSource;
        this.tutorService = tutorService;
        this.studentService = studentService;
    }

    /**
     * @see EventService#save(Event)
     */
    @Override
    public Event save(Event event) {
        validate(event.getId() != null, localizedMessageSource.getMessage("error.event.notHaveId", new Object[]{}));
        return saveAndFlush(event);
    }

    /**
     * @see EventService#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        findById(id);
        eventRepository.deleteById(id);
    }

    /**
     * @see EventService#delete(Event)
     */
    @Override
    public void delete(Event entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.event.haveId", new Object[]{}));
        findById(id);
        eventRepository.delete(entity);
    }

    /**
     * @see EventService#getOne(Long)
     */
    @Override
    public Event getOne(Long id) {
        return eventRepository.getOne(id);
    }

    /**
     * @see EventService#update(Event)
     */
    @Override
    public Event update(Event event) {
        validate(event.getId() == null, localizedMessageSource.getMessage("error.event.haveId", new Object[]{}));
        findById(event.getId());
        return saveAndFlush(event);
    }

    /**
     * @see EventService#findAll()
     */
    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    /**
     * @see EventService#isEnrolled(Long, Long)
     */
    @Override
    public boolean isEnrolled(Long eventId, Long studentId) {
        Student student = studentService.findById(studentId);
        Set<Event> events = student.getEvents();
        if (events != null) {
            for (Event event : events) {
                if (event.getId() == eventId)
                    return true;
            }
        }
        return false;
    }

    /**
     * @see EventService#enrollInEvent(Long, Long)
     */
    @Override
    public Event enrollInEvent(Long eventId, Long studentId) {
        Event event = findById(eventId);
        Student student = studentService.findById(studentId);
        validate(isEnrolled(eventId, studentId), localizedMessageSource.getMessage("error.student.isEnrolled", new Object[]{}));
        validate(event.getPlaces() == 0, localizedMessageSource.getMessage("error.event.isFull", new Object[]{}));
        Set<Event> events = student.getEvents();
        if (events == null)
            events = new HashSet<>();
        events.add(event);
        student.setEvents(events);
        studentService.update(student);
        event.setPlaces(event.getPlaces() - 1);
        return update(event);
    }

    /**
     * @see EventService#findById(Long)
     */
    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.event.notExist", new Object[]{})));
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

    /**
     * @see EventService#sortEventsByCost()
     */
    @Override
    public List<Event> sortEventsByCost() {
        return eventRepository.sortEventsByCost();
    }

    /**
     * @see EventService#findEventsWithStudents()
     */
    @Override
    public List<Event> findEventsWithStudents() {
        return eventRepository.findEventsWithStudents();
    }

    private Event saveAndFlush(Event event) {
        validate(event.getTutor() == null || event.getTutor().getId() == null, localizedMessageSource.getMessage("error.event.tutor.isNull", new Object[]{}));
        event.setTutor(tutorService.findById(event.getTutor().getId()));

        return eventRepository.saveAndFlush(event);
    }
}
