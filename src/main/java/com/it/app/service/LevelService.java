package com.it.app.service;

import com.it.app.model.Level;
import com.it.app.repository.LevelRepository;

import java.util.List;

/**
 * Service for Level entity
 *
 * @author A. Rutkouskaya
 * @see Level
 * @see LevelRepository
 */
public interface LevelService {
    /**
     * Save Level entity in database
     *
     * @param level - level
     * @return
     */
    Level save(Level level);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - Level entity
     */
    void delete(Level entity);

    /**
     * Returns a reference to the Level entity
     *
     * @param id - id
     * @return Level
     */
    Level getOne(Long id);

    /**
     * Updates Level entity
     *
     * @param level - Level
     * @return Level
     */
    Level update(Level level);

    /**
     * Finds Level by id
     *
     * @param id - id
     * @return Level
     */
    Level findById(Long id);

    /**
     * Finds all Levels
     *
     * @return List<Level>
     */
    List<Level> findAll();


    /**
     * Finds Level by name
     *
     * @param name
     * @return Level
     */
    Level findByName(String name);

    /**
     * Finds page of All Levels and fetching Tutors
     * JPA QL implementation
     *
     * @return List<Level>
     */
    List<Level> findAllWithTutors();
}
