package ru.shornikov.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;

@JmixEntity
public class SheetAssignetInfo {
    Integer SheetNumber;
    String Teacher;
    String DateAssign;

    public Integer getSheetNumber() {
        return SheetNumber;
    }

    public void setSheetNumber(Integer sheetNumber) {
        SheetNumber = sheetNumber;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public String getDateAssign() {
        return DateAssign;
    }

    public void setDateAssign(String dateAssign) {
        DateAssign = dateAssign;
    }
}