package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "invalid name")
    private String name;

    @ElementCollection
    private Set<EmployeeSkill> employeeSkills;

    @ElementCollection
    private Set<DayOfWeek> dayAvailable  = new HashSet<>();


    public EmployeeEntity() {
    }

    public void addDay(DayOfWeek day){
        dayAvailable.add(day);
    }

    public Set<DayOfWeek> getDayAvailable() {
        return dayAvailable;
    }

    public void setDayAvailable(Set<DayOfWeek> dayAvailable) {
        this.dayAvailable = dayAvailable;
    }

    public Set<EmployeeSkill> getEmployeeSkills() {
        return employeeSkills;
    }

    public void setEmployeeSkills(Set<EmployeeSkill> employeeSkills) {
        this.employeeSkills = employeeSkills;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeSkills=" + employeeSkills +
                ", dayAvailable=" + dayAvailable +
                '}';
    }

    public static EmployeeEntity fromDTO(EmployeeDTO obj) {
        return new EmployeeEntity(obj.getId(), obj.getName());
    }

}
