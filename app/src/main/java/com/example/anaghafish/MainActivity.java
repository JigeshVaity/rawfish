package com.example.anaghafish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //meow bottom navigation
    MeowBottomNavigation bottomNavigation;
    // RelativeLayout home_layout,category_layout,cart_layout,profile_layout;
    //horizontal slider
    private ViewPager viewPager; // Move the initialization here
    private int currentPage = 0;
    private Timer timer;
    private final long delay = 5000; // 5 seconds delay


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //meow bottom navigation
        bottomNavigation = findViewById(R.id.bottomNavigation);

        replaceFragment(new HomeFragment());//for default page
        //navigation icon
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.category));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.baseline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.baseline_person_24));

        meownavigation();// calling meow function
        bottomNavigation.show(1, true);// 1 is the ID of the Home item
        replaceFragment(new HomeFragment());//for default page

        //slider



    }//----------on create end---------

    //meow function
    private void meownavigation(){
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model)
            {

                switch (model.getId()){

                    case 1:
                        replaceFragment(new HomeFragment());
                        break;
                    case 2:
                        replaceFragment(new CategoryFragment());
                        break;
                    case 3:
                        replaceFragment(new CartFragment());
                        break;
                    case 4:
                        replaceFragment(new ProfileFragment());
                        break;

                }
                return null;
            }
        });
    }
    private void replaceFragment(Fragment fragment) {
        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a transaction to replace the current fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Replace the current fragment with the new one
        transaction.replace(R.id.fragment_container, fragment);

        // Commit the transaction
        transaction.commit();
    }
    //ending meow

}