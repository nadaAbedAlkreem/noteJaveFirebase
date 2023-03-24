package com.example.applicationjave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.applicationjave.Model.notes;
import com.example.applicationjave.adapter.adapterShowNote;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements adapterShowNote.ItemClickListener, adapterShowNote.ItemClickListener2 {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<notes> items;
    adapterShowNote adapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView rv;
    ImageView delete;
    EditText updateNote;
    EditText updateheader  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        updateHeader = findViewById(R.id.update_header);

        updateNote = findViewById(R.id.update_Note);
        rv = findViewById(R.id.rvRecyc);
        items = new ArrayList<notes>();
        adapter = new adapterShowNote(this, items, this, this);
        delete = findViewById(R.id.delete);


        GetAllUserss();



    }

    private void GetAllUserss() {

        db.collection("Notes").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("drn", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                                if (documentSnapshot.exists()) {
                                    String id = documentSnapshot.getId();
                                    String noteText = documentSnapshot.getString("note");
                                    String notheader = documentSnapshot.getString("header");

                                    notes note = new notes(id,  notheader ,   noteText );
                                    items.add(note);
                                    rv.setLayoutManager(layoutManager);
                                    rv.setHasFixedSize(true);
                                    rv.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    Log.e("LogDATA", items.toString());
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("LogDATA", "get failed with ");


                    }
                });
    }

    public void Delete(final notes note) {
        db.collection("Notes").document(note.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("nada", "deleted");
                        items.remove(note);
                        adapter.notifyDataSetChanged();


                        Toast.makeText( getApplicationContext(), "successfully deleted " , Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("nada", "fail");
                        Toast.makeText( getApplicationContext(), "faild deleted " , Toast.LENGTH_SHORT).show();

                    }
                });
    }


    public void updateNote(final notes note) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name");
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog, null);

        builder.setView(customLayout);
        updateNote = customLayout.findViewById(R.id.update_Note);
        updateheader = customLayout.findViewById(R.id.update_header);
        updateheader = customLayout.findViewById(R.id.update_header);

        updateNote.setText(note.getText());
        updateheader.setText(note.getHeader());

        builder.setPositiveButton(
                "Update",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                         updateNote = customLayout.findViewById(R.id.update_Note);
                         updateheader = customLayout.findViewById(R.id.update_header);

                            db.collection("Notes").document(note.getId()).update("note", updateNote.getText().toString()
                                    ,
                                           "header", updateheader.getText().toString()


                                    )
                                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                                       @Override
                                       public void onSuccess(Void aVoid) {
//                                           Intent intent = new Intent(getApplicationContext(), Home.class);
//                                           startActivity(intent);
                                           items.clear();

                                            adapter.notifyDataSetChanged();
                                           Log.d("nada", "DocumentSnapshot successfully updated!");
                                       }
                                   })
                                   .addOnFailureListener(new OnFailureListener() {
                                       @Override
                                       public void onFailure(@NonNull Exception e) {
                                           Log.w("nada", "Error updating document", e);
                                       }
                                   });


                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void addNote(View view) {
         Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position, String id) {
        Delete(items.get(position));

}

    @Override
    public void onItemClick2(int position, String id) {
        updateNote(items.get(position));


    }
}