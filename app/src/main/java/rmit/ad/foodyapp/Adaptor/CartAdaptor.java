package rmit.ad.foodyapp.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import rmit.ad.foodyapp.Domain.FoodDomain;
import rmit.ad.foodyapp.Helper.ManagementCart;
import rmit.ad.foodyapp.Interface.ChangeNumberItemsListener;
import rmit.ad.foodyapp.R;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdaptor(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((
                foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getFee()) * 100) / 100));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));
        holder.notes.setText(foodDomains.get(position).getOrderNote());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(
                foodDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    managementCart.plusFoodNum(foodDomains, position, new ChangeNumberItemsListener() {
                        @Override
                        public void changed() {
                            notifyDataSetChanged();
                            changeNumberItemsListener.changed();
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    managementCart.minusFoodNum(foodDomains, position, new ChangeNumberItemsListener() {
                        @Override
                        public void changed() {
                            notifyDataSetChanged();
                            changeNumberItemsListener.changed();
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem, notes;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;
        TextView checkoutBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleCart);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCart);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            plusItem=itemView.findViewById(R.id.cartPlusBtn);
            minusItem=itemView.findViewById(R.id.cartMinusBtn);
            num=itemView.findViewById(R.id.numberItem);
            checkoutBtn=itemView.findViewById(R.id.checkoutBtn);
            notes=itemView.findViewById(R.id.notes);
        }
    }
}
