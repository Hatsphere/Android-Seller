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

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public String getDel_date() {
        return del_date;
    }

    public void setDel_date(String del_date) {
        this.del_date = del_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getmAccept() {
        return mAccept;
    }

    public void setmAccept(String mAccept) {
        this.mAccept = mAccept;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public static int getNoImageProvided() {
        return NO_IMAGE_PROVIDED;
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

    /*public String getmProductDate() {
        return order_date;

    }*/

    public String getmTypeOfRequest() {
        return status;
    }


    public int getmNum() {
        return quantity;
    }


}
