package ro.nicolaemariusghergu.presentationtesting.service;

import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.presentationtesting.model.Animal;
import ro.nicolaemariusghergu.presentationtesting.payload.AnimalRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AnimalService {

    private final List<Animal> animalList = new ArrayList<>();

    public List<Animal> getAnimals() {
        return animalList;
    }

    public boolean add(AnimalRequest animal) {
        System.out.println(animal.toString());
        animalList.add(Animal.builder()
                        .animalId(UUID.randomUUID())
                        .name(animal.getName())
                        .race(animal.getRace())
                .build());

        return true;
    }
}
