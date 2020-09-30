package com.siddevlops.finisheditemfromrefrigeratoralertapp.model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.siddevlops.finisheditemfromrefrigeratoralertapp.R;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity.Notedetail;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> title;
    List<String> content;


    public Adapter(List<String> title,List <String> content){
        this.title = title;
        this.content = content;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view,parent,false);

        return new Adapter.ViewHolder(view);


    }

    //binding the data //

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.notetitle.setText(title.get(position));
        holder.noteContent.setText(content.get(position)); // extract the title from the list and assing to content field //

        final int code = getrandomcolor();

        holder.mCardView.setBackgroundColor(holder.view.getResources().getColor(code,null));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"the item is clicked",Toast.LENGTH_SHORT).show();
                Intent transition_to_note_detail_activity = new Intent(v.getContext(), Notedetail.class);

                transition_to_note_detail_activity.putExtra("title",title.get(position));
                transition_to_note_detail_activity.putExtra("content",content.get(position));
                transition_to_note_detail_activity.putExtra("code",code);

                v.getContext().startActivity(transition_to_note_detail_activity);
            }
        });


    }

    private int getrandomcolor() {
        List<Integer> colocode = new ArrayList<>();

        colocode.add(R.color.blue);
        colocode.add(R.color.lpurple);
        colocode.add(R.color.magenta);
        colocode.add(R.color.red);
        colocode.add(R.color.purpel);
        colocode.add(R.color.cyan);
        colocode.add(R.color.green);
        colocode.add(R.color.yellow);
        colocode.add(R.color.violet);
        colocode.add(R.color.grey);

        Random randomcolor = new Random();

        int number = randomcolor.nextInt(colocode.size());

        return colocode.get(number);

    }

    @Override
    public int getItemCount(){
        return title.size();
    }



    // view holder inner class //

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notetitle,noteContent;
        View view;

        CardView mCardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notetitle = itemView.findViewById(R.id.titles);
            noteContent =  itemView.findViewById(R.id.content);
            mCardView = itemView.findViewById(R.id.noteCard);

            view = itemView;
        }
    }





}
