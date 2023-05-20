package ro.nicolaemariusghergu.presentationtesting.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalRequest {

    private String name;

    private String race;
}
