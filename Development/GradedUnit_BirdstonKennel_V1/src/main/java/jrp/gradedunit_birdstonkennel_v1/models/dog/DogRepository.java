package jrp.gradedunit_birdstonkennel_v1.models.dog;

import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.DogGender;
import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.Size;
import jrp.gradedunit_birdstonkennel_v1.models.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * This repository communicates to the dog table
 */
@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    /**
     * Finds the dog by the owner
     *
     * @param owner The owner of the dog being searched for
     * @return The dog that was found
     */
    Optional<Dog> findByOwnerAndActiveOnAccountTrue(Customer owner);

    /**
     * Finds all the dogs belonging to an owner
     *
     * @param owner The owner of the dogs being searched for
     * @return All the dogs of the owner
     */
    Collection<Dog> findAllByOwnerAndActiveOnAccountTrue(Customer owner);

    @Transactional
    @Modifying
    @Query("UPDATE Dog d SET d.activeOnAccount = false")
    void setDogInactive(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Dog d SET " +
            "d.name = ?2, " +
            "d.breed = ?3, " +
            "d.age = ?4, " +
            "d.sex = ?5, " +
            "d.size = ?6, " +
            "d.isSpayedNeutered = ?7, " +
            "d.isVaccinated = ?8, " +
            "d.weight = ?9, " +
            "d.insuranceProvider = ?10, " +
            "d.microchipNumber = ?11, " +
            "d.medicalNotes = ?12, " +
            "d.behaviouralNotes = ?13 " +
            "WHERE d.id = ?1")
    int updateDog(Long id, String name, String breed, String age, DogGender sex, Size size, boolean isSpayed, boolean isVaccinated,
                  String weight, String insuranceProvider, String microchipNumber, String medicalNotes, String behaviouralNotes);
}
