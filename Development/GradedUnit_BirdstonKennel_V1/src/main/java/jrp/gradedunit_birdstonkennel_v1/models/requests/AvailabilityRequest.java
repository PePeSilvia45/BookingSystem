package jrp.gradedunit_birdstonkennel_v1.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AvailabilityRequest {
    private LocalDate arrival;
    private LocalDate departure;

}
