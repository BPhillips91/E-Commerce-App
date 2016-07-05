package generalassemb.ly.kikz;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by brendan on 6/27/16.
 */
public class DBAssetHelper extends SQLiteAssetHelper {
        private static final String DATABASE_NAME="KikzDB";
    private static final int DATABASE_VERSION=1;
    public DBAssetHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
}
