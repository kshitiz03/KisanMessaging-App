package com.example.lenovo.kisanapp;

import org.json.simple.parser.JSONParser;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.text.ParseException;
/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {


    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        final ArrayList<Word> words1 = new ArrayList<Word>();


//        JSONParser parser = new JSONParser();
//
//        try {
//
//            Object obj = parser.parse(new FileReader("f:\\test.json"));
//
//            JSONObject jsonObject = (JSONObject) obj;
//            System.out.println(jsonObject);
//
//            String name = (String) jsonObject.get("name");
//            System.out.println(name);
//
//            long age = (Long) jsonObject.get("age");
//            System.out.println(age);
//
//            // loop array
//            JSONArray msg = (JSONArray) jsonObject.get("messages");
//            Iterator<String> iterator = msg.iterator();
//            while (iterator.hasNext()) {
//                System.out.println(iterator.next());
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        //parse json data
        JSONParser parser = new JSONParser();
        Log.e("json","done");
        try {
            Log.e("json","done");
//            Object obj = parser.parse(new FileReader("C:\\Users\\LENOVO\\KisanApp\\app\\info.json"));
//            Log.e("json",obj.toString());
//            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObject = new JSONObject(JsonContacts.CONTACTS);
            Log.e("json",jsonObject.toString());
            JSONArray jArray = jsonObject.getJSONArray("users");
            for(int i=0;i<jArray.length();i++){
                //get our object, this is one person's worth of data
                JSONObject json_data = jArray.getJSONObject(i);
                //set that person's attributes
                String name = json_data.getString("name");
                String contact = json_data.getString("contact");
                //this is our arrayList object, we add our Person object to it
                words1.add(new Word(name,contact));
            }
        }
        catch(JSONException e){
            Log.e("log_tag", "Error parsing data "+e.toString());
        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (org.json.simple.parser.ParseException e) {
//            e.printStackTrace();
//        }


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter2 adapter = new WordAdapter2(getActivity(), words1);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);


        return rootView;
    }

    public class WordAdapter2 extends ArrayAdapter<Word>  {

        /** Resource ID for the background color for this list of words */
        private int mColorResourceId;

//redefined wordadapter2 to impose the onclick functionality in contacts tab
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

            final Word currentWord = getItem(position);
            //get name and display
            TextView name1 = (TextView) listItemView.findViewById(R.id.name);

            name1.setText(currentWord.getName());

            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.containers);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);

            //onclick list item
            listItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),ContactInfo.class);

                    //variable extra earlier declared to send to next activity
                    intent.putExtra(ContactInfo.EXTRA_NAME,currentWord.getName());
                    intent.putExtra(ContactInfo.EXTRA_CONTACT,currentWord.getPhone_no());
                    startActivity(intent);
                }
            });
            // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
            // the ListView.
            return listItemView;
        }
    }

}
