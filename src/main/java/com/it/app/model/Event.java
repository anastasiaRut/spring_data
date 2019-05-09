package com.it.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Set;

/**
 * The class represents a language school event entity
 *
 * @author A. Rutkouskaya
 */
@Entity
@Getter
@Setter
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "{event.name.notNull}")
    @NotEmpty(message = "{ event.name.notEmpty}")
    private String name;

    @Column
    @NotNull(message = "{event.cost.notNull}")
    @Positive(message = "{event.cost.positive}")
    private BigDecimal cost;

    /**
     * The value is used to describe how many free places are left
     */
    @Column
    @NotNull(message = "{event.places.notNull}")
    @PositiveOrZero(message = "{event.places.positiveOrZero}")
    private Integer places;

    @ManyToOne
    @NotNull(message = "{event.tutor.notNull}")
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    private Set<Student> students;

}
