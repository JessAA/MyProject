package com.xebia.hpbook.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xebia.hpbook.R;
import com.xebia.hpbook.model.Books;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomAdapter Class
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    List<Books> listBooks;
    private static LayoutInflater inflater=null;

    /**
     * Constructor method
     * @param context
     * @param books
     */
    public CustomAdapter(Context context, List<Books> books) {
        this.context = context;
        this.listBooks = books;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listBooks.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder= null;
        if(convertView == null){
            holder = new Holder();
            convertView = inflater.inflate(R.layout.list_item_books, null);
            holder.bookTitle = (TextView) convertView.findViewById(R.id.book_title);
            holder.bookPrice = (TextView) convertView.findViewById(R.id.book_price);
            //holder.bookCover = (ImageView) convertView.findViewById(R.id.book_image);

            holder.checkBox = (CheckBox) convertView.findViewById(R.id.check_box);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Here we get the position that we have set for the checkbox using setTag.
                    int getPosition = (Integer) buttonView.getTag();
                    // Set the value of checkbox to maintain its state.
                    listBooks.get(getPosition).setSelected(buttonView.isChecked());
                }
            });

            convertView.setTag(holder);
            convertView.setTag(R.id.book_title, holder.bookTitle);
            convertView.setTag(R.id.book_price, holder.bookPrice);
            convertView.setTag(R.id.check_box, holder.checkBox);


        } else{
            holder = (Holder) convertView.getTag();

        }
        holder.checkBox.setTag(position); // This line is important.
        holder.bookTitle.setText(listBooks.get(position).getTitle());
        holder.bookPrice.setText(context.getString(R.string.book_price,listBooks.get(position).getPrice()));
        holder.checkBox.setChecked(listBooks.get(position).isSelected());
        /*Picasso.with(context)
                .load(listBooks.get(position).getCover())
                .into(holder.bookCover);
        */

        return convertView;
    }

    /**
     * Holder class
     */
    public class Holder
    {
        TextView bookTitle;
        TextView bookPrice;
        ImageView bookCover;
        CheckBox checkBox;

    }

}
