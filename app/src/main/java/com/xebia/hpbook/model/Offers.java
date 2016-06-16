package com.xebia.hpbook.model;

import java.util.List;

/**
 * Offers class
 */
public class Offers {

    private List<OfferTypes> offers;

    /**
     * @param offers
     */
    public Offers(List<OfferTypes> offers) {
        this.offers = offers;
    }

    /**
     * @return list of offers type
     */
    public List<OfferTypes> getOffers() {
        return offers;
    }

}
