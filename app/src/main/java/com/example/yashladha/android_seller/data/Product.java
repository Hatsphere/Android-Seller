package com.example.yashladha.android_seller.data;

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
    private int mProductImageResourceId = NO_IMAGE_PROVIDED;
    private int mProductRemoveImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Product(String mProductName, String mProductRating, String mProductNewPrice,
                   String mProductOriginalPrice, String mProductDiscount, String mExchange,
                   String mYesNo, int mProductImageResourceId, int mProductRemoveImageResourceId)
    {

        this.mProductName = mProductName;
        this.mProductRating = mProductRating;
        this.mProductNewPrice = mProductNewPrice;
        this.mProductOriginalPrice = mProductOriginalPrice;
        this.mProductDiscount = mProductDiscount;
        this.mExchange = mExchange;
        this.mYesNo = mYesNo;
        this.mProductImageResourceId = mProductImageResourceId;
        this.mProductRemoveImageResourceId = mProductRemoveImageResourceId;
    }

    public String getmProductName() {
        return mProductName;
    }

    public String getmProductRating() {return mProductRating;}

    public String getmProductNewPrice() {
        return mProductNewPrice;
    }

    public String getmProductOriginalPrice() {
        return mProductOriginalPrice;
    }

    public String getmProductDiscount() {
        return mProductDiscount;
    }

    public String getmExchange() {
        return mExchange;
    }

    public String getmYesNo() {
        return mYesNo;
    }

    public int getmProductImageResourceId() {
        return mProductImageResourceId;
    }

    public int getmProductRemoveImageResourceId() {
        return mProductRemoveImageResourceId;
    }

    public static int getNoImageProvided() {
        return NO_IMAGE_PROVIDED;
    }

    public boolean hasImage2()
    {
        boolean result = mProductRemoveImageResourceId!=NO_IMAGE_PROVIDED;
        return result;
    }

    public boolean hasImage1()
    {
        boolean result = mProductImageResourceId!=NO_IMAGE_PROVIDED;
        return result;
    }
}
