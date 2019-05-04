package com.it.app.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The class represents a schedule maker entity
 *
 * @author A. Rutkouskaya
 * @see User
 */
@Entity
@Data
@Table(name = "schedule_makers")
@PrimaryKeyJoinColumn(name = "user_id")
public class ScheduleMaker extends User {

    @ManyToOne
    @NotNull(message = "{scheduleMaker.language.notNull}")
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;
}
