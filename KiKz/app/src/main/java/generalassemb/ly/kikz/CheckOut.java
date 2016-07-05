package generalassemb.ly.kikz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CheckOut extends FragmentActivity implements NewUser.NewUserListener, View.OnClickListener {
    ArrayList<Shoes> shoesBought;
    EditText userEmail;
    Button submit, finish;
    String emailInput;
    TextView finishDialog;
    List<Users> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out_layout);
        DBhelper helper = new DBhelper(this);


        // This retrieves the items from the shopping cart
        shoesBought = SumShoe.getInstance().getShoe();

        finish = (Button) findViewById(R.id.finish);

        finishDialog = (TextView) findViewById(R.id.finish_dialog);
        userEmail = (EditText) findViewById(R.id.email_et);


        submit = (Button) findViewById(R.id.submit_button);

        emailInput = null;
        submit.setOnClickListener(this);
        finish.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        DBhelper helper = new DBhelper(CheckOut.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        emailInput = userEmail.getText().toString();

        switch (view.getId()) {
            case R.id.submit_button:

                if (emailInput.isEmpty()) {


                        userEmail.setError("Enter your Email dude!");


                    break;

                }
                // Checking if a User exists
                else if (emailInput != null) {

                    boolean exists = helper.verification(emailInput);
                    //user doesn't exist so make a new user
                    if (!exists) {
                        submit.setVisibility(View.GONE);
                        userEmail.setVisibility(View.GONE);
                        finish.setVisibility(View.VISIBLE);

                        showNewUser();

                        break;

                    } else {
                        //user does exist

                        // Cleaning up the UI
                        submit.setVisibility(View.GONE);
                        userEmail.setVisibility(View.GONE);
                        finish.setVisibility(View.VISIBLE);

                        // Getting the user info from UsersTable and displaying it
                        user = helper.getUser(emailInput);
                        String id = user.get(0).getUSER_FIRST_NAME();

                        String insert = "INSERT INTO Orders (UserID) SELECT ID From Users Where First= ('" + id + "');";

                        db.execSQL(insert);


                        finishDialog.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            default:
                break;

            case R.id.finish:
                for (int i = 0; i < shoesBought.size(); ++i) {

                    String itemName = shoesBought.get(i).getSHOE_NAME();
                    String insert = "INSERT INTO Order_Items (ItemID) SELECT ID FROM Inventory WHERE Name =('" + itemName + "');";
                    db.execSQL(insert);

                    String query = "SELECT (OrderID) FROM Orders ORDER BY OrderID DESC LIMIT 1";
                    String insertOrder = "INSERT INTO Order_Items (OrderID) SELECT OrderID FROM Orders WHERE OrderID=(" + query + ") AND OrderID=-1;";
                    db.execSQL(insertOrder);
                }
                for (int i = 0; i < shoesBought.size(); ++i) {
                    while (shoesBought.size() > 0) {


                        shoesBought.remove(i);

                    }
                }
                Intent intent = new Intent(CheckOut.this,MainActivity.class);
                startActivity(intent);

                break;
        }
    }


    private void showNewUser() {
        FragmentManager fm = getSupportFragmentManager();
        NewUser mNewUser = new NewUser();
        mNewUser.show(fm, "fragment_new_user");
    }

    // This will add a new User to the Users table
    // retrive the new order number and pass that to the OrdersItem table
    private void addUser(Users user) {


        final Users newUser = user;
        new AsyncTask<Users, Void, Void>() {
            @Override
            protected Void doInBackground(Users... params) {

                DBhelper helper = DBhelper.getInstance(CheckOut.this);

                helper.insertRow(newUser);


                return null;
            }

            @Override
            protected void onPostExecute(Void voids) {
                super.onPostExecute(voids);
                Log.d("DIALOG", "added a record");
                Toast.makeText(CheckOut.this, "Thanks for Joining!", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    @Override
    public void onFinishedNewUser(String userFirst, String userLast, String userAddress) {
        DBhelper helper = DBhelper.getInstance(CheckOut.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Users user = new Users();
        user.setUSER_FIRST_NAME(userFirst);
        user.setUSER_LAST_NAME(userLast);
        user.setUSER_EMAIL(emailInput);
        user.setUSER_ADDRESS(userAddress);
        addUser(user);
        finishDialog.setVisibility(View.VISIBLE);
        Toast.makeText(CheckOut.this, "You're now a Member Big Pimpin!", Toast.LENGTH_SHORT).show();
        String insert = "INSERT INTO Orders (UserID) SELECT ID FROM Users Where Email =('" + emailInput + "');";
        db.execSQL(insert);
    }

}
