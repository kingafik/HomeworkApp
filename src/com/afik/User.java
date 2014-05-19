package com.afik;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This Class represent User in the system.
 * Fields: username, password.
 * SQL: Table name = User,
 *      Columns: USERNAME(ID), PASSWORD.
 *      USERNAME is also constrain key in Homework and Course tables.
 */
@Entity
@Table(name = "User")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade =
            {CascadeType.ALL})
    @JoinColumn(name = "USERNAME")
    private Set<Course> courses = new HashSet<Course>(0);

    @OneToMany(fetch = FetchType.LAZY, cascade =
            {CascadeType.ALL})
    @JoinColumn(name = "USERNAME")
    private Set<Homework> homeworks = new HashSet<Homework>(0);

    public User(String email, String password) {
        super();
        this.username = email;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(Set<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ("[ " + username + ", " + password + "]");
    }

}
