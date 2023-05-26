package ro.nicolaemariusghergu.presentationtesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.nicolaemariusghergu.presentationtesting.model.Animal;

import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {

    @Query("SELECT count(a) FROM Animal a")
    long countAll();
}
