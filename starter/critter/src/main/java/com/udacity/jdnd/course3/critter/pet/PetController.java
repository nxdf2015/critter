package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    PetConversion petConversion;


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO pet) {
        System.out.println(pet);
        return petConversion.toDTO(petService.save(petConversion.toEntity(pet)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return petConversion.toDTO(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.findAll()
                .stream()
                .map(petConversion::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.findPetByOwner(ownerId)
                .stream()
                .map(petConversion::toDTO)
                .collect(Collectors.toList());
    }
}
