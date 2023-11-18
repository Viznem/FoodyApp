package rmit.ad.foodyapp.Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import rmit.ad.foodyapp.Domain.FoodDomain;
import rmit.ad.foodyapp.Interface.ChangeNumberItemsListener;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood (FoodDomain item) {
        ArrayList<FoodDomain> foodList = getListCart();
        boolean existAlready = false;
        int n = 0;
        for(int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            foodList.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            foodList.add(item);
        }
        tinyDB.putListObject("CartList", foodList);
        Toast.makeText(context, "Added To Your Cart!", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void plusFoodNum (ArrayList<FoodDomain> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemsListener.changed();
    }

    public void minusFoodNum(ArrayList<FoodDomain> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(listFood.get(position).getNumberInCart()==1){
            listFood.remove(position);
        }else {
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<FoodDomain> listFood = getListCart();
        double fee=0;
        for (int i=0; i < listFood.size(); i++){
            fee = fee + (listFood.get(i).getFee() * listFood.get(i).getNumberInCart());
        }
        return fee;
    }
}
