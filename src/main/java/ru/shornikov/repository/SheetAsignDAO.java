package ru.shornikov.repository;

import io.jmix.core.DataManager;
import io.jmix.core.repository.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.jmx.JmxException;
import org.springframework.stereotype.Component;
import ru.shornikov.entity.Sheet;
import ru.shornikov.entity.SheetAsign;
import ru.shornikov.entity.SheetAssignetInfo;
import ru.shornikov.entity.Teacher;
import ru.shornikov.security.DatabaseUserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


            SheetAsign sheetAsign = sheetAsignRepository.create();
            sheetAsign.setSheet(sheet);
            sheetAsign.setTeacher(teacher);

            //TODO CreateDate автоматически не проставиляется!
            //sheetAsign.setCreatedate(new Date());

            //return  mongoTemplate.insert(sheet_asign);

            return sheetAsignRepository.save(sheetAsign);
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

    @Autowired
    private DataManager dataManager;


    /**
     *Получение статистики, подписанных ведомостей
     */
    public List<SheetAssignetInfo> GetAssignetAheets(){
        List<SheetAssignetInfo> sheetlist = new ArrayList<>();

        for(SheetAsign asign : sheetAsignRepository.findAll()){
            SheetAssignetInfo info = new SheetAssignetInfo();
            info.setSheetNumber(asign.getSheet().getSheetnumber()); //TODO Не понятно как сделать nvl для пустого значения SheetNumber (asign.getSheet().getSheetnumber()) Внутри get функции говнорешение
            info.setTeacher(asign.getTeacher().GetFullName());

            info.setDateAssign(asign.GetCreatedDateAsString());
            sheetlist.add(info);
        }

        return sheetlist;
    }





}
