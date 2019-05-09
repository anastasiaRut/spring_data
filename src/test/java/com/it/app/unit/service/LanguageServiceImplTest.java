package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Language;
import com.it.app.repository.LanguageRepository;
import com.it.app.service.impl.LanguageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LanguageServiceImplTest {

    @InjectMocks
    private LanguageServiceImpl languageService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private LanguageRepository languageRepository;

    @Test
    public void testFindAll() {
        final List<Language> languageList = Collections.singletonList(new Language());
        when(languageRepository.findAll()).thenReturn(languageList);
        assertEquals(languageService.findAll(), languageList);
    }

    @Test
    public void testFindById() {
        final Language language = new Language();
        when(languageRepository.findById(any(Long.class))).thenReturn(Optional.of(language));
        assertEquals(languageService.findById(1L), language);
    }

    @Test
    public void testSave() {
        final Language language = new Language();
        when(languageRepository.saveAndFlush(language)).thenReturn(language);
        assertEquals(languageService.save(language), language);
    }

    @Test
    public void testUpdate() {
        final Language language = new Language();
        language.setId(1L);
        when(languageRepository.findById(1L)).thenReturn(Optional.of(language));
        when(languageRepository.saveAndFlush(language)).thenReturn(language);
        assertEquals(languageService.update(language), language);
    }

    @Test
    public void testDelete() {
        final Language language = new Language();
        language.setId(1L);
        when(languageRepository.findById(1L)).thenReturn(Optional.of(language));
        doNothing().when(languageRepository).delete(language);
        assertDoesNotThrow(() -> languageService.delete(language));
    }

    @Test
    public void testDeleteById() {
        final Language language = new Language();
        language.setId(1L);
        doNothing().when(languageRepository).deleteById(any(Long.class));
        when(languageRepository.findById(1L)).thenReturn(Optional.of(language));
        assertDoesNotThrow(() -> languageService.deleteById(1L));
    }
}