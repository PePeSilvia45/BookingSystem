package jrp.gradedunit_birdstonkennel_v1.models.kennel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface KennelRepository extends JpaRepository<Kennel, Long> {

    ArrayList<Kennel> findAll();

}
