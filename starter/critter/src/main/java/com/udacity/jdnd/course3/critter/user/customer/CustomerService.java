package com.udacity.jdnd.course3.critter.user.customer;

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



    public CustomerEntity findCustomerByPetId(long petId) {
        PetEntity pet = petService.findById(petId);
        return   pet.getOwner() ;
    }

    public CustomerEntity findById(long ownerId) throws IllegalArgumentException{
        Optional<CustomerEntity> customerEntity = customerRepository.findById(ownerId);
        if (customerEntity.isPresent()){
            return customerEntity.get();
        }

        throw  new IllegalArgumentException("owner not find");
    }
}
