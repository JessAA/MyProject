package com.xebia.hpbook.model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Books class
 */

public class Books implements Serializable {

    private String isbn;
    private String title;
    private String cover;
    private int price;
    private boolean isSelected;

    /**
     *
     */
    public Books() {

    }

    /**
     * @param isbn
     * @param title
     * @param cover
     * @param price
     */
    public Books(String isbn, String title, String cover, int price) {
        this.isbn = isbn;
        this.title = title;
        this.cover = cover;
        this.price = price;
    }

    protected Books(Parcel in) {
        isbn = in.readString();
        title = in.readString();
        cover = in.readString();
        price = in.readInt();
        isSelected = in.readByte() != 0;
    }

    /**
     * @return
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return
     */
    public String getCover() {
        return cover;
    }

    /**
     * @param cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * @param selected
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "Books{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", price=" + price +
                ", isSelected=" + isSelected +
                '}';
    }


}
