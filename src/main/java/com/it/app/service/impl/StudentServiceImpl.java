package com.it.app.service.impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Student;
import com.it.app.repository.StudentRepository;
import com.it.app.service.RoleService;
import com.it.app.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The class represents a Implementation of StudentService interface
 *
 * @author A. Rutkouskaya
 */
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

    /**
     * @see StudentService#save(Student)
     */
    @Override
    public Student save(Student student) {
        validate(student.getId() != null, localizedMessageSource.getMessage("error.student.notHaveId", new Object[]{}));
        return saveAndFlush(student);
    }

    /**
     * @see StudentService#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        findById(id);
        studentRepository.deleteById(id);
    }

    /**
     * @see StudentService#delete(Student)
     */
    @Override
    public void delete(Student entity) {
        final Long id = entity.getId();
        validate(id == null, localizedMessageSource.getMessage("error.student.haveId", new Object[]{}));
        findById(id);
        studentRepository.delete(entity);
    }

    /**
     * @see StudentService#getOne(Long)
     */
    @Override
    public Student getOne(Long id) {
        return studentRepository.getOne(id);
    }

    /**
     * @see StudentService#update(Student)
     */
    @Override
    public Student update(Student student) {
        validate(student.getId() == null, localizedMessageSource.getMessage("error.student.haveId", new Object[]{}));
        findById(student.getId());
        return saveAndFlush(student);
    }

    /**
     * @see StudentService#findById(Long)
     */
    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.student.notExist", new Object[]{})));

    }

    /**
     * @see StudentService#findAll()
     */
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    /**
     * @see StudentService#findByPhoneNumber(String)
     */
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
