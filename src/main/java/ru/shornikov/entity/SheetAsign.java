package ru.shornikov.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;
import ru.shornikov.ShornikovApplication;

import javax.persistence.*;
import java.util.Date;

@JmixEntity
@Entity
public class SheetAsign {

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;

    @JoinColumn(name = "EXAM_SHEET_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Sheet sheet;

    @JoinColumn(name = "EXAM_TEACHER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Teacher teacher;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public Date getCreatedate() {
        try {
            if(!createdate.equals(null))
            return createdate;
            else return new Date();
        }catch (Exception e){
            LoggerFactory.getLogger(SheetAsign.class).warn("Не удалось вернуть корректную дату, возможно значение в бд null! SheetAsignId:" + id);
            return new Date();
        }

    }

    public String GetCreatedDateAsString(){
        try {
            return createdate.toString();
        }catch (Exception e){
            LoggerFactory.getLogger(SheetAsign.class).warn("Не удалось вернуть корректную дату, возможно значение в бд null! SheetAsignId:" + id);
            return "Не указана";
        }
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
