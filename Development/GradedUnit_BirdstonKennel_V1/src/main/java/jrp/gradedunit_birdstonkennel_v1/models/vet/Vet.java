package jrp.gradedunit_birdstonkennel_v1.models.vet;

import jakarta.persistence.*;
import jrp.gradedunit_birdstonkennel_v1.models.dog.Dog;
import lombok.*;

import java.util.Collection;

/**
 * This class stores information on vet objects
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "VETS")
public class Vet {

    /**
     * Stores the vets id
     */
    @SequenceGenerator(name = "vets_sequence", sequenceName = "vets_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vets_sequence")
    @Id
    private Long id;
    /**
     * Stores the vets name
     */
    @Column(nullable = false)
    private String name;
    /**
     * Stores the vets practice
     */
    @Column(nullable = false)
    private String practice;
    /**
     * Stores the vets contact number
     */
    @Column(nullable = false)
    private String contactNumber;
    /**
     * Stores the first address line of the vets Address
     */
    @Column(nullable = false)
    private String addressLine1;
    /**
     * Stores the second address line of the vets Address
     */
    private String addressLine2;
    /**
     * Stores the town of the vets Address
     */
    @Column(nullable = false)
    private String town;
    /**
     * Stores the postcode of the vets Address
     */
    @Column(nullable = false)
    private String postcode;
    /**
     * Stores the dogs under this vet
     */
    @OneToMany(mappedBy = "vet")
    private Collection<Dog> dogs;
    /**
     * This constructor is used to create instances of vet objects
     *
     * @param name          the vets name
     * @param practice      the vets practice
     * @param contactNumber the vets contact number
     * @param addressLine1  this first line of the vets address
     * @param addressLine2  this second line of the vets address
     * @param town          this town of the vets address
     * @param postcode      this postcode of the vets address
     */
    public Vet(String name, String practice, String contactNumber, String addressLine1, String addressLine2, String town, String postcode) {
        this.name = name;
        this.practice = practice;
        this.contactNumber = contactNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postcode = postcode;
    }

    /**
     * @return the users information in the form of a string
     */
    @Override
    public String toString() {
        return "Vet\n{" +
                "\n\tname='" + this.name + '\'' +
                "\n\tpractice='" + this.practice + '\'' +
                "\n\tcontactNumber='" + this.contactNumber + '\'' +
                "\n}";
    }
}
