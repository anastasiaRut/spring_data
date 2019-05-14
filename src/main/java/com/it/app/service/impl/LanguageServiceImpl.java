package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Language;
import com.it.app.model.Language;
import com.it.app.repository.LanguageRepository;
import com.it.app.service.LanguageService;
import com.it.app.service.LanguageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The class represents a Implementation of LanguageService interface
 *
 * @author A. Rutkouskaya
 */
@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {
    private final LocalizedMessageSource localizedMessageSource;

    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LocalizedMessageSource localizedMessageSource, LanguageRepository languageRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.languageRepository = languageRepository;
    }

    /**
     * @see LanguageService#save(Language)
     */
    @Override
    public Language save(Language language) {
        validate(language.getId() != null, localizedMessageSource.getMessage("error.language.notHaveId", new Object[]{}));
        validate(languageRepository.existsByName(language.getName()), localizedMessageSource.getMessage("error.language.name.notUnique", new Object[]{}));
        return languageRepository.saveAndFlush(language);
    }

    /**
     * @see LanguageService#findById(Long)
     */
    @Override
    public Language findById(Long id) {
        return languageRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.language.notExist", new Object[]{})));
    }

    /**
     * @see LanguageService#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        findById(id);
        languageRepository.deleteById(id);
    }

    /**
     * @see LanguageService#delete(Language)
     */
    @Override
    public void delete(Language entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.language.haveId", new Object[]{}));
        findById(id);
        languageRepository.delete(entity);
    }

    /**
     * @see LanguageService#getOne(Long)
     */
    @Override
    public Language getOne(Long id) {
        return languageRepository.getOne(id);
    }

    /**
     * @see LanguageService#update(Language)
     */
    @Override
    public Language update(Language language) {
        validate(language.getId() == null, localizedMessageSource.getMessage("error.language.haveId", new Object[]{}));
        findById(language.getId());
        validate(languageRepository.existsByName(language.getName()), localizedMessageSource.getMessage("error.language.name.notUnique", new Object[]{}));
        return languageRepository.saveAndFlush(language);
    }

    /**
     * @see LanguageService#findAll()
     */
    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    /**
     * @see LanguageService#findLanguageWithTutors(String)
     */
    @Override
    public Language findLanguageWithTutors(String language) {
        return languageRepository.findLanguageWithTutors(language);
    }

    /**
     * @see LanguageService#findAllWithTutors()
     */
    @Override
    public List<Language> findAllWithTutors() {
        return languageRepository.findAllWithTutors();
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}