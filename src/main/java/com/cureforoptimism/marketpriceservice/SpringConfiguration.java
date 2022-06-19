package com.cureforoptimism.marketpriceservice;

import com.cureforoptimism.marketpriceservice.domain.MarketPrice;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@Configuration
@EnableScheduling
@AllArgsConstructor
public class SpringConfiguration {
  @Bean
  public CoinGeckoApiClient coinGeckoApiClient() {
    return new CoinGeckoApiClientImpl();
  }

  @Bean
  RedisTemplate<String, MarketPrice> redisTemplate(RedisConnectionFactory connectionFactory,
      Jackson2JsonRedisSerializer<MarketPrice> serializer) {
    RedisTemplate<String, MarketPrice> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setDefaultSerializer(serializer);
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  public Jackson2JsonRedisSerializer<MarketPrice> jackson2JsonRedisSerializer() {
    return new Jackson2JsonRedisSerializer<>(MarketPrice.class);
  }

  @Bean
  ChannelTopic topic() {
    return new ChannelTopic("market-price");
  }
}
