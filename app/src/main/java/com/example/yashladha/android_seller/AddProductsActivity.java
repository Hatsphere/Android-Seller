package com.example.yashladha.android_seller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.yashladha.android_seller.fragments.DisplayFrag;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProductsActivity extends AppCompatActivity {

    ImageView ivAdd;
    TextView tvAddPhoto, tvProductName, tvProDes, tvOriginalPrice, tvDiscount, tvCategory;
    EditText etProductName, etProDes, etOriginalPrice, etDiscount, etCategory;
    Button btDone;
    LinearLayout sv1;
    ToggleButton tbOnSale;
    String productName, proDes, originalPrice, discount, category;
    boolean sale, image;
    SharedPreferences myPrefs = getSharedPreferences("myprfs", MODE_PRIVATE);
    String plan = myPrefs.getString("Plan", "");
    int noOfImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        noOfImages = 0;
        ivAdd = (ImageView) findViewById(R.id.ivAdd);
        tvAddPhoto = (TextView) findViewById(R.id.tvAddPhoto);
        tvProductName = (TextView) findViewById(R.id.tvProductName);
        tvProDes = (TextView) findViewById(R.id.tvProDes);
        tvOriginalPrice = (TextView) findViewById(R.id.tvOriginalPrice);
        tvDiscount = (TextView) findViewById(R.id.tvDiscount);
        tvCategory = (TextView) findViewById(R.id.tvCategory);
        etProductName = (EditText) findViewById(R.id.etProductName);
        etProDes = (EditText) findViewById(R.id.etProDes);
        etOriginalPrice = (EditText) findViewById(R.id.etOriginalPrice);
        etDiscount = (EditText) findViewById(R.id.etDiscount);
        etCategory = (EditText) findViewById(R.id.etCategory);
        sv1 = (LinearLayout) findViewById(R.id.sv1);
        tbOnSale = (ToggleButton) findViewById(R.id.tbOnSale);


        etProductName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                productName = etProductName.getText().toString();
            }
        });

        etProDes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                proDes = etProDes.getText().toString();
            }
        });
        etOriginalPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                originalPrice = etOriginalPrice.getText().toString();
            }
        });
        etDiscount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                discount = etDiscount.getText().toString();
            }
        });
        etCategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                category = etCategory.getText().toString();
            }
        });
        tbOnSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbOnSale.getText().toString() == "Yes") {
                    sale = true;
                } else {
                    sale = false;
                }
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, 0);
            }
        });
        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProductsActivity.this, DisplayFrag.class);
                startActivity(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            String partFilename = currentDateFormat();
            storeCameraPhotoInSDCard(bitmap, partFilename);

            // display the image from SD Card to ImageView Control
            String storeFilename = "photo_" + partFilename + ".jpg";
            Bitmap mBitmap = getImageFileFromSDCard(storeFilename);
            if (noOfImages <= 2 && plan == "0" || noOfImages <= 3 && plan == "1" || noOfImages <= 5 && plan == "2") {
                image = true;
                ImageView image = new ImageView(AddProductsActivity.this);
                Drawable d = new BitmapDrawable(getResources(), mBitmap);
                image.setBackground(d);
                sv1.addView(image);
                getImageUri(this, mBitmap);
            }
        }
    }


    private String currentDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

    private void storeCameraPhotoInSDCard(Bitmap bitmap, String currentDate) {
        File outputFile = new File(Environment.getExternalStorageDirectory(), "photo_" + currentDate + ".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImageFileFromSDCard(String filename) {
        Bitmap bitmap = null;
        File imageFile = new File(Environment.getExternalStorageDirectory() + filename);
        try {
            FileInputStream fis = new FileInputStream(imageFile);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
