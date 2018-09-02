
package com.example.lenovo.kisanapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>  {

   //for displaying content of messaging app


    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item2, parent, false);
        }

        Word currentWord = getItem(position);

        //get name of contact
        TextView name1 = (TextView) listItemView.findViewById(R.id.name);

        name1.setText(currentWord.getName());

        //get time
        TextView time1 = (TextView) listItemView.findViewById(R.id.time);

        time1.setText(currentWord.getmTime());

        //get date
        TextView date1 = (TextView) listItemView.findViewById(R.id.date);

        date1.setText(currentWord.getmDate());

        //get otp
        TextView otp1 = (TextView) listItemView.findViewById(R.id.otp);

        otp1.setText(currentWord.getOtp());


        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.containerm);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(),R.color.colorPrimary);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
            return listItemView;
    }
}