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


public class MyVideoFragment extends Fragment {

    private RecyclerView myVideoRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FavRecyclerViewAdapter myVideoAdapter;


    public MyVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myVideoView =  inflater.inflate(R.layout.fragment_fav, container, false);
        myVideoRecyclerView = (RecyclerView) myVideoView.findViewById(R.id.fav_rv);
        layoutManager = new LinearLayoutManager(myVideoView.getContext());
        myVideoRecyclerView.setLayoutManager(layoutManager);
//        JSONObject obj=new JSONObject();
        String[] data = {"data-1", "data-2", "data-3", "data-4", "data-5", "data-6"};
        myVideoAdapter = new FavRecyclerViewAdapter(data);
        myVideoRecyclerView.setAdapter(myVideoAdapter);
        // Inflate the layout for this fragment
        return myVideoView;
    }


}
