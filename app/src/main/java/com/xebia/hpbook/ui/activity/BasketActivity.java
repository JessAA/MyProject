package com.xebia.hpbook.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xebia.hpbook.R;
import com.xebia.hpbook.model.Books;

import java.util.ArrayList;

/**
 * BasketActivity class
 */
public class BasketActivity extends AppCompatActivity {

    private static final String SELECTED_BOOKS = "SELECTED_BOOKS";
    private static final String BEST_PRICE = "PRICE";
    private static final String TOTAL_PRICE = "TOTAL";

    private TextView numberOfProducts;
    private TextView totalPrice;
    private TextView economizePrice;
    private Button validate;
    private LinearLayout lLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        numberOfProducts = (TextView) findViewById(R.id.products);
        totalPrice = (TextView) findViewById(R.id.total_price);
        economizePrice = (TextView) findViewById(R.id.econmize_price);
        validate = (Button) findViewById(R.id.validate);
        lLayout = (LinearLayout) findViewById(R.id.container);

        Bundle b = getIntent().getExtras();
        if (b != null) {

            initializeBasketWithNProducts(b);

        } else {

            initializeBasketWithNoProducts();

        }

    }

    private void initializeBasketWithNoProducts() {
        totalPrice.setText(getString(R.string.zero));
        numberOfProducts.setText(getString(R.string.choose_books));
        economizePrice.setText(getString(R.string.economize_prize, 0));
        lLayout.setVisibility(View.GONE);
    }


    private void initializeBasketWithNProducts(Bundle b) {
        lLayout.setVisibility(View.VISIBLE);

        float bestPrice = b.getFloat(BEST_PRICE);
        float totalBookPrice = b.getFloat(TOTAL_PRICE);
        ArrayList<Books> listOfSelectedBooks = (ArrayList<Books>) getIntent().getSerializableExtra(SELECTED_BOOKS);

        for (Books books : listOfSelectedBooks) {
            TextView bookTitle = new TextView(this);
            TextView bookPrice = new TextView(this);
            bookPrice.setPadding(10, 0, 0, 0);
            bookTitle.setText(getString(R.string.display_products, books.getTitle()));
            bookPrice.setText(getString(R.string.book_price, books.getPrice()));
            lLayout.addView(bookTitle);
            lLayout.addView(bookPrice);
        }

        validate.setVisibility(View.VISIBLE);
        String s = Float.toString(bestPrice);
        s = s.replace(".", "€");
        totalPrice.setText(s);
        numberOfProducts.setText(getString(R.string.n_products, listOfSelectedBooks.size()));
        String sEconomize = Float.toString(getEconomizePrice(totalBookPrice, bestPrice));
        sEconomize = sEconomize.replace(".", "€");
        economizePrice.setText(getString(R.string.economize_prize, sEconomize));
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BasketActivity.this, CreateAccountActivity.class));
                finish();
            }
        });

    }

    private float getEconomizePrice(float totalBookPrice, float bestPrice) {
        return Math.abs(totalBookPrice - bestPrice) * 100 / 100;
        //double d = (double) Math.round(tonDouble * 100) / 100;
    }
}
