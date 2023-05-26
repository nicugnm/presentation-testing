package ro.nicolaemariusghergu.presentationtesting.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.presentationtesting.config.CacheHelper;
import ro.nicolaemariusghergu.presentationtesting.model.Animal;
import ro.nicolaemariusghergu.presentationtesting.payload.AnimalRequest;
import ro.nicolaemariusghergu.presentationtesting.repository.AnimalRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    private final CacheHelper cache;

    private final Cache<UUID, Animal> animalCache;

    private final Cache<UUID, List<Animal>> animalListCache;

    private final HazelcastInstance hazelcastInstance;

    private final IMap<UUID, Animal> hazelcastMap;

    public AnimalService(AnimalRepository animalRepository,
                         CacheHelper cache,
                         Cache<UUID, Animal> animalCache,
                         Cache<UUID, List<Animal>> animalListCache,
                         HazelcastInstance hazelcastInstance) {
        this.animalRepository = animalRepository;
        this.cache = cache;
        this.animalCache = animalCache;
        this.animalListCache = animalListCache;
        this.hazelcastInstance = hazelcastInstance;
        this.hazelcastMap = hazelcastInstance.getMap("animal-map");
    }

    private final UUID ALL_ANIMALS_UUID = UUID.randomUUID();

    public List<Animal> getAnimals() {
        List<Animal> animalsCache = animalListCache.getIfPresent(ALL_ANIMALS_UUID);

        if (animalsCache == null) {
            System.out.println("Cache not found - getAnimals()");
            animalsCache = animalRepository.findAll();

            animalListCache.put(ALL_ANIMALS_UUID, animalsCache);
        }

        return animalsCache;
    }

    public Animal getAnimal(UUID animalId) {
        Animal animal = animalCache.getIfPresent(animalId);

        if (animal == null) {
            System.out.println("Cache not found!");
            animal = animalRepository.getReferenceById(animalId);
            animalCache.put(animalId, animal);
        }

        return animal;
    }

    public Animal getAnimalHazelcast(UUID animalId) {
        Animal animal = hazelcastMap.get(animalId);

        if (animal == null) {
            System.out.println("Cache not found!");
            animal = animalRepository.getReferenceById(animalId);
            hazelcastMap.put(animalId, animal);
        }

        return animal;
    }

    public Animal getAnimalEhCache(UUID animalId) {
        if (cache.getAnimalByIdCacheFromCacheManager().containsKey(animalId)) {
            System.out.println("Cache found!");
            return cache.getAnimalByIdCacheFromCacheManager().get(animalId);
        }

        Animal animal = animalRepository.getReferenceById(animalId);

        cache.getAnimalByIdCacheFromCacheManager().put(animalId, animal);

        return animal;
    }

    public boolean add(AnimalRequest animal) {
        System.out.println(animal.toString());
        animalRepository.saveAndFlush(Animal.builder()
                        .animalId(UUID.randomUUID())
                        .name(animal.getName())
                        .race(animal.getRace())
                .build());

        return true;
    }
}
