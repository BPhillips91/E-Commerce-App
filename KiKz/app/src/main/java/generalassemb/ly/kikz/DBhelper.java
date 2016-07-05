package generalassemb.ly.kikz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brendan on 6/26/16.
 */
public class DBhelper extends SQLiteOpenHelper {
//TODO: set up database and 3 tables: iventory,users, and orders

    private static DBhelper instance;

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KikzDB";

    public static final String INVENTORY_TABLE = "Inventory";
    public static final String USERS_TABLE = "Users";
    public static final String ORDERS_TABLE = "Orders";
    public static final String ORDER_ITEM_TABLE="Order_Items";

    //columns for inventory
    public static final String COL_ID = "ID";
    public static final String COL_SHOE_NAME = "Name";
    public static final String COL_SHOE_PRICE = "Price";
    public static final String COL_SHOE_TYPE = "Type";
    public static final String COL_DESCRIPTION = "Description";
    public static final String COL_INSTOCK = "InStock";
    public static final String COL_SHOE_IMAGE = "Image";

    // columns for user table
    public static final String COL_FIRST_NAME="First";
    public static final String COL_LAST_NAME="Last";
    public static final String COL_EMAIL="Email";
    public static final String COL_ADDRESS="Address";

    //columns for order item table
    public static final String COL_USER_ID="UserID";
    public static final String COL_ITEM_ID="ItemID";
    public static final String COL_ORDER_ID="OrderID";



    public static final String[] INVENTORY_COLUMNS = {COL_ID, COL_SHOE_NAME, COL_SHOE_TYPE, COL_SHOE_PRICE,
            COL_DESCRIPTION, COL_INSTOCK, COL_SHOE_IMAGE};

