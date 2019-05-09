package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Language;
import com.it.app.model.Level;
import com.it.app.model.Tutor;
import com.it.app.repository.TutorRepository;
import com.it.app.service.LanguageService;
import com.it.app.service.LevelService;
import com.it.app.service.impl.TutorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TutorServiceImplTest {
    @InjectMocks
    private TutorServiceImpl tutorService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private LanguageService languageService;

    @Mock
    private LevelService levelService;

    @Test
    public void testFindAll() {
        final List<Tutor> tutorList = Collections.singletonList(new Tutor());
        when(tutorRepository.findAll()).thenReturn(tutorList);
        assertEquals(tutorService.findAll(), tutorList);
    }

    @Test
    public void testFindById() {
        final Tutor tutor = new Tutor();
        when(tutorRepository.findById(any(Long.class))).thenReturn(Optional.of(tutor));
        assertEquals(tutorService.findById(1L), tutor);
    }

    @Test
    public void testSave() {
        final Tutor tutor = new Tutor();
        final Language language = new Language();
        final Level level = new Level();
        level.setId(1l);
        level.setName("level");
        final Set<Level> levels = Collections.singleton(level);
        language.setId(1l);
        language.setName("language");
        tutor.setLanguage(language);
        tutor.setSurname("surname");
        tutor.setName("tutor");
        tutor.setLevels(levels);
        when(languageService.findById(1L)).thenReturn(language);
        when(levelService.findById(1L)).thenReturn(level);
        when(tutorRepository.saveAndFlush(tutor)).thenReturn(tutor);
        assertEquals(tutorService.save(tutor), tutor);
    }

    @Test
    public void testUpdate() {
        final Tutor tutor = new Tutor();
        tutor.setId(1l);
        final Language language = new Language();
        final Level level = new Level();
        level.setId(1l);
        level.setName("level");
        final Set<Level> levels = Collections.singleton(level);
        language.setId(1l);
        language.setName("language");
        tutor.setLanguage(language);
        tutor.setSurname("surname");
        tutor.setName("tutor");
        tutor.setLevels(levels);
        when(languageService.findById(1L)).thenReturn(language);
        when(levelService.findById(1L)).thenReturn(level);
        when(tutorRepository.saveAndFlush(tutor)).thenReturn(tutor);
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));
        assertEquals(tutorService.update(tutor), tutor);
    }

    @Test
    public void testDelete() {
        final Tutor tutor = new Tutor();
        tutor.setId(1L);
        doNothing().when(tutorRepository).delete(tutor);
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));
        assertDoesNotThrow(() -> tutorService.delete(tutor));
    }

    @Test
    public void testDeleteById() {
        final Tutor tutor = new Tutor();
        tutor.setId(1L);
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));
        doNothing().when(tutorRepository).deleteById(any(Long.class));
        assertDoesNotThrow(() -> tutorService.deleteById(1L));
    }

}