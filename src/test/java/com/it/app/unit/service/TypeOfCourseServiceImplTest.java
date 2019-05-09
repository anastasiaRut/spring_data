package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.TypeOfCourse;
import com.it.app.repository.TypeOfCourseRepository;
import com.it.app.service.impl.TypeOfCourseServiceImpl;
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

class TypeOfCourseServiceImplTest {

    @InjectMocks
    private TypeOfCourseServiceImpl typeOfCourseService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private TypeOfCourseRepository typeOfCourseRepository;

    @Test
    public void testFindAll() {
        final List<TypeOfCourse> typeOfCourseList = Collections.singletonList(new TypeOfCourse());
        when(typeOfCourseRepository.findAll()).thenReturn(typeOfCourseList);
        assertEquals(typeOfCourseService.findAll(), typeOfCourseList);
    }

    @Test
    public void testFindById() {
        final TypeOfCourse typeOfCourse = new TypeOfCourse();
        when(typeOfCourseRepository.findById(any(Long.class))).thenReturn(Optional.of(typeOfCourse));
        assertEquals(typeOfCourseService.findById(1L), typeOfCourse);
    }

    @Test
    public void testSave() {
        final TypeOfCourse typeOfCourse = new TypeOfCourse();
        when(typeOfCourseRepository.saveAndFlush(typeOfCourse)).thenReturn(typeOfCourse);
        assertEquals(typeOfCourseService.save(typeOfCourse), typeOfCourse);
    }

    @Test
    public void testUpdate() {
        final TypeOfCourse typeOfCourse = new TypeOfCourse();
        typeOfCourse.setId(1L);
        when(typeOfCourseRepository.findById(1L)).thenReturn(Optional.of(typeOfCourse));
        when(typeOfCourseRepository.saveAndFlush(typeOfCourse)).thenReturn(typeOfCourse);
        assertEquals(typeOfCourseService.update(typeOfCourse), typeOfCourse);
    }

    @Test
    public void testDelete() {
        final TypeOfCourse typeOfCourse = new TypeOfCourse();
        typeOfCourse.setId(1L);
        when(typeOfCourseRepository.findById(1L)).thenReturn(Optional.of(typeOfCourse));
        doNothing().when(typeOfCourseRepository).delete(typeOfCourse);
        assertDoesNotThrow(() -> typeOfCourseService.delete(typeOfCourse));
    }

    @Test
    public void testDeleteById() {
        final TypeOfCourse typeOfCourse = new TypeOfCourse();
        typeOfCourse.setId(1L);
        doNothing().when(typeOfCourseRepository).deleteById(any(Long.class));
        when(typeOfCourseRepository.findById(1L)).thenReturn(Optional.of(typeOfCourse));
        assertDoesNotThrow(() -> typeOfCourseService.deleteById(1L));
    }
}