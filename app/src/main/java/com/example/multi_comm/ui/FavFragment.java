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

import java.util.List;


public class FavFragment extends Fragment {
    private RecyclerView favRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FavRecyclerViewAdapter favAdapter;

    public FavFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // keep the fragment and all its data across screen rotation
        setRetainInstance(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View favView =  inflater.inflate(R.layout.fragment_fav, container, false);
        favRecyclerView = (RecyclerView) favView.findViewById(R.id.fav_rv);
        layoutManager = new LinearLayoutManager(favView.getContext());
        favRecyclerView.setLayoutManager(layoutManager);
//        JSONObject obj=new JSONObject();
        String[] data = {"data-1", "data-2", "data-3", "data-4", "data-5", "data-6"};
        favAdapter = new FavRecyclerViewAdapter(data);
        favRecyclerView.setAdapter(favAdapter);
        // Inflate the layout for this fragment
        return favView;


    }




}
