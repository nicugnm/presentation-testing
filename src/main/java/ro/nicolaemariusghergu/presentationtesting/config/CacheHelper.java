package ro.nicolaemariusghergu.presentationtesting.config;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.stereotype.Component;
import ro.nicolaemariusghergu.presentationtesting.model.Animal;

import java.util.UUID;

@Component
public class CacheHelper {

    private CacheManager cacheManager;
    private Cache<UUID, Animal> animalByIdCache;

    public CacheHelper() {
        cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder().build();
        cacheManager.init();

        animalByIdCache = cacheManager
                .createCache("getAnimalById", CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(
                                UUID.class, Animal.class,
                                ResourcePoolsBuilder.heap(10)));
    }

    public Cache<UUID, Animal> getAnimalByIdCacheFromCacheManager() {
        return cacheManager.getCache("getAnimalById", UUID.class, Animal.class);
    }

    // standard getters and setters
}