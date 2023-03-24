package com.example.applicationjave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editTextNote;
    EditText editTextHeader;
    Button buttonSave;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog progressDialog;
    Calendar calendar = Calendar.getInstance() ;
    int houres  = calendar.get(Calendar.HOUR) ;
    int minutes  = calendar.get(Calendar.MINUTE) ;
    int second  = calendar.get(Calendar.SECOND) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        editTextNote  = findViewById(R.id.et_note);
//        editTextHeader= findViewById(R.id.et_header);
//        buttonSave    = findViewById(R.id.btn_save);


    }



//
//    public void saveToFirebase(View view) {
//
//        String note  = editTextNote.getText().toString();
//        String header  = editTextHeader.getText().toString();
//
//        Map<String, Object> notes = new HashMap<>();
//        if (!note.isEmpty()) {
//            notes.put("note", note);
//            notes.put("header", header);
//
//            db.collection("Notes")
//                    .add(notes)
//
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                              @Override
//                                              public void onSuccess(DocumentReference documentReference) {
//                                                  openActivity2();
//                                                  Log.e("TAG", "Data added successfully to database");
//                                                  Toast.makeText( getApplicationContext(), "successfully added " , Toast.LENGTH_SHORT).show();
//                                              }
//                                          }
//                    )
//
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.e("TAG", "Failed to add database");
//                            Toast.makeText( getApplicationContext(), "faild added " , Toast.LENGTH_SHORT).show();
//
//
//                        }
//                    });
//
//        } else {
//            Toast.makeText(this, "Please Fill fields", Toast.LENGTH_SHORT).show();
//        }
//
//    }
}
