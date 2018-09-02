package com.example.lenovo.kisanapp;



import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;

import static android.support.v4.content.ContextCompat.startActivity;

public class WordAdapter2 extends ArrayAdapter<Word>  {

    //for first tab to display nlist of names


    public WordAdapter2(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView name1 = (TextView) listItemView.findViewById(R.id.name);

        name1.setText(currentWord.getName());

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.containers);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}