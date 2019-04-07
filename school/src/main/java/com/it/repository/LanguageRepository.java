package com.it.repository;

import com.it.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface for operations on a repository for Language entity
 *
 * @author A. Rutkouskaya
 * @see Language
 */
public interface LanguageRepository extends JpaRepository<Language, Long> {

    /**
     * Find Language and fetching Tutors
     * JPA QL implementation
     *
     * @param language - language
     * @return Language
     */
    @Query("FROM Language language JOIN FETCH language.tutors WHERE language.name = :name")
    Language findLanguageWithTutors(@Param("name") String language);

    /**
     * Find page of All Languages and fetching Tutors
     * JPA QL implementation
     *
     * @return List<Language>
     */
    @Query("FROM Language language JOIN FETCH language.tutors")
    List<Language> findAllWithTutors();
}
