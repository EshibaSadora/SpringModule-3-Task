package ru.shornikov.repository;


import ru.shornikov.entity.Teacher;

import java.util.UUID;


public interface TeacherRepository extends JpaJmixDataRepository<Teacher, UUID> {
}