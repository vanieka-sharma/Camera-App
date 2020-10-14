package com.example.mycamera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button camera;
    ImageView imageView;
    final int request_pic=1;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera=(Button)findViewById(R.id.camera);
        imageView=(ImageView)findViewById(R.id.imageView);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},1);
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,request_pic);
            }
        });
    }
    protected void onActivityResult(int request_pic, int request_code, Intent data){
        super.onActivityResult(request_pic,request_code,data);
        ll=(LinearLayout)findViewById(R.id.linear);
        Bundle bundle= data.getExtras();
        Bitmap photo =(Bitmap) bundle.get("data");
        Drawable draw = new BitmapDrawable(getResources(),photo);
        //imageView.setImageBitmap(photo);
        ll.setBackground(draw);
    }
}