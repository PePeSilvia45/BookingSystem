package jrp.gradedunit_birdstonkennel_v1.models.requests;

import lombok.Data;

@Data
public class PaymentRequest {

    public enum Currency{
        GBP
    }

    private String description;
    private double amount;
    private String stripeEmail;
    private String stripeToken;


}
