package com.hhx.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BrowserScurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic() //配置HttpBasic
        http.formLogin() //配置表单登录
                .and()
                .authorizeRequests()  //配置身份认证
                .anyRequest()  //所有身份都需要登录
                .authenticated();  //所有请求都需要验证
    }
}
