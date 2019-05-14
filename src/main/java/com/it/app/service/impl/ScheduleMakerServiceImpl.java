package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.ScheduleMaker;
import com.it.app.repository.ScheduleMakerRepository;
import com.it.app.service.LanguageService;
import com.it.app.service.RoleService;
import com.it.app.service.ScheduleMakerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The class represents a Implementation of ScheduleMakerService interface
 *
 * @author A. Rutkouskaya
 */
@Service
@Transactional
public class ScheduleMakerServiceImpl implements ScheduleMakerService {
    private final LocalizedMessageSource localizedMessageSource;

    private final ScheduleMakerRepository scheduleMakerRepository;

    private final RoleService roleService;

    private final LanguageService languageService;


    public ScheduleMakerServiceImpl(LocalizedMessageSource localizedMessageSource, ScheduleMakerRepository scheduleMakerRepository, RoleService roleService, LanguageService languageService) {
        this.localizedMessageSource = localizedMessageSource;
        this.scheduleMakerRepository = scheduleMakerRepository;
        this.roleService = roleService;
        this.languageService = languageService;
    }

    /**
     * @see ScheduleMakerService#save(ScheduleMaker)
     */
    @Override
    public ScheduleMaker save(ScheduleMaker scheduleMaker) {
        validate(scheduleMaker.getId() != null, localizedMessageSource.getMessage("error.scheduleMaker.notHaveId", new Object[]{}));
        return saveAndFlush(scheduleMaker);
    }

    /**
     * @see ScheduleMakerService#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        findById(id);
        scheduleMakerRepository.deleteById(id);
    }

    /**
     * @see ScheduleMakerService#delete(ScheduleMaker)
     */
    @Override
    public void delete(ScheduleMaker entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.scheduleMaker.haveId", new Object[]{}));
        findById(id);
        scheduleMakerRepository.delete(entity);
    }

    /**
     * @see ScheduleMakerService#getOne(Long)
     */
    @Override
    public ScheduleMaker getOne(Long id) {
        return scheduleMakerRepository.getOne(id);
    }

    /**
     * @see ScheduleMakerService#update(ScheduleMaker)
     */
    @Override
    public ScheduleMaker update(ScheduleMaker scheduleMaker) {
        validate(scheduleMaker.getId() == null, localizedMessageSource.getMessage("error.scheduleMaker.haveId", new Object[]{}));
        findById(scheduleMaker.getId());
        return saveAndFlush(scheduleMaker);
    }

    /**
     * @see ScheduleMakerService#findAll()
     */
    @Override
    public List<ScheduleMaker> findAll() {
        return scheduleMakerRepository.findAll();
    }

    /**
     * @see ScheduleMakerService#findById(Long)
     */
    @Override
    public ScheduleMaker findById(Long id) {
        return scheduleMakerRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.scheduleMaker.notExist", new Object[]{})));
    }

    /**
     * @see ScheduleMakerService#findAllByLanguage(String)
     */
    @Override
    public List<ScheduleMaker> findAllByLanguage(String language) {
        return scheduleMakerRepository.findAllByLanguage(language);
    }


    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

    private ScheduleMaker saveAndFlush(ScheduleMaker scheduleMaker) {
        validate(scheduleMaker.getRole() == null || scheduleMaker.getRole().getId() == null, localizedMessageSource.getMessage("error.scheduleMaker.role.isNull", new Object[]{}));
        scheduleMaker.setRole(roleService.findById(scheduleMaker.getRole().getId()));
        validate(scheduleMaker.getLanguage() == null || scheduleMaker.getLanguage().getId() == null, localizedMessageSource.getMessage("error.scheduleMaker.language.isNull", new Object[]{}));
        scheduleMaker.setLanguage(languageService.findById(scheduleMaker.getLanguage().getId()));
        return scheduleMakerRepository.saveAndFlush(scheduleMaker);
    }
}
