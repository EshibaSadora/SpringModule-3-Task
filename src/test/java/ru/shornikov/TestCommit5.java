package ru.shornikov;


import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shornikov.entity.Sheet;
import ru.shornikov.entity.SheetAsign;
import ru.shornikov.entity.SheetAssignetInfo;
import ru.shornikov.entity.Teacher;
import ru.shornikov.repository.SheetAsignDAO;
import ru.shornikov.repository.SheetAsignRepository;
import ru.shornikov.repository.SheetRepository;
import ru.shornikov.repository.TeacherRepository;
import ru.shornikov.security.DatabaseUserRepository;

import java.util.List;

@SpringBootTest
public class TestCommit5 {
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



    //получение статистики, подписанных ведомостей
    @Test
    void TestGroupByTeacher(){
        systemAuthenticator.runWithSystem(() -> {
            List<SheetAssignetInfo> lst = sheetAsignDAO.GetAssignetAheets();
        });

    }






}
