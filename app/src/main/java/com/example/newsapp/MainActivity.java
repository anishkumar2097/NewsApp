package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.newsapp.utils.Constants;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = "MainActivity";
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private TabLayout mTabLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Find the view pager that will allow the user to swipe between fragments
        //  mViewPager=findViewById(R.id.view_pager);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                Log.v(TAG, "drawer is slide");

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
           CategoryFragmentPagerAdapter fragmentAdapter=new CategoryFragmentPagerAdapter(getSupportFragmentManager(),this);
           mViewPager.setAdapter(fragmentAdapter);

    }


    public void onBackPressed() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

   @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
  //      int id = item.getItemId();

        // Switch Fragments in a ViewPager on clicking items in Navigation Drawer
   //     if (id == R.id.nav_home) {
  //          mViewPager.setCurrentItem(Constants.HOME);}
     //   } else if (id == R.id.nav_world) {
    //        mViewPager.setCurrentItem(Constants.WORLD);
  //      } else if (id == R.id.nav_science) {
    //        mViewPager.setCurrentItem(Constants.SCIENCE);
      //  } else if (id == R.id.nav_sport) {
        //    mViewPager.setCurrentItem(Constants.SPORT);
    //    } else if (id == R.id.nav_environment) {
      //      mViewPager.setCurrentItem(Constants.ENVIRONMENT);
       // } else if (id == R.id.nav_society) {
         //   mViewPager.setCurrentItem(Constants.SOCIETY);
      //  } else if (id == R.id.nav_fashion) {
        //    mViewPager.setCurrentItem(Constants.FASHION);
     //  }
    //    else if (id == R.id.nav_business) {
  //          mViewPager.setCurrentItem(Constants.BUSINESS);
   //     } // else if (id == R.id.nav_culture) {
           // mViewPager.setCurrentItem(Constants.CULTURE);
        //}
       Toast.makeText(getApplicationContext(),"sending intent",Toast.LENGTH_SHORT).show();
       DrawerLayout drawer = findViewById(R.id.drawer_layout);
       drawer.closeDrawer(GravityCompat.START);
        return true;
    }

 //   @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
  //      getMenuInflater().inflate(R.menu.main,menu);
 //       return super.onCreateOptionsMenu(menu);
    }

//  @Override
// public boolean onOptionsItemSelected(@NonNull MenuItem item) {
  //    int id =item.getItemId();
    //    if(id==R.id.action_settings){
      //     Intent Setting_intent=new Intent(this,Setting_Activity.class);
      //startActivity(Setting_intent);
      //      return true;
       // }
      //     return super.onOptionsItemSelected(item);
   // }
