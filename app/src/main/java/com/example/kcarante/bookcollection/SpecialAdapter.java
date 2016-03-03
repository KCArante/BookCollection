package com.example.kcarante.bookcollection;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by SARJ on 2/23/2016.
 */
public class SpecialAdapter extends ArrayAdapter {

    ArrayList<Books> books;
    int textView;
    Context context;

    public SpecialAdapter(Context context, int resource, ArrayList<Books> booksArrayList ) {
        super(context, resource, booksArrayList);
        this.context = context;
        this.books = booksArrayList;
        this.textView = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder;

        if(convertView == null){

            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Books temp = books.get(position);

        if(temp != null){

            if(viewHolder.mTextView != null){
                viewHolder.mTextView.setText(temp.getTitle());

                if(temp.isRead()){
                    viewHolder.mTextView.setTextColor(Color.RED);
                    viewHolder.mTextView.setPaintFlags(viewHolder.mTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    viewHolder.mTextView.setTextColor(Color.DKGRAY);
                    viewHolder.mTextView.setPaintFlags( viewHolder.mTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }

            }
        }

        return convertView;
    }
}
