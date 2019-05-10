package com.it.app.service;

import com.it.app.model.ScheduleMaker;
import com.it.app.repository.ScheduleMakerRepository;

import java.util.List;

/**
 * Service for ScheduleMaker entity
 *
 * @author A. Rutkouskaya
 * @see ScheduleMaker
 * @see ScheduleMakerRepository
 */
public interface ScheduleMakerService {
    /**
     * Save ScheduleMaker entity in database
     *
     * @param scheduleMaker - scheduleMaker
     * @return
     */
    ScheduleMaker save(ScheduleMaker scheduleMaker);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - ScheduleMaker entity
     */
    void delete(ScheduleMaker entity);

    /**
     * Returns a reference to the ScheduleMaker entity
     *
     * @param id - id
     * @return ScheduleMaker
     */
    ScheduleMaker getOne(Long id);

    /**
     * Updates ScheduleMaker entity
     *
     * @param scheduleMaker - ScheduleMaker
     * @return ScheduleMaker
     */
    ScheduleMaker update(ScheduleMaker scheduleMaker);

    /**
     * Finds ScheduleMaker by id
     *
     * @param id - id
     * @return ScheduleMaker
     */
    ScheduleMaker findById(Long id);

    /**
     * Finds all ScheduleMakers
     *
     * @return List<ScheduleMaker>
     */
    List<ScheduleMaker> findAll();

    /**
     * Find Schedule Makers by Language
     * JPA QL implementation
     *
     * @param language - language
     * @return List<ScheduleMaker>
     */
    List<ScheduleMaker> findAllByLanguage(String language);

}
