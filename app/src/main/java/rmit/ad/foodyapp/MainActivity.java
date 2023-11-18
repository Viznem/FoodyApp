package rmit.ad.foodyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import rmit.ad.foodyapp.Adaptor.CategoryAdaptor;
import rmit.ad.foodyapp.Adaptor.PopularAdaptor;
import rmit.ad.foodyapp.Domain.CategoryDomain;
import rmit.ad.foodyapp.Domain.FoodDomain;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategoryList();
        recyclerViewPopularList();
        bottomNavigation();
    }

    private void recyclerViewCategoryList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.categoryView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Vietnam", "vnfood_icon"));
        category.add(new CategoryDomain("USA", "koreafood_icon"));
        category.add(new CategoryDomain("Korea", "thaifood_icon"));
        category.add(new CategoryDomain("Thai", "usafood"));
        category.add(new CategoryDomain("Dessert", "cat_4"));

        adapter= new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private  void recyclerViewPopularList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.popularView);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pho Pizza", "pop_1", "The combination between Pizza and Vietnamese traditional food Pho","", 10.00));
        foodList.add(new FoodDomain("Cheese Burger", "pop_2", "Traditional Burger with tons of cheese","", 7.00));
        foodList.add(new FoodDomain("Vegan Pizza", "pop_3", "Pizza with only vegetable","", 8.00));

        adapter2 = new PopularAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void bottomNavigation(){
        FloatingActionButton cartBtn =findViewById(R.id.toCartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(MainActivity.this, CartActivity.class));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}