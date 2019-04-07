package com.it.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Set;

/**
 * The class represents a student entity
 *
 * @author A. Rutkouskaya
 * @see User
 */
@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "student")
    private Set<StudentCourse> studentCourses;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "students_events",
            joinColumns = {@JoinColumn(name = "student_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "event_id", nullable = false)})
    private Set<Event> events;


    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
