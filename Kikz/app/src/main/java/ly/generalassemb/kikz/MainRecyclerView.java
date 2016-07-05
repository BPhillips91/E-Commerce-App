package ly.generalassemb.kikz;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

/**
 * Created by B on 6/23/2016.
 */
public class MainRecyclerView extends RecyclerView.Adapter {
LayoutInflater inflater;
    TextView shoeName;
    TextView price;
    ImageView shoePic;

    public class myViewHolder extends RecyclerView.ViewHolder {


        public myViewHolder(View itemView) {
            super(itemView);

        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_layout,parent,false);
        myViewHolder holder = new myViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
