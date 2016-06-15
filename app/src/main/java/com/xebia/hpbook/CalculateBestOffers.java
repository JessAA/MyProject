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
    private float bestPrice = 0f;

    /**
     * @param selectedBooksList
     * @param listOffers
     */
    public CalculateBestOffers(List<Books> selectedBooksList, List<OfferTypes> listOffers) {
        this.listBooks = selectedBooksList;
        this.listOffers = listOffers;
    }

    /**
     * @return
     */
    public float calculateTotalPriceOfSelectedBooks() {
        float totalPrice = 0f;
        for (Books b : listBooks) {
            totalPrice += b.getPrice();
        }

        return totalPrice;
    }

    /**
     * @return
     */
    public float getBestOffer() {
        float totalBooksPrice = calculateTotalPriceOfSelectedBooks();
        if (listOffers.size() > 2) {
            sliceValue = getSliceValue(listOffers);
            return getBestPrice(totalBooksPrice, sliceValue);
        } else {
            //    reduceBookPrice = offerPercentageOrMinus(listOffers, totalBooksPrice);
            return offerPercentageOrMinus(listOffers, totalBooksPrice);

        }
    }

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

            return bestPrice = compareOffers(percentagePrice, minusPrice);

        } else {
           /* float test1 = (totalBooksPrice * list.get(0).getValue()) / 100.0f;
            float test3 =  Math.abs(((totalBooksPrice * list.get(0).getValue()) / 100.0f) * 100) / 100;
            float test2 =  Math.round(((totalBooksPrice * list.get(0).getValue()) / 100.0f) * 1000) / 1000;
            float test = Math.round(((totalBooksPrice * list.get(0).getValue()) / 100.0f) * 10) / 10;*/
            percentagePrice = totalBooksPrice -
                    (totalBooksPrice * list.get(0).getValue()) / 100.0f;
            return percentagePrice;
        }

    }

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

    private float compareOffers(float percentagePrice, float minusPrice) {
        if (percentagePrice < minusPrice) {
            return percentagePrice;
        } else {
            return minusPrice;
        }
    }

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
