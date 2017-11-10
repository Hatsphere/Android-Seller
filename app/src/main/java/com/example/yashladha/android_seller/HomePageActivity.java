package com.example.yashladha.android_seller;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
//import android.widget.Toolbar;

import com.example.yashladha.android_seller.classes.SimpleFragmentPagerAdapter;
import com.example.yashladha.android_seller.navigation.AboutUsFragment;
import com.example.yashladha.android_seller.navigation.HelpFragment;
import com.example.yashladha.android_seller.fragments.DisplayFrag;
import com.example.yashladha.android_seller.navigation.FAQsFragment;
import com.example.yashladha.android_seller.navigation.MyAccountFragment;


public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /*private int[] icons = {
            R.drawable.display_icon,
            R.drawable.sales_analysis_icon,
            R.drawable.orders_icon
    };*/

    TabLayout tabLayout;
    ViewPager viewPager;
    private String mActivityTitle;
    //FloatingActionButton fabAdd;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mActivityTitle = getTitle().toString();

        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        SimpleFragmentPagerAdapter simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(simpleFragmentPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(viewPager);
        //fabAdd = (FloatingActionButton) findViewById(R.id.fabAddProduct);
        /*for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(icons[i]);
        }*/

        //Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_drawer);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setVerticalScrollBarEnabled(true);
        setupDrawer();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        /*fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePageActivity.this, AddProductsActivity.class);
                startActivity(i);
            }
        });*/
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("HatSphere");

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
/*        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        */

        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings || mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if (id == R.drawable.ic_drawer) {
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.itMyAccount) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MyAccountFragment()).commit();

        } else if (id == R.id.itHelp) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HelpFragment()).commit();

        } else if (id == R.id.itAboutUs) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new AboutUsFragment()).commit();

        } else if (id == R.id.itFaq) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FAQsFragment()).commit();
        } else if (id == R.id.itAddProduct) {
            Intent i = new Intent(HomePageActivity.this, AddProductsActivity.class);
            startActivity(i);
        } else if (id == R.id.itLogOut) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Log Out")
                    .setMessage("Do you really want to log out?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logOut();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DisplayFrag()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void logOut() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(HomePageActivity.this, LoginActivity.class);
        startActivity(i);
        finish();

    }
}
