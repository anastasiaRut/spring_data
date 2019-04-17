package com.it.app.service;

import com.it.app.model.Level;

import java.util.List;

public interface LevelService {
    Level addLevel(Level level);

    void deleteById(Long id);

    void delete(Level entity);

    Level getOne(Long id);

    Level editLevel(Level level);

    List<Level> findAll();

    Level findByName(String name);

    List<Level> findAllWithTutors();
}
