package com.example.yashladha.android_seller.data;

import android.graphics.drawable.Drawable;

/**
 * Created by dell pc on 22-10-2017.
 */

public class Product {

    private String mProductName;
    private String mProductRating;
    private String mProductNewPrice;
    private String mProductOriginalPrice;
    private String mProductDiscount;
    private String mExchange;
    private String mYesNo;
    private String primaryImage ;
    private int mProductRemoveImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Product(String mProductName, String mProductRating, String mProductNewPrice, String mProductOriginalPrice, String mProductDiscount, String mExchange, String mYesNo, String primaryImage, int mProductRemoveImageResourceId) {
        this.mProductName = mProductName;
        this.mProductRating = mProductRating;
        this.mProductNewPrice = mProductNewPrice;
        this.mProductOriginalPrice = mProductOriginalPrice;
        this.mProductDiscount = mProductDiscount;
        this.mExchange = mExchange;
        this.mYesNo = mYesNo;
        this.primaryImage = primaryImage;
        this.mProductRemoveImageResourceId = mProductRemoveImageResourceId;
    }

    String getmProductName() {
        return mProductName;
    }

    public void setmProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public String getmProductRating() {
        return mProductRating;
    }

    public void setmProductRating(String mProductRating) {
        this.mProductRating = mProductRating;
    }

    public String getmProductNewPrice() {
        return mProductNewPrice;
    }

    public void setmProductNewPrice(String mProductNewPrice) {
        this.mProductNewPrice = mProductNewPrice;
    }

    public String getmProductOriginalPrice() {
        return mProductOriginalPrice;
    }

    public void setmProductOriginalPrice(String mProductOriginalPrice) {
        this.mProductOriginalPrice = mProductOriginalPrice;
    }

    public String getmProductDiscount() {
        return mProductDiscount;
    }

    public void setmProductDiscount(String mProductDiscount) {
        this.mProductDiscount = mProductDiscount;
    }

    public String getmExchange() {
        return mExchange;
    }

    public void setmExchange(String mExchange) {
        this.mExchange = mExchange;
    }

    public String getmYesNo() {
        return mYesNo;
    }

    public void setmYesNo(String mYesNo) {
        this.mYesNo = mYesNo;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public int getmProductRemoveImageResourceId() {
        return mProductRemoveImageResourceId;
    }

    public void setmProductRemoveImageResourceId(int mProductRemoveImageResourceId) {
        this.mProductRemoveImageResourceId = mProductRemoveImageResourceId;
    }

    public static int getNoImageProvided() {
        return NO_IMAGE_PROVIDED;
    }
}
