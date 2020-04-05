package com.example.multi_comm.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.multi_comm.R;
import com.example.multi_comm.data.DataModel;
import com.example.multi_comm.data.DrawerItemCustomAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity   {
    private RecyclerView mainRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    FloatingActionButton streamFabBtn ;
    private CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;
    Fragment streamFragment = null;
    BottomNavigationView bottomNavigationView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(R.layout.activity_main);
        streamFabBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

//        Toast.makeText(this, "This is my Toast message!--"+bottomNavigationView,
//                Toast.LENGTH_LONG).show();

        mainRecyclerView = (RecyclerView) findViewById(R.id.main_rv);
        mainRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
//        JSONObject obj=new JSONObject();
        String[] data = {"data-1", "data-2", "data-3", "data-4", "data-5", "data-6"};
        mAdapter = new MainActivityAdapter(data);
        mainRecyclerView.setAdapter(mAdapter);

        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        DrawerItemClickListener drawer = new DrawerItemClickListener();
        drawer.setupToolbar();
        DataModel[] drawerItem = new DataModel[7];
        drawerItem[0] = new DataModel(R.drawable.ic_home_36pt, "Home");
        drawerItem[1] = new DataModel(R.drawable.ic_sd_storage_36pt, "Local");
        drawerItem[2] = new DataModel(R.drawable.ic_videocam_36pt, "Reprter Videos");
        drawerItem[3] = new DataModel(R.drawable.ic_mail_36pt, "Invite");
        drawerItem[4] = new DataModel(R.drawable.ic_group_add_36pt, "Join");
        drawerItem[5] = new DataModel(R.drawable.ic_add_box_36pt, "Advertisement");
        drawerItem[6] = new DataModel(R.drawable.ic_settings_36pt, "Setting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        drawer.setupDrawerToggle();
        if(savedInstanceState==null){
           drawer.selectItem(0);
        }
        streamFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new StreamFragment();
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


                    getSupportActionBar().setTitle("Live Streaming");


                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }
            }
        });



//        bottomNavigationView.setSelectedItemId(0);
        bottomNavigationView.animate();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment;
                Toast.makeText(getBaseContext(), "This is my Toast message!--"+(item.getItemId()==R.id.action_fav),
                        Toast.LENGTH_LONG).show();

                if(item.getItemId()==R.id.action_home){

                    bottomNavigationView.getMenu().getItem(0).setChecked(true);

                    fragment = new HomeFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                    getSupportActionBar().setTitle("Home");

                }

                if(item.getItemId()==R.id.action_fav){
                    bottomNavigationView.getMenu().getItem(1).setChecked(true);
                    fragment = new FavFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                    getSupportActionBar().setTitle("Favourite");

                }

                if(item.getItemId()==R.id.action_myvideo){
                    bottomNavigationView.getMenu().getItem(2).setChecked(true);
                    fragment = new MyVideoFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                    getSupportActionBar().setTitle("My Video");

                }

                return true;
            }
        });
    }





    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
        else{
//            showSystemUI();
        }
    }


    private  class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }


        private void selectItem(int position) {
            bottomNavigationView.getMenu().getItem(getCheckedItem(bottomNavigationView)).setChecked(false);
            Fragment fragment = null;

            switch (position) {
                case 0:
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                    fragment = new HomeFragment();
                    break;
                case 1:


                    fragment = new LocalVideoFragment();
                    break;
                case 2:

                    fragment = new ReporterVideoFragment();
                    break;
                case 3:


                    fragment = new InviteFragment();
                    break;
                case 4:

                    fragment = new JoinFragment();
                    break;
                case 5:

                    fragment = new AdvertisementFragment();
                    break;
                case 6:

                    fragment = new SettingFragment();
                    break;


                default:
                    break;
            }

            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                mDrawerList.setItemChecked(position, true);
                mDrawerList.setSelection(position);
                setTitle(mNavigationDrawerItemTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);

            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }
        }



        public void setTitle(CharSequence title) {
            mTitle = title;
            getSupportActionBar().setTitle(mTitle);
        }


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        void  setupToolbar() {
            toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        void setupDrawerToggle() {
            mDrawerToggle = new ActionBarDrawerToggle(getParent(), mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
            //This is necessary to change the icon of the Drawer Toggle upon state change.
            mDrawerToggle.syncState();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {

            fragment = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            return true;
        }
        if (id == R.id.action_fav) {
//            change the api same ui
            fragment = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            return true;
        }
        if (id == R.id.action_myvideo) {
            //            change the api same ui
            fragment = new HomeFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            return true;
        }
        Toast.makeText(this,id,Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

//                View.SYSTEM_UI_FLAG_IMMERSIVE
//                        // Set the content to appear under the system bars so that the
//                        // content doesn't resize when the system bars hide and show.
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


    private int getCheckedItem(BottomNavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.isChecked()) {
                return i;
            }
        }

        return -1;
    }

}