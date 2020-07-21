package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final ArrayList<ItemList> list = new ArrayList<>();

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Event Details");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int n = (int)dataSnapshot.getChildrenCount();

                for (int i=1;i<=n;i++) {
                    reference.child("event" + i).addValueEventListener(new ValueEventListener() {
                        String heading = "";
                        String location = "";
                        String img = "";
                        String date = "";
                        int price = 0;
                        String priceString = "Rs.";

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //list.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                //list.add(new ItemList(R.drawable.index, snapshot.getKey(), "location"));
                                if (snapshot.getKey().equals("heading")) {
                                    heading = snapshot.getValue().toString();
                                } else if (snapshot.getKey().equals("location")) {
                                    location = snapshot.getValue().toString();
                                } else if (snapshot.getKey().equals("image")) {
                                    img = snapshot.getValue().toString();
                                } else if (snapshot.getKey().equals("price")) {
                                    price = Integer.parseInt(snapshot.getValue().toString());
                                    priceString = priceString + price;
                                } else if (snapshot.getKey().equals("date")) {
                                    date = snapshot.getValue().toString();
                                }
                            }
                            list.add(new ItemList(img, heading, location, priceString, date));
                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new Adapter(list);
        mRecyclerView.setAdapter(mAdapter);


        int images[] = {R.drawable.sunburn, R.drawable.walker, R.drawable.garrix};

        viewFlipper = findViewById(R.id.viewFlipper);

        for (int image: images) {
            flipperImage(image);
        }
    }

    public void flipperImage(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}
