package com.example.yashladha.android_seller.data;

/**
 * Created by dell pc on 23-10-2017.
 */

public class SalesItem {

    private String mProductName;
    private String mProductCode;
    private String mNumItemsSold;
    private String mNum;
    private String mTotalAmount;
    private String mAmount;

    private int mProductImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public SalesItem(String mProductName, String mProductCode, String mNumItemsSold,
                     String mNum, String mTotalAmount, String mAmount, int mProductImageResourceId) {
        this.mProductName = mProductName;
        this.mProductCode = mProductCode;
        this.mNumItemsSold = mNumItemsSold;
        this.mNum = mNum;
        this.mTotalAmount = mTotalAmount;
        this.mAmount = mAmount;
        this.mProductImageResourceId = mProductImageResourceId;
    }

    public String getmProductName() {
        return mProductName;
    }

    public String getmProductCode() {
        return mProductCode;
    }

    public String getmNumItemsSold() {
        return mNumItemsSold;
    }

    public String getmNum() {
        return mNum;
    }

    public String getmTotalAmount() {
        return mTotalAmount;
    }

    public String getmAmount() {
        return mAmount;
    }

    public int getmProductImageResourceId() {
        return mProductImageResourceId;
    }

    public static int getNoImageProvided() {
        return NO_IMAGE_PROVIDED;
    }

    public boolean hasImage1() {
        boolean result = mProductImageResourceId != NO_IMAGE_PROVIDED;
        return result;
    }
}
