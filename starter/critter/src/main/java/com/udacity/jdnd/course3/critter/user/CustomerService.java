package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CustomerService {
    @Autowired
    PetService petService;

    @Autowired
    CustomerRepository customerRepository;





    public CustomerEntity save(CustomerEntity customer) {
        return  customerRepository.save(customer);
    }


    public List<CustomerEntity> findAll() {
       return  customerRepository.findAll()
                .stream()

                .collect(toList());
    }



    public CustomerEntity findByCustomerByPetId(long petId) {
        PetEntity pet = petService.findById(petId);
        return   pet.getOwner() ;
    }

    public CustomerEntity findByID(long ownerId) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(ownerId);
        if (customerEntity.isPresent()){
            return customerEntity.get();
        }
        return  null;
    }
}
