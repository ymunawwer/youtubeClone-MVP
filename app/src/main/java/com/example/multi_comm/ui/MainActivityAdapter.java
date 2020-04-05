package com.example.multi_comm.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.multi_comm.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.text.PrecomputedTextCompat;
import androidx.recyclerview.widget.RecyclerView;

import static android.app.PendingIntent.getActivity;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> implements ExoPlayer.EventListener {

//    JSONObject data;
    String[] data;
    Context context;
    boolean isFullscreen;
    private SimpleExoPlayer player;
    public MainActivityAdapter(String[] data) {
        this.data = data;


    }

    @NonNull
    @Override
    public MainActivityAdapter.MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_ui,parent,false);
        this.player = ExoPlayerFactory.newSimpleInstance(parent.getContext());
        this.context = parent.getContext();
        MainActivityViewHolder vh = new MainActivityViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainActivityViewHolder holder, int position) {
//        holder.mVideoView.setVideoPath("https://law.duke.edu/cspd/contest/videos/Framed-Contest_Documentaries-and-You.mp4");
////        holder.mVideoView.start();
//        holder.mMediaController.setTitle("Big Buck Bunny");
//
//        holder.mMediaController.showError();
//        final int mSeekPosition = holder.mVideoView.getCurrentPosition();
        holder.moreMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(v.getContext(),"Hello Javatpoint",Toast. LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();


            }
        });

//        holder.mStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if (mSeekPosition > 0) {
////                    holder.mVideoView.seekTo(mSeekPosition);
////                }
////                holder.mVideoView.start();
////                holder.mMediaController.setTitle("Big Buck Bunny");
//
//                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
//                        holder.playerView.getLayoutParams();
//                params.width=params.MATCH_PARENT;
//                params.height=params.MATCH_PARENT;
//                holder.playerView.setLayoutParams(params);
//            }
//        });
//
//
//        holder.mVideoView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
//            private int cachedHeight;
//            private boolean isFullscreen;
//
//            @Override
//            public void onScaleChange(boolean isFullscreen) {
//                this.isFullscreen = isFullscreen;
//
//                if (isFullscreen) {
//                    Log.e(TAG, "onScaleChange: "+this.isFullscreen );
//                    ViewGroup.LayoutParams layoutParams = holder.mVideoLayout.getLayoutParams();
//                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//                    holder.mVideoLayout.setLayoutParams(layoutParams);
//                    //GONE the unconcerned views to leave room for video and controller
////                    holder.mBottomLayout.setVisibility(View.GONE);
//                } else {
//                    Log.e(TAG, "onScaleChange: "+this.isFullscreen );
//                    ViewGroup.LayoutParams layoutParams = holder.mVideoLayout.getLayoutParams();
//                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                    layoutParams.height = this.cachedHeight;
//                    holder.mVideoLayout.setLayoutParams(layoutParams);
//                    holder.mBottomLayout.setVisibility(View.VISIBLE);
//                }
////                switchTitleBar(!this.isFullscreen);
//            }
//
//            @Override
//            public void onPause(MediaPlayer mediaPlayer) {
//                Log.d(TAG, "onPause UniversalVideoView callback");
//            }
//
//            @Override
//            public void onStart(MediaPlayer mediaPlayer) {
//                Log.d(TAG, "onStart UniversalVideoView callback");
//            }
//
//            @Override
//            public void onBufferingStart(MediaPlayer mediaPlayer) {
//                Log.d(TAG, "onBufferingStart UniversalVideoView callback");
//            }
//
//            @Override
//            public void onBufferingEnd(MediaPlayer mediaPlayer) {
//                Log.d(TAG, "onBufferingEnd UniversalVideoView callback");
//            }
//
//
//        });

      ;


        holder.playerView.setPlayer(this.player);
//        holder.playerView.set
//        holder.playerView.sett
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this.context,
                Util.getUserAgent(this.context, "yourApplicationName"));
        MediaSource videoSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(Uri.parse("https://law.duke.edu/cspd/contest/videos/Framed-Contest_Documentaries-and-You.mp4"));
// Prepare the player with the source.

        this.player.prepare(videoSource);
        holder.playerView.requestFocus();
        this.player.setPlayWhenReady(false);

        holder.popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), holder.popupBtn);
                popup.getMenuInflater()
                        .inflate(R.menu.video_popup_menu, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                context,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu

            }
        });


    }

    @Override
    public int getItemCount() {


        return this.data.length;
    }

    public static class MainActivityViewHolder extends RecyclerView.ViewHolder{
        public ImageButton moreMenu;
//        public VideoView videoView;
//        UniversalVideoView mVideoView;
//        UniversalMediaController mMediaController;
        Button mStart;
//        View mBottomLayout;
//        View mVideoLayout;
        private PlayerView playerView ;
        private ImageButton popupBtn;

        public MainActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            moreMenu = itemView.findViewById(R.id.imageButton);
//            mVideoLayout = itemView.findViewById(R.id.video_layout);
//            mVideoView = (UniversalVideoView) itemView.findViewById(R.id.videoView);
//            mMediaController = (UniversalMediaController) itemView.findViewById(R.id.media_controller);
//            mVideoView.setMediaController(mMediaController);
//            mStart = (Button) itemView.findViewById(R.id.start);

            playerView = itemView.findViewById(R.id.video_view);
            popupBtn = itemView.findViewById(R.id.imageButton);





        }

    }

//    private void switchTitleBar(boolean show) {
//        ActionBar supportActionBar = getSupportActionBar();
//
//        if (supportActionBar != null) {
//            if (show) {
//                supportActionBar.show();
//            } else {
//                supportActionBar.hide();
//            }
//        }
//    }


}
