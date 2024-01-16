package jrp.gradedunit_birdstonkennel_v1.models.vet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {

    Collection<Vet> findAllByPractice(String practice);


}
