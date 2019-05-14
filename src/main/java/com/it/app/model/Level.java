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
 * The class represents a CEFR level entity
 *
 * @author A. Rutkouskaya
 */
@Entity
@Getter
@Setter
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "{level.name.notNull}")
    @NotEmpty(message = "{level.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{level.name.size}")
    private String name;

    @ManyToMany(mappedBy = "levels", fetch = FetchType.LAZY)
    private Set<Tutor> tutors;

    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY)
    private Set<Course> courses;

}
