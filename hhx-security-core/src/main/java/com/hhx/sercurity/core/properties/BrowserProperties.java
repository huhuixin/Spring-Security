package com.hhx.sercurity.core.properties;

public class BrowserProperties {

    //默认标准登录页面
    private String loginPage = "/login.html";
    //设置默认返回类型为JSON
    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
