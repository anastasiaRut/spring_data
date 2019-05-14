package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Role;
import com.it.app.model.Student;
import com.it.app.repository.StudentRepository;
import com.it.app.service.RoleService;
import com.it.app.service.impl.StudentServiceImpl;
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
class StudentServiceImplTest {
    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private RoleService roleService;

    @Test
    public void testFindAll() {
        final List<Student> studentList = Collections.singletonList(new Student());
        when(studentRepository.findAll()).thenReturn(studentList);
        assertEquals(studentService.findAll(), studentList);
    }

    @Test
    public void testFindById() {
        final Student student = new Student();
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        assertEquals(studentService.findById(1L), student);
    }

    @Test
    public void testSave() {
        final Student student = new Student();
        final Role role = new Role();
        role.setId(1l);
        role.setName("student");
        student.setRole(role);
        when(roleService.findById(1L)).thenReturn(role);
        when(studentRepository.saveAndFlush(student)).thenReturn(student);
        assertEquals(studentService.save(student), student);
    }

    @Test
    public void testUpdate() {
        final Student student = new Student();
        student.setId(1L);
        final Role role = new Role();
        role.setId(1l);
        role.setName("student");
        student.setRole(role);
        when(studentRepository.saveAndFlush(student)).thenReturn(student);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(roleService.findById(1L)).thenReturn(role);
        assertEquals(studentService.update(student), student);
    }

    @Test
    public void testDelete() {
        final Student student = new Student();
        student.setId(1L);
        doNothing().when(studentRepository).delete(student);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        assertDoesNotThrow(() -> studentService.delete(student));
    }

    @Test
    public void testDeleteById() {
        final Student student = new Student();
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        doNothing().when(studentRepository).deleteById(any(Long.class));
        assertDoesNotThrow(() -> studentService.deleteById(1L));
    }
}