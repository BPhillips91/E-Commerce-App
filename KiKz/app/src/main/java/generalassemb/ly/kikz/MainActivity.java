package generalassemb.ly.kikz;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // define my variables
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setting up the database in the asset folder
        DBAssetHelper dbAssetHelper = new DBAssetHelper(MainActivity.this);
        dbAssetHelper.getReadableDatabase();

        // Calls the method to initialize and  populate my RecyclerView
        setRecycler();

        handleIntent(getIntent());

        fab = (FloatingActionButton) findViewById(R.id.fab);

        // Adding a scroll listener to the recylerview and adding animation to the FAB
        // to disappear while user is scrolling

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        // this will start the shopping cart activity
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    // method to populate my Recyclerview with values from the Shoe Table
    public List<Shoes> getShoes() {

        List<Shoes> shoes = new ArrayList<>();
        Shoes shoeList = null;
        DBhelper helper = new DBhelper(MainActivity.this);
        Cursor cursor = helper.getAll();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // this gets the index for each item
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


    // this method sets the RecyclerView, LayoutManager, and Adapter
    public void setRecycler() {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerAdapter(getShoes(), this);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Setting up search functionality
        getMenuInflater().inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.action_search),
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        Log.d("TAG", "this was run when we closed it");


                        setRecycler();

                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        Log.d("TAG", "this was run when we opened it");
                        return true;
                    }

                });
        return true;
    }

    //
    private void handleIntent(Intent intent) {
        Log.d("CHECK INTENT", "THE INTENT IS :" + intent);
        List<Shoes> newList = new ArrayList<>();
        mAdapter = new RecyclerAdapter(newList, MainActivity.this);

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            DBhelper helper = new DBhelper(MainActivity.this);
            mAdapter = new RecyclerAdapter(helper.searchItem(query), this);
            mRecyclerView.setAdapter(mAdapter);
        }

    }


    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_search) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.fresh_in) {
            DBhelper helper = new DBhelper(MainActivity.this);
            mAdapter = new RecyclerAdapter(helper.sortNew(), this);
            mRecyclerView.setAdapter(mAdapter);


        } else if (id == R.id.top_seller) {
            DBhelper helper = new DBhelper(MainActivity.this);
            mAdapter = new RecyclerAdapter(helper.sortFire(), this);
            mRecyclerView.setAdapter(mAdapter);


        } else if (id == R.id.prices) {
            DBhelper helper = new DBhelper(MainActivity.this);
            mAdapter = new RecyclerAdapter(helper.sortPriceLH(), this);
            mRecyclerView.setAdapter(mAdapter);

        } else if (id == R.id.prices_high) {
            DBhelper helper = new DBhelper(MainActivity.this);
            mAdapter = new RecyclerAdapter(helper.sortPriceHL(), this);
            mRecyclerView.setAdapter(mAdapter);
        }
        // TODO Set up sharable intent
        else if (id == R.id.nav_bookface) {
            Toast.makeText(this, "This is currently under construction. Try again later.", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_twatter) {
            Toast.makeText(this, "This is currently under construction. Try again later.", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // These two overirde methods will hide and show the floatingActionButton when the MainActivity
    // in the background
    @Override
    protected void onPause() {
        super.onPause();
        fab.hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.show();
    }
}

