package generalassemb.ly.kikz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brendan on 6/26/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder> {

    LayoutInflater inflater;
    List<Shoes> shoesList;
    Context context;

    public RecyclerAdapter(List<Shoes> shoeList, Context context) {
        this.shoesList = shoeList;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.from(parent.getContext()).inflate(R.layout.item_layout_card,
                parent, false);
        myViewHolder holder = new myViewHolder(v, context, shoesList);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        String imgURL = shoesList.get(position).getSHOE_IMAGE();
        //TODO setup images from picasso to use for shoeImage
        Picasso.with(this.context)
                .load(imgURL)
                .resize(50,50)
                .onlyScaleDown()
                .into(holder.shoeImage);
        //holder.shoeImage.setImageResource(R.mipmap.shoe1_thumbnail);
        holder.shoeName.setText(shoesList.get(position).getSHOE_NAME());
        holder.shoePrice.setText("Price: $" + shoesList.get(position).getSHOE_PRICE());
        holder.currentShoe = shoesList.get(position).getSHOE_ID();

    }


    @Override
    public int getItemCount() {
        return shoesList.size();
    }

    // setting the ViewHolder for my recyclerview with a clickListener

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public String currentShoe;
        public ImageView shoeImage;
        public TextView shoeName;
        public TextView shoePrice;
        List<Shoes> shoeList = new ArrayList<>();
        Context context;

        public myViewHolder(View itemView, Context context, List<Shoes> shoes) {
            super(itemView);
            this.shoeList = shoes;
            this.context = context;

            itemView.setOnClickListener(this);

            shoeImage = (ImageView) itemView.findViewById(R.id.shoe_pic);
            shoeName = (TextView) itemView.findViewById(R.id.shoe_name);
            shoePrice = (TextView) itemView.findViewById(R.id.shoe_price);


        }

        // telling my ClickListener what to do
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Shoes shoe = this.shoeList.get(position);
            Intent intent = new Intent(this.context, ShoeDetail.class);
            intent.putExtra("shoe_id", shoe.getSHOE_ID());
            intent.putExtra("img_id", shoe.getSHOE_IMAGE());
            intent.putExtra("name", shoe.getSHOE_NAME());
            intent.putExtra("price", shoe.getSHOE_PRICE());
            intent.putExtra("description", shoe.getSHOE_DESCRIPTION());
            this.context.startActivity(intent);


        }
    }


}
