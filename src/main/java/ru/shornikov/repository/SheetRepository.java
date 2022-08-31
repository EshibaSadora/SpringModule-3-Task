package ru.shornikov.repository;


import ru.shornikov.entity.Sheet;
import ru.shornikov.entity.Teacher;

import java.util.UUID;


public interface SheetRepository extends JpaJmixDataRepository<Sheet, UUID> {
}