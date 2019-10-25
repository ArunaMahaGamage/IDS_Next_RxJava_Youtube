package com.aruna.pagination;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerAdapterEvent recyclerAdapterEvent;
    private Context mContext;
    List<String> items = new ArrayList<>();

    public RecyclerAdapter(Context context, List<String> nicePlaces, RecyclerAdapterEvent recyclerAdapterEvents) {
        items = nicePlaces;
        mContext = context;
        recyclerAdapterEvent = recyclerAdapterEvents;
    }

    public void addItems(List<String> items) {
        this.items.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).mName.setText(items.get(position));

        videoPlayer(holder, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView mImage;
        private TextView mName, mDetails;
        private VideoView Vv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
            mDetails = itemView.findViewById(R.id.mDetails);
            Vv = (VideoView) itemView.findViewById(R.id.videoView);
            Vv.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = v.getVerticalScrollbarPosition();
//            recyclerAdapterEvent.onClick(v.getVerticalScrollbarPosition());
            /*Intent intent = new Intent(mContext, DetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("title", mNicePlaces.get(position).getTitle());
            bundle.putString("image", mNicePlaces.get(position).getImageUrl());
            mContext.startActivity(intent);*/
        }
    }

    private void videoPlayer(RecyclerView.ViewHolder viewHolder, int position) {
        String link = "https://www.radiantmediaplayer.com/media/bbb-360p.mp4";

        ((ViewHolder)viewHolder).Vv.setVideoURI(Uri.parse(link));
        ((ViewHolder)viewHolder).Vv.setMediaController(new MediaController(mContext));
        ((ViewHolder)viewHolder).Vv.requestFocus();
        new Thread(new Runnable() {
            public void run() {
                ((ViewHolder)viewHolder).Vv.start();
            }
        }).start();

        MediaController ctrl = new MediaController(mContext);
        ctrl.setVisibility(View.GONE);
        ((ViewHolder)viewHolder).Vv.setMediaController(ctrl);
    }

    public interface RecyclerAdapterEvent {
        void stayPosition(int pos);
//        void onClick(int position);
    }
}
