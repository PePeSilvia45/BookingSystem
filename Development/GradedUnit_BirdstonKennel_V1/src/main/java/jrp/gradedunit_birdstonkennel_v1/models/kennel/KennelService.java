package jrp.gradedunit_birdstonkennel_v1.models.kennel;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KennelService {

    @Autowired
    private final KennelRepository kennelRepository;


    public boolean save(Kennel kennel){
        if(kennel == null)
            throw new IllegalStateException("No kennel passed to save");

        try{
            kennelRepository.save(kennel);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public List<Kennel> findAll(){
        return kennelRepository.findAll();
    }

}
