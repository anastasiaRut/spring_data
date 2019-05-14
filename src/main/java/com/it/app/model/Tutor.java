package com.it.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * The class represents a tutor entity
 *
 * @author A. Rutkouskaya
 */
@Entity
@Getter
@Setter
@Table(name = "tutors")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @NotNull(message = "{tutor.name.notNull}")
    @NotEmpty(message = "{tutor.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{tutor.name.size}")
    private String name;

    @Column
    @NotNull(message = "{tutor.surname.notNull}")
    @NotEmpty(message = "{tutor.surname.notEmpty}")
    @Size(min = 3, max = 50, message = "{ tutor.surname.size}")
    private String surname;

    @ManyToOne
    @NotNull(message = "{tutor.language.notNull}")
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToMany(cascade = {CascadeType.ALL})
    @NotNull(message = "{tutor.levels.notNull}")
    @JoinTable(name = "tutors_levels",
            joinColumns = {@JoinColumn(name = "tutor_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "level_id", nullable = false)})
    private Set<Level> levels;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    private Set<Course> courses;

}
