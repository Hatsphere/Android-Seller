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
    private String mProductDate;
    private String mOrderID;
    private int mProductImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;


    public Order(String mProductName, String mProductDate, String mOrderId, String mTypeOfRequest,
                 int mNumOfRequest) {
        this.mProductDate = mProductDate;
        this.mProductName = mProductName;
        this.mTypeOfRequest = mTypeOfRequest;
        this.mNumOfRequest = mNumOfRequest;
        this.mOrderID = mOrderId;
        //this.mProductImageResourceId = mProductImageResourceId;
    }

    public String getmOrderID() {
        return mOrderID;
    }

    public void setmOrderID(String mOrderID) {
        this.mOrderID = mOrderID;
    }

    public String getmProductName() {
        return mProductName;
    }

    public String getmProductDate() {
        return mProductDate;
    }

    public String getmTypeOfRequest() {
        return mTypeOfRequest;
    }


    public int getmNum() {
        return mNumOfRequest;
    }

    public int getmProductImageResourceId() {
        return mProductImageResourceId;
    }

    public boolean hasImage1() {
        boolean result = mProductImageResourceId != NO_IMAGE_PROVIDED;
        return result;
    }
}
