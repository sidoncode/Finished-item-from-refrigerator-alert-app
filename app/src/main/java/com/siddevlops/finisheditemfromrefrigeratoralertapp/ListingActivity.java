package com.siddevlops.finisheditemfromrefrigeratoralertapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.ThrowOnExtraProperties;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.model.Adapter;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity.AddNote;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity.EditNote;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity.Note;
import com.siddevlops.finisheditemfromrefrigeratoralertapp.noteactivity.Notedetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView noteLists;
    Adapter adapter;
    private ImageView menuicon;
    FloatingActionButton btnfb;
    FirebaseFirestore firestore;

    FirebaseAuth fauth;
    FirebaseUser user;

    FirestoreRecyclerAdapter<Note,NoteViewHolder> notepaper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);


        firestore = FirebaseFirestore.getInstance();

        fauth = FirebaseAuth.getInstance();
        user = fauth.getCurrentUser();

        Query query = firestore.collection("notes").document(user.getUid()).collection
                ("myNotes")
                .orderBy("title",Query.Direction.DESCENDING);



        FirestoreRecyclerOptions<Note> allnotes  = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query,Note.class).build();

        notepaper = new FirestoreRecyclerAdapter<Note, NoteViewHolder>(allnotes) {
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, final int i, @NonNull final Note note) {

                noteViewHolder.notetitle.setText(note.getTitle());
                noteViewHolder.noteContent.setText(note.getContent()); // extract the title from the list and assing to content field //

                final int code = getrandomcolor();

                noteViewHolder.mCardView.setBackgroundColor(noteViewHolder.view.getResources().getColor(code,null));

                final String docid = notepaper.getSnapshots().getSnapshot(i).getId();

                noteViewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(v.getContext(),"the item is clicked",Toast.LENGTH_SHORT).show();
                        Intent transition_to_note_detail_activity = new Intent(v.getContext(), Notedetail.class);

                        transition_to_note_detail_activity.putExtra("title",note.getTitle());
                        transition_to_note_detail_activity.putExtra("content",note.getContent());
                        transition_to_note_detail_activity.putExtra("code",code);
                        transition_to_note_detail_activity.putExtra("noteId",docid);

                        v.getContext().startActivity(transition_to_note_detail_activity);
                    }
                });

                ImageView menuicon = noteViewHolder.view.findViewById(R.id.menuIcon);

                menuicon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        final String docid = notepaper.getSnapshots().getSnapshot(i).getId();
                        //Toast.makeText(getApplicationContext(),"menu icon is clicked",Toast.LENGTH_SHORT).show();
                        PopupMenu menu  = new PopupMenu(v.getContext(),v);
                        menu.setGravity(Gravity.END);

                        menu.getMenu().add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                Intent i = new Intent(v.getContext(), EditNote.class);

                                i.putExtra("title",note.getTitle());
                                i.putExtra("content",note.getContent());
                                i.putExtra("noteId",docid);
                                startActivity(i);

                                return false;
                            }
                        });
                        menu.getMenu().add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                DocumentReference documentReference = firestore.collection("notes").document(docid);
                                documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(),"error while parsing the data",
                                                Toast.LENGTH_SHORT).show();;
                                    }
                                });

                                return false;
                            }
                        });

                        menu.show();
                    }
                });



            }

            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_view,parent,false);

                return new NoteViewHolder(view);
            }
        };

        noteLists =findViewById(R.id.notelist);

        btnfb = findViewById(R.id.fab);


        btnfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trasition_to_add_note =new Intent(ListingActivity.this, AddNote.class);
                startActivity(trasition_to_add_note);
                finish();
            }
        });


        //init the adapter //
        List<String> title = new ArrayList<>();
        List<String> content = new ArrayList<>();
        /**
            title.add("First note title");
            content.add("First note content");

            title.add("Second note title");
            content.add("Second note content");

            title.add("Third note title");
            content.add("Third note content");

            title.add("4 note title");
            content.add("4 note content"); **/

        adapter = new com.siddevlops.finisheditemfromrefrigeratoralertapp.model.Adapter(title,content);
        noteLists.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        noteLists.setAdapter(notepaper);


    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView notetitle,noteContent;
        View view;

        CardView mCardView;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            notetitle = itemView.findViewById(R.id.titles);
            noteContent =  itemView.findViewById(R.id.content);
            mCardView = itemView.findViewById(R.id.noteCard);

            view = itemView;
        }
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
    protected void onStart() {
        super.onStart();
        notepaper.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (notepaper != null) {
            notepaper.stopListening();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListingActivity.this,DashboardActivity.class));
        super.onBackPressed();
    }
}
