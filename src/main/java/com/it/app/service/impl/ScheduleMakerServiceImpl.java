package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.ScheduleMaker;
import com.it.app.model.StudentCourse;
import com.it.app.repository.ScheduleMakerRepository;
import com.it.app.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleMakerServiceImpl implements ScheduleMakerService {
    private final LocalizedMessageSource localizedMessageSource;

    private final ScheduleMakerRepository scheduleMakerRepository;

    private final EventService eventService;

    private final RoleService roleService;

    private final LanguageService languageService;


    public ScheduleMakerServiceImpl(LocalizedMessageSource localizedMessageSource, ScheduleMakerRepository scheduleMakerRepository, EventService eventService, RoleService roleService, LanguageService languageService) {
        this.localizedMessageSource = localizedMessageSource;
        this.scheduleMakerRepository = scheduleMakerRepository;
        this.eventService = eventService;
        this.roleService = roleService;
        this.languageService = languageService;
    }

    @Override
    public ScheduleMaker save(ScheduleMaker scheduleMaker) {
        validate(scheduleMaker.getId() != null, localizedMessageSource.getMessage("error.scheduleMaker.notHaveId", new Object[]{}));
        return saveAndFlush(scheduleMaker);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        scheduleMakerRepository.deleteById(id);
    }

    @Override
    public void delete(ScheduleMaker entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.scheduleMaker.haveId", new Object[]{}));
        findById(id);
        scheduleMakerRepository.delete(entity);
    }

    @Override
    public ScheduleMaker getOne(Long id) {
        return scheduleMakerRepository.getOne(id);
    }

    @Override
    public ScheduleMaker update(ScheduleMaker scheduleMaker) {
        validate(scheduleMaker.getId() == null, localizedMessageSource.getMessage("error.scheduleMaker.haveId", new Object[]{}));
        findById(scheduleMaker.getId());
        return saveAndFlush(scheduleMaker);
    }

    @Override
    public List<ScheduleMaker> findAll() {
        return scheduleMakerRepository.findAll();
    }

    @Override
    public ScheduleMaker findById(Long id) {
        return scheduleMakerRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.scheduleMaker.notExist", new Object[]{})));
    }

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
