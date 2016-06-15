package com.xebia.hpbook.model;

/**
 * OfferTypes class
 */
public class OfferTypes {

    private String type;
    private int value;
    private int sliceValue;

    public OfferTypes() {
    }

    public OfferTypes(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSliceValue() {
        return sliceValue;
    }

    public void setSliceValue(int sliceValue) {
        this.sliceValue = sliceValue;
    }
}
