package ro.nicolaemariusghergu.presentationtesting.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Animal {

    private UUID animalId;

    private String name;

    private String race;
}
