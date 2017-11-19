package com.example.yashladha.android_seller.navigation;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yashladha.android_seller.R;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyAccountEditActivity extends AppCompatActivity {

    TextView tvName, tvContact, tvEmail, tvPic;
    EditText etName, etContact, etEmail, etResAddress;
    ImageView ivPic;
    SharedPreferences myPrefs;
    int noOfImages;
    String plan;
    boolean image;
    LinearLayout sv1;
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
    static Date now = new Date();
    private static final String IMAGE_DIRECTORY = "/hatsphere/seller" + formatter.format(now);
    private int GALLERY = 1, CAMERA = 2;
    Button btDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_my_account_edit);
        tvName = (TextView) findViewById(R.id.tvName);
        tvContact = (TextView) findViewById(R.id.tvContact);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPic = (TextView) findViewById(R.id.tvEditPic);
        btDone = (Button) findViewById(R.id.btDone);
        etName = (EditText) findViewById(R.id.etName);
        etContact = (EditText) findViewById(R.id.etContact);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etResAddress = (EditText) findViewById(R.id.etResAddress);

        myPrefs = getSharedPreferences("myprfs", MODE_PRIVATE);
        plan = myPrefs.getString("Plan", "");
        final String uid = myPrefs.getString("UID", "");

        ivPic = (ImageView) findViewById(R.id.ivEditPic);
        ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
                tvPic.setText("");
            }
        });
        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etContact.getText().equals("")) {
                    JsonObject json = new JsonObject();
                    json.addProperty("field", "ContactNo");
                    json.addProperty("value", etContact.getText().toString());
                    json.addProperty("uid", uid);
                    Ion.with(MyAccountEditActivity.this)
                            .load("http://10.0.2.2:3000/user/update/")
                            .setJsonObjectBody(json)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    // do stuff with the result or error
                                    if (result.get("response").toString().equals("500")) {
                                        Toast.makeText(MyAccountEditActivity.this, "Something went wrong",
                                                Toast.LENGTH_LONG).show();

                                    } else if (result.get("response").toString().equals("200")) {
                                        Toast.makeText(MyAccountEditActivity.this, "Successfully Changed",
                                                Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                } else if (!etResAddress.getText().equals("")) {
                    JsonObject json = new JsonObject();
                    json.addProperty("field", "Address");
                    json.addProperty("value", etResAddress.getText().toString());
                    json.addProperty("uid", uid);

                    Ion.with(MyAccountEditActivity.this)
                            .load("http://10.0.2.2:3000/user/update/")
                            .setJsonObjectBody(json)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    // do stuff with the result or error
                                    if (result.get("response").toString().equals("500")) {
                                        Toast.makeText(MyAccountEditActivity.this, "Something went wrong",
                                                Toast.LENGTH_LONG).show();

                                    } else if (result.get("response").toString().equals("200")) {
                                        Toast.makeText(MyAccountEditActivity.this, "Successfully Changed",
                                                Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                } else if (!etName.getText().equals("")) {
                    JsonObject json = new JsonObject();
                    json.addProperty("field", "Name");
                    json.addProperty("value", etName.getText().toString());
                    json.addProperty("uid", uid);

                    Ion.with(MyAccountEditActivity.this)
                            .load("http://10.0.2.2:3000/user/update/")
                            .setJsonObjectBody(json)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    // do stuff with the result or error
                                    if (result.get("response").toString().equals("500")) {
                                        Toast.makeText(MyAccountEditActivity.this, "Something went wrong",
                                                Toast.LENGTH_LONG).show();

                                    } else if (result.get("response").toString().equals("200")) {
                                        Toast.makeText(MyAccountEditActivity.this, "Successfully Changed",
                                                Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                }
                
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etName.getText().toString().trim().length() < 3) {
                        etName.setError("Minimum length should be 3 characters");
                    } else {
                        etName.setError(null);
                    }
                }
            }
        });


        etContact.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etContact.getText().toString().trim().length() < 7) {
                        etContact.setError("Minimum length should be 7 characters");
                    } else {
                        etContact.setError(null);
                    }
                }
            }
        });

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etEmail.getText().toString().trim().length() < 3) {
                        etEmail.setError("Minimum length should be 3 characters");
                    } else {
                        etEmail.setError(null);
                    }
                }
            }
        });

        etResAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etResAddress.getText().toString().trim().length() < 5) {
                        etResAddress.setError("Minimum length should be 5 characters");
                    } else {
                        etResAddress.setError(null);
                    }
                }
            }
        });

    }

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
                    Toast.makeText(MyAccountEditActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    image = true;
                    ivPic.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MyAccountEditActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            image = true;
            saveImage(thumbnail);
            ivPic.setImageBitmap(thumbnail);
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
            isStoragePermissionGranted();
            wallpaperDirectory.createNewFile();
            Log.d("TAG", "File Saved::--->" + wallpaperDirectory.getAbsolutePath());
            //Log.d("TAG", image.toString());

            return wallpaperDirectory.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Tag", "Permission is granted");
                return true;
            } else {

                Log.v("Tag", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Tag", "Permission is granted");
            return true;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("Tag", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
