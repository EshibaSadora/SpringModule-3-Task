package ru.shornikov.service;

import io.jmix.core.security.SystemAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shornikov.entity.SheetAssignetInfo;
import ru.shornikov.repository.SheetAsignDAO;

import java.util.List;



@RestController()
@RequestMapping("/assigments")
public class GetAssignetService {

    @Autowired
    SheetAsignDAO sheetAsignDAO;

    @Autowired
    private SystemAuthenticator systemAuthenticator;



    List<SheetAssignetInfo> GetAssignetSheets(){

        List<SheetAssignetInfo> ls = null;

        systemAuthenticator.runWithSystem(() -> {
            List<SheetAssignetInfo> lst = sheetAsignDAO.GetAssignetAheets();
        });

        return ls;
    }


}
