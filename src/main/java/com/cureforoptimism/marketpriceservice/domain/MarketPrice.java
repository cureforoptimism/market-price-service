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
}
