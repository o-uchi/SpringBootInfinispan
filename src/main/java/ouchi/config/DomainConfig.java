package ouchi.config;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
@EnableJpaAuditing
public class DomainConfig {
    @Bean
    AuditorAware<String> auditorAwareBean() {
        return () -> "test";
    }

    @Bean
    EmbeddedCacheManager embeddedCacheManager() throws IOException {
        DefaultCacheManager cacheManager = new DefaultCacheManager("infinispan.xml");
//        cacheManager.startCaches("todos", "LuceneIndexesMetadata", "LuceneIndexesData", "LuceneIndexesLocking");
        return cacheManager;
    }

    @PreDestroy
    void stop() throws IOException {
        System.out.println("stop.");
        embeddedCacheManager().stop();
    }
}
