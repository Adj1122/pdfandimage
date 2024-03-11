package com.example.pdfandimageupload;
// UploadActivity.java
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadActivity extends AppCompatActivity {

    private Button btnUpload;
    private final int REQ =1;
    private Button selectimage;
    private Bitmap bitmap;
    private StorageReference reference;
    private StorageReference storageReference;
    ProgressDialog pd;
    String downloadUrl;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ && resultCode==RESULT_OK){
            Uri uri =data.getData();

            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        selectimage=findViewById(R.id.selectImage);
        btnUpload = findViewById(R.id.btnUpload);
        storageReference = FirebaseStorage.getInstance().getReference().child("gallery");
        reference = FirebaseStorage.getInstance().getReference().child("gallery");

        pd=new ProgressDialog(this);
        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }

            private void openGallery() {
                Intent pickImage=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickImage,REQ);
            }
        });



        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(bitmap==null){
                   Toast.makeText(UploadActivity.this,"Please Upload Image ",Toast.LENGTH_SHORT).show();

               }
               else{
                   pd.setMessage("Uploading...");
                   pd.show();
                   //uploadImage();
               }
            }
            /*private void uploadImage() {
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
                byte[] finaling =baos.toByteArray();
                final StorageReference filepath;
                filepath=storageReference.child(finaling+"jpg");
                final UploadTask uploadTask= filepath.putBytes(finaling);
                uploadTask.addOnCompleteListener(UploadActivity.this,(OnCompleteListener<task>) {
                    filepath.getDownloadUrl().addOnSuccessListener((OnSuccessListener< ?super Uri>) {
                        downloadUrl=String.valueOf(uri);
                        uploadData();
                    });
                });

            }

            private void uploadData() {
                reference=reference.child("gallery");
                final String uniqueKey=reference.push().getKey();

                reference.child(uniqueKey).setValue(downloadUrl).addOnSuccessListener(new OnSuccessListener<Void>(){
                    public void onSuccess(Void aVoid){
                        pd.dismiss();
                        Toast.makeText(UploadActivity.this,"Image Uploaded Successfully",Toast.LENGTH_SHORT).show();

                    }

                }).addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();

                        Toast.makeText(UploadActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                    }
                });

            }*/
        });
    }
}
