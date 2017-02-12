package com.example.esraahosny.graduationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MenuItemsActivity extends AppCompatActivity {


    ListView list;
    String[] itemname ={
            "it is a kind of pie full of vegetables,sauces,garlic,meat,...",
            "it is a kind of makaroni with sauce ",
            "all types of fruits",
            "all plants , tomatos,garlic,oil,...",
            "steak with spicy sauce",

    };

    Integer[] imgid={
            R.drawable.pizza,
            R.drawable.pasta,
            R.drawable.juice,
            R.drawable.salad,
            R.drawable.meat,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_items);



            CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
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
            });
        }
    }

