package generalassemb.ly.kikz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewUser extends DialogFragment  implements TextView.OnEditorActionListener {

    private EditText firstInput,lastInput,addressInput;

    public interface NewUserListener{
        void onFinishedNewUser(String userFirst,String userLast,String userAddress);
    }

    public NewUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_new_user,container);
        getDialog().setTitle("Shipping Information");
        firstInput = (EditText)view.findViewById(R.id.first_name_et);
        lastInput = (EditText)view.findViewById(R.id.last_name_et);
        addressInput = (EditText)view.findViewById(R.id.address_et);

        addressInput.setOnEditorActionListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    //
    @Override // method to send the user input back to CheckOut
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        String first, last, address;
        first = firstInput.getText().toString();
        last = lastInput.getText().toString();
        address = addressInput.getText().toString();
        boolean filledOut = false;
        if (first.isEmpty() ) {
                firstInput.setError("UserName Should not be blank");
            filledOut= true;
        }
         if(last.isEmpty()){
            lastInput.setError("UserName Should not be blank");
             filledOut= true;
        }
         if(address.isEmpty()){
            addressInput.setError("UserName Should not be blank");
             filledOut= true;
        }
        if (!filledOut  && EditorInfo.IME_ACTION_DONE == i) {
                // Return input text to activity
                NewUserListener activity = (NewUserListener) getActivity();
                activity.onFinishedNewUser(firstInput.getText().toString(), lastInput.getText().toString(), addressInput.getText().toString());
                this.dismiss();
                return true;
            }
            return false;
        }


}
