package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {
    private ImageView imageView2;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private ImageView shareButton;
    private ImageView backButton;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView2 = findViewById(R.id.imageView2);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        shareButton = findViewById(R.id.shareButton);
        backButton = findViewById(R.id.backButton);
        button2 = findViewById(R.id.button2);

        String heading = getIntent().getStringExtra("EVENT_HEADING");
        String img = getIntent().getStringExtra("EVENT_IMG");
        String location = getIntent().getStringExtra("EVENT_LOCATION");
        String price = getIntent().getStringExtra("EVENT_PRICE");
        String date = getIntent().getStringExtra("EVENT_DATE");

        Picasso.get().load(img).into(imageView2);
        textView4.setText(heading);
        textView5.setText(location);
        textView6.setText(price);
        textView7.setText("Date: " + date);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoActivity.this, MainActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InfoActivity.this, "Tickets Booked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
