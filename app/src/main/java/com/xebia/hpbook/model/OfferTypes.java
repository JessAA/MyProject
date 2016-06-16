package com.xebia.hpbook.model;

import java.io.Serializable;

/**
 * OfferTypes class
 */
public class OfferTypes implements Serializable {

    private String type;
    private int value;
    private int sliceValue;

    /**
     *
     * @param type
     * @param value
     */
    public OfferTypes(String type, int value) {
        this.type = type;
        this.value = value;
    }

    /**
     *
     * @return the offer type example : {"percentage", "minus", "slice"}
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return the value offer
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @return the slice value of the 3rd offer (slice offer)
     */
    public int getSliceValue() {
        return sliceValue;
    }

    @Override
    public String toString() {
        return "Offer Types{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", sliceValue='" + sliceValue + '\'' +
                '}';
    }

}
