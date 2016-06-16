package com.xebia.hpbook.model;

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
     * Constructor method
     */
    public Books() {

    }

    /**
     * @return the isbn of a book, example : {c8fabf68-8374-48fe-a7ea-a00ccd07afff}
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @return the title of a book, example : {Henri Potier à l'école des sorciers}
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the url image of a book, example : {http://henri-potier.xebia.fr/hp0.jpg}
     */
    public String getCover() {
        return cover;
    }

    /**
     * @return the price of a book
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return {true or false}
     * if true -> checkbox is checked
     * if false -> checkbox is unchecked
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
