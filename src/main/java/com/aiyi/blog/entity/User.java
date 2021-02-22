package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.FieldName;
import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.annotation.po.TempField;
import com.aiyi.core.annotation.po.vali.Validation;
import com.aiyi.core.annotation.po.vali.enums.ValidationType;
import com.aiyi.core.beans.PO;
import com.aiyi.core.util.MD5;

@TableName(name = "bbs_user")
public class User extends PO {

    @ID
    private int id;

    @Validation(/*value = ValidationType.Email,*/ name = "用户名")
    private String phone;

    private String nicker;

    @Validation(name = "密码")
    private String password;

    @TempField
    private String smsCode;

    @TempField
    private String newPassowrd;
    /**
     * 签名
     */
    private String sign;

    @FieldName(name = "head_img")
    private String headImg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNicker() {
        return nicker;
    }

    public void setNicker(String nicker) {
        this.nicker = nicker;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getNewPassowrd() {
        return newPassowrd;
    }

    public void setNewPassowrd(String newPassowrd) {
        this.newPassowrd = newPassowrd;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
