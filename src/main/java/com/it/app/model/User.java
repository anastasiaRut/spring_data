package com.it.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The class represents an user entity
 *
 * @author A. Rutkouskaya
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

     @NotNull(message = "{ user.username.notNull}")
     @NotEmpty(message = "{ user.username.notEmpty}")
     @Size(min = 3, max = 50, message = "{ user.username.size}")
    @Column(name = "username", unique = true)
    private String username;

    @NotNull(message = "{ user.password.notNull}")
    @NotEmpty(message = "{ user.password.notEmpty}")
    @Size(min = 3, max = 100, message = "{ user.password.size}")
    @Column(name = "password")
    private String password;

   /* @NotNull(message = "{ user.name.notNull}")
    @NotEmpty(message = "{ user.name.notEmpty}")*/
    @Size(min = 3, max = 50, message = "{ user.name.size}")
    @Column(name = "name")
    private String name;

    /*@NotNull(message = "{ user.surname.notNull}")
    @NotEmpty(message = "{ user.surname.notEmpty}")*/
    @Size(min = 3, max = 50, message = "{ user.surname.size}")
    @Column(name = "surname")
    private String surname;
/*
    @NotNull(message = "{ user.email.notNull}")
    @NotEmpty(message = "{ user.email.notEmpty}")*/
    @Email(message = "{{ user.email.valid}")
    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @NotNull(message = "{user.role.notNull}")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


}
