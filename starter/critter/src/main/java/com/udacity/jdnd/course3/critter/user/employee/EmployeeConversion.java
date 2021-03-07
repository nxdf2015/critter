package com.udacity.jdnd.course3.critter.user.employee;


import org.springframework.stereotype.Component;

@Component
public class EmployeeConversion {
    public   EmployeeDTO toDTO(EmployeeEntity employeeEntity){

        return new EmployeeDTO(employeeEntity.getId(), employeeEntity.getName(),employeeEntity.getEmployeeSkills(),
                employeeEntity.getDayAvailable().isEmpty() ? null : employeeEntity.getDayAvailable());

    }

    public   EmployeeEntity toEntity(EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setId(employeeDTO.getId());
        employeeEntity.setEmployeeSkills(employeeDTO.getSkills());
        if(employeeDTO.getDaysAvailable() != null) {
            employeeEntity.setDayAvailable(employeeDTO.getDaysAvailable());
        }
        return employeeEntity;
    }
}
