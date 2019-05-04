package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Level;
import com.it.app.repository.LevelRepository;
import com.it.app.service.LevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LevelServiceImpl implements LevelService {
    private final LocalizedMessageSource localizedMessageSource;

    private final LevelRepository levelRepository;

    public LevelServiceImpl(LocalizedMessageSource localizedMessageSource, LevelRepository levelRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.levelRepository = levelRepository;
    }

    @Override
    public Level save(Level level) {
        validate(level.getId() != null, localizedMessageSource.getMessage("error.level.notHaveId", new Object[]{}));
        validate(levelRepository.existsByName(level.getName()), localizedMessageSource.getMessage("error.level.name.notUnique", new Object[]{}));
        return levelRepository.saveAndFlush(level);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        levelRepository.deleteById(id);
    }

    @Override
    public void delete(Level entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.level.haveId", new Object[]{}));
        findById(id);
        levelRepository.delete(entity);
    }

    @Override
    public Level getOne(Long id) {
        return levelRepository.getOne(id);
    }

    @Override
    public Level update(Level level) {
        validate(level.getId() == null, localizedMessageSource.getMessage("error.level.haveId", new Object[]{}));
        findById(level.getId());
        validate(levelRepository.existsByName(level.getName()), localizedMessageSource.getMessage("error.level.name.notUnique", new Object[]{}));
        return levelRepository.saveAndFlush(level);
    }

    @Override
    public Level findById(Long id) {
        return levelRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.level.notExist", new Object[]{})));

    }

    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public Level findByName(String name) {
        validate(!levelRepository.existsByName(name), localizedMessageSource.getMessage("error.level.notExist", new Object[]{}));
        return levelRepository.findByName(name);
    }

    @Override
    public List<Level> findAllWithTutors() {
        return levelRepository.findAllWithTutors();
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
