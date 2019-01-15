package com.example.bilal.opdracht6_recipe;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    Api service;
    List<Recipe> recipes = new ArrayList<>();

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = Api.retrofit.create(Api.class);
        requestData();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    public static class PlaceholderFragment extends Fragment {

        @BindView(R.id.text_recipe)
        TextView textRecipe;

        @BindView(R.id.image_recipe)
        ImageView imageRecipe;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {

        }


        public static PlaceholderFragment newInstance(String title, String imgUrl) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString("title",title);
            args.putString("img",imgUrl);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
            ButterKnife.bind(this, rootView);
            textRecipe.setText(getArguments().getString("title"));
            Glide.with(this).load(getArguments().getString("img")).into(imageRecipe);

            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            Recipe r = recipes.get(position);
            return PlaceholderFragment.newInstance(r.getTitle(),r.getImageUrl());
        }

        @Override
        public int getCount() {
            return recipes.size();
        }
    }

    private void addRecipes(List<Recipe> recipes){
        this.recipes = recipes;
        mSectionsPagerAdapter.notifyDataSetChanged();

    }

    private void requestData() {
        Call<ListRecipe> call = service.getdata();
        call.enqueue(new Callback<ListRecipe>() {
            @Override
            public void onResponse(Call<ListRecipe> call, Response<ListRecipe> response) {
                ListRecipe recipes = response.body();
                addRecipes(recipes.getRecipes());


            }

            @Override
            public void onFailure(Call<ListRecipe> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong, try again later or restart the app", '3');
            }
        });

    }
}