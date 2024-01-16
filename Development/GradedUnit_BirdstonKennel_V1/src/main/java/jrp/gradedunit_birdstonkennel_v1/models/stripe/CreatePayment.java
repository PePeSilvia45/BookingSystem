package jrp.gradedunit_birdstonkennel_v1.models.stripe;

import com.google.gson.annotations.SerializedName;

public class CreatePayment {
    @SerializedName("items")
    Object[] items;
    public Object[] getItems(){
        return items;
    }
}
