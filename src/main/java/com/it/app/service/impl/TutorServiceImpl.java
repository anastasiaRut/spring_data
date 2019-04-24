package com.it.app.service.impl;

import com.it.app.model.Tutor;
import com.it.app.repository.TutorRepository;
import com.it.app.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TutorServiceImpl implements TutorService {
    @Autowired
    TutorRepository tutorRepository;

    @Override
    public Tutor addTutor(Tutor tutor) {
        Tutor savedTutor = tutorRepository.saveAndFlush(tutor);
        return savedTutor;
    }

    @Override
    public void deleteById(Long id) {
        tutorRepository.deleteById(id);
    }

    @Override
    public void delete(Tutor entity) {
        tutorRepository.delete(entity);
    }

    @Override
    public Tutor getOne(Long id) {
        return tutorRepository.getOne(id);
    }

    @Override
    public Tutor editTutor(Tutor tutor) {
        return tutorRepository.saveAndFlush(tutor);
    }

    @Override
    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    @Override
    public List<Tutor> findTutorsByLanguage(String language) {
        return tutorRepository.findTutorsByLanguage(language);
    }
}
