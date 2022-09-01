package ru.shornikov.repository;

import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jmx.JmxException;
import org.springframework.stereotype.Component;
import ru.shornikov.entity.Sheet;
import ru.shornikov.entity.SheetAsign;
import ru.shornikov.entity.Teacher;
import ru.shornikov.security.DatabaseUserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class SheetAsignDAO {

    @Autowired
    SheetRepository sheetRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    DatabaseUserRepository userRepository;


    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    SheetAsignRepository sheetAsignRepository;


    /**
     *Cоздание подписи (на вход принимает учителя и ведомость, выбрасывает ошибку если такая подпись уже была созданаs
     * @param teacher Учитель
     * @param sheet Ведомость
     */
    public SheetAsign CreateSheetAsignByTeacherAndSheet(Teacher teacher, Sheet sheet) {

        //TODO Unable to make jdk.internal.ref.PhantomCleanable() accessible: module java.base does not "opens jdk.internal.ref" to unnamed module @5918c260
        //List<SheetAsign> asigns = mongoTemplate.findAll(SheetAsign.class);

       // List<SheetAsign> asigns = mongoTemplate.find(Query.query(Criteria.where("teacher").is(teacher).and("sheet").is(sheet)), SheetAsign.class);


        List<SheetAsign> asigns = sheetAsignRepository.findAllSheetAsignsByTeacherAndSheet(teacher, sheet);

        if (asigns.size() > 0) {
            throw new JmxException("Такая подпись уже была создана для этой ведомости и учителя! веомость:" + String.valueOf(asigns));
        } else {


            SheetAsign sheet_asign = new SheetAsign();
            sheet_asign.setSheet(sheet);
            sheet_asign.setTeacher(teacher);

            //return  mongoTemplate.insert(sheet_asign);

            return sheetAsignRepository.save(sheet_asign);
        }
    }


    public List<SheetAsign> GetSignByCreatedate(String createdate) {

        try {
            return sheetAsignRepository.getSheetByCreatedate(new SimpleDateFormat("dd.MM.yyyy").parse(createdate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


    public List<SheetAsign> GetSignByDeltaDay(String datestart, String dateend) {

        try {
            return sheetAsignRepository.getSheetByDeltaDay(new SimpleDateFormat("dd.MM.yyyy").parse(datestart),new SimpleDateFormat("dd.MM.yyyy").parse(dateend));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }



}
