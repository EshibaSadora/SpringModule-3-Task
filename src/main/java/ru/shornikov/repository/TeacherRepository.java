package ru.shornikov.repository;


import io.jmix.core.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.shornikov.entity.Teacher;

import javax.persistence.Column;
import java.util.List;
import java.util.UUID;


public interface TeacherRepository extends JpaJmixDataRepository<Teacher, Integer> {


    @Query("select t from Teacher t where t.firstName=:firstname and t.lastName=:lastname")
    List<Teacher> findAllByfirstNameAndBylastname(@Param("firstname") String firstname, @Param("lastname") String lastname);

    List<Teacher> getTeacherById(Integer id);


}

