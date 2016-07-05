package generalassemb.ly.kikz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brendan on 6/29/16.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    LayoutInflater inflater;
    List<Shoes> shoesList;
    Context context;

    //setting a listener to handel changes in the Cart
    private onItemRemoved mListener;

    public interface onItemRemoved {
        void onItemRemoved();
    }

    public void setmListener(onItemRemoved listener) {
        this.mListener = listener;
    }


    public CartAdapter(List<Shoes> shoeList, Context context) {
        this.shoesList = shoeList;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.from(parent.getContext()).inflate(R.layout.shopping_cart_item,
                parent, false);
        CartViewHolder holder = new CartViewHolder(v, context, shoesList);
        return holder;
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {

        //TODO setup images from picasso to use for shoeImage

        // holder.shoeImage.setImageResource(R.mipmap.shoe1_thumbnail);
        holder.shoeName.setText(shoesList.get(position).getSHOE_NAME());
        holder.shoePrice.setText("$" + shoesList.get(position).getSHOE_PRICE());
        holder.currentShoe = shoesList.get(position).getSHOE_ID();

    }


    @Override
    public int getItemCount() {
        return shoesList.size();
    }

    // setting the ViewHolder for my recyclerview with a clickListener

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        public String currentShoe;
        public TextView shoeName;
        public TextView shoePrice;

        List<Shoes> shoeList = new ArrayList<>();
        Context context;

        public CartViewHolder(View itemView, Context context, List<Shoes> shoes) {
            super(itemView);
            this.shoeList = shoes;
            this.context = context;

            itemView.setOnLongClickListener(this);


            shoeName = (TextView) itemView.findViewById(R.id.cart_name);
            shoePrice = (TextView) itemView.findViewById(R.id.cart_price);


        }

        // removing an item from the RecyclerView and notifing the cart adapter
        @Override
        public boolean onLongClick(View view) {

            int position = getAdapterPosition();
            Shoes shoe = this.shoeList.get(position);
            this.shoeList.remove(position);
            notifyItemRemoved(position);
            mListener.onItemRemoved();
            Toast.makeText(this.context, "Shoe Removed. Keep on shopping!", Toast.LENGTH_SHORT).show();
            return false;

        }


    }

}





