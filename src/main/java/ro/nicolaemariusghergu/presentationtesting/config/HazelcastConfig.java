package ro.nicolaemariusghergu.presentationtesting.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.*;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("localhost:5701");

        /*MapConfig mapConfig = new MapConfig("animal-map");
        mapConfig.setEvictionConfig(new EvictionConfig()
                .setEvictionPolicy(EvictionPolicy.LRU)
                .setMaxSizePolicy(MaxSizePolicy.PER_NODE)
                .setSize(1000)); // Maximum number of entries*/

        return HazelcastClient.newHazelcastClient(clientConfig);
    }

}
