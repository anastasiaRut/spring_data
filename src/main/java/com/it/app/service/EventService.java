package com.it.app.service;

import com.it.app.model.Event;
import com.it.app.repository.EventRepository;

import java.util.List;

/**
 * Service for Event entity
 *
 * @author A. Rutkouskaya
 * @see Event
 * @see EventRepository
 */
public interface EventService {
    /**
     * Save Event entity in database
     *
     * @param event - event
     * @return
     */
    Event save(Event event);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - Event entity
     */
    void delete(Event entity);

    /**
     * Returns a reference to the Event entity
     *
     * @param id - id
     * @return Event
     */
    Event getOne(Long id);

    /**
     * Updates Event entity
     *
     * @param event - Event
     * @return Event
     */
    Event update(Event event);

    /**
     * Finds Event by id
     *
     * @param id - id
     * @return Event
     */
    Event findById(Long id);

    /**
     * Finds all Events
     *
     * @return List<Event>
     */
    List<Event> findAll();

    /**
     * Enrolls Student in Event by ids
     *
     * @param eventId   - id of event
     * @param studentId - id of student
     * @return Event
     */
    Event enrollInEvent(Long eventId, Long studentId);

    /**
     * Find out if a Student is enrolled in a Event
     *
     * @param eventId   - id of event
     * @param studentId - id of student
     * @return boolean
     */
    boolean isEnrolled(Long eventId, Long studentId);

    /**
     * Sort Events by cost
     * JPA QL implementation
     *
     * @return List<Event>
     */
    List<Event> sortEventsByCost();

    /**
     * Find page of All Events and fetching Students
     * JPA QL implementation
     *
     * @return List<Event>
     */
    List<Event> findEventsWithStudents();
}
