package com.example.pdfandimageupload;
// FullscreenActivity.java
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class FullscreenActivity extends AppCompatActivity {

    private ImageView imageViewFullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        imageViewFullScreen = findViewById(R.id.imageViewFullScreen);

        // Retrieve image URI from intent and load into imageViewFullScreen
        // Example: String imageUrl = getIntent().getStringExtra("imageUrl");
        // Load image using Glide or Picasso libraries
    }
}
