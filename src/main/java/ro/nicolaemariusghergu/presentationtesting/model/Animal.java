package ro.nicolaemariusghergu.presentationtesting.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID animalId;

    private String name;

    private String race;
}
