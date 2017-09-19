package com.hhx.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.hhx.validator.UsernameValidtor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    public interface SimpleUserView {};//返回列表
    public interface UserDetail extends SimpleUserView{};//返回详情

    @Id
    @GeneratedValue
    private Integer id;

    @UsernameValidtor
    @NotBlank(message = "姓名不能为空")
    @Column(length = 30,unique = true,nullable = false)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Column(length = 30,nullable = false)
    private String password;

    @Column(columnDefinition = "tinyint(1)")
    private boolean state;

    private Integer sex;


    private Date birthday;

    @JsonView(SimpleUserView.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(SimpleUserView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetail.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserDetail.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
