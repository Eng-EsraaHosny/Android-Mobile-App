package com.example.esraahosny.graduationapp.Activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.esraahosny.graduationapp.Adapters.menuitemAdapter;
import com.example.esraahosny.graduationapp.DataModels.menuitems;
import com.example.esraahosny.graduationapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsActivity extends AppCompatActivity {


   /* ListView list;
    String[] itemname ={
            "it is a kind of pie full of vegetables,sauces,garlic,meat,...",
            "it is a kind of makaroni with sauce ",
            "all types of fruits",
            "all plants , tomatos,garlic,oil,...",
            "steak with spicy sauce",

    };
*/
   /* Integer[] imgid={
            R.drawable.pizza,
            R.drawable.pasta,
            R.drawable.juice,
            R.drawable.salad,
            R.drawable.meat,

    };*/
   public menuitemAdapter adapter;
    public List<menuitems> arrayList = new ArrayList<>();
    public ListView list;
    //DataBaseFavourites dbf;
   // ArrayList<menuitems> arrayList_Fav_movie = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_items);

       // adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
        JsonTask movieTask = new JsonTask();
        String api_key = "http://cafeteriaapptest.azurewebsites.net/api/menuitem";
        movieTask.execute(api_key);

          /*  CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
            list=(ListView)findViewById(R.id.list);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    String Slecteditem= itemname[+position];
                    Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                  // Intent intent = new Intent(MenuItemsActivity.this,MenuItemDetailsActivity.class);
                  //  intent.putExtra()
                  //  startActivity(intent);

                }
            });*/
        }
    public class JsonTask extends AsyncTask<String, Void, List<menuitems>> {
        // private final String LOG = JsonTask.class.getSimpleName();


        //doInBackground part contains connection + url of api + exception handlers
        protected List<menuitems> doInBackground(String... params) {

            //initialization of variables
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String finaljson = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                if (stream == null) {
                    // Nothing to do.
                    return null;
                }
                StringBuffer buffer = new StringBuffer();


                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                finaljson = buffer.toString();
                Log.v("finalJSON", finaljson);

            } catch (IOException e1) {
                Log.e("LOG", "error", e1);
                return null;

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ioe) {
                        //log statement or any message
                        Log.e("LOG", "error", ioe);
                    }
                }
                try {
                    return getData(finaljson);
                } catch (JSONException e) {
                    //e.printStackTrace();
                    Log.e("LOG", "error", e);
                }
                return null;
            }

        }

        private List<menuitems> getData(String jsontoString) throws JSONException {

            JSONObject movieJson = new JSONObject(jsontoString);
           JSONArray movieArray = movieJson.getJSONArray("category");

            //here we write all the data to be string and showed in movie details
            final String nameofitem = "Name";
            final String priceofitem = "Price";
            for (int i = 0; i < movieArray.length(); i++) {
                JSONObject finalObject = movieArray.getJSONObject(i);
               menuitems items = new menuitems();

                items.setName(finalObject.getString(nameofitem));
                items.setPrice(finalObject.getString(priceofitem));

               // Log.v("avg", finalObject.getString(priceofitem));
                arrayList.add(items);
            }

          //  Log.d("vote data",item);
            return arrayList;
        }

        @Override
        protected void onPostExecute(List<menuitems> movies) {
            super.onPostExecute(arrayList);
            adapter.arrayList.addAll(movies);
            adapter.notifyDataSetChanged();
        }
    }
    }

