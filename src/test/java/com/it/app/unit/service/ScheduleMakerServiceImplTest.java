package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Language;
import com.it.app.model.Role;
import com.it.app.model.ScheduleMaker;
import com.it.app.repository.ScheduleMakerRepository;
import com.it.app.service.LanguageService;
import com.it.app.service.RoleService;
import com.it.app.service.impl.ScheduleMakerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleMakerServiceImplTest {
    @InjectMocks
    private ScheduleMakerServiceImpl scheduleMakerService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private ScheduleMakerRepository scheduleMakerRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private LanguageService languageService;

    @Test
    public void testFindAll() {
        final List<ScheduleMaker> scheduleMakerList = Collections.singletonList(new ScheduleMaker());
        when(scheduleMakerRepository.findAll()).thenReturn(scheduleMakerList);
        assertEquals(scheduleMakerService.findAll(), scheduleMakerList);
    }

    @Test
    public void testFindById() {
        final ScheduleMaker scheduleMaker = new ScheduleMaker();
        when(scheduleMakerRepository.findById(any(Long.class))).thenReturn(Optional.of(scheduleMaker));
        assertEquals(scheduleMakerService.findById(1L), scheduleMaker);
    }

    @Test
    public void testSave() {
        final ScheduleMaker scheduleMaker = new ScheduleMaker();
        final Role role = new Role();
        final Language language = new Language();
        role.setId(1l);
        role.setName("scheduleMaker");
        language.setId(1l);
        language.setName("language");
        scheduleMaker.setRole(role);
        scheduleMaker.setLanguage(language);
        when(roleService.findById(1L)).thenReturn(role);
        when(languageService.findById(1L)).thenReturn(language);
        when(scheduleMakerRepository.saveAndFlush(scheduleMaker)).thenReturn(scheduleMaker);
        assertEquals(scheduleMakerService.save(scheduleMaker), scheduleMaker);
    }

    @Test
    public void testUpdate() {
        final ScheduleMaker scheduleMaker = new ScheduleMaker();
        scheduleMaker.setId(1L);
        final Role role = new Role();
        final Language language = new Language();
        role.setId(1l);
        role.setName("scheduleMaker");
        language.setId(1l);
        language.setName("language");
        scheduleMaker.setRole(role);
        scheduleMaker.setLanguage(language);
        when(roleService.findById(1L)).thenReturn(role);
        when(languageService.findById(1L)).thenReturn(language);
        when(scheduleMakerRepository.saveAndFlush(scheduleMaker)).thenReturn(scheduleMaker);
        when(scheduleMakerRepository.findById(1L)).thenReturn(Optional.of(scheduleMaker));
        assertEquals(scheduleMakerService.update(scheduleMaker), scheduleMaker);
    }

    @Test
    public void testDelete() {
        final ScheduleMaker scheduleMaker = new ScheduleMaker();
        scheduleMaker.setId(1L);
        doNothing().when(scheduleMakerRepository).delete(scheduleMaker);
        when(scheduleMakerRepository.findById(1L)).thenReturn(Optional.of(scheduleMaker));
        assertDoesNotThrow(() -> scheduleMakerService.delete(scheduleMaker));
    }

    @Test
    public void testDeleteById() {
        final ScheduleMaker scheduleMaker = new ScheduleMaker();
        scheduleMaker.setId(1L);
        when(scheduleMakerRepository.findById(1L)).thenReturn(Optional.of(scheduleMaker));
        doNothing().when(scheduleMakerRepository).deleteById(any(Long.class));
        assertDoesNotThrow(() -> scheduleMakerService.deleteById(1L));
    }
}