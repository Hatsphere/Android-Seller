package com.example.yashladha.android_seller.data;

/**
 * Created by dell pc on 22-10-2017.
 */

public class Order {

    private String mProductName;
    private String mProductPrice;
    private String mDate;
    private String mAccept;
    private String mStatus;

    private int mProductImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Order(String mProductName, String mProductPrice, String mDate, String mAccept,
                   String mStatus, int mProductImageResourceId) {
        this.mProductName = mProductName;
        this.mProductPrice = mProductPrice;
        this.mDate = mDate;
        this.mAccept = mAccept;
        this.mStatus = mStatus;
        this.mProductImageResourceId = mProductImageResourceId;
    }

    public String getmProductName() {
        return mProductName;
    }

    public String getmProductPrice() {
        return mProductPrice;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmAccept() {
        return mAccept;
    }

    public String getmStatus() {
        return mStatus;
    }

    public int getmProductImageResourceId() {
        return mProductImageResourceId;
    }

    public boolean hasImage1() {
        boolean result = mProductImageResourceId != NO_IMAGE_PROVIDED;
        return result;
    }
}
