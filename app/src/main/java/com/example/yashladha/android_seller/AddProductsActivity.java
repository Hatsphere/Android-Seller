package com.example.yashladha.android_seller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.model.UriLoader;
import com.example.yashladha.android_seller.fragments.DisplayFrag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddProductsActivity extends AppCompatActivity {

    ImageView ivAdd;
    TextView tvAddPhoto, tvProductName, tvProDes, tvOriginalPrice, tvDiscount, tvCategory;
    EditText etProductName, etProDes, etOriginalPrice, etDiscount, etCategory;
    Button btDone;
    LinearLayout sv1;
    ToggleButton tbOnSale;
    String productName = "", proDes = "", originalPrice = "", discount = "", category = "";
    boolean sale, image;
    SharedPreferences myPrefs;
    int noOfImages;
    String plan;
    private RequestQueue rq;
    private static final String IMAGE_DIRECTORY = "/hatsphere";
    private int GALLERY = 1, CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        noOfImages = 0;
        btDone = (Button) findViewById(R.id.btDone);
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
        rq = Volley.newRequestQueue(AddProductsActivity.this);

        myPrefs = getSharedPreferences("myprfs", MODE_PRIVATE);
        plan = myPrefs.getString("Plan", "");
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
                showPictureDialog();

            }
        });
        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject obj = new JSONObject();
                if (!productName.equals("") && !originalPrice.equals("") && !discount.equals("") && !proDes.equals("") && !category.equals("")) {
                    try {

                        obj.put("pName", productName);
                        obj.put("pPrice", Integer.toString(Integer.parseInt(originalPrice) - Integer.parseInt(discount)));
                        obj.put("pDescription", proDes);
                        obj.put("pClass", category);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.POST, "http://10.0.2.2:3000/user/login/", obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Toast.makeText(AddProductsActivity.this, response.get("flag").toString(), Toast.LENGTH_SHORT).show();

                                if (response.get("response").toString().equals("200")) {

                                    Toast.makeText(AddProductsActivity.this, "Your Product has been added",
                                            Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(AddProductsActivity.this, HomePageActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(AddProductsActivity.this, response.get("Something is wrond").toString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                        }
                    });

                    rq.add(jsonObjectRequest);

                }
            }
        });
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            String partFilename = currentDateFormat();
            storeCameraPhotoInSDCard(bitmap, partFilename);

            // display the image from SD Card to ImageView Control
            String storeFilename = "photo_" + partFilename + ".jpg";
            Bitmap mBitmap = getImageFileFromSDCard(storeFilename);
            //if (noOfImages <= 2 && plan == "0" || noOfImages <= 3 && plan == "1" || noOfImages <= 5 && plan == "2") {
                image = true;
                ImageView image = new ImageView(AddProductsActivity.this);
                Drawable d = new BitmapDrawable(getResources(), mBitmap);
                image.setBackground(d);
                sv1.addView(image);
                try {
                    getImageUri(this, mBitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            //}
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

    public Uri getImageUri(Context inContext, Bitmap inImage) throws FileNotFoundException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        //Log.v("HTTPGet", "testurl.toString == " + Uri.parse().toString());
        return Uri.parse(path);
    }
*/
    //

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(AddProductsActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    image = true;
                    ImageView image = new ImageView(AddProductsActivity.this);
                    image.setImageBitmap(bitmap);
                    sv1.addView(image);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddProductsActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            image = true;
            ImageView image = new ImageView(AddProductsActivity.this);
            image.setImageBitmap(thumbnail);
            sv1.addView(image);
            saveImage(thumbnail);
            Toast.makeText(AddProductsActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
            
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
}
