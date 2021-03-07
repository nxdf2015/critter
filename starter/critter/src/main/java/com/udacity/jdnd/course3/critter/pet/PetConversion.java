package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PetConversion {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    public PetEntity toEntity(PetDTO petDTO){

        PetEntity pet = new PetEntity();
        pet.setId(petDTO.getId());
        pet.setName(petDTO.getName());
        pet.setType(petDTO.getType());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());



        if (petDTO.getOwnerId() > 0){
            Optional<CustomerEntity> customerEntity = customerRepository.findById(petDTO.getOwnerId());
            pet.setOwner(customerService.findByID(petDTO.getOwnerId()));
        }

        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println(pet);
        return pet;
    }

    public PetDTO toDTO(PetEntity pet){
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        if (pet.getOwner() != null) {
            petDTO.setOwnerId(pet.getOwner().getId());
        }
        petDTO.setType(pet.getType());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());

        return petDTO;

    }

}
