package com.example.yashladha.android_seller;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yashladha.android_seller.classes.FileUriHelper;
import com.example.yashladha.android_seller.helper.BaseUrlConfig;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AddProductsActivity extends AppCompatActivity {

    private ImageView ivAdd;
    private TextView tvAddPhoto, tvProductName, tvProDes, tvOriginalPrice, tvDiscount, tvCategory;
    private EditText etProductName, etProDes, etOriginalPrice, etDiscount;
    private Spinner spinner;
    private Button btDone;
    private LinearLayout sv1;
    private File wallpaperDirectory;
    private ToggleButton tbOnSale;
    private String productName = "", proDes = "", originalPrice = "", discount = "", category = "";
    private boolean sale, image;
    private SharedPreferences myPrefs;
    private int noOfImages;
    private Uri uriContent;
    private String plan;
    private String UID;
    private RequestQueue rq;
    private Button btProceed;
    private Context context;
    private ArrayList<String> dataUri;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
    private static Date now = new Date();
    private static final String IMAGE_DIRECTORY = "/hatsphere" + formatter.format(now);
    private int GALLERY = 1, CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        noOfImages = 0;
        dataUri = new ArrayList<>();
        context = this.getBaseContext();
        btProceed = (Button) findViewById(R.id.btProceed);
        spinner = (Spinner) findViewById(R.id.spCategory);
        btDone = findViewById(R.id.btDone);
        ivAdd = findViewById(R.id.ivAdd);
        tvAddPhoto = findViewById(R.id.tvAddPhoto);
        tvProductName = findViewById(R.id.tvProductName);
        tvProDes = findViewById(R.id.tvProDes);
        tvOriginalPrice = findViewById(R.id.tvOriginalPrice);
        tvDiscount = findViewById(R.id.tvDiscount);
        tvCategory = findViewById(R.id.tvCategory);
        etProductName = findViewById(R.id.etProductName);
        etProDes = findViewById(R.id.etProDes);
        etOriginalPrice = findViewById(R.id.etOriginalPrice);
        etDiscount = findViewById(R.id.etDiscount);
        sv1 = findViewById(R.id.sv1);
        tbOnSale = findViewById(R.id.tbOnSale);
        rq = Volley.newRequestQueue(AddProductsActivity.this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        

        final List<String> categoryList = new ArrayList<>();
        categoryList.add("Select a Category");
        JSONObject obj = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "http://10.0.2.2:3000/product/all/cateogry", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Iterator<String> iterator = response.keys();
                        while (iterator.hasNext()) {
                            String key = iterator.next();
                            //Log.i("TAG","key:"+key +"--Value::"+response.optString(key);
                            try {
                                categoryList.add(response.getString(key).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.e(rootview.getClass().getSimpleName(), error.getMessage());
                    }
                }

        );
        rq.add(jsonObjectRequest);


        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, categoryList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinnerArrayAdapter.setNotifyOnChange(true);
        spinnerArrayAdapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                    category = selectedItemText;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        myPrefs = getSharedPreferences("myprfs", MODE_PRIVATE);
        UID = myPrefs.getString("UID", "");
        plan = myPrefs.getString("Plan", "");
        SharedPreferences myPrefs = getSharedPreferences("myprfs", MODE_PRIVATE);
        //Toast.makeText(AddProductsActivity.this, UID, Toast.LENGTH_LONG).show();
        System.out.println(UID);
        /**
         * taking the entry from the edit text to string product name
         */

        etProductName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etProductName.getText().toString().trim().length() < 5) {
                        etProductName.setError("Minimum length should be 5 characters");
                    } else {
                        etProductName.setError(null);
                    }
                }
            }
        });

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
                if (productName.equals("")) {
                    etProductName.setError("Please fill this entry");
                }
            }
        });
