package com.it.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@ToString(exclude={"courses"})
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

    @OneToMany(mappedBy = "typeOfCourse", fetch = FetchType.LAZY)
    private Set<Course> courses;


}
