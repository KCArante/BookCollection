package com.example.kcarante.bookcollection;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SARJ on 2/23/2016.
 */
public class SpecialAdapter extends ArrayAdapter<Books> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<Books> books;

    public SpecialAdapter(Context context, int resource, ArrayList<Books> booksArrayList ) {
        super(context, resource, booksArrayList);
        this.books = booksArrayList;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View row = convertView;

       if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1 , parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        Books temp = books.get(position);

        if(temp != null){

              TextView mTextView = null;
            if(mTextView != null){
                mTextView.setText(temp.getTitle());

                if(temp.isRead()){
                    mTextView.setTextColor(Color.RED);
                    mTextView.setPaintFlags(mTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    mTextView.setTextColor(Color.DKGRAY);
                    mTextView.setPaintFlags( mTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }

            }
        }

        return null;
    }
}
