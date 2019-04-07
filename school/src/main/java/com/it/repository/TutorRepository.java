package com.it.repository;

import com.it.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface for operations on a repository for Tutor entity
 *
 * @author A. Rutkouskaya
 * @see Tutor
 */
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    /**
     * Find Tutors by language
     * JPA QL implementation
     *
     * @param language - language
     * @return List<Tutor>
     */
    @Query("FROM Tutor tutor WHERE tutor.language.name = :name")
    List<Tutor> findTutorsByLanguage(@Param("name") String language);

}