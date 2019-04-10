package com.it.app.repository;

import com.it.app.model.ScheduleMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface for operations on a repository for ScheduleMaker entity
 *
 * @author A. Rutkouskaya
 * @see ScheduleMaker
 */
public interface ScheduleMakerRepository extends JpaRepository<ScheduleMaker, Long> {

    /**
     * Find Schedule Makers by Language
     * JPA QL implementation
     *
     * @param language - language
     * @return List<ScheduleMaker>
     */
    @Query("FROM ScheduleMaker scheduleMaker WHERE scheduleMaker.language.name = :name")
    List<ScheduleMaker> findAllByLanguage(@Param("name") String language);
}
