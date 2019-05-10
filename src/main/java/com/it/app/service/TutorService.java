package com.it.app.service;

import com.it.app.model.Tutor;
import com.it.app.repository.TutorRepository;

import java.util.List;

/**
 * Service for Tutor entity
 *
 * @author A. Rutkouskaya
 * @see Tutor
 * @see TutorRepository
 */
public interface TutorService {
    /**
     * Save Tutor entity in database
     *
     * @param tutor - tutor
     * @return
     */
    Tutor save(Tutor tutor);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - Tutor entity
     */
    void delete(Tutor entity);

    /**
     * Returns a reference to the Tutor entity
     *
     * @param id - id
     * @return Tutor
     */
    Tutor getOne(Long id);

    /**
     * Updates Tutor entity
     *
     * @param tutor - Tutor
     * @return Tutor
     */
    Tutor update(Tutor tutor);

    /**
     * Finds Tutor by id
     *
     * @param id - id
     * @return Tutor
     */
    Tutor findById(Long id);

    /**
     * Finds all Tutors
     *
     * @return List<Tutor>
     */
    List<Tutor> findAll();

    /**
     * Find Tutors by language
     * JPA QL implementation
     *
     * @param language - language
     * @return List<Tutor>
     */
    List<Tutor> findTutorsByLanguage(String language);
}
