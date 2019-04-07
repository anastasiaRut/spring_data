package com.it.model;

import javax.persistence.*;
import java.util.Set;

/**
 * The class represents a tutor entity
 *
 * @author A. Rutkouskaya
 */
@Entity
@Table(name = "tutors")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "tutors_levels",
            joinColumns = {@JoinColumn(name = "tutor_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "level_id", nullable = false)})
    private Set<Level> levels;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    private Set<Course> courses;

    public Tutor() {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Set<Level> getLevels() {
        return levels;
    }

    public void setLevels(Set<Level> levels) {
        this.levels = levels;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
