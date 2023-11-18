package rmit.ad.foodyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import rmit.ad.foodyapp.Adaptor.CartAdaptor;
import rmit.ad.foodyapp.Helper.ManagementCart;
import rmit.ad.foodyapp.Interface.ChangeNumberItemsListener;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView itemsCostTxt, taxCostTxt, deliveryCostTxt, totalCostTxt, emptyTxt;
    private  double taxFee;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initListCart();
        CalculateCart();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton cartBtn =findViewById(R.id.toCartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });
    }

    private void initView() {
        recyclerViewList=findViewById(R.id.cartRecyclerView);
        itemsCostTxt = findViewById(R.id.itemsCost);
        taxCostTxt = findViewById(R.id.taxCost);
        deliveryCostTxt = findViewById(R.id.deliveryCost);
        totalCostTxt = findViewById(R.id.totalCost);
        emptyTxt = findViewById(R.id.empty);
        scrollView = findViewById(R.id.scrollView3);
    }

    private void initListCart(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartAdaptor(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);

        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart(){
        double taxRate = 0.05;
        double deliveryFee = 20;

        taxFee = Math.round((managementCart.getTotalFee()*taxRate)*100)/100;
        double itemsTotalCost = Math.round(managementCart.getTotalFee()*100)/100;
        double total = Math.round((managementCart.getTotalFee() + taxFee + deliveryFee)*100)/100;

        itemsCostTxt.setText("$" + itemsTotalCost);
        taxCostTxt.setText("$" + taxFee);
        deliveryCostTxt.setText("$" + deliveryFee);
        totalCostTxt.setText("$" + total);
    }
}