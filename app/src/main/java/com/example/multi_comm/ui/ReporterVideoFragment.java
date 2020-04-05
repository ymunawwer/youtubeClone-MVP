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


public class ReporterVideoFragment extends Fragment {
    private RecyclerView reporterVideoRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FavRecyclerViewAdapter reporterVideoAdapter;


    public ReporterVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View reporterVideoView =  inflater.inflate(R.layout.fragment_fav, container, false);
        reporterVideoRecyclerView = (RecyclerView) reporterVideoView.findViewById(R.id.fav_rv);
        layoutManager = new LinearLayoutManager(reporterVideoView.getContext());
        reporterVideoRecyclerView.setLayoutManager(layoutManager);
//        JSONObject obj=new JSONObject();
        String[] data = {"data-1", "data-2", "data-3", "data-4", "data-5", "data-6"};
        reporterVideoAdapter = new FavRecyclerViewAdapter(data);
        reporterVideoRecyclerView.setAdapter(reporterVideoAdapter);
        // Inflate the layout for this fragment
        return reporterVideoView;
    }


}
