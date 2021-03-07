package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ScheduleConversion scheduleConversion;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleConversion.toDTO(scheduleService.save(scheduleConversion.toEntity(scheduleDTO)));

    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.findAll()
                .stream()
                .map(scheduleConversion::toDTO)
                .collect(toList());

    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.findByPetId(petId)
                .stream()
                .map(scheduleConversion::toDTO)
                .collect(toList());

    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return  scheduleService.findByEmployeeId(employeeId)
                .stream()
                .map(scheduleConversion::toDTO)
                .collect(toList());


    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
       return scheduleService.findBCustomeryId(customerId)
               .stream()
               .map(scheduleConversion::toDTO)
               .collect(toList());


    }
}
