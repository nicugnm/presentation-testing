package ro.nicolaemariusghergu.presentationtesting.cache;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.nicolaemariusghergu.presentationtesting.service.AnimalService;

import java.util.UUID;

@SpringBootTest
class AnimalServiceTests {


    public static final UUID animalId = UUID.fromString("e1599d51-6e05-46cf-8d50-f026ba6d9044");

    private final AnimalService animalService;

    @Autowired
    public AnimalServiceTests(AnimalService animalService) {
        this.animalService = animalService;
    }

    @Test
    void testEhCache() {
        for (int i = 0; i < 10; i++) {
            animalService.getAnimalEhCache(animalId);
        }
    }

    @Test
    void testCaffeine() {
        for (int i = 0; i < 10; i++) {
            animalService.getAnimal(animalId);
        }
    }
}
