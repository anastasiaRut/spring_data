package com.it;

import com.it.config.AppConfiguration;
import com.it.model.*;
import com.it.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class Main {
    private static Main main;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ScheduleMakerRepository scheduleMakerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private TypeOfCourseRepository typeOfCourseRepository;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotatedClassApplicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        main = annotatedClassApplicationContext.getBean("main", Main.class);
        performLanguageOperation();
        performLevelOperation();
        performRoleOperation();
        performStudentOperation();
        performScheduleMakerOperation();
        performTypeOfCourseOperation();
        performTutorsOperation();
        performEventsOperation();
        performCoursesOperation();
        performStudentCoursesOperation();
        /*Optional<Language> language = main.getLanguageRepository().findById(1l);
        language.ifPresent(System.out::println);*/
    }

    private static void performLanguageOperation() {
        Optional<Language> language = main.getLanguageRepository().findById(1l);
        if (!language.isPresent()) {
            Language entity = new Language();
            entity.setName("English");
            main.getLanguageRepository().save(entity);
        }


    }

    private static void performLevelOperation() {
        Optional<Level> level = main.getLevelRepository().findById(1l);
        if (!level.isPresent()) {
            Level entity = new Level();
            entity.setName("Elementary");
            main.getLevelRepository().save(entity);
            entity = new Level();
            entity.setName("Pre-Intermediate");
            main.getLevelRepository().save(entity);
            entity = new Level();
            entity.setName("Intermediate");
            main.getLevelRepository().save(entity);
        }

    }

    private static void performRoleOperation() {
        Optional<Role> role = main.getRoleRepository().findById(1l);
        if (!role.isPresent()) {
            Role entity = new Role();
            entity.setName("student");
            main.getRoleRepository().save(entity);
            entity = new Role();
            entity.setName("maker");
            main.getRoleRepository().save(entity);
        }
    }

    private static void performStudentOperation() {
        Optional<Student> student = main.getStudentRepository().findById(1l);
        if (!student.isPresent()) {
            Student entity = new Student();
            entity.setName("Anastasia");
            entity.setPhoneNumber("+375333333");
            entity.setRole(main.getRoleRepository().getOne(1l));
            entity.setSurname("Rutkouskaya");
            entity.setUsername("nastya_aaa");
            entity.setPassword("aaa12345");
            entity.setE_mail("mmm@mail.ru");
            main.getStudentRepository().save(entity);
            System.out.println(main.getStudentRepository().findByPhoneNumber("+375333333").getUsername());
        }
    }

    private static void performScheduleMakerOperation() {
        Optional<ScheduleMaker> scheduleMaker = main.getScheduleMakerRepository().findById(1l);
        if (!scheduleMaker.isPresent()) {
            ScheduleMaker entity = new ScheduleMaker();
            entity.setName("Olga");
            entity.setLanguage(main.getLanguageRepository().getOne(1L));
            entity.setRole(main.getRoleRepository().getOne(2l));
            entity.setSurname("Ivanova");
            entity.setUsername("olly");
            entity.setPassword("11112345");
            entity.setE_mail("oll@mail.ru");
            main.getScheduleMakerRepository().save(entity);
            System.out.println(main.getScheduleMakerRepository().findAllByLanguage("English").get(0).getUsername());
        }
    }

    private static void performTypeOfCourseOperation() {
        Optional<TypeOfCourse> typeOfCourse = main.getTypeOfCourseRepository().findById(1l);
        if (!typeOfCourse.isPresent()) {
            TypeOfCourse entity = new TypeOfCourse();
            entity.setName("Express");
            entity.setCost(new BigDecimal(150));
            entity.setTimeType("Evening");
            main.getTypeOfCourseRepository().save(entity);
            entity = new TypeOfCourse();
            entity.setName("Weekends");
            entity.setCost(new BigDecimal(330));
            entity.setTimeType("Morning");
            main.getTypeOfCourseRepository().save(entity);
            entity = new TypeOfCourse();
            entity.setName("Standart");
            entity.setCost(new BigDecimal(440));
            entity.setTimeType("Evening");
            main.getTypeOfCourseRepository().save(entity);

        }
    }

    private static void performTutorsOperation() {
        Optional<Tutor> tutor = main.getTutorRepository().findById(1l);
        if (!tutor.isPresent()) {
            Tutor entity = new Tutor();
            entity.setName("Elena");
            entity.setSurname("Petrova");
            entity.setLanguage(main.getLanguageRepository().getOne(1L));
            Set<Level> levels = new HashSet<>();
            levels.add(main.getLevelRepository().getOne(1L));
            levels.add(main.getLevelRepository().getOne(3L));
            entity.setLevels(levels);
            entity.setId(1l);
            main.getTutorRepository().save(entity);
            List<Tutor> tutors = main.getTutorRepository().findTutorsByLanguage("English");
            for (Tutor t : tutors
                    ) {
                System.out.println(t.getName() + " " + t.getSurname());

            }
        }
    }

    private static void performEventsOperation() {
        Optional<Event> event = main.getEventRepository().findById(1l);
        if (!event.isPresent()) {
            Event entity = new Event();
            entity.setName("Book evening");
            entity.setPlaces(15);
            entity.setCost(new BigDecimal(7));
            entity.setTutor(main.getTutorRepository().getOne(1L));
            main.getEventRepository().saveAndFlush(entity);
            Set<Event> events = new HashSet<>();
            entity.setPlaces(main.getEventRepository().getOne(1L).getPlaces() - 1);
            events.add(entity);
            main.getEventRepository().save(entity);
            Student student = main.getStudentRepository().getOne(1L);
            student.setEvents(events);
            main.getStudentRepository().save(student);
            List<Event> sortEvents = main.getEventRepository().sortEventsByCost();
        }
    }

    private static void performCoursesOperation() {
        Optional<Course> course = main.getCourseRepository().findById(1l);
        if (!course.isPresent()) {
            Course entity = new Course();
            entity.setLanguage(main.getLanguageRepository().getOne(1L));
            short places = 15;
            entity.setPlaces(places);
            entity.setLevel(main.getLevelRepository().getOne(3L));
            entity.setTutor(main.getTutorRepository().getOne(1L));
            entity.setStartDate(LocalDate.of(2019, 4, 12));
            entity.setTypeOfCourse(main.getTypeOfCourseRepository().getOne(3L));
            main.getCourseRepository().save(entity);
            List<Course> courses = main.getCourseRepository().sortCoursesByFreePlaces();
            courses = main.getCourseRepository().findCoursesByLanguage("French");
        }
    }

    private static void performStudentCoursesOperation() {
        if (enrolIInCourse(main.getCourseRepository().getOne(1L), main.getStudentRepository().getOne(1L), main.getStudentCourseRepository()))
            System.out.println("Your application is accepted");
        else System.out.println("No free places");

        List<StudentCourse> studentCourses = main.getStudentCourseRepository().findUnacceptedApplications();
        for (StudentCourse studentCourse : studentCourses
                ) {
            System.out.println(studentCourse.getStatus());
        }
    }

    private static boolean enrolIInCourse(Course course, Student student, StudentCourseRepository repository) {
        if (course.getPlaces() == 0) {
            return false;
        }
        Optional<StudentCourse> studentCourse = main.getStudentCourseRepository().findById(1l);
        if (!studentCourse.isPresent()) {
            StudentCourse entity = new StudentCourse();
            entity.setCourse(course);
            entity.setStudent(student);
            entity.setStatus(false);
            repository.save(entity);
        }
        return true;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ScheduleMakerRepository getScheduleMakerRepository() {
        return scheduleMakerRepository;
    }

    public void setScheduleMakerRepository(ScheduleMakerRepository scheduleMakerRepository) {
        this.scheduleMakerRepository = scheduleMakerRepository;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public LevelRepository getLevelRepository() {
        return levelRepository;
    }

    public void setLevelRepository(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public LanguageRepository getLanguageRepository() {
        return languageRepository;
    }

    public void setLanguageRepository(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public TutorRepository getTutorRepository() {
        return tutorRepository;
    }

    public void setTutorRepository(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public StudentCourseRepository getStudentCourseRepository() {
        return studentCourseRepository;
    }

    public void setStudentCourseRepository(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }

    public TypeOfCourseRepository getTypeOfCourseRepository() {
        return typeOfCourseRepository;
    }

    public void setTypeOfCourseRepository(TypeOfCourseRepository typeOfCourseRepository) {
        this.typeOfCourseRepository = typeOfCourseRepository;
    }
}
