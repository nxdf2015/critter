package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CustomerConversion {
    @Autowired
    PetService petService;

    public  CustomerEntity toEntity(CustomerDTO customerDTO){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDTO.getName());
        if (customerDTO.getId() > 0)
            customerEntity.setId(customerDTO.getId());
        customerEntity.setNotes(customerDTO.getNotes());
        customerEntity.setPhoneNumber(customerDTO.getPhoneNumber());
        if (customerDTO.getPetIds() != null) {
            List<PetEntity> pets = customerDTO
                    .getPetIds()
                    .stream()
                    .map(e -> petService.findById(e))
                    .map(pet -> {
                        pet.setOwner(customerEntity);
                        return pet;
                    })
                    .collect(toList());

            customerEntity.setPets(pets);
        }
        return customerEntity;
    }

    public CustomerDTO toDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customerEntity.getName());
        customerDTO.setPhoneNumber(customerEntity.getPhoneNumber());
        customerDTO.setId(customerEntity.getId());
        customerDTO.setNotes(customerEntity.getNotes());
        if (customerEntity.getPets() != null){
            List<Long> petsID = customerEntity
                    .getPets()
                    .stream()
                    .map(e -> e.getId())
                    .collect(toList());
            customerDTO.setPetIds(petsID);
        }


        return customerDTO;
    }
}
