package ru.shornikov;


import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shornikov.entity.Sheet;
import ru.shornikov.entity.Teacher;
import ru.shornikov.repository.SheetRepository;
import ru.shornikov.repository.TeacherRepository;
import ru.shornikov.security.DatabaseUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TestCommit3 {

    @Autowired
    SheetRepository sheetRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    DatabaseUserRepository userRepository;

    @Autowired
    private SystemAuthenticator systemAuthenticator;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Test
    void GetRows() {
        systemAuthenticator.runWithSystem(() -> {
            List<Sheet> sheets = sheetRepository.findAll();
        });


    }

    //Получение ведомости по номеру
    @Test
    void FindByNumber(){
        systemAuthenticator.runWithSystem(() -> {
            List<Sheet> o = sheetRepository.findAllBysheetnumber(1);
        });
    }

    @Test
    void FindTeacherAll(){
        systemAuthenticator.runWithSystem(() -> {
            List<Teacher> o = teacherRepository.findAll();
        });
    }

    //Получение списка учителей по фамилии и имени
    @Test
    void FindTeacherByNames(){
        systemAuthenticator.runWithSystem(() -> {
            Object o = teacherRepository.findAllByfirstNameAndBylastname("Иван","Иванов");
        });
    }

    //Получение списка ведомостей по учителю
    @Test
    void FindSheetByTeacher(){
        List<Teacher> teachers = new ArrayList<>();
        systemAuthenticator.runWithSystem(() -> {
            for(Teacher teacher : teacherRepository.findAll()){
                for (Sheet s : sheetRepository.findAllByTeachers(teacher)){
                    for (Teacher t : s.getTeachers())
                    if(teacher.getFirstName()!=null)teachers.add(t);
                }
            }
        });
    }


    //Получение учителя по идентификатору с возможностью передать fetch plan (проверить фамилию и имя)
    //TODO Не понял : с возможностью передать fetch plan (проверить фамилию и имя)
    @Test
    void GetTeacherById(){
        systemAuthenticator.runWithSystem(() -> {
            Object o = teacherRepository.getTeacherById(2);
        });
    }
}