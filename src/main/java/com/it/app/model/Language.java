package com.it.app.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * The class represents a language entity
 *
 * @author A. Rutkouskaya
 */
@Entity
@Table(name = "languages")
@Data
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull(message = "{language.name.notNull}")
    @NotEmpty(message = "{language.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{language.name.size}")
    private String name;

    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
    private Set<Tutor> tutors;

    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
    private Set<ScheduleMaker> scheduleMakers;

}
