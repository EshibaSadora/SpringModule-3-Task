package ru.shornikov.repository;

import io.jmix.core.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.shornikov.entity.Sheet;
import ru.shornikov.entity.SheetAsign;
import ru.shornikov.entity.Teacher;

import java.util.Date;
import java.util.List;

public interface SheetAsignRepository extends JpaJmixDataRepository<SheetAsign, Integer>{

    List<SheetAsign>findAllSheetAsignsByTeacherAndSheet(Teacher teacher, Sheet sheet);

    List<SheetAsign> getSheetAsignBySheet(Sheet sheet);

    List<SheetAsign> getSheetAsignByTeacher(Teacher teacher);

    @Query("select s from SheetAsign s where s.createdate > :createdate")
    List<SheetAsign> getSheetByCreatedate(@Param("createdate") Date createdate);

    @Query("select s from SheetAsign s where s.createdate > :datestart and s.createdate < :dateend")
    List<SheetAsign> getSheetByDeltaDay(@Param("datestart") Date datestart, @Param("dateend") Date dateend);

}


