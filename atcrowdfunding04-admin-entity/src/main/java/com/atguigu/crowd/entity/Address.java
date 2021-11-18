package com.atguigu.crowd.entity;

public class Address {
    private String province;
    private String city;
    private String stress;

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", stress='" + stress + '\'' +
                '}';
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStress() {
        return stress;
    }

    public void setStress(String stress) {
        this.stress = stress;
    }

    public Address() {
    }

    public Address(String province, String city, String stress) {
        this.province = province;
        this.city = city;
        this.stress = stress;
    }
}
