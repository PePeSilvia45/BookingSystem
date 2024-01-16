package jrp.gradedunit_birdstonkennel_v1.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewDogRequest {

    //dog information
    /**
     * Stores the dog's name
     */
    private String dogName;
    /**
     * Stores the breed of the dog
     */
    private String breed;
    /**
     * Stores the color of the dog
     */
    private String color;
    /**
     * Stores the size of the dog
     */
    private String size;
    /**
     * Stores the sex of the dog
     */
    private String sex;
    /**
     * Stores the age of the dog
     */
    private String age;
    /**
     * Stores information on whether the dog is spayed/neutered or not
     */
    private String isSpayedNeutered;
    /**
     * Sores information on whether the dog is vaccinated or not
     */
    private String isVaccinated;
    /**
     * Stores the weight of the dog
     */
    private String weight;
    /**
     * Stores the dogs insurance providers name
     */
    private String insuranceProvider;
    /**
     * Stores the microchip number of the dog
     */
    private String microchipNumber;
    /**
     * Stores any medical notes for the dog
     */
    private String medicalNotes;
    /**
     * Stores any behavioural notes for the dog
     */
    private String behaviouralNotes;

    //vet information
    /**
     * Stores the vet's name
     */
    private String vetName;
    /**
     * Stores the vet's practice
     */
    private String practice;
    /**
     * Stores the vet's contact number
     */
    private String contactNumber;
    /**
     * Stores the first address line of the vets Address
     */
    private String addressLine1;
    /**
     * Stores the second address line of the vets Address
     */
    private String addressLine2;
    /**
     * Stores the town of the vets Address
     */
    private String town;
    /**
     * Stores the postcode of the vets Address
     */
    private String postcode;
}
