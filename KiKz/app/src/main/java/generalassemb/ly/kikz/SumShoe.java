package generalassemb.ly.kikz;

import java.util.ArrayList;

/**
 * Created by brendan on 6/28/16.
 */
public class SumShoe {
    private static SumShoe ourInstance = new SumShoe();
    private static ArrayList<Shoes> mShoe;


    public static SumShoe getInstance() {
        if(ourInstance == null)
            ourInstance= new SumShoe();

        return ourInstance;
    }

    private SumShoe() {
        mShoe=new ArrayList<>();
    }
    public void addShoe(Shoes shoe){
        mShoe.add(shoe);
    }
    public ArrayList<Shoes>getShoe(){
        return mShoe;
    }
}
