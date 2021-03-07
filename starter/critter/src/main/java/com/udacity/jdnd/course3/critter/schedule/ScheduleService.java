package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetService petService;


    public ScheduleEntity save(ScheduleEntity scheduleEntity) {
        return scheduleRepository.save(scheduleEntity);
    }

    public List<ScheduleEntity> findAll() {
        return scheduleRepository.findAll();
    }

    public List<ScheduleEntity> findByPetId(long petId) {

        return scheduleRepository.findByPetId(petId);
    }






    public List<ScheduleEntity> findByEmployeeId(long employeeId) {
        return scheduleRepository.findByEmployeeId(employeeId);
    }

    public List<ScheduleEntity> findBCustomeryId(long customerId) {
        List<Long> petIds = petService.findPetByOwner(customerId)
                .stream()
                .map(PetEntity::getId)
                .collect(toList());

        return scheduleRepository.findByPets(petIds);
    }
}
