package com.maple.touriseguide.Util;

import java.io.Serializable;

/**
 * Created by Maple on 2017/11/26.
 */

public class MarkerInfoUtil implements Serializable {
    private static final long serialVersionUID = 8633299996744734593L;

    private double latitude;//纬度
    private double longitude;//经度
    private String user_name;//名字
    //private int imgId;//图片
    private String phone;//电话

    //构造方法
    public MarkerInfoUtil() {
    }

    public MarkerInfoUtil(double latitude, double longitude, String user_names, String phone) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.user_name = user_name;
        //this.imgId = imgId;
        this.phone = phone;
    }

    //toString方法
    @Override
    public String toString() {
        return "MarkerInfoUtil [latitude=" + latitude + ", longitude=" + longitude + ", user_name=" + user_name + ", phone=" + phone + "]";
    }

    //getter setter
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /*public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }*/

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
