package ru.shornikov.repository;


import io.jmix.core.repository.Query;
import ru.shornikov.entity.Sheet;
import ru.shornikov.entity.Teacher;

import java.util.List;
import java.util.Optional;


public interface SheetRepository extends JpaJmixDataRepository<Sheet, Integer> {

    List<Sheet> findSheetById (Integer id);

    List<Sheet> findAllByTeachers(Teacher teacher);

    List<Sheet> findAllBysheetnumber(Integer Number);

    @Query(value = "SELECT * FROM USERS u WHERE u.status = 1")
    List<Sheet> findAllWithNvl();



}