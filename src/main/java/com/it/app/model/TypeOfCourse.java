package com.it.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

/**
 * The class represents a type of course entity
 *
 * @author A. Rutkouskaya
 * @see Course
 */
@Entity
@Table(name = "types")
public class TypeOfCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "{typeOfCourse.name.notNull}")
    @NotEmpty(message = "{typeOfCourse.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{typeOfCourse.name.size}")
    private String name;

    /**
     * The value is used to describe the time of the day
     */
    @Column(name = "time_type")
    @NotNull(message = "{typeOfCourse.timeType.notNull}")
    @NotEmpty(message = "{typeOfCourse.timeType.notEmpty}")
    private String timeType;

    @Column
    @NotNull(message = "{typeOfCourse.cost.notNull}")
    @Positive(message = "{typeOfCourse.cost.positive}")
    private BigDecimal cost;

    @OneToMany(mappedBy = "typeOfCourse")
    private Set<Course> courses;

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public TypeOfCourse() {

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

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
