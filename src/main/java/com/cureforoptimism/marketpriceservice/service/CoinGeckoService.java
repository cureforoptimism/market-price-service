package com.cureforoptimism.marketpriceservice.service;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinGeckoService {
  private final MarketPriceService marketPriceService;
  private final CoinGeckoApiClient client;

  @Scheduled(fixedDelay = 30000)
  public synchronized void refreshMagicPrice() {
    try {
      marketPriceService.setEthPrice(client
                  .getPrice("ethereum", "usd", false, false, false, false)
                  .get("ethereum")
                  .get("usd"));

    } catch (Exception ex) {
      log.error("Error retrieving MAGIC price from coingecko", ex);
      // Ignore, it'll retry
    } finally {
      client.shutdown();
    }
  }
}
