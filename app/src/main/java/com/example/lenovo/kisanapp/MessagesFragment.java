package com.example.lenovo.kisanapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment {
    DatabaseHandler handler;
    WordAdapter adapter;
    ArrayList<Word> words;
ListView listView;

    public MessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        DatabaseHandler handler = new DatabaseHandler(getContext());
        final ArrayList<Word> words = handler.getAllMessages();
        if(words==null)
        {
            return rootView;
        }
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words);

        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the  use the { WordAdapter} we created above, so that the
        // { ListView} will display list items for each { Word} in the list.
        listView.setAdapter(adapter);


        return rootView;
    }

    //get the latest changes on resume into the listview
    @Override
    public void onResume()
    {
          super.onResume();
        //do the data changes. In this case, I am refreshing the arrayList cart_list and then calling the listview to refresh.
        if(words==null){
            return;
        }
        listView.setAdapter(new WordAdapter(this.getActivity(),words));
    }


}
