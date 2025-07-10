package com.kronospan.aibi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Kronospan AI Business Intelligence Platform
 * 
 * Context-Engineered approach for natural language to SQL conversion
 * Optimized for 8GB RAM systems with careful resource management
 */
@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class KronospanAiBiApplication {

    public static void main(String[] args) {
        // Configure for limited memory environment
        System.setProperty("spring.jpa.properties.hibernate.jdbc.batch_size", "25");
        System.setProperty("spring.jpa.properties.hibernate.order_inserts", "true");
        System.setProperty("spring.jpa.properties.hibernate.order_updates", "true");
        
        SpringApplication.run(KronospanAiBiApplication.class, args);
    }
    
    /**
     * Configure async executor for parallel processing
     * Limited threads to conserve memory
     */
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);  // Conservative for 4-core CPU
        executor.setMaxPoolSize(4);   // Max 4 threads
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Kronospan-");
        executor.initialize();
        return executor;
    }
}