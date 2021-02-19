package com.aiyi.blog.entity;

import com.aiyi.core.annotation.po.ID;
import com.aiyi.core.annotation.po.TableName;
import com.aiyi.core.annotation.po.vali.Validation;
import com.aiyi.core.annotation.po.vali.enums.ValidationType;
import com.aiyi.core.beans.PO;
import com.aiyi.core.util.MD5;

@TableName(name = "blog_user")
public class User extends PO {

    @ID
    private int id;

    @Validation(/*value = ValidationType.Email,*/ name = "用户名")
    private String email;

    private String nicker;

    @Validation(name = "密码")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
