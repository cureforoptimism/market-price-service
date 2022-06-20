package com.cureforoptimism.marketpriceservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
public class MarketPrice {
  private String id;

  private Double price;
  private Double change;
  private Double change12h;
  private Double change4h;
  private Double change1h;

  private Double volume24h;
  private Double volume12h;
  private Double volume4h;
  private Double volume1h;

  private Double ethPrice;
  private Long marketCapRank;
  private Double marketCap;
  private Double circulatingSupply;
  private Double totalSupply;
  private Double maxSupply;
  private Double ath;
  private String athDate;
  private Double high24h;
  private Double low24h;
  private Double priceInEth;
  private Double priceInBtc;
  private Double priceChangePercentage7d;
  private Double priceChangePercentage30d;
}
