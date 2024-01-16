package jrp.gradedunit_birdstonkennel_v1.models.vet;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class VetService {

    @Autowired
    private final VetRepository vetRepository;

    public void check(Vet vet){
        Collection<Vet> vets = vetRepository.findAllByPractice(vet.getPractice());
        for(Vet v : vets)
            if (v.getName().equals(vet.getName())){
                vet.setId(v.getId());
            }
        this.saveVet(vet);
    }

    public boolean saveVet(Vet vet){
        try{
            vetRepository.save(vet);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
