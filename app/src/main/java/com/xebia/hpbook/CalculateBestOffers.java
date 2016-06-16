package com.xebia.hpbook;

import com.xebia.hpbook.model.Books;
import com.xebia.hpbook.model.OfferTypes;

import java.util.List;

/**
 * CalculateBestOffers Class
 */
public class CalculateBestOffers {

    private List<OfferTypes> listOffers;
    private List<Books> listBooks;
    private float sliceValue = 0;
    private static final String PERCENTAGE_OFFER = "percentage";
    private static final String MINUS_OFFER = "minus";
    private static final String SLICE_OFFER = "slice";

    /**
     * @param selectedBooksList
     * @param listOffers
     */
    public CalculateBestOffers(List<Books> selectedBooksList, List<OfferTypes> listOffers) {
        this.listBooks = selectedBooksList;
        this.listOffers = listOffers;
    }

    /**
     * @return the total of the price books selected
     */
    public float calculateTotalPriceOfSelectedBooks() {
        float totalPrice = 0f;
        for (Books b : listBooks) {
            if(b.getQuantity() > 1){

                totalPrice += b.getQuantity() * b.getPrice();
            }else{
                totalPrice += b.getPrice();
            }
        }

        return totalPrice;
    }

    /**
     * @return the best offer price value
     */
    public float getBestOffer() {
        float totalBooksPrice = calculateTotalPriceOfSelectedBooks();
        if (listOffers.size() > 2) {
            sliceValue = getSliceValue(listOffers);
            return getBestPrice(totalBooksPrice, sliceValue);
        } else {
            return offerPercentageOrMinus(listOffers, totalBooksPrice);

        }
    }

    /**
     * return the best offer price between (Percentage and minus offers)
     * @param list
     * @param totalBooksPrice
     * @return the best price between percentage offer and minus offer
     */
    private float offerPercentageOrMinus(List<OfferTypes> list, float totalBooksPrice) {
        float percentagePrice = 0f;
        float minusPrice = 0f;
        if (list.size() > 1) {
            for (OfferTypes offerTypes : list) {

                if (offerTypes.getType().equals(PERCENTAGE_OFFER)) {
                    percentagePrice = totalBooksPrice -
                            (totalBooksPrice * offerTypes.getValue()) / 100.0f;
                }

                if (offerTypes.getType().equals(MINUS_OFFER)) {
                    minusPrice = totalBooksPrice - offerTypes.getValue();
                }

            }

            return compareOffers(percentagePrice, minusPrice);

        } else {
           percentagePrice = totalBooksPrice -
                    (totalBooksPrice * list.get(0).getValue()) / 100.0f;
            return percentagePrice;
        }

    }

    /**
     * get the price of the offer type -> slice
     * @param list
     * @param totalBooksPrice
     * @return the price of slice Offer
     */
    private float offerSlice(List<OfferTypes> list, float totalBooksPrice) {
        float slicePrice = 0f;
        for (OfferTypes offerTypes :
                list) {
            if (offerTypes.getType().equals(SLICE_OFFER)) {
                slicePrice = totalBooksPrice - offerTypes.getValue();
            }
        }

        return slicePrice;
    }

    /**
     * compares the prices between two offers
     * @param percentagePrice
     * @param minusPrice
     * @return the lowest best price
     */
    private float compareOffers(float percentagePrice, float minusPrice) {
        if (percentagePrice < minusPrice) {
            return percentagePrice;
        } else {
            return minusPrice;
        }
    }

    /**
     * get the best price between all the offers
     * @param totalBooksPrice
     * @param sliceValue
     * @return the best price
     */
    private float getBestPrice(float totalBooksPrice, float sliceValue) {
        float price = 0f;
        if (totalBooksPrice >= sliceValue) {
            //
            price = compareOffers(offerPercentageOrMinus(listOffers, totalBooksPrice),
                    offerSlice(listOffers, totalBooksPrice));

        } else {
            //
            price = offerPercentageOrMinus(listOffers, totalBooksPrice);
        }
        return price;
    }

    /**
     * To get the slice value of the 3rd offer (slice offer)
     * @param listOffers
     * @return slice value
     */
    private float getSliceValue(List<OfferTypes> listOffers) {
        for (OfferTypes o :
                listOffers) {

            if (o.getType().equals(SLICE_OFFER)) {
                sliceValue = o.getSliceValue();
            }

        }
        return sliceValue;
    }

}
