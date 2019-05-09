package com.it.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The class represents notes about students on courses
 *
 * @author A. Rutkouskaya
 */
@Entity
@Getter
@Setter
@Table(name = "students_courses")
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "{studentCourse.student.notNull}")

    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @NotNull(message = "{studentCourse.course.notNull}")
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    /**
     * The value is used to describe the status of the application
     * If the status is true, the application is accepted by schedule maker
     */
    @Column(columnDefinition = "TINYINT")
    @NotNull(message = "{studentCourse.status.notNull}")
    private Boolean status;
}
