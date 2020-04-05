package com.example.multi_comm.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.multi_comm.R;




public class LocalVideoFragment extends Fragment {
    private RecyclerView localVideoRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FavRecyclerViewAdapter localVideoAdapter;


    public LocalVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            View localVideoView =  inflater.inflate(R.layout.fragment_fav, container, false);
        localVideoRecyclerView = (RecyclerView) localVideoView.findViewById(R.id.fav_rv);
            layoutManager = new LinearLayoutManager(localVideoView.getContext());
        localVideoRecyclerView.setLayoutManager(layoutManager);
//        JSONObject obj=new JSONObject();
            String[] data = {"data-1", "data-2", "data-3", "data-4", "data-5", "data-6"};
        localVideoAdapter = new FavRecyclerViewAdapter(data);
        localVideoRecyclerView.setAdapter(localVideoAdapter);
            // Inflate the layout for this fragment
            return localVideoView;
    }


}