    public static final String[] USER_COLUMNS={COL_ID,COL_FIRST_NAME,COL_LAST_NAME,COL_EMAIL,
    COL_ADDRESS};


    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static DBhelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBhelper(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public void insertRow(Users user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID,user.getUSER_ID());
        values.put(COL_FIRST_NAME,user.getUSER_FIRST_NAME());
        values.put(COL_LAST_NAME,user.getUSER_LAST_NAME());
        values.put(COL_EMAIL,user.getUSER_EMAIL());
        values.put(COL_ADDRESS,user.getUSER_ADDRESS());


        db.insertOrThrow(USERS_TABLE, null, values);
    }
    public void addUserID(int id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_ID,id);
         db.insert(ORDERS_TABLE,null,values);

    }
    public void addToItems(String orderID, String itemID){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ORDER_ID,orderID);
        values.put(COL_ITEM_ID,itemID);
        db.insert(ORDER_ITEM_TABLE,null,values);
    }









    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(INVENTORY_TABLE, INVENTORY_COLUMNS, null, null, null, null, null, null);
        return cursor;

    }

    // These are my sort by queries
    public List<Shoes> sortNew() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Shoes> shoes = new ArrayList<>();
        Shoes shoeList = null;

        Cursor cursor = db.query(INVENTORY_TABLE, INVENTORY_COLUMNS, null, null, null, null, "ID DESC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(DBhelper.getColShoeName());
                int priceIndex = cursor.getColumnIndex(DBhelper.getColShoePrice());
                int typeIndex = cursor.getColumnIndex(DBhelper.getColShoeType());
                int desIndex = cursor.getColumnIndex(DBhelper.getColDescription());
                int stockIndex = cursor.getColumnIndex(DBhelper.getColInstock());
                int imageIndex = cursor.getColumnIndex(DBhelper.getColShoeImage());
                int shoeID = cursor.getColumnIndex(DBhelper.getColId());
                // this will store the string
                String name = cursor.getString(nameIndex);
                String price = cursor.getString(priceIndex);
                String type = cursor.getString(typeIndex);
                String description = cursor.getString(desIndex);
                String image = cursor.getString(imageIndex);
                String inStock = cursor.getString(stockIndex);
                String shoeId = cursor.getString(shoeID);
                // this adds the strings into my Shoe object to transfer to my adapter
                shoeList = new Shoes();
                shoeList.setSHOE_ID(shoeId);
                shoeList.setSHOE_NAME(name);
                shoeList.setSHOE_PRICE(price);
                shoeList.setSHOE_TYPE(type);
                shoeList.setSHOE_DESCRIPTION(description);
                shoeList.setSHOE_IMAGE(image);
                shoeList.setSHOE_INSTOCK(inStock);
                shoes.add(shoeList);

            }


        }

        return shoes;

    }

    public List<Shoes> sortFire() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Shoes> shoes = new ArrayList<>();
        Shoes shoeList = null;

        Cursor cursor = db.query(INVENTORY_TABLE, INVENTORY_COLUMNS, null, null, null, null, "InStock ASC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(DBhelper.getColShoeName());
                int priceIndex = cursor.getColumnIndex(DBhelper.getColShoePrice());
                int typeIndex = cursor.getColumnIndex(DBhelper.getColShoeType());
                int desIndex = cursor.getColumnIndex(DBhelper.getColDescription());
                int stockIndex = cursor.getColumnIndex(DBhelper.getColInstock());
                int imageIndex = cursor.getColumnIndex(DBhelper.getColShoeImage());
                int shoeID = cursor.getColumnIndex(DBhelper.getColId());
                // this will store the string
                String name = cursor.getString(nameIndex);
                String price = cursor.getString(priceIndex);
                String type = cursor.getString(typeIndex);
                String description = cursor.getString(desIndex);
                String image = cursor.getString(imageIndex);
                String inStock = cursor.getString(stockIndex);
                String shoeId = cursor.getString(shoeID);
                // this adds the strings into my Shoe object to transfer to my adapter
                shoeList = new Shoes();
                shoeList.setSHOE_ID(shoeId);
                shoeList = new Shoes();
                shoeList.setSHOE_NAME(name);
                shoeList.setSHOE_PRICE(price);
                shoeList.setSHOE_TYPE(type);
                shoeList.setSHOE_DESCRIPTION(description);
                shoeList.setSHOE_IMAGE(image);
                shoeList.setSHOE_INSTOCK(inStock);
                shoes.add(shoeList);

            }


        }

        return shoes;

    }

    public List<Shoes> sortPriceLH() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Shoes> shoes = new ArrayList<>();
        Shoes shoeList = null;

        Cursor cursor = db.query(INVENTORY_TABLE, INVENTORY_COLUMNS, null, null, null, null, "Price ASC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(DBhelper.getColShoeName());
                int priceIndex = cursor.getColumnIndex(DBhelper.getColShoePrice());
                int typeIndex = cursor.getColumnIndex(DBhelper.getColShoeType());
                int desIndex = cursor.getColumnIndex(DBhelper.getColDescription());
                int stockIndex = cursor.getColumnIndex(DBhelper.getColInstock());
                int imageIndex = cursor.getColumnIndex(DBhelper.getColShoeImage());
                int shoeID = cursor.getColumnIndex(DBhelper.getColId());
                // this will store the string
                String name = cursor.getString(nameIndex);
                String price = cursor.getString(priceIndex);
                String type = cursor.getString(typeIndex);
                String description = cursor.getString(desIndex);
                String image = cursor.getString(imageIndex);
                String inStock = cursor.getString(stockIndex);
                String shoeId = cursor.getString(shoeID);
                // this adds the strings into my Shoe object to transfer to my adapter
                shoeList = new Shoes();
                shoeList.setSHOE_ID(shoeId);
                shoeList.setSHOE_NAME(name);
                shoeList.setSHOE_PRICE(price);
                shoeList.setSHOE_TYPE(type);
                shoeList.setSHOE_DESCRIPTION(description);
                shoeList.setSHOE_IMAGE(image);
                shoeList.setSHOE_INSTOCK(inStock);
                shoes.add(shoeList);

            }
        }
        return shoes;
    }

    public List<Shoes> sortPriceHL() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Shoes> shoes = new ArrayList<>();
        Shoes shoeList = null;

        Cursor cursor = db.query(INVENTORY_TABLE, INVENTORY_COLUMNS, null, null, null, null, "Price DESC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(DBhelper.getColShoeName());
                int priceIndex = cursor.getColumnIndex(DBhelper.getColShoePrice());
                int typeIndex = cursor.getColumnIndex(DBhelper.getColShoeType());
                int desIndex = cursor.getColumnIndex(DBhelper.getColDescription());
                int stockIndex = cursor.getColumnIndex(DBhelper.getColInstock());
                int imageIndex = cursor.getColumnIndex(DBhelper.getColShoeImage());
                int shoeID = cursor.getColumnIndex(DBhelper.getColId());
                // this will store the string
                String name = cursor.getString(nameIndex);
                String price = cursor.getString(priceIndex);
                String type = cursor.getString(typeIndex);
                String description = cursor.getString(desIndex);
                String image = cursor.getString(imageIndex);
                String inStock = cursor.getString(stockIndex);
                String shoeId = cursor.getString(shoeID);
                // this adds the strings into my Shoe object to transfer to my adapter
                shoeList = new Shoes();
                shoeList.setSHOE_ID(shoeId);
                shoeList.setSHOE_NAME(name);
                shoeList.setSHOE_PRICE(price);
                shoeList.setSHOE_TYPE(type);
                shoeList.setSHOE_DESCRIPTION(description);
                shoeList.setSHOE_IMAGE(image);
                shoeList.setSHOE_INSTOCK(inStock);
                shoes.add(shoeList);

            }
        }
        return shoes;
    }

    // This is the Search query
    public List<Shoes> searchItem(String query) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Shoes> shoes = new ArrayList<>();
        Shoes shoeList;

        Cursor cursor = db.query(INVENTORY_TABLE, // a. table
                INVENTORY_COLUMNS, // b. column names
                COL_SHOE_NAME + " LIKE ? OR " + COL_SHOE_TYPE + " LIKE ? OR "+ COL_SHOE_PRICE+" LIKE ?", // c. selections
                new String[]{"%" + query + "%", "%" + query + "%","%" + query + "%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null) {
            while (cursor.moveToNext()) {


                // this will store the string
                int nameIndex = cursor.getColumnIndex(DBhelper.getColShoeName());
                int priceIndex = cursor.getColumnIndex(DBhelper.getColShoePrice());
                int typeIndex = cursor.getColumnIndex(DBhelper.getColShoeType());
                int desIndex = cursor.getColumnIndex(DBhelper.getColDescription());
                int stockIndex = cursor.getColumnIndex(DBhelper.getColInstock());
                int imageIndex = cursor.getColumnIndex(DBhelper.getColShoeImage());
                int shoeID = cursor.getColumnIndex(DBhelper.getColId());
                // this will store the string
                String name = cursor.getString(nameIndex);
                String price = cursor.getString(priceIndex);
                String type = cursor.getString(typeIndex);
                String description = cursor.getString(desIndex);
                String image = cursor.getString(imageIndex);
                String inStock = cursor.getString(stockIndex);
                String shoeId = cursor.getString(shoeID);
                // this adds the strings into my Shoe object to transfer to my adapter
                shoeList = new Shoes();
                shoeList.setSHOE_ID(shoeId);
                shoeList.setSHOE_NAME(name);
                shoeList.setSHOE_PRICE(price);
                shoeList.setSHOE_TYPE(type);
                shoeList.setSHOE_DESCRIPTION(description);
                shoeList.setSHOE_IMAGE(image);
                shoeList.setSHOE_INSTOCK(inStock);
                shoes.add(shoeList);

            }
        }
        return shoes;
    }

    // This is the query to check if a users Email Address is in use or not
    public boolean verification(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT 1 FROM "+USERS_TABLE+" WHERE "+COL_EMAIL+"=?", new String[] {email});
        boolean exists = c.moveToFirst();
        c.close();
        return exists;
    }

    // This method retrives the user information based on the email
    public List<Users> getUser(String Email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE "+COL_EMAIL+"=?",new String[]{Email});
        List<Users> userInfo = new ArrayList<>();
        Users user = null;

        if (cursor != null) {
            while (cursor.moveToNext()) {


                // this will store the string
                int firstIndex = cursor.getColumnIndex(DBhelper.getColFirstName());
                int lastIndex = cursor.getColumnIndex(DBhelper.getColLastName());
                int emailIndex = cursor.getColumnIndex(DBhelper.getColEmail());
                int addressIndex = cursor.getColumnIndex(DBhelper.getColAddress());

                int userID = cursor.getColumnIndex(DBhelper.getColId());
                // this will store the string
                String first = cursor.getString(firstIndex);
                String last = cursor.getString(lastIndex);
                String email = cursor.getString(emailIndex);
                String address = cursor.getString(addressIndex);
                String userId = cursor.getString(userID);
                // this adds the strings into my Shoe object to transfer to my adapter
                user= new Users();
                user.setUSER_ID(userId);
                user.setUSER_FIRST_NAME(first);
                user.setUSER_LAST_NAME(last);
                user.setUSER_EMAIL(email);
                user.setUSER_ADDRESS(address);

                userInfo.add(user);

            }
        }
        return userInfo;
    }



    // These are my get Methods for my columns
    public static String getColShoeName() {
        return COL_SHOE_NAME;
    }

    public static String getColShoePrice() {
        return COL_SHOE_PRICE;
    }

    public static String getColShoeType() {
        return COL_SHOE_TYPE;
    }

    public static String getColDescription() {
        return COL_DESCRIPTION;
    }

    public static String getColInstock() {
        return COL_INSTOCK;
    }

    public static String getColShoeImage() {
        return COL_SHOE_IMAGE;
    }

    public static String getColId() {
        return COL_ID;
    }

    public static String getColFirstName() {
        return COL_FIRST_NAME;
    }

    public static String getColLastName() {
        return COL_LAST_NAME;
    }

    public static String getColEmail() {
        return COL_EMAIL;
    }

    public static String getColAddress() {
        return COL_ADDRESS;
    }
}
