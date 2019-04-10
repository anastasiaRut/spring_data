package com.it.app.model;

import javax.persistence.*;

/**
 * The class represents a schedule maker entity
 *
 * @author A. Rutkouskaya
 * @see User
 */
@Entity
@Table(name = "schedule_makers")
@PrimaryKeyJoinColumn(name = "user_id")
public class ScheduleMaker extends User {

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
