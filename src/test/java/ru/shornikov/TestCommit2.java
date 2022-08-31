package ru.shornikov;


import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import ru.shornikov.entity.Sheet;
import ru.shornikov.entity.Teacher;
import ru.shornikov.repository.SheetRepository;
import ru.shornikov.repository.TeacherRepository;
import ru.shornikov.security.DatabaseUserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestCommit2 {

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
    void CreateRows() {
        systemAuthenticator.runWithSystem(() -> {
            Sheet sheet = sheetRepository.create();
            sheet.setName("Тест");
            sheet.setSheetnumber(1);
            List<Teacher> teachers = new ArrayList<>();
            Teacher t1 = teacherRepository.create();
            t1.setFirstName("Иван");
            t1.setLastName("Иванов");
            t1 = teacherRepository.save(t1);

            //t1.setUser(userRepository.loadUserByUsername("admin"));
            teachers.add(t1);
            sheet.setTeachers(teachers);

            sheetRepository.save(sheet);
        });
    }

    @Test
    void GetRows() {
        systemAuthenticator.runWithSystem(() -> {
            List<Sheet> sheets = sheetRepository.findAll();
        });


    }
}

