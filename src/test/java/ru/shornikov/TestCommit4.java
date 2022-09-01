package ru.shornikov;


import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shornikov.entity.Sheet;
import ru.shornikov.entity.SheetAsign;
import ru.shornikov.entity.Teacher;
import ru.shornikov.repository.SheetAsignDAO;

import ru.shornikov.repository.SheetAsignRepository;
import ru.shornikov.repository.SheetRepository;
import ru.shornikov.repository.TeacherRepository;
import ru.shornikov.security.DatabaseUserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class TestCommit4 {
    @Autowired
    SheetRepository sheetRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    DatabaseUserRepository userRepository;
    @Autowired
    SheetAsignRepository sheetAsignRepository;



    @Autowired
    private SystemAuthenticator systemAuthenticator;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    private SheetAsignDAO sheetAsignDAO;


    //создание подписи (кастомный метод, на вход принимает учителя и ведомость, выбрасывает ошибку если такая подпись уже была создана)
    @Test
    void DoAssign(){
        systemAuthenticator.runWithSystem(() -> {
            Teacher teacher = teacherRepository.findAllByfirstNameAndBylastname("Иван","Иванов").get(4);
            Sheet sheet = sheetRepository.findAllByTeachers(teacher).get(0);
            SheetAsign asign = sheetAsignDAO.CreateSheetAsignByTeacherAndSheet(teacher, sheet);
        });

    }


    @Test
    void RemoveAllSheet(){
        systemAuthenticator.runWithSystem(() -> {
            sheetAsignRepository.findAll().forEach(sheetAsign -> {
                sheetAsignRepository.delete(sheetAsign);
            });
        });
    }

    @Test
    void GetAllSheet(){
        systemAuthenticator.runWithSystem(() -> {
            List<SheetAsign> assigns = sheetAsignRepository.findAll();
        });
    }


    //получение подписи для ведомости
    @Test
    void GetSignBySheet(){
        systemAuthenticator.runWithSystem(() -> {
            Teacher teacher = teacherRepository.findAllByfirstNameAndBylastname("Иван","Иванов").get(4);
            Sheet sheet = sheetRepository.findAllByTeachers(teacher).get(0);
            List<SheetAsign> assigns = sheetAsignRepository.getSheetAsignBySheet(sheet);
        });
    }

    // получение всех подписей учителя
    @Test
    void GetSignByTeacher(){
        systemAuthenticator.runWithSystem(() -> {
            Teacher teacher = teacherRepository.findAllByfirstNameAndBylastname("Иван","Иванов").get(4);
            List<SheetAsign> assigns = sheetAsignRepository.getSheetAsignByTeacher(teacher);
        });
    }



    @Test
    void GetSignByChangedate(){
            systemAuthenticator.runWithSystem(() -> {
                    List<SheetAsign> assigns = sheetAsignDAO.GetSignByCreatedate("10.08.2022");
            });
    }

    // получение всех подписей в интервале времени (на вход примет дата начала и дата конца)
    @Test
    void GetSignByDelta(){
        systemAuthenticator.runWithSystem(() -> {
            List<SheetAsign> assigns = sheetAsignDAO.GetSignByDeltaDay("10.08.2022", "10.09.2022");
            List<SheetAsign> noassigns = sheetAsignDAO.GetSignByDeltaDay("10.08.2022", "10.08.2022");
        });
    }




}
