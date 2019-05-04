package com.it.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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
    @FutureOrPresent(message = "{course.startDate.futureOrPresent}")
    @JsonFormat(pattern = "10.04.2019") //TODO converting
    private LocalDate startDate;

    /**
     * The value is used to describe how many free places are left
     */
    @Column
    @PositiveOrZero(message = "{course.places.positiveOrZero}")
    private Short places;

    @ManyToOne
    @NotNull(message = "{course.language.notNull}")
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne
    @NotNull(message = "{course.typeOfCourse.notNull}")
    @JoinColumn(name = "type_id", nullable = false)
    private TypeOfCourse typeOfCourse;

    @ManyToOne
    @NotNull(message = "{course.tutor.notNull}")
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @ManyToOne
    @NotNull(message = "{course.level.notNull}")
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
