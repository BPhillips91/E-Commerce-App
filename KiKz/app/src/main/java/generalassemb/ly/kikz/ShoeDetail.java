package generalassemb.ly.kikz;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShoeDetail extends AppCompatActivity implements View.OnClickListener {
    ImageView shoePic;
    TextView shoeName, shoePrice, shoeDetail;
    Button addToCart, cancel;
    List<Shoes> mshoe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoe_detail_layout);

        shoePic = (ImageView) findViewById(R.id.d_shoe_pic);
        shoeName = (TextView) findViewById(R.id.d_shoe_name);
        shoePrice = (TextView) findViewById(R.id.d_shoe_price);
        shoeDetail = (TextView) findViewById(R.id.d_shoe_description);
        addToCart = (Button) findViewById(R.id.add_cart);
        cancel = (Button) findViewById(R.id.cancel);

        Picasso.with(ShoeDetail.this)
                .load(getIntent().getStringExtra("img_id"))
                .resize(200, 200)
                .onlyScaleDown()
                .into(shoePic);

        shoeName.setText("Shoe: " + getIntent().getStringExtra("name"));
        shoePrice.setText("Price: $" + getIntent().getStringExtra("price"));
        shoeDetail.setText("Discription: " + getIntent().getStringExtra("description"));

        addToCart.setOnClickListener(this);
        cancel.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.add_cart:
                //TODO make method to add to cart
                Toast.makeText(this, "Added to cart!", Toast.LENGTH_SHORT).show();

                addShoeToCart();

                finish();
                break;
            case R.id.cancel:
                finish();
                break;


        }

    }

    public List<Shoes> addShoeToCart() {

        List<Shoes> newShoe = new ArrayList<>();
        Shoes shoe = new Shoes();

        shoe.setSHOE_NAME(getIntent().getStringExtra("name"));
        shoe.setSHOE_PRICE(getIntent().getStringExtra("price"));
        shoe.setSHOE_IMAGE(getIntent().getStringExtra("img_id"));
        newShoe.add(shoe);
        SumShoe.getInstance().addShoe(shoe);


        return newShoe;

    }

}
