package com.example.studentshop.entity;

/**
 * Created by Intellij IDEA.
 *
 * @Author haosen
 * Date:  2022/12/15
 */

import java.util.Objects;

/**
 * 地址实体类，收货地址，收货人，省代号，市代号，区代号
 * 省市区的名称，邮政编码，详细地址，手机，固话，地址类型，是否默认
 */
public class Address extends BaseEntity {
    private static final long serialVersionUID = 6946915401608396201L;

    private Integer aid;
    private Integer UID;
    private String name;
    private String province;
    private String provinceCode;
    private String city;
    private String cityCode;
    private String area;
    private String areaCode;
    private String district;
    private String address;
    private String zip;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return aid.equals(address.aid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aid);
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Address{" +
                "district='" + district + '\'' +
                '}';
    }
}
