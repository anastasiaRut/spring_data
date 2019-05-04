package com.it.app.service.impl;

import com.it.app.model.Event;
import com.it.app.repository.EventRepository;
import com.it.app.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        Event savedEvent = eventRepository.saveAndFlush(event);
        return savedEvent;
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public void delete(Event entity) {
        eventRepository.delete(entity);
    }

    @Override
    public Event getOne(Long id) {
        return eventRepository.getOne(id);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.saveAndFlush(event);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        //TODO
        return null;
    }

    @Override
    public List<Event> sortEventsByCost() {
        return eventRepository.sortEventsByCost();
    }

    @Override
    public List<Event> findEventsWithStudents() {
        return eventRepository.findEventsWithStudents();
    }
}