/**
 * taking the entry from the edit text to string product description
 */

        etProDes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etProDes.getText().toString().trim().length() < 15) {
                        etProDes.setError("Minimum length should be 15 characters");
                    } else {
                        etProDes.setError(null);
                    }
                }
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
                if (proDes.equals("")) {
                    etProDes.setError("Please fill this entry");
                }
            }
        });
        /**
         * taking the entry from the edit text to string original price
         */

        etOriginalPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etOriginalPrice.getText().toString().trim().length() < 2) {
                        etOriginalPrice.setError("Minimum length should be 2 characters");
                    } else {
                        etOriginalPrice.setError(null);
                    }
                }
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
                if (etOriginalPrice.getText().equals("")) {
                    etOriginalPrice.setError("Please fill this entry");
                }
                if (!(etOriginalPrice.getText().toString()).matches("^-?\\d+$")) {
                    etOriginalPrice.setError("Please enter an integer");
                    etOriginalPrice.setText("");
                }
            }
        });
        /**
         * taking the entry from the edit text to string discount
         */

        etDiscount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etDiscount.getText().toString().trim().length() < 1) {
                        etDiscount.setError("Minimum length should be 1 character");
                    } else {
                        etDiscount.setError(null);
                    }
                }
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
                if (etDiscount.getText().equals("")) {
                    etDiscount.setError("Please fill this entry");
                }
                if (!(etDiscount.getText().toString()).matches("^-?\\d+$")) {
                    etDiscount.setError("Please enter an integer");
                    etDiscount.setText("");
                }
            }
        });
        /**
         * taking the entry from the edit text to string category
         */

        /**
         * If the product is on sale we make this text box as Yes
         */
        tbOnSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sale = Objects.equals(tbOnSale.getText().toString(), "Yes");
            }
        });
        /**
         * adding the image of the product by clicking on this + image
         */
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });
        /**
         * adding the image of the product by clicking on text view
         */
        tvAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isReadPermissionGranted()) {
                    showPictureDialog();
                }
            }
        });
        /**
         * By clicking this button the seller can send the details of his product to the seller.
         * Images of the product are also sent to the server
         */
        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObject obj = new JsonObject();
                if (!productName.equals("") && !originalPrice.equals("") && !discount.equals("") && !proDes.equals("") && !category.equals("")) {
                    obj.addProperty("pName", productName);
                    obj.addProperty("pPrice", Integer.toString(Integer.parseInt(originalPrice) - Integer.parseInt(discount)));
                    obj.addProperty("pDescription", proDes);
                    obj.addProperty("pClass", category);
                    obj.addProperty("pSale", sale);

                    Ion.with(AddProductsActivity.this)
                            .load("http://10.0.2.2:3000/product/send/" + UID)
                            .setJsonObjectBody(obj)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    // do stuff with the result or error
                                    Toast.makeText(AddProductsActivity.this, "Your Product has been added",
                                            Toast.LENGTH_LONG).show();

                                    Builders.Any.B builder = Ion.with(context)
                                            .load(BaseUrlConfig.getBaseURL() + "product/send/image/" + UID + "/" + productName);
                                    for (String item : dataUri) {
                                        builder.setMultipartFile(UID, new File(item));
                                    }
                                    builder.asJsonObject()
                                            .setCallback(new FutureCallback<JsonObject>() {
                                                @Override
                                                public void onCompleted(Exception e, JsonObject result) {
                                                    if (result != null) {
                                                        Log.d("onCompleted: ", result.toString());
                                                    } else {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                    btProceed.setEnabled(true);
                                }
                            });

                } else {
                    Toast.makeText(AddProductsActivity.this, "You have not entered some entries", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductsActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * This function shows a dialog box containing two option, viz. Selecting a photo from the gallery or clicking a picture from the camera
     */
    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery"
        //        "Capture photo from camera"
        };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery();
                                break;
                            /*case 1:
                                takePhotoFromCamera();
                                break;*/
                        }
                    }
                });
        pictureDialog.show();
    }

    /**
     * This function helps us to choose our photo from the gallery and uses general intent to do that
     */
    public void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select product images"), GALLERY);
    }

    /**
     * This function helps us to click our photo from the camera and uses general intent to do that
     */
    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    /**
     * THis function takes from the result of the above activities and produces a output on the screen.
     * This gives us a photograph as uri and thus we can send it to the server
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                if (data.getClipData() != null) {
                    ClipData contentURI = data.getClipData();
                    Log.d("URI", contentURI.toString());
                    int items = contentURI.getItemCount();
                    for (int i = 0; i < items; ++i) {
                        Uri itemUri = contentURI.getItemAt(i).getUri();
                        Log.d("ItemPath", getFileUri(itemUri));
                        dataUri.add(getFileUri(itemUri));
                    }
                } else if (data.getData() != null) {
                    Uri contentUri = data.getData();
                    try {
                        dataUri.add(getFileUri(contentUri));
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentUri);
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
            }

        } /*else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            image = true;
            saveImage(thumbnail);
            ImageView image = new ImageView(AddProductsActivity.this);
            image.setImageBitmap(thumbnail);
            sv1.addView(image);
        }*/
    }

    /**
     * gives the file uri from the item uri.
     * It is necessary for us to get the file uri to be able to send the file to the server
     *
     * @param itemUri
     * @return
     */
    private String getFileUri(Uri itemUri) {
        String fileUri = FileUriHelper.Companion.getFileUri(itemUri, context);
        return fileUri;
    }

    /**
     * It helps us to save the image taken from the camera on the SD card can we can use it in future times too
     *
     * @param myBitmap
     * @return
     */
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
            if (isStoragePermissionGranted()) {
                if (wallpaperDirectory.createNewFile()) {
                    Log.d("TAG", "File Saved::--->" + wallpaperDirectory.getAbsolutePath());
                    return wallpaperDirectory.getAbsolutePath();
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    /**
     * This function is required to get the permissions from the user read from the
     * external source
     *
     * @return
     */
    public boolean isReadPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Tag", "Permission is granted");
                return true;
            } else {

                Log.v("Tag", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Tag", "Permission is granted");
            return true;
        }
    }

    /**
     * This function is required to get the permissions from the user write on the
     * external source
     *
     * @return
     */
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
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddProductsActivity.this, HomePageActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

}
