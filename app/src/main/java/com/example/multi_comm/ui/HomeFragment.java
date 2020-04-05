package com.example.multi_comm.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.multi_comm.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {
    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_connect, container, false);
        RecyclerView mainRecyclerView = (RecyclerView) rootView.findViewById(R.id.main_rv);
        mainRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mainRecyclerView.setLayoutManager(layoutManager);
//        JSONObject obj=new JSONObject();
        String[] data2 = {"data-1", "data-2", "data-3", "data-4", "data-5", "data-6"};
        MainActivityAdapter mAdapter = new MainActivityAdapter(data2);
        mainRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
