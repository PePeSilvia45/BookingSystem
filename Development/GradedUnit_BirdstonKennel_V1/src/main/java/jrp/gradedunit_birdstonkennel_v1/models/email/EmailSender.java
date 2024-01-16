package jrp.gradedunit_birdstonkennel_v1.models.email;

/**
 * This interface handles the sending of emails to the user
 */
public interface EmailSender {

    /**
     * This method sends an email to a specific user
     *
     * @param to    The email recipient
     * @param email The email being sent
     */
    void send(String to, String email);

}
