package com.it.app.repository;

import com.it.app.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface for operations on a repository for Event entity
 *
 * @author A. Rutkouskaya
 * @see Event
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Sort Events by cost
     * JPA QL implementation
     *
     * @return List<Event>
     */
    @Query("FROM Event event order by event.cost desc")
    List<Event> sortEventsByCost();

    /**
     * Find page of All Events and fetching Students
     * JPA QL implementation
     *
     * @return List<Event>
     */
    @Query("FROM Event event JOIN FETCH event.students")
    List<Event> findEventsWithStudents();


}
