package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Level;
import com.it.app.repository.LevelRepository;
import com.it.app.service.LevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The class represents a Implementation of LevelService interface
 *
 * @author A. Rutkouskaya
 */
@Service
@Transactional
public class LevelServiceImpl implements LevelService {
    private final LocalizedMessageSource localizedMessageSource;

    private final LevelRepository levelRepository;

    public LevelServiceImpl(LocalizedMessageSource localizedMessageSource, LevelRepository levelRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.levelRepository = levelRepository;
    }

    /**
     * @see LevelService#save(Level)
     */
    @Override
    public Level save(Level level) {
        validate(level.getId() != null, localizedMessageSource.getMessage("error.level.notHaveId", new Object[]{}));
        validate(levelRepository.existsByName(level.getName()), localizedMessageSource.getMessage("error.level.name.notUnique", new Object[]{}));
        return levelRepository.saveAndFlush(level);
    }

    /**
     * @see LevelService#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        findById(id);
        levelRepository.deleteById(id);
    }

    /**
     * @see LevelService#delete(Level)
     */
    @Override
    public void delete(Level entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.level.haveId", new Object[]{}));
        findById(id);
        levelRepository.delete(entity);
    }

    /**
     * @see LevelService#getOne(Long)
     */
    @Override
    public Level getOne(Long id) {
        return levelRepository.getOne(id);
    }

    /**
     * @see LevelService#update(Level)
     */
    @Override
    public Level update(Level level) {
        validate(level.getId() == null, localizedMessageSource.getMessage("error.level.haveId", new Object[]{}));
        findById(level.getId());
        validate(levelRepository.existsByName(level.getName()), localizedMessageSource.getMessage("error.level.name.notUnique", new Object[]{}));
        return levelRepository.saveAndFlush(level);
    }

    /**
     * @see LevelService#findById(Long)
     */
    @Override
    public Level findById(Long id) {
        return levelRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.level.notExist", new Object[]{})));

    }

    /**
     * @see LevelService#findAll()
     */
    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }

    /**
     * @see LevelService#findByName(String)
     */
    @Override
    public Level findByName(String name) {
        validate(!levelRepository.existsByName(name), localizedMessageSource.getMessage("error.level.notExist", new Object[]{}));
        return levelRepository.findByName(name);
    }

    /**
     * @see LevelService#findAllWithTutors()
     */
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
