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
      final var coinFullData = client.getCoinById("magic");
      marketPriceService.setAth(coinFullData.getMarketData().getAth().get("usd"));
      marketPriceService.setMarketCapRank(coinFullData.getMarketCapRank());
      marketPriceService.setAthDate(coinFullData.getMarketData().getAthDate().get("usd"));
      marketPriceService.setHigh24h(coinFullData.getMarketData().getHigh24h().get("usd"));
      marketPriceService.setLow24h(coinFullData.getMarketData().getLow24h().get("usd"));
      marketPriceService.setPriceInEth(coinFullData.getMarketData().getCurrentPrice().get("eth"));
      marketPriceService.setPriceInBtc(coinFullData.getMarketData().getCurrentPrice().get("btc"));
      marketPriceService.setPriceChangePercentage7d(coinFullData.getMarketData().getPriceChangePercentage7d());
      marketPriceService.setPriceChangePercentage30d(coinFullData.getMarketData().getPriceChangePercentage30d());

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
