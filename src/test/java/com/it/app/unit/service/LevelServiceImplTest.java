package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Level;
import com.it.app.repository.LevelRepository;
import com.it.app.service.impl.LevelServiceImpl;
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
class LevelServiceImplTest {

    @InjectMocks
    private LevelServiceImpl levelService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private LevelRepository levelRepository;

    @Test
    public void testFindAll() {
        final List<Level> levelList = Collections.singletonList(new Level());
        when(levelRepository.findAll()).thenReturn(levelList);
        assertEquals(levelService.findAll(), levelList);
    }

    @Test
    public void testFindById() {
        final Level level = new Level();
        when(levelRepository.findById(any(Long.class))).thenReturn(Optional.of(level));
        assertEquals(levelService.findById(1L), level);
    }

    @Test
    public void testSave() {
        final Level level = new Level();
        when(levelRepository.saveAndFlush(level)).thenReturn(level);
        assertEquals(levelService.save(level), level);
    }

    @Test
    public void testUpdate() {
        final Level level = new Level();
        level.setId(1L);
        when(levelRepository.findById(1L)).thenReturn(Optional.of(level));
        when(levelRepository.saveAndFlush(level)).thenReturn(level);
        assertEquals(levelService.update(level), level);
    }

    @Test
    public void testDelete() {
        final Level level = new Level();
        level.setId(1L);
        when(levelRepository.findById(1L)).thenReturn(Optional.of(level));
        doNothing().when(levelRepository).delete(level);
        assertDoesNotThrow(() -> levelService.delete(level));
    }

    @Test
    public void testDeleteById() {
        final Level level = new Level();
        level.setId(1L);
        doNothing().when(levelRepository).deleteById(any(Long.class));
        when(levelRepository.findById(1L)).thenReturn(Optional.of(level));
        assertDoesNotThrow(() -> levelService.deleteById(1L));
    }
}