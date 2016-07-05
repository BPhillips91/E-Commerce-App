package generalassemb.ly.kikz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener, CartAdapter.onItemRemoved {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    CartAdapter cartAdapter;
    ArrayList<Shoes> shoesInCart;
    Button keepShoppin, checkOut;
    TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_layout);


        totalPrice = (TextView) findViewById(R.id.cart_total);
        keepShoppin = (Button) findViewById(R.id.keep_shopping);
        checkOut = (Button) findViewById(R.id.check_out);
        mRecyclerView = (RecyclerView) findViewById(R.id.cart_recycler);

        shoesInCart = SumShoe.getInstance().getShoe();
        cartAdapter = new CartAdapter(shoesInCart, this);
        layoutManager = new LinearLayoutManager(this);
        cartAdapter.setmListener(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(cartAdapter);

        Log.d("TAG", "How many shoes" + shoesInCart.size());

        Toast.makeText(this, "Remove a Shoe by selecting and holding it", Toast.LENGTH_SHORT).show();
        keepShoppin.setOnClickListener(this);
        checkOut.setOnClickListener(this);
        //int size=shoesInCart.size();
        int i = 0;
        int total = 0;

        while (i < shoesInCart.size()) {
            total = total + Integer.parseInt(shoesInCart.get(i).getSHOE_PRICE());
            i++;
            totalPrice.setText("Total: $" + total);

        }


    }

    // TODO customize button colors
    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.keep_shopping:
                finish();
                break;

                case R.id.check_out:
                    if(shoesInCart.size()>0) {
                    // TODO set up checkout activity or fragment


                        Intent intent = new Intent(ShoppingCartActivity.this, CheckOut.class);

                        startActivity(intent);


                    finish();}
                    else{Toast.makeText(this,"How you gon' try and checkout with no shoes?",Toast.LENGTH_SHORT).show();

                    }
                    break;
            }

        }


    @Override
    public void onItemRemoved() {
        int i = 0;
        int total = 0;

        while (i < shoesInCart.size()) {
            total = total + Integer.parseInt(shoesInCart.get(i).getSHOE_PRICE());
            i++;

        }
        totalPrice.setText("Total: $" + total);

    }
}
