package com.it.app.service.impl;

import com.it.app.model.ScheduleMaker;
import com.it.app.repository.ScheduleMakerRepository;
import com.it.app.service.ScheduleMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleMakerServiceImpl implements ScheduleMakerService {
    @Autowired
    ScheduleMakerRepository scheduleMakerRepository;

    @Override
    public ScheduleMaker addScheduleMaker(ScheduleMaker scheduleMaker) {
        ScheduleMaker savedScheduleMaker = scheduleMakerRepository.saveAndFlush(scheduleMaker);
        return savedScheduleMaker;
    }

    @Override
    public void deleteById(Long id) {
        scheduleMakerRepository.deleteById(id);
    }

    @Override
    public void delete(ScheduleMaker entity) {
        scheduleMakerRepository.delete(entity);
    }

    @Override
    public ScheduleMaker getOne(Long id) {
        return scheduleMakerRepository.getOne(id);
    }

    @Override
    public ScheduleMaker editScheduleMaker(ScheduleMaker scheduleMaker) {
        return scheduleMakerRepository.saveAndFlush(scheduleMaker);
    }

    @Override
    public List<ScheduleMaker> findAll() {
        return scheduleMakerRepository.findAll();
    }

    @Override
    public List<ScheduleMaker> findAllByLanguage(String language) {
        return scheduleMakerRepository.findAllByLanguage(language);
    }
}
