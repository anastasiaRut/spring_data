package com.it.app.model;

import javax.persistence.*;
import java.util.Set;

/**
 * The class represents a CEFR level entity
 *
 * @author A. Rutkouskaya
 */
@Entity
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "levels", fetch = FetchType.LAZY)
    private Set<Tutor> tutors;

    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY)
    private Set<Course> courses;

    public Level() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(Set<Tutor> tutors) {
        this.tutors = tutors;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
