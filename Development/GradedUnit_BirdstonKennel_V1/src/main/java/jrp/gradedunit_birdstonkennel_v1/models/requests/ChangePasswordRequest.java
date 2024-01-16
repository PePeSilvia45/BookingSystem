package jrp.gradedunit_birdstonkennel_v1.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordRequest {

    private String password, confirmPassword;


}
