package com.it.app.service;

import com.it.app.model.Event;

import java.util.List;

public interface EventService {
    Event save(Event event);

    void deleteById(Long id);

    void delete(Event entity);

    Event getOne(Long id);

    Event update(Event event);

    List<Event> findAll();

    Event findById(Long id);

    List<Event> sortEventsByCost();

    List<Event> findEventsWithStudents();
}
