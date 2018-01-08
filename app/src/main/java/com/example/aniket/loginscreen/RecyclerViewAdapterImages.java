package com.example.aniket.loginscreen;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by aniket on 7/1/18.
 */

public class RecyclerViewAdapterImages extends RecyclerView.Adapter<RecyclerViewAdapterImages.ViewHolder>{

    Context context;
    List<MessageImage> messages;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView TitleTextView;
        public TextView BodyTextView;
        public ImageView ImageShow;

        public ViewHolder(View itemView) {

            super(itemView);

            TitleTextView = (TextView) itemView.findViewById(R.id.title_image);

            BodyTextView = (TextView) itemView.findViewById(R.id.body_image);

            ImageShow = (ImageView) itemView.findViewById(R.id.image_download);
        }
    }

    public RecyclerViewAdapterImages(Context context, List <MessageImage> tempList){
        this.messages = tempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_images, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MessageImage message = messages.get(position);

        holder.TitleTextView.setText(message.getTitle());

        holder.BodyTextView.setText(message.getBody());

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(message.getImageName());
        Glide.with(this.context).using(new FirebaseImageLoader()).load(storageReference)
                .into(holder.ImageShow);
    }

    @Override
    public int getItemCount() {

        return messages.size();
    }
}
