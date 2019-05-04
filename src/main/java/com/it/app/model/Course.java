package com.it.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
@Data
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


}
