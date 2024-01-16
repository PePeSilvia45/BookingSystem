package jrp.gradedunit_birdstonkennel_v1.models.requests;


import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.DogGender;
import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Used to create a request to update a dogs information
 */
@Data
@AllArgsConstructor
public class UpdateDogRequest {
    /**
     * Stores the dog's name
     */
    private String name;
    /**
     * Stores the dog's breed
     */
    private String breed;
    /**
     * Stores the dog's age
     */
    private String age;
    /**
     * Stores the dog's sex
     */
    private DogGender sex;
    /**
     * Stores the dog's Size
     */
    private Size size;
    /**
     * Stores the dog's spayed
     */
    private boolean spayed;
    /**
     * Stores the dog's vaccinated
     */
    private boolean vaccinated;
    /**
     * Stores the dog's weight
     */
    private String weight;
    /**
     * Stores the dog's insuranceProvider
     */
    private String insuranceProvider;
    /**
     * Stores the dog's microchipNumber
     */
    private String microchipNumber;
    /**
     * Stores the dog's medicalNotes
     */
    private String medicalNotes;
    /**
     * Stores the dog's behaviouralNotes
     */
    private String behaviouralNotes;


}
