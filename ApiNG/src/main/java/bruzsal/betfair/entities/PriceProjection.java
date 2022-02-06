package bruzsal.betfair.entities;

import bruzsal.betfair.enums.PriceData;

import java.util.Set;


public record PriceProjection(

        Set<PriceData> priceData,
        ExBestOfferOverRides exBestOfferOverRides,
        Boolean virtualise,
        Boolean rolloverStakes

) {
}