package ro.nicolaemariusghergu.presentationtesting.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.nicolaemariusghergu.presentationtesting.model.Animal;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public Caffeine<Object, Object> caffeineCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeineCache) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeineCache);
        return caffeineCacheManager;
    }

    @Bean
    public Cache<UUID, Animal> animalCache() {
        return caffeineCache()
                .build();
    }

    @Bean
    public Cache<UUID, List<Animal>> animalListCache() {
        return caffeineCache()
                .build();
    }
}
