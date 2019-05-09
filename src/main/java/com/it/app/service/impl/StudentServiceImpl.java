package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Course;
import com.it.app.model.Event;
import com.it.app.model.Student;
import com.it.app.model.StudentCourse;
import com.it.app.repository.StudentRepository;
import com.it.app.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final LocalizedMessageSource localizedMessageSource;

    private final StudentRepository studentRepository;

    private final RoleService roleService;

    public StudentServiceImpl(LocalizedMessageSource localizedMessageSource, StudentRepository studentRepository, RoleService roleService) {
        this.localizedMessageSource = localizedMessageSource;
        this.studentRepository = studentRepository;
        this.roleService = roleService;
    }

    @Override
    public Student save(Student student) {
        validate(student.getId() != null, localizedMessageSource.getMessage("error.student.notHaveId", new Object[]{}));
        return saveAndFlush(student);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        studentRepository.deleteById(id);
    }

    @Override
    public void delete(Student entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.student.haveId", new Object[]{}));
        findById(id);
        studentRepository.delete(entity);
    }

    @Override
    public Student getOne(Long id) {
        return studentRepository.getOne(id);
    }

    @Override
    public Student update(Student student) {
        validate(student.getId() == null, localizedMessageSource.getMessage("error.student.haveId", new Object[]{}));
        findById(student.getId());
        return saveAndFlush(student);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.student.notExist", new Object[]{})));

    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByPhoneNumber(String phoneNumber) {
        return studentRepository.findByPhoneNumber(phoneNumber);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }

    private Student saveAndFlush(Student student) {
        validate(student.getRole() == null || student.getRole().getId() == null, localizedMessageSource.getMessage("error.student.role.isNull", new Object[]{}));
        student.setRole(roleService.findById(student.getRole().getId()));

        return studentRepository.saveAndFlush(student);
    }
}
