package com.example.yashladha.android_seller.data;

/**
 * Created by dell pc on 22-10-2017.
 */

public class Order {

    private String mProductName;
    private String mProductPrice;
    private String mTypeOfRequest;
    private String mAccept;
    private int mNumOfRequest;
    private String mNum;

    private int mProductImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Order(String mProductName, String mProductPrice, String mTypeOfRequest, String mAccept,
                  int mNumOfRequest, String mNum, int mProductImageResourceId) {
        this.mProductName = mProductName;
        this.mProductPrice = mProductPrice;
        this.mTypeOfRequest = mTypeOfRequest;
        this.mAccept = mAccept;
        this.mNumOfRequest = mNumOfRequest;
        this.mNum = mNum;
        this.mProductImageResourceId = mProductImageResourceId;
    }

    public String getmProductName() {
        return mProductName;
    }

    public String getmProductPrice() {
        return mProductPrice;
    }

    public String getmTypeOfRequest() {
        return mTypeOfRequest;
    }

    public String getmAccept() {
        return mAccept;
    }

    public int getmNumOfRequest() {
        return mNumOfRequest;
    }

    public String getmNum() {
        return mNum;
    }

    public int getmProductImageResourceId() {
        return mProductImageResourceId;
    }

    public boolean hasImage1() {
        boolean result = mProductImageResourceId != NO_IMAGE_PROVIDED;
        return result;
    }
}
