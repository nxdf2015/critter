package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    CustomerService customerService;

    @Autowired
    PetRepository petRepository;


    public PetEntity findById(Long petId) {
        Optional<PetEntity> petEntity = petRepository.findById(petId) ;
        if (petEntity.isPresent()){
            return petEntity.get();
        }
        System.out.println("pet not present");
        return null;
    }

    public PetEntity save(PetEntity pet ) {
        CustomerEntity customerEntity = pet.getOwner();
        customerEntity = customerService.save(customerEntity);

        pet.setOwner(customerEntity);
        PetEntity petEntity = petRepository.save(pet);

        customerEntity.addPet(petEntity);
        customerService.save(customerEntity);
        return petEntity;
    }

    public List<PetEntity> findAll() {
        return petRepository.findAll();
    }

    public List<PetEntity> findPetByOwner(long ownerId) {
        return petRepository.findPetByOwner(ownerId);
    }
}
