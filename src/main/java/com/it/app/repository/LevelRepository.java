package com.it.app.repository;

import com.it.app.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface for operations on a repository for Level entity
 *
 * @author A. Rutkouskaya
 * @see Level
 */
public interface LevelRepository extends JpaRepository<Level, Long> {
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
    @Query("FROM Level level JOIN FETCH level.tutors")
    List<Level> findAllWithTutors();

    /**
     * Find out by name if this level exists
     *
     * @param levelName - name of language
     * @return boolean
     */
    boolean existsByName(String levelName);
}
