package ro.nicolaemariusghergu.presentationtesting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.presentationtesting.model.Animal;
import ro.nicolaemariusghergu.presentationtesting.payload.AnimalRequest;
import ro.nicolaemariusghergu.presentationtesting.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/all")
    public List<Animal> getAnimals() {
        return animalService.getAnimals();
    }

    @PostMapping
    public boolean addAnimal(@RequestBody AnimalRequest animal) {
        return animalService.add(animal);
    }
}
