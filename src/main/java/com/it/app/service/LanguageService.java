package com.it.app.service;

import com.it.app.model.Language;
import com.it.app.repository.LanguageRepository;

import java.util.List;

/**
 * Service for Language entity
 *
 * @author A. Rutkouskaya
 * @see Language
 * @see LanguageRepository
 */
public interface LanguageService {
    /**
     * Save Language entity in database
     *
     * @param language - language
     * @return
     */
    Language save(Language language);

    /**
     * Delete entity from database by id
     *
     * @param id - id
     */
    void deleteById(Long id);

    /**
     * Delete entity from database
     *
     * @param entity - Language entity
     */
    void delete(Language entity);

    /**
     * Returns a reference to the Language entity
     *
     * @param id - id
     * @return Language
     */
    Language getOne(Long id);

    /**
     * Updates Language entity
     *
     * @param language - Language
     * @return Language
     */
    Language update(Language language);

    /**
     * Finds Language by id
     *
     * @param id - id
     * @return Language
     */
    Language findById(Long id);

    /**
     * Finds all Languages
     *
     * @return List<Language>
     */
    List<Language> findAll();

    /**
     * Find Language and fetching Tutors
     * JPA QL implementation
     *
     * @param language - language
     * @return Language
     */
    Language findLanguageWithTutors(String language);

    /**
     * Find page of All Languages and fetching Tutors
     * JPA QL implementation
     *
     * @return List<Language>
     */
    List<Language> findAllWithTutors();

}
