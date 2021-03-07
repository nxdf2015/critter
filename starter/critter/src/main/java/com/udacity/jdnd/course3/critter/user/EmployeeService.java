package com.udacity.jdnd.course3.critter.user;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeService {




    @Autowired
    EmployeeRepository employeeRepository;


    public EmployeeEntity save(EmployeeEntity employee) {
        return  employeeRepository.save(employee);

    }

    public EmployeeEntity findById(long employeeId) {
        Optional<EmployeeEntity> response = employeeRepository.findById(employeeId);
        if (response.isPresent()) {
            return  response.get();
        }


        return null;
    }

    public void update(long employeeId, Set<DayOfWeek> daysAvailable) {

        employeeRepository.findById(employeeId)
                .map( value -> {
                    for (DayOfWeek day : daysAvailable){
                        value.addDay(day);
                    }
                    System.out.println(value);
                    return employeeRepository.save(value);
                });


    }

    public List<EmployeeEntity> findBySkill(DayOfWeek day , Set<EmployeeSkill> skills) {


         return employeeRepository.findAll()
                 .stream()
                 .filter(e -> e.getDayAvailable().contains(day))
                 .filter(e -> e.getEmployeeSkills().containsAll(skills))
                 .collect(Collectors.toList());

    }
}
