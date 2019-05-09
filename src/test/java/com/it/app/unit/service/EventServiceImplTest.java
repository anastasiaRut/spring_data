package com.it.app.unit.service;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.*;
import com.it.app.repository.EventRepository;
import com.it.app.service.LanguageService;
import com.it.app.service.LevelService;
import com.it.app.service.StudentService;
import com.it.app.service.TutorService;
import com.it.app.service.impl.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {
    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private LocalizedMessageSource localizedMessageSource;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private LanguageService languageService;

    @Mock
    private LevelService levelService;

    @Mock
    private TutorService tutorService;

    @Mock
    private StudentService studentService;

    @Test
    public void testFindAll() {
        final List<Event> eventList = Collections.singletonList(new Event());
        when(eventRepository.findAll()).thenReturn(eventList);
        assertEquals(eventService.findAll(), eventList);
    }

    @Test
    public void testFindById() {
        final Event event = new Event();
        when(eventRepository.findById(any(Long.class))).thenReturn(Optional.of(event));
        assertEquals(eventService.findById(1L), event);
    }

    @Test
    public void enrollInEvent() {
        final Event event = new Event();
        event.setId(1l);
        final Tutor tutor = new Tutor();
        final Student student = new Student();
        student.setId(1l);
        tutor.setId(1l);
        event.setTutor(tutor);
        event.setPlaces(10);
        when(tutorService.findById(1L)).thenReturn(tutor);
        when(studentService.findById(1L)).thenReturn(student);
        when(eventRepository.saveAndFlush(event)).thenReturn(event);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        assertEquals(eventService.update(event), event);
        assertEquals(9,eventService.enrollInEvent(event.getId(),student.getId()).getPlaces());
    }

    @Test
    public void testSave() {
        final Event event = new Event();
        final Level level = new Level();
        final Language language = new Language();
        final Tutor tutor = new Tutor();
        level.setId(1l);
        level.setName("level");
        language.setId(1l);
        language.setName("level");
        tutor.setId(1l);
        tutor.setLanguage(language);
        tutor.setSurname("surname");
        tutor.setName("tutor");
        final Set<Level> levels = Collections.singleton(level);
        tutor.setLevels(levels);
        event.setTutor(tutor);
        when(tutorService.findById(1L)).thenReturn(tutor);
        when(eventRepository.saveAndFlush(event)).thenReturn(event);
        assertEquals(eventService.save(event), event);
    }

    @Test
    public void testUpdate() {
        final Event event = new Event();
        event.setId(1l);
        final Level level = new Level();
        final Language language = new Language();
        final Tutor tutor = new Tutor();
        level.setId(1l);
        level.setName("level");
        language.setId(1l);
        language.setName("level");
        tutor.setId(1l);
        tutor.setLanguage(language);
        tutor.setSurname("surname");
        tutor.setName("tutor");
        final Set<Level> levels = Collections.singleton(level);
        tutor.setLevels(levels);
        event.setTutor(tutor);
        when(tutorService.findById(1L)).thenReturn(tutor);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        when(eventRepository.saveAndFlush(event)).thenReturn(event);
        assertEquals(eventService.update(event), event);
    }

    @Test
    public void testDelete() {
        final Event event = new Event();
        event.setId(1L);
        doNothing().when(eventRepository).delete(event);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        assertDoesNotThrow(() -> eventService.delete(event));
    }

    @Test
    public void testDeleteById() {
        final Event event = new Event();
        event.setId(1L);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        doNothing().when(eventRepository).deleteById(any(Long.class));
        assertDoesNotThrow(() -> eventService.deleteById(1L));
    }

}