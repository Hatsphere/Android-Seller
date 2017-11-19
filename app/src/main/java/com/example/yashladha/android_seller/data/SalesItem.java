package com.example.yashladha.android_seller.data;

/**
 * Created by dell pc on 23-10-2017.
 */

public class SalesItem {

    private String mProductName;
    private String mNum;
    private String primaryImage;
    private String mTotalAmount;
    private String mAmount;

    private String  mProductImageResourceId = "";
    private static final int NO_IMAGE_PROVIDED = -1;

    public SalesItem(String mProductName,
                     String mNum,  String mAmount,String mTotalAmount, String mProductImageResourceId) {
        this.mProductName = mProductName;
        this.mNum = mNum;
        this.mTotalAmount = mTotalAmount;
        this.primaryImage = mProductImageResourceId;
        this.mAmount = mAmount;
        this.mProductImageResourceId = mProductImageResourceId;
    }

    public String getmProductName() {
        return mProductName;
    }
    public String getmTotalAmount() {
        return mTotalAmount;
    }


        public String getmNum() {
        return mNum;
    }
    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public String getmAmount() {
        return mAmount;
    }

    public String getmProductImageResourceId() {
        return mProductImageResourceId;
    }

    public static int getNoImageProvided() {
        return NO_IMAGE_PROVIDED;
    }


}
