package com.example.applicationjave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.applicationjave.Model.category;
import com.example.applicationjave.Model.notes;
import com.example.applicationjave.adapter.adapterShowCategory;
import com.example.applicationjave.adapter.adapterShowNote;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Category extends AppCompatActivity  implements   adapterShowCategory.ItemClickListener2{

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<category> items;
    adapterShowCategory adapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView rv;
    Calendar calendar = Calendar.getInstance() ;
    int houres  = calendar.get(Calendar.HOUR) ;
    int minutes  = calendar.get(Calendar.MINUTE) ;
    int second  = calendar.get(Calendar.SECOND) ;

    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
         rv = findViewById(R.id.rec_category);
        items = new ArrayList<category>();
        screenTrack("category");

        adapter = new adapterShowCategory(this, items, this);
         GetAllUserss();


    }

    public void btnEvent (String id   , String  name , String  contentType){
        Bundle bundle =  new Bundle() ;
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID  , id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME  , name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE  , contentType);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT   , bundle);


    }
    private void GetAllUserss() {

        db.collection("category").get()
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
                                    String category_name = documentSnapshot.getString("name");

                                    category category_ = new category(id,  category_name  );
                                    items.add(category_);
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

    @Override
    public void onItemClick2(int position, String id) {
        btnEvent("btn1" , "btn click category"  , "event button");
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("id" , items.get(position).getId());
        startActivity(intent);
    }
    public  void screenTrack(String name){
        Bundle bundle =  new Bundle() ;
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME  , name);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS  , "Main home " );

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW   , bundle);


    }

    @Override
    protected void onPause() {

        Calendar calendar = Calendar.getInstance() ;
        int houres2  = calendar.get(Calendar.HOUR) ;
        int minutes2  = calendar.get(Calendar.MINUTE) ;
        int second2  = calendar.get(Calendar.SECOND) ;
        int h = houres2 - houres  ;
        int m = minutes2   - minutes  ;
        int s = second2 - second ;
        HashMap<String , Object > time  = new HashMap<>() ;
        time.put("hourse" , h ) ;
        time.put("minuset" , m ) ;
        time.put("second" , s ) ;
        time.put("screen_name" , "Category" ) ;


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("trackUser")
                .add(time)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                          @Override
                                          public void onSuccess(DocumentReference documentReference) {
                                              Log.e("TAG", "Data added successfully to database");
                                          }
                                      }
                )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAG", "Failed to add database");


                    }
                });
        super.onPause();
    }

}