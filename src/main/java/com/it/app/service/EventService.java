package com.it.app.service;

import com.it.app.model.Event;

import java.util.List;

public interface EventService {
    Event addEvent(Event event);

    void deleteById(Long id);

    void delete(Event entity);

    Event getOne(Long id);

    Event editEvent(Event event);

    List<Event> findAll();

    List<Event> sortEventsByCost();

    List<Event> findEventsWithStudents();
}
