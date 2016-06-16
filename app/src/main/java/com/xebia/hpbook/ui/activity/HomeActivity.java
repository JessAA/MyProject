package com.xebia.hpbook.ui.activity;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xebia.hpbook.CalculateBestOffers;
import com.xebia.hpbook.R;
import com.xebia.hpbook.model.Books;
import com.xebia.hpbook.model.OfferTypes;
import com.xebia.hpbook.model.Offers;
import com.xebia.hpbook.rest.APIError;
import com.xebia.hpbook.rest.ApiClient;
import com.xebia.hpbook.rest.ApiInterface;
import com.xebia.hpbook.ui.adapter.CustomAdapter;
import com.xebia.hpbook.utils.BookAlertDialog;
import com.xebia.hpbook.utils.ConnectivityUtils;
import com.xebia.hpbook.utils.ErrorUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * HomeActivity class
 */
public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String SELECTED_BOOKS = "SELECTED_BOOKS";
    private static final String BEST_PRICE = "PRICE";
    private static final String TOTAL_PRICE = "TOTAL";
    private static final String OFFER_TYPES = "TYPES";
    private List<Books> listOfBooks = new ArrayList<>();
    private List<OfferTypes> listOffers = new ArrayList<>();
    private List<String> listISBN;
    private ListView listView;
    private ArrayList<Books> selectedBooksList;
    private Button addBook;
    private LinearLayout linearLayout;
    private int numberOfBooksSelected = 0;
    private TextView tv;
    private BookAlertDialog dialog;
    private CheckBox selectAll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = (ListView) findViewById(R.id.list_books);
        linearLayout = (LinearLayout) findViewById(R.id.loading_list);
        selectAll = (CheckBox) findViewById(R.id.select_all);
        dialog = new BookAlertDialog(this);

        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        addBook = (Button) findViewById(R.id.add_book);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listISBN = new ArrayList<>();
                selectedBooksList = new ArrayList<>();
                numberOfBooksSelected = 0;
                for (Books b : listOfBooks) {


                    if (b.isSelected()) {
                        listISBN.add(b.getIsbn());
                        selectedBooksList.add(b);
                        numberOfBooksSelected++;
                    }
                }
                tv.setText(getString(R.string.n_books, numberOfBooksSelected));
                if (listISBN == null || listISBN.isEmpty()) {

                    dialog.showSelectBookDialog();
                }
            }
        });

        new GetBooksList(apiService).execute();

        selectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isAllBooksSelected();
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book, menu);
        tv = new TextView(this);
        tv.setText(getString(R.string.n_books, numberOfBooksSelected));
        tv.setTextColor(getResources().getColor(R.color.colorAccent));
        tv.setTypeface(null, Typeface.BOLD);
        tv.setTextSize(20);
        tv.setPadding(0, 0, 20, 0);
        menu.add(0, 0, 1, R.string.n_books).setActionView(tv).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.basket) {

            if (listISBN != null && !listISBN.isEmpty()) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

                String params = TextUtils.join(",", listISBN);

                new GetBestOffer(apiService, params).execute();

            } else {
                startActivity(new Intent(HomeActivity.this, BasketActivity.class));
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView bookTitle = (TextView) view.getTag(R.id.book_title);
        CheckBox checkbox = (CheckBox) view.getTag(R.id.check_box);
        Log.d(bookTitle + " is ", "checked? " + isCheckedOrNot(checkbox));
    }

    private String isCheckedOrNot(CheckBox checkbox) {
        if (checkbox.isChecked()) {
            return "is checked";
        } else {
            return "is not checked";
        }

    }


    private class GetBooksList extends AsyncTask<Void, Void, Void> {

        private ApiInterface apiService;

        public GetBooksList(ApiInterface apiInterface) {
            this.apiService = apiInterface;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            Call<List<Books>> call = apiService.getBooksList();
            call.enqueue(new Callback<List<Books>>() {
                @Override
                public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {

                    Log.d("Response  ", "Code " + response.code());

                    if (response.isSuccessful() && !response.body().isEmpty()) {
                        linearLayout.setVisibility(View.GONE);
                        Log.d("Success ", "request");
                        response.body().get(0);
                        Log.d("Response object 1>>>", "" + response.body().get(0));
                        listOfBooks = response.body();
                        listView.setAdapter(new CustomAdapter(getApplicationContext(), listOfBooks));
                        return;
                    } else {
                        APIError error = ErrorUtils.parseError(response);
                        Log.d("error message", error.message());
                    }


                }

                @Override
                public void onFailure(Call<List<Books>> call, Throwable t) {
                    // there is more than just a failing request (like: no internet connection)
                    Log.d("Failed ", "request: " + t.toString());
                    if (!ConnectivityUtils.hasNetworkConnection(HomeActivity.this)) {
                        dialog.showConnectionDialog();
                    }

                }
            });
            return null;
        }
    }

    private class GetBestOffer extends AsyncTask<Void, Void, Void> {

        private ApiInterface apiService;
        private String parameters;

        public GetBestOffer(ApiInterface apiInterface, String isbnParams) {
            this.apiService = apiInterface;
            this.parameters = isbnParams;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Call<Offers> bestOffer = apiService.getBestOffers(parameters);
            bestOffer.enqueue(new Callback<Offers>() {
                @Override
                public void onResponse(Call<Offers> call, Response<Offers> response) {
                    Log.d("Response  ", "Code " + response.code());

                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("Success ", "request Offers");
                        listOffers = response.body().getOffers();
                        CalculateBestOffers calculateBestOffers = new CalculateBestOffers(selectedBooksList, listOffers);
                        float bestPrice = calculateBestOffers.getBestOffer();
                        float totalPrice = calculateBestOffers.calculateTotalPriceOfSelectedBooks();
                        Log.d("Best ", "Offer >>>" + bestPrice);
                        goToBasketActivity(bestPrice, totalPrice);
                        return;
                    } else {
                        APIError error = ErrorUtils.parseError(response);
                        Log.d("error message", error.message());
                    }

                }

                @Override
                public void onFailure(Call<Offers> call, Throwable t) {
                    // there is more than just a failing request (like: no internet connection)
                    Log.d("Failed ", "request Offers");
                    dialog.showConnectionDialog();
                }
            });
            return null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void goToBasketActivity(float bestPrice, float totalPrice) {
        Intent intent = new Intent(HomeActivity.this, BasketActivity.class);
        Bundle b = new Bundle();
        b.putFloat(BEST_PRICE, bestPrice);
        b.putFloat(TOTAL_PRICE, totalPrice);
        intent.putExtras(b);
        intent.putExtra(SELECTED_BOOKS, selectedBooksList);
        intent.putExtra(OFFER_TYPES, (Serializable) listOffers);
        startActivity(intent);

    }

    private void isAllBooksSelected() {

        for (Books b : listOfBooks) {
            b.setSelected(selectAll.isChecked());
        }

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), listOfBooks);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
