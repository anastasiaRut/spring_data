package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Course;
import com.it.app.model.Event;
import com.it.app.repository.EventRepository;
import com.it.app.service.EventService;
import com.it.app.service.TutorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    private final LocalizedMessageSource localizedMessageSource;

    private final TutorService tutorService;

    public EventServiceImpl(EventRepository eventRepository, LocalizedMessageSource localizedMessageSource, TutorService tutorService) {
        this.eventRepository = eventRepository;
        this.localizedMessageSource = localizedMessageSource;
        this.tutorService = tutorService;
    }

    @Override
    public Event save(Event event) {
        validate(event.getId() != null, localizedMessageSource.getMessage("error.event.notHaveId", new Object[]{}));
        return saveAndFlush(event);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        eventRepository.deleteById(id);
    }

    @Override
    public void delete(Event entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.event.haveId", new Object[]{}));
        findById(id);
        eventRepository.delete(entity);
    }

    @Override
    public Event getOne(Long id) {
        return eventRepository.getOne(id);
    }

    @Override
    public Event update(Event event) {
        validate(event.getId() == null, localizedMessageSource.getMessage("error.event.haveId", new Object[]{}));
        findById(event.getId());
        return saveAndFlush(event);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.event.notExist", new Object[]{})));
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Event> sortEventsByCost() {
        return eventRepository.sortEventsByCost();
    }

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
