package com.it.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * The class represents a language school course entity
 *
 * @author A. Rutkouskaya
 */
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * The value is used to describe how many free places are left
     */
    @Column
    private Short places;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TypeOfCourse typeOfCourse;

    @ManyToOne
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<StudentCourse> studentCourses;

    public Course() {
    }

    public Course(LocalDate startDate, Short places, Language language, TypeOfCourse typeOfCourse, Tutor tutor, Level level) {
        this.startDate = startDate;
        this.places = places;
        this.language = language;
        this.typeOfCourse = typeOfCourse;
        this.tutor = tutor;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Short getPlaces() {
        return places;
    }

    public void setPlaces(Short places) {
        this.places = places;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public TypeOfCourse getTypeOfCourse() {
        return typeOfCourse;
    }

    public void setTypeOfCourse(TypeOfCourse typeOfCourse) {
        this.typeOfCourse = typeOfCourse;
    }


    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
