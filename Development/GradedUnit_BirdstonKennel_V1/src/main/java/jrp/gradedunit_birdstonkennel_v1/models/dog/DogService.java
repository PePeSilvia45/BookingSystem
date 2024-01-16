package jrp.gradedunit_birdstonkennel_v1.models.dog;

import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.DogGender;
import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.Size;
import jrp.gradedunit_birdstonkennel_v1.models.requests.NewDogRequest;
import jrp.gradedunit_birdstonkennel_v1.models.requests.UpdateDogRequest;
import jrp.gradedunit_birdstonkennel_v1.models.user.AppUserService;
import jrp.gradedunit_birdstonkennel_v1.models.user.Customer;
import jrp.gradedunit_birdstonkennel_v1.models.validation.DataValidator;
import jrp.gradedunit_birdstonkennel_v1.models.vet.Vet;
import jrp.gradedunit_birdstonkennel_v1.models.vet.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * This service handles processes involving dog objects
 */
@Service
@RequiredArgsConstructor
public class DogService {

    /**
     * Stores an instance of the DogRepository objects
     */
    private final DogRepository dogRepository;
    /**
     * Stores an instance of the AppUserService objects
     */
    private final AppUserService appUserService;
    /**
     * Stores an instance of the VetService objects
     */
    private final VetService vetService;
    /**
     * Stores an instance of the DataValidator objects
     */
    private final DataValidator validator;

    /**
     * Gets the dogs from the repository based on the owner
     *
     * @param owner The owner of the dog being searched for
     * @return The dogs of one user
     */
    public Collection<Dog> getDogs(Customer owner) {

        if (owner == null)
            throw new IllegalStateException("No owner passed");

        return dogRepository.findAllByOwnerAndActiveOnAccountTrue(owner);
    }


    public void setDogInactive(Long id){
        dogRepository.setDogInactive(id);
    }

    public Dog getDogById(Long id){
        if(id == null)
            throw new IllegalStateException("No id passed");

        Optional<Dog> dog = dogRepository.findById(id);
        return dog.orElse(null);

    }

    public void addDog(NewDogRequest request) {

        var owner = appUserService.getCurrentCustomerFromContext();

        Vet vet = new Vet(
                request.getVetName(),
                request.getPractice(),
                request.getContactNumber(),
                request.getAddressLine1(),
                request.getAddressLine2(),
                request.getTown(),
                request.getPostcode()
        );
        vetService.check(vet);

        var dog = new Dog(
                request.getDogName(),
                request.getBreed(),
                request.getColor(),
                Size.valueOf(request.getSize()),
                DogGender.valueOf(request.getSex()),
                request.getAge(),
                Boolean.parseBoolean(request.getIsSpayedNeutered()),
                Boolean.parseBoolean(request.getIsVaccinated()),
                request.getWeight(),
                request.getInsuranceProvider(),
                request.getMicrochipNumber(),
                request.getMedicalNotes(),
                request.getBehaviouralNotes(),
                appUserService.getCurrentCustomerFromContext(),
                vet
        );
        this.saveDog(dog);
    }

    public boolean saveDog(Dog dog) {

        if (dog == null)
            throw new IllegalStateException("No dog to save");

        try {
            dogRepository.save(dog);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String validateRequest(NewDogRequest request) {

        if (!validator.sizeEnum(request.getSize()))
            return "size";

        if (!validator.genderEnum(request.getSex()))
            return "gender";

        if (validator.checkBool(request.getIsSpayedNeutered()))
            return "spayed";

        if (validator.checkBool(request.getIsVaccinated()))
            return "vaccinated";

        if (!validator.phoneNumber(request.getContactNumber()))
            return "contactNo";


        return null;
    }

    public String updateDog(UpdateDogRequest request, Long id){
        try{
            dogRepository.updateDog(id,
                    request.getName(),
                    request.getBreed(),
                    request.getAge(),
                    request.getSex(),
                    request.getSize(),
                    request.isSpayed(),
                    request.isVaccinated(),
                    request.getWeight(),
                    request.getInsuranceProvider(),
                    request.getMicrochipNumber(),
                    request.getMedicalNotes(),
                    request.getBehaviouralNotes()
            );
        }catch (Exception e){
            return "update";
        }
        return null;
    }

}
