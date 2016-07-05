package ly.generalassemb.kikz;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by B on 6/23/2016.
 */
public class ShoppingCartFrag extends DialogFragment {
    private static final String CART_ITEMS="shopping_cart";
    private ArrayList<String> selectedItem;

    public static ShoppingCartFrag newInstance(ArrayList<String> item){
        Bundle args = new Bundle();
        args.putSerializable(CART_ITEMS, item);

        ShoppingCartFrag fragment = new ShoppingCartFrag();
        fragment.setArguments(args);
        return fragment;

    }

    @NonNull
    @Override
//TODO finsih setting up the Dialog for the shopping cart
    public Dialog onCreateDialog(Bundle savedInstance){
        selectedItem = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Shoppin' Cart")
                .setMessage("Thats all you gon git??");

        return builder.create();
    }

}
