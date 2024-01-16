package jrp.gradedunit_birdstonkennel_v1.models.dog;

import jakarta.persistence.*;
import jrp.gradedunit_birdstonkennel_v1.models.booking.Booking;
import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.DogGender;
import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.Size;
import jrp.gradedunit_birdstonkennel_v1.models.user.Customer;
import jrp.gradedunit_birdstonkennel_v1.models.vet.Vet;
import lombok.*;

import java.util.Collection;

/**
 * This class is used to hold all the information of Dog objects
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "DOGS")
public class Dog {

    /**
     * Stores the dogs id
     */
    @Id
    @SequenceGenerator(name = "dogs_sequence", sequenceName = "dogs_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dogs_sequence")
    private int id;
    /**
     * Stores whether this dog is still active on the account or not
     */
    @Column(nullable = false)
    private boolean activeOnAccount;
    /**
     * Stores the dog's name
     */
    @Column(nullable = false)
    private String name;
    /**
     * Stores the breed of the dog
     */
    @Column(nullable = false)
    private String breed;
    /**
     * Stores the color of the dog
     */
    @Column(nullable = false)
    private String color;
    /**
     * Stores the size of the dog
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Size size;
    /**
     * Stores the sex of the dog
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DogGender sex;
    /**
     * Stores the age of the dog
     */
    @Column(nullable = false)
    private String age;
    /**
     * Stores information on whether the dog is spayed/neutered or not
     */
    @Column(nullable = false)
    private boolean isSpayedNeutered;
    /**
     * Sores information on whether the dog is vaccinated or not
     */
    @Column(nullable = false)
    private boolean isVaccinated;
    /**
     * Stores the weight of the dog
     */
    @Column(nullable = false)
    private String weight;
    /**
     * Stores the dogs insurance providers name
     */
    @Column(nullable = false)
    private String insuranceProvider;
    /**
     * Stores the microchip number of the dog
     */
    @Column(nullable = false)
    private String microchipNumber;
    /**
     * Stores any medical notes for the dog
     */
    private String medicalNotes;
    /**
     * Stores any behavioural notes for the dog
     */
    private String behaviouralNotes;
    /**
     * Stores the owner of the dog
     */
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "owner_id"
    )
    private Customer owner;
    /**
     * Stores the dogs vet
     */
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "vet_id"
    )
    private Vet vet;
    /**
     * Stores all the bookings this dog has been part of
     */
    @ManyToMany(mappedBy = "dogs")
    private Collection<Booking> bookings;

    /**
     * This constructor is used to make a dog object
     *
     * @param name              the dogs name
     * @param breed             the dogs breed
     * @param color             the dogs color
     * @param size              the dogs size
     * @param sex               the dogs sex
     * @param age               the dogs age
     * @param isSpayedNeutered  whether the dog is spayed or neutered or not
     * @param isVaccinated      whether the dog is vaccinated or not
     * @param weight            the dogs weight
     * @param insuranceProvider the dogs insurance provider
     * @param microchipNumber   the dogs microchip number
     * @param medicalNotes      the dogs medical notes
     * @param behaviouralNotes  the dogs behavioural notes
     * @param owner             the dogs owner
     * @param vet               the dogs vet
     */
    public Dog(String name, String breed, String color, Size size, DogGender sex, String age, boolean isSpayedNeutered, boolean isVaccinated, String weight, String insuranceProvider, String microchipNumber, String medicalNotes, String behaviouralNotes, Customer owner, Vet vet) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.size = size;
        this.age = age;
        this.sex = sex;
        this.isSpayedNeutered = isSpayedNeutered;
        this.isVaccinated = isVaccinated;
        this.weight = weight;
        this.insuranceProvider = insuranceProvider;
        this.microchipNumber = microchipNumber;
        this.medicalNotes = medicalNotes;
        this.behaviouralNotes = behaviouralNotes;
        this.owner = owner;
        this.vet = vet;
        this.activeOnAccount = true;
    }


    /**
     * @return the users information in the form of a string
     */
    @Override
    public String toString() {
        return "Dog\n{" +
                "\n\tname='" + this.name + '\'' +
                "\n\tbreed='" + this.breed + '\'' +
                "\n\tcolor='" + this.color + '\'' +
                "\n\tsize='" + this.size + '\'' +
                "\n\tsex='" + this.sex + '\'' +
                "\n\tage='" + this.age + '\'' +
                "\n\tisSpayedNeutered='" + this.isSpayedNeutered + '\'' +
                "\n\tisVaccinated='" + this.isVaccinated + '\'' +
                "\n\tweight='" + this.weight + '\'' +
                "\n\tinsuranceProvider='" + this.insuranceProvider + '\'' +
                "\n\tmicrochipNumber='" + this.microchipNumber + '\'' +
                "\n\tmedicalNotes='" + this.medicalNotes + '\'' +
                "\n\tbehaviouralNotes='" + this.behaviouralNotes + '\'' +
                "\n}";
    }




}

























