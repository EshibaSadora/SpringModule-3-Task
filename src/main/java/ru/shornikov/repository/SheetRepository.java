package ru.shornikov.repository;


import ru.shornikov.entity.Sheet;
import ru.shornikov.entity.Teacher;

import java.util.List;
import java.util.Optional;


public interface SheetRepository extends JpaJmixDataRepository<Sheet, Integer> {
        /*
    - Получение ведомости по номеру
    - Получение списка ведомостей по учителю
    - Получение списка учителей по фамилии и имени
    - Получение учителя по идентификатору с возможностью передать fetch plan (проверить фамилию и имя)
     */

    List<Sheet> findSheetById (Integer id);

    List<Sheet> findAllByTeachers(Teacher teacher);

     List<Sheet> findAllBysheetnumber(Integer Number);


}