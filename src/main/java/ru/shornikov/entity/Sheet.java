package ru.shornikov.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;

@JmixEntity
@Table(name = "SHEET")
@Entity
public class Sheet {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;

    @Column(name = "NAME")
    private String name;


    private Integer sheetnumber;

    @JoinTable(name = "JSDD_EXAM_SHEET_TEACHER_LINK",
            joinColumns = @JoinColumn(name = "SHEET_ID"),
            inverseJoinColumns = @JoinColumn(name = "TEACHER_ID"))
    @ManyToMany
    private List<Teacher> teachers;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }


    public Integer getSheetnumber() {
        return sheetnumber;
    }

    public void setSheetnumber(Integer sheetnumber) {
        this.sheetnumber = sheetnumber;
    }
}