package com.it.app.service.impl;

import com.it.app.model.Event;
import com.it.app.repository.EventRepository;
import com.it.app.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public Event addEvent(Event event) {
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
    public Event editEvent(Event event) {
        return eventRepository.saveAndFlush(event);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
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
