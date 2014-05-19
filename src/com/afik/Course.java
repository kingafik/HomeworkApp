package com.afik;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This Class represent Course in the system.
 * Fields: courseId, user, courseName, building, room, lecturer, additionalInfo.
 * SQL: Table name = Course,
 *      Columns: COURSE_ID(ID), COURSE_NAME, BUILDING, ROOM, LECTURER, INFO.
 *      Description: Every user is in OneToMany relationship with courses and course
 *      is in ManyToOne relationship with Homework, therefore COURSE_ID, USERNAME is a constrain key in Homework.
 */
@Entity
@Table(name = "Course")
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COURSE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;

    @Column(name = "COURSE_NAME", nullable = false)
    private String courseName;

    @Column(name = "BUILDING")
    private String building;

    @Column(name = "ROOM")
    private String room;

    @Column(name = "LECTURER")
    private String lecturer;

    @Column(name = "INFO", length = 100)
    private String additionalInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNAME", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Homework> homeworks = new HashSet<Homework>(0);

    public Course(String courseName, String building, String room,
                  String lecturer, String additionalInfo) {
        super();
        this.courseName = courseName;
        this.building = building;
        this.room = room;
        this.lecturer = lecturer;
        this.additionalInfo = additionalInfo;
    }

    public Course() {
    }

    public long getCourseId() {
        return this.courseId;
    }

    public void setId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Set<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(Set<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return ("[ " + courseName + ", " + lecturer + ", " + building + ", "
                + room + ", " + additionalInfo + "]");
    }

}
