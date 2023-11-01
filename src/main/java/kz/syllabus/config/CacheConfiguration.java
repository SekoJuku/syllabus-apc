package kz.syllabus.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public Caffeine<Object, Object> caffeine() {
        return Caffeine.newBuilder()
                       .expireAfterWrite(5, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager() {
        var cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine());
        return cacheManager;
    }
}
