package com.it.app.model;

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

    public Event() {
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}
