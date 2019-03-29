package com.it;

import com.it.config.AppConfiguration;
import com.it.model.Language;
import com.it.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Main {

    @Autowired
    private StudentRepository studentDAO;
    @Autowired
    private ScheduleMakerRepository scheduleMakerDAO;
    @Autowired
    private RoleRepository roleDAO;
    @Autowired
    private LevelRepository levelDAO;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private TutorRepository tutorDAO;
    @Autowired
    private CourseRepository courseDAO;
    @Autowired
    private EventRepository eventDAO;
    @Autowired
    private StudentCourseRepository studentCourseDAO;
    @Autowired
    private TypeOfCourseRepository typeOfCourseDAO;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotatedClassApplicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Main main = annotatedClassApplicationContext.getBean("main", Main.class);
        performLanguageOperation(main);
        Optional<Language> language = main.getLanguageRepository().findById(1l);
        language.ifPresent(System.out::println);
    }

    private static void performLanguageOperation(Main main) {
        Optional<Language> language = main.getLanguageRepository().findById(1l);
        if (!language.isPresent()) {
            Language entity = new Language();
            entity.setName("English");
            main.getLanguageRepository().save(entity);
        }


    }

    public StudentRepository getStudentDAO() {
        return studentDAO;
    }

    public ScheduleMakerRepository getScheduleMakerDAO() {
        return scheduleMakerDAO;
    }

    public RoleRepository getRoleDAO() {
        return roleDAO;
    }

    public LevelRepository getLevelDAO() {
        return levelDAO;
    }

    public LanguageRepository getLanguageRepository() {
        return languageRepository;
    }

    public TutorRepository getTutorDAO() {
        return tutorDAO;
    }

    public CourseRepository getCourseDAO() {
        return courseDAO;
    }

    public EventRepository getEventDAO() {
        return eventDAO;
    }

    public StudentCourseRepository getStudentCourseDAO() {
        return studentCourseDAO;
    }

    public TypeOfCourseRepository getTypeOfCourseDAO() {
        return typeOfCourseDAO;
    }
}
