package rmit.ad.foodyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;

import rmit.ad.foodyapp.Domain.FoodDomain;
import rmit.ad.foodyapp.Helper.ManagementCart;

public class FoodDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private FoodDomain object;
    private ArrayList<String> toppings;
    int numberOrder = 1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        managementCart = new ManagementCart(this);
        toppings = new ArrayList<String>();

        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText( "$" + object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteExtra = "";
                for (String topping: toppings){
                    noteExtra += "" + topping +", ";
                }
                object.setNumberInCart(numberOrder);
                object.setOrderNote(noteExtra);
                try{
                    managementCart.insertFood(object);
                    Intent intent = new Intent(FoodDetailActivity.this, CartActivity.class);
                   //intent.putExtra("toppings", toppings);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }

                toppings.clear();
            }
        });
    }

    public void onCheckboxClicked(View view){
        CheckBox checkBox = (CheckBox)view;
        String topping = checkBox.getText().toString();
        if(checkBox.isChecked()){
            toppings.add(topping);
        }else {
            toppings.remove(topping);
        }
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.price);
        descriptionTxt = findViewById(R.id.description);
        numberOrderTxt = findViewById(R.id.numberOrder);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.foodDetailPic);
        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
    }
}