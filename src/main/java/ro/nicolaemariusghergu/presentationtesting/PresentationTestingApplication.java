package ro.nicolaemariusghergu.presentationtesting;

import com.github.javafaker.Animal;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.nicolaemariusghergu.presentationtesting.repository.AnimalRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class PresentationTestingApplication implements CommandLineRunner {

	private final AnimalRepository animalRepository;

	@Autowired
	public PresentationTestingApplication(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PresentationTestingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker();

		List<Animal> animalList = Stream.generate(faker::animal)
				.limit(200_000)
				.toList();

		List<ro.nicolaemariusghergu.presentationtesting.model.Animal> entities = animalList.parallelStream()
				.map(an -> ro.nicolaemariusghergu.presentationtesting.model.Animal.builder()
						.animalId(UUID.randomUUID())
						.name(an.name())
						.race(UUID.randomUUID().toString())
						.build()
				).toList();

		long countAnimals = animalRepository.countAll();

		if (countAnimals < 200_000) {
			animalRepository.saveAllAndFlush(entities);
		}

	}
}
