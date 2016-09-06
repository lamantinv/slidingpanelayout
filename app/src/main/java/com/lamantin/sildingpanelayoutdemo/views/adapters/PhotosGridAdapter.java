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
import com.lamantin.sildingpanelayoutdemo.presenters.AlbumsPresenter;

import java.util.ArrayList;
import java.util.List;

public class PhotosGridAdapter extends RecyclerView.Adapter<PhotosGridAdapter.PhotosViewHolder> {

    List<Photo> values = new ArrayList<>();
    private AlbumsPresenter presenter;

    public PhotosGridAdapter(AlbumsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_recycler_grid_item, parent, false);
        return new PhotosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotosViewHolder holder, int position) {
        Photo photo = values.get(position);
        setPhoto(photo, holder);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    private void setPhoto(Photo photo, PhotosViewHolder holder) {
        Glide.with(holder.itemView.getContext()).load(photo.getThumbnail()).into(holder.photo);
        holder.photo.setOnClickListener(view -> {
            presenter.onPhotoClick(photo);
        });
        holder.photoName.setText(photo.getTitle());
    }

    public void setValues(List<Photo> values) {
        this.values = values;
        notifyDataSetChanged();
    }

    public static class PhotosViewHolder extends RecyclerView.ViewHolder {

        public final ImageView photo;
        public final TextView photoName;

        public PhotosViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo_grid);
            photoName = (TextView) itemView.findViewById(R.id.photo_name);
        }
    }
}
