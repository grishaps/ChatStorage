package net.chatstorage.config;

import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {

    @Bean
    public RateLimiter rateLimiter() {
        return new SimpleRateLimiter(20); // Limit 20 req per minute
    }
}
 //we can have custom period, refresh period and timeout duration