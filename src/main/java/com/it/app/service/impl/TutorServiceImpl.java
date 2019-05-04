package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Level;
import com.it.app.model.Tutor;
import com.it.app.repository.TutorRepository;
import com.it.app.service.LanguageService;
import com.it.app.service.LevelService;
import com.it.app.service.TutorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TutorServiceImpl implements TutorService {
    private final LocalizedMessageSource localizedMessageSource;

    private final TutorRepository tutorRepository;

    private final LanguageService languageService;

    private final LevelService levelService;

    public TutorServiceImpl(LocalizedMessageSource localizedMessageSource, TutorRepository tutorRepository, LanguageService languageService, LevelService levelService) {
        this.localizedMessageSource = localizedMessageSource;
        this.tutorRepository = tutorRepository;
        this.languageService = languageService;
        this.levelService = levelService;
    }

    @Override
    public Tutor save(Tutor tutor) {
        validate(tutor.getId() != null, localizedMessageSource.getMessage("error.tutor.notHaveId", new Object[]{}));
        return saveAndFlush(tutor);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        tutorRepository.deleteById(id);
    }

    @Override
    public void delete(Tutor entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.tutor.haveId", new Object[]{}));
        findById(id);
        tutorRepository.delete(entity);
    }

    @Override
    public Tutor getOne(Long id) {
        return tutorRepository.getOne(id);
    }

    @Override
    public Tutor update(Tutor tutor) {
        validate(tutor.getId() == null, localizedMessageSource.getMessage("error.tutor.haveId", new Object[]{}));
        findById(tutor.getId());
        return saveAndFlush(tutor);
    }

    @Override
    public Tutor findById(Long id) {
        return tutorRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.tutor.notExist", new Object[]{})));

    }

    @Override
    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    @Override
    public List<Tutor> findTutorsByLanguage(String language) {
        return tutorRepository.findTutorsByLanguage(language);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

    private Tutor saveAndFlush(Tutor tutor) {
        validate(tutor.getLanguage() == null || tutor.getLanguage().getId() == null, localizedMessageSource.getMessage("error.tutor.language.isNull", new Object[]{}));
        validate(tutor.getLevels() == null, localizedMessageSource.getMessage("error.tutor.levels.isNull", new Object[]{}));
        tutor.setLanguage(languageService.findById(tutor.getLanguage().getId()));
        Set<Level> levelsId = tutor.getLevels();
        Set<Level> levels = new HashSet<>();
        for (Level level : levelsId) {
            levels.add(levelService.findById(level.getId()));

        }
        tutor.setLevels(levels);
        return tutorRepository.saveAndFlush(tutor);
    }
}
