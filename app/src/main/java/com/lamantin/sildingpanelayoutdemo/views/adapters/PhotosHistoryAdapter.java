package com.lamantin.sildingpanelayoutdemo.views.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lamantin.sildingpanelayoutdemo.R;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;

import java.util.LinkedList;

public class PhotosHistoryAdapter extends RecyclerView.Adapter<PhotosHistoryAdapter.PhotosHistoryViewHolder> {

    private static final String TAG = "PhotosHistoryAdapter";
    LinkedList<Photo> values = new LinkedList<>();

    @Override
    public PhotosHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_recycler_history_item, parent, false);
        return new PhotosHistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotosHistoryViewHolder holder, int position) {
        Photo photo = values.get(position);
        setPhoto(photo, holder);
        setPhotoName(photo, holder);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    private void setPhoto(Photo photo, PhotosHistoryViewHolder holder) {
        Glide.with(holder.itemView.getContext()).load(photo.url()).into(holder.photo);
    }

    private void setPhotoName(Photo photo, PhotosHistoryViewHolder holder) {
        holder.text.setText(photo.title());
    }

    public void setValues(LinkedList<Photo> values) {
        this.values = values;
        notifyDataSetChanged();
    }

    public void addValue(Photo value) {
        values.add(value);
        notifyItemInserted(values.size() - 1);
    }

    public static class PhotosHistoryViewHolder extends RecyclerView.ViewHolder {

        public final ImageView photo;
        public final TextView text;

        public PhotosHistoryViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo_history_iv);
            text = (TextView) itemView.findViewById(R.id.photo_name);
        }
    }
}
