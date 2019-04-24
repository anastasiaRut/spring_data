package com.it.app.service.impl;

import com.it.app.model.Level;
import com.it.app.repository.LevelRepository;
import com.it.app.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LevelServiceImpl implements LevelService {
    @Autowired
    LevelRepository levelRepository;

    @Override
    public Level addLevel(Level level) {
        Level savedLevel = levelRepository.saveAndFlush(level);
        return savedLevel;
    }

    @Override
    public void deleteById(Long id) {
        levelRepository.deleteById(id);
    }

    @Override
    public void delete(Level entity) {
        levelRepository.delete(entity);
    }

    @Override
    public Level getOne(Long id) {
        return levelRepository.getOne(id);
    }

    @Override
    public Level editLevel(Level level) {
        return levelRepository.saveAndFlush(level);
    }

    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public Level findByName(String name) {
        return levelRepository.findByName(name);
    }

    @Override
    public List<Level> findAllWithTutors() {
        return levelRepository.findAllWithTutors();
    }
}
