package com.example.aniket.loginscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aniket on 6/1/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List <Message> messages;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView TitleTextView;
        public TextView BodyTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            TitleTextView = (TextView) itemView.findViewById(R.id.title);

            BodyTextView = (TextView) itemView.findViewById(R.id.body);
        }
    }

    public RecyclerViewAdapter(Context context, List <Message> tempList){
        this.messages = tempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Message message = messages.get(position);

        holder.TitleTextView.setText(message.getTitle());

        holder.BodyTextView.setText(message.getBody());

    }

    @Override
    public int getItemCount() {

        return messages.size();
    }

}
