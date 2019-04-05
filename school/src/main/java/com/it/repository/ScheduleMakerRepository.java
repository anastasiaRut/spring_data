package com.it.repository;

import com.it.model.ScheduleMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleMakerRepository extends JpaRepository<ScheduleMaker, Long> {

    @Query("FROM ScheduleMaker scheduleMaker WHERE scheduleMaker.language.name = :name")
    List<ScheduleMaker> findAllByLanguage(@Param("name") String language);
}
