package com.xebia.hpbook.model;

import java.util.List;

/**
 * Offers class
 */
public class Offers {

    private List<OfferTypes> offers;

    /**
     *
     */
    public Offers() {
    }

    /**
     * @param offers
     */
    public Offers(List<OfferTypes> offers) {
        this.offers = offers;
    }

    /**
     * @return
     */
    public List<OfferTypes> getOffers() {
        return offers;
    }

    /**
     * @param offers
     */
    public void setOffers(List<OfferTypes> offers) {
        this.offers = offers;
    }

}
