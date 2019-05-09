package com.it.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * The class represents a student entity
 *
 * @author A. Rutkouskaya
 * @see User
 */
@Entity
@Getter
@Setter
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    @Column(name = "phone_number")
    @Pattern(regexp = "^[+]375(29|33|25|44)[0-9]{7}$", message = "{student.phoneNumber.valid}")
    private String phoneNumber;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<StudentCourse> studentCourses;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "students_events",
            joinColumns = {@JoinColumn(name = "student_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "event_id", nullable = false)})
    private Set<Event> events;

}
