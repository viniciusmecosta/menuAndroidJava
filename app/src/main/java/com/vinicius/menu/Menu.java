package com.vinicius.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.vinicius.menu.Fragments.DessertFragment;
import com.vinicius.menu.Fragments.DrinksFragment;
import com.vinicius.menu.Fragments.MainCourseFragment;
import com.vinicius.menu.Fragments.StarterFragment;

public class Menu extends AppCompatActivity {
    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = findViewById(R.id.action_Bar);
        setSupportActionBar(toolbar);

        smartTabLayout = findViewById(R.id.menu_Tab_Pager);
        viewPager = findViewById(R.id.menu_Pager);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.starter_Page, StarterFragment.class)
                .add(R.string.maincourse_Page, MainCourseFragment.class)
                .add(R.string.drinks_Page, DrinksFragment.class)
                .add(R.string.dessert_Page, DessertFragment.class)
                .create());
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);



    }
}