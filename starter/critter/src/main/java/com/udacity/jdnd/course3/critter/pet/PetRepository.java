package com.udacity.jdnd.course3.critter.pet;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity,Long> {
    @Query("select p from PetEntity p  join p.owner o where o.id = ?1")
    List<PetEntity> findPetByOwner(long ownerId);
}
