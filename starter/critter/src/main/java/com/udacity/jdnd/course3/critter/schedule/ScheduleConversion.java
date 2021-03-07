package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleConversion {

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;


    public ScheduleEntity  toEntity(ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setId(scheduleDTO.getId());
        scheduleEntity.setDate(scheduleDTO.getDate());
        List<PetEntity> pets = scheduleDTO
                .getPetIds()
                .stream()
                .map(petService::findById)
                .collect(Collectors.toList());

        scheduleEntity.setPets(pets);

        scheduleEntity.setActivities(scheduleDTO.getActivities());
        List<EmployeeEntity> employees = scheduleDTO
                .getEmployeeIds()
                .stream()
                .map(employeeService::findById)
                .collect(Collectors.toList());

        scheduleEntity.setEmployee(employees);

        return scheduleEntity;
    }
    public ScheduleDTO toDTO(ScheduleEntity schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setDate(schedule.getDate());

        List<Long> employeeId = schedule
                .getEmployee()
                .stream()
                .map(EmployeeEntity::getId)
                .collect(Collectors.toList());

        scheduleDTO.setEmployeeIds(employeeId);

        List<Long> petsId = schedule
                .getPets()
                .stream()
                .map(PetEntity::getId)
                .collect(Collectors.toList());

        scheduleDTO.setPetIds(petsId);

        return scheduleDTO;
    }
}
