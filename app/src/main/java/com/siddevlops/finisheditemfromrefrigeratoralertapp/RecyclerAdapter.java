package com.siddevlops.finisheditemfromrefrigeratoralertapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.userauth.ModelEatingHabit;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;

        View view = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.message_item,parent,false);

        return new ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mTextView.setText(mModelEatingHabits.get(position).getName());

        //image view: glide library //

        //Glide.with(this)
        Glide.with(mContext).load(mModelEatingHabits.get(position)

                .getImage()).into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        //return 0;
       return mModelEatingHabits.size();
    }


    private Context mContext;
    private ArrayList<ModelEatingHabit> mModelEatingHabits;

    private static final String tag = "RecyclerView";

    public RecyclerAdapter(Context context, ArrayList<ModelEatingHabit> modelEatingHabits) {
        mContext = context;
        mModelEatingHabits = modelEatingHabits;
    }









    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageview);
            mTextView = itemView.findViewById(R.id.textviewy);
        }
    }
}
