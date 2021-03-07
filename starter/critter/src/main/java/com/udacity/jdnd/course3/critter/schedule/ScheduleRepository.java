package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {

    @Query("select s from ScheduleEntity s join s.employee e where e.id = ?1")
    List<ScheduleEntity> findByEmployeeId(long employeeId);


    @Query("select s from ScheduleEntity s join s.pets p where p.id = ?1")
    List<ScheduleEntity> findByPetId(long petId);

    @Query("select s from ScheduleEntity s join s.pets p where p.id in ?1")
    List<ScheduleEntity> findByPets(List<Long> petIds);
}
