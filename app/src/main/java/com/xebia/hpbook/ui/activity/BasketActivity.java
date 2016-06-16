package com.xebia.hpbook.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xebia.hpbook.CalculateBestOffers;
import com.xebia.hpbook.R;
import com.xebia.hpbook.model.Books;
import com.xebia.hpbook.model.OfferTypes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * BasketActivity class
 */
public class BasketActivity extends AppCompatActivity {

    private static final String SELECTED_BOOKS = "SELECTED_BOOKS";
    private static final String BEST_PRICE = "PRICE";
    private static final String NUMBER_BOOKS = "NUMBER";
    private static final String TOTAL_PRICE = "TOTAL";
    /*To manage test automatic */
    private static final String OFFER_TYPES = "TYPES";
    private static final String PERCENTAGE_OFFER = "percentage";
    private static final String MINUS_OFFER = "minus";
    private static final String SLICE_OFFER = "slice";

    private TextView numberOfProducts;
    private TextView totalPriceWithOffer;
    private TextView totalPriceWithoutOffer;
    private TextView economizePrice;
    private Button validate;
    private LinearLayout lLayout;
    /*To manage test automatic */
    private TextView offer1;
    private TextView offer2;
    private TextView offer3;
    private TextView sliceValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        numberOfProducts = (TextView) findViewById(R.id.products);
        totalPriceWithOffer = (TextView) findViewById(R.id.total_price_withOffer);
        totalPriceWithoutOffer = (TextView)findViewById(R.id.total_price_withoutOffer);
        economizePrice = (TextView) findViewById(R.id.econmize_price);
        validate = (Button) findViewById(R.id.validate);
        lLayout = (LinearLayout) findViewById(R.id.container);
        offer1 = (TextView) findViewById(R.id.test_offre1_automatic);
        offer2 = (TextView) findViewById(R.id.test_offre2_automatic);
        offer3 = (TextView) findViewById(R.id.test_offre3_automatic);
        sliceValue = (TextView) findViewById(R.id.test_offre3_sliceValue);

        Bundle b = getIntent().getExtras();
        if (b != null) {

            initializeBasketWithNProducts(b);

        } else {

            initializeBasketWithNoProducts();

        }

    }

    private void initializeBasketWithNoProducts() {
        totalPriceWithOffer.setText(getString(R.string.zero));
        totalPriceWithoutOffer.setText(getString(R.string.zero));
        numberOfProducts.setText(getString(R.string.choose_books));
        economizePrice.setText(getString(R.string.economize_prize, 0));
        lLayout.setVisibility(View.GONE);
    }


    private void initializeBasketWithNProducts(Bundle b) {
        lLayout.setVisibility(View.VISIBLE);

        totalPriceWithoutOffer.setVisibility(View.VISIBLE);

        float bestPrice = b.getFloat(BEST_PRICE);
        float totalBookPrice = b.getFloat(TOTAL_PRICE);
        int totalNumberProducts = b.getInt(NUMBER_BOOKS);
        ArrayList<Books> listOfSelectedBooks = (ArrayList<Books>) getIntent().getSerializableExtra(SELECTED_BOOKS);
        //To manage automatic test
        ArrayList<OfferTypes> listOfOffers = (ArrayList<OfferTypes>) getIntent().getSerializableExtra(OFFER_TYPES);
        //testOffers(listOfOffers);

        CalculateBestOffers c = new CalculateBestOffers(listOfSelectedBooks, listOfOffers);
        float priceWithoutOffer = c.calculateTotalPriceOfSelectedBooks();
        totalPriceWithoutOffer.setText(getString(R.string.price, round(priceWithoutOffer, 2)));

        for (Books books : listOfSelectedBooks) {
            TextView bookTitle = new TextView(this);
            TextView bookPrice = new TextView(this);
            bookPrice.setPadding(10, 0, 0, 0);
            bookTitle.setText(getString(R.string.display_products, books.getQuantity(),
                    books.getQuantity())+ books.getTitle());
            bookPrice.setText(getString(R.string.book_price, books.getPrice()));
            lLayout.addView(bookTitle);
            lLayout.addView(bookPrice);
        }

        validate.setVisibility(View.VISIBLE);
        totalPriceWithOffer.setText(getString(R.string.price, round(bestPrice, 2)));
        numberOfProducts.setText(getString(R.string.n_products, totalNumberProducts));
        economizePrice.setText(getString(R.string.economize_prize, getEconomizePrice(totalBookPrice, bestPrice)));
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BasketActivity.this, CreateAccountActivity.class));
                finish();
            }
        });

    }

    private BigDecimal getEconomizePrice(float totalBookPrice, float bestPrice) {
        return round((totalBookPrice - bestPrice), 2);
    }

    private static BigDecimal round(float d, int decimalPlace) {

        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    /**
     * Test automatic
     *
     * @param list
     */
    private void testOffers(List<OfferTypes> list) {
        int offre1Value = 0;
        int offre2Value = 0;
        int offre3Value = 0;
        int slice3Value = 0;
        for (OfferTypes o : list) {

            if (o.getType().equals(PERCENTAGE_OFFER)) {
                offre1Value = o.getValue();
            }

            if (o.getType().equals(MINUS_OFFER)) {
                offre2Value = o.getValue();
            }

            if (o.getType().equals(SLICE_OFFER)) {
                offre3Value = o.getValue();
                slice3Value = o.getSliceValue();
            }


        }
        offer1.setText(getString(R.string.test_offre1_automatic, offre1Value));
        offer2.setText(getString(R.string.test_offre2_automatic, offre2Value));
        offer3.setText(getString(R.string.test_offre3_automatic, offre3Value));
        sliceValue.setText(getString(R.string.test_offre3_sliceValue, slice3Value));
    }
}
