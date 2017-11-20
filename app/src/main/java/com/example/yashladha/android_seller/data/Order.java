package com.example.yashladha.android_seller.data;

/**
 * Created by dell pc on 22-10-2017.
 */

public class Order {

    private String order_date;
    private String pay_id;
    private String del_date;
    private String status;
    private String mAccept;
    private int quantity;
    private String productName;
    private String sellerId;
    private String order_id;
    private String uid;
    private static final int NO_IMAGE_PROVIDED = -1;



    public Order(String order_date, String pay_id, String productName, String status, String order_id, String uid, String del_date,
                 int quantity, String sellerId) {
        this.order_date = order_date;
        this.pay_id = pay_id;
        this.productName = productName;
        this.status = status;
        this.order_id = order_id;
        this.uid = uid;
        this.del_date = del_date;
        this.quantity = quantity;
        this.sellerId = sellerId;

        //this.mProductImageResourceId = mProductImageResourceId;
    }

    public String getmOrderID() {
        return order_id;
    }

    public void setmOrderID(String mOrderID) {
        this.order_id = mOrderID;
    }

    public String getmProductName() {
        return productName;
    }

    public String getmProductDate() {
        return order_date;

    }

    public String getmTypeOfRequest() {
        return status;
    }


    public int getmNum() {
        return quantity;
    }


}
