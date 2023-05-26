package ro.nicolaemariusghergu.presentationtesting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.presentationtesting.model.Animal;
import ro.nicolaemariusghergu.presentationtesting.payload.AnimalRequest;
import ro.nicolaemariusghergu.presentationtesting.service.AnimalService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/all")
    public List<Animal> getAnimals() {
        return animalService.getAnimals();
    }

    @GetMapping("/caffeine/{animalId}")
    public Animal getAnimalById(@PathVariable(name = "animalId") UUID animalId) {
        return animalService.getAnimal(animalId);
    }

    @GetMapping("/eh-cache/{animalId}")
    public Animal getAnimalByIdCaffeine(@PathVariable(name = "animalId") UUID animalId) {
        return animalService.getAnimalEhCache(animalId);
    }

    @GetMapping("/hazelcast/{animalId}")
    public Animal getAnimalByIdHazelcast(@PathVariable(name = "animalId") UUID animalId) {
        return animalService.getAnimalHazelcast(animalId);
    }


    @PostMapping
    public boolean addAnimal(@RequestBody AnimalRequest animal) {
        return animalService.add(animal);
    }
}
