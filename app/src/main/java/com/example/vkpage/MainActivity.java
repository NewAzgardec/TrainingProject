package com.example.vkpage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.vkpage.headerView.HeaderView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] scope = new String[]{VKScope.FRIENDS, VKScope.WALL, VKScope.AUDIO, VKScope.PHOTOS, VKScope.STATUS, VKScope.OFFLINE};
    private DrawerLayout drawer;
    private Switch mySwitch;
    private Toolbar toolbar;
    private String TAG = "TAG";

    SharedPref sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = new SharedPref(this);
        if (sharedPreferences.loadNightMode()) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!VKSdk.isLoggedIn()) {
            VKSdk.login(this, scope);
        } else {
            Log.i(TAG, "Authorized");
        }

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(navigationListener);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final HeaderView headerView = navigationView.getHeaderView(0).findViewById(R.id.header_view);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color = generateColor();
                headerView.updateImage(color);
            }
        });

        RelativeLayout relativeLayout = findViewById(R.id.first_fragment);
        MenuItem menuItem = navigationView.getMenu().findItem(R.id.navigation_settings);
        mySwitch = menuItem.getActionView().findViewById(R.id.switch_dark);
        if (sharedPreferences.loadNightMode()) {
            mySwitch.setChecked(true);
        }
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPreferences.setNightMode(true);
                    recreate();

                } else {
                    sharedPreferences.setNightMode(false);
                    recreate();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {

            @Override
            public void onResult(VKAccessToken res) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                finish();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    protected NavigationView.OnNavigationItemSelectedListener navigationListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.navigation_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new ProfileFragment()).commit();
                    break;

                case R.id.navigation_friends:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new FriendsFragment()).commit();
                    break;

                case R.id.navigation_gallery:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new GalleryFragment()).commit();
                    break;

                default:
                    break;
            }

            drawer.closeDrawer(GravityCompat.START);

            return true;
        }
    };

    private String generateColor() {
        Random random = new Random();
        int colorCode = random.nextInt(0xffffff + 1);
        String color = String.format("#%06x", colorCode);

        return color;
    }
}
