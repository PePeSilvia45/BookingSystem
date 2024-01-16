package jrp.gradedunit_birdstonkennel_v1.models.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This repository is used to communicate with the ContactMessage table in the database
 */
@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

    /**
     * Finds a ContactMessage in the database based on the email
     *
     * @param email The email of the message being looked for
     * @return The Contact message if found
     */
    Optional<ContactMessage> findByEmail(String email);

}
