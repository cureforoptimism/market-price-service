package com.cureforoptimism.marketpriceservice.service;

import com.cureforoptimism.marketpriceservice.domain.MarketPrice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarketPriceService {
  private final RedisTemplate<String, MarketPrice> redisTemplate;

  @Value("${redis.marketprice.topic}")
  private String topic;

  private final MarketPrice marketPrice = MarketPrice.builder().id("market-price").build();

  @Scheduled(fixedDelay = 30000)
  public void publishMarketPrices() {
    if(marketPrice.getPrice() == null) {
      return;
    }

    redisTemplate.convertAndSend(topic, marketPrice);
    log.info("Market prices updated: " + marketPrice.getPrice());
  }

  public void setMagicPrice(Double magicPrice) {
    marketPrice.setPrice(magicPrice);
  }

  public void set24hChange(Double value) {
    marketPrice.setChange(value);
  }

  public void set12hChange(Double value) {
    marketPrice.setChange12h(value);
  }

  public void set4hChange(Double value) {
    marketPrice.setChange4h(value);
  }

  public void set1hChange(Double value) {
    marketPrice.setChange1h(value);
  }

  public void set24hVolume(Double value) {
    marketPrice.setVolume24h(value);
  }

  public void set12hVolume(Double value) {
    marketPrice.setVolume12h(value);
  }

  public void set4hVolume(Double value) {
    marketPrice.setVolume4h(value);
  }

  public void set1hVolume(Double value) {
    marketPrice.setVolume1h(value);
  }

  public void setEthPrice(Double value) {
    marketPrice.setEthPrice(value);
  }

  public void setMarketCapRank(Long value) {
    marketPrice.setMarketCapRank(value);
  }
  public void setAth(Double value) {
    marketPrice.setAth(value);
  }

  public void setAthDate(String value) {
    marketPrice.setAthDate(value);
  }

  public void setHigh24h(Double value) {
    marketPrice.setHigh24h(value);
  }
  public void setLow24h(Double value) {
    marketPrice.setLow24h(value);
  }
  public void setPriceInEth(Double value) {
    marketPrice.setPriceInEth(value);
  }
  public void setPriceInBtc(Double value) {
    marketPrice.setPriceInBtc(value);
  }
  public void setPriceChangePercentage7d(Double value) {
    marketPrice.setPriceChangePercentage7d(value);
  }
  public void setPriceChangePercentage30d(Double value) {
    marketPrice.setPriceChangePercentage30d(value);
  }
}
