package com.example.anaghafish;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    //--------------------start of view pager -----------------------
    private ViewPager viewPager;
    private int currentPage = 0;
    private Timer timer;
    final long delay = 5000; // 5 seconds delay between auto-scroll

    //--------------------end of view pager -----------------------
    //recyclerview
    private RecyclerView recyclerView;
    private FishProductAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //recyclerview
        recyclerView = view.findViewById(R.id.rawFishRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Create a list of FishProduct objects
        List<FishProduct> fishProducts = new ArrayList<>();
        fishProducts.add(new FishProduct("Ravas", 900, R.drawable.fish1));
        fishProducts.add(new FishProduct("Surmai", 900, R.drawable.fish2));
        fishProducts.add(new FishProduct("Bombill", 300, R.drawable.fish3));
        fishProducts.add(new FishProduct("Prawn", 500, R.drawable.fish4));
        fishProducts.add(new FishProduct("Paplet", 400, R.drawable.raw));
        fishProducts.add(new FishProduct("fish", 350, R.drawable.driedfish));
        fishProducts.add(new FishProduct("fish2", 450, R.drawable.dish));
        // Add more fish products as needed

        // Initialize the adapter with the list of fish products
        adapter = new FishProductAdapter(fishProducts);
        recyclerView.setAdapter(adapter);

        //-------------------------start of view pager code ---------------------------
        // Initialize the ViewPager
        viewPager = view.findViewById(R.id.adv);
        AdvertisementAdapter viewPagerAdapter = new AdvertisementAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);

        // Set up a timer to auto-scroll the ViewPager
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == viewPagerAdapter.getCount()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, delay, delay);

        return view;
    }

    //-------------------------end of view pager code ---------------------------


}
