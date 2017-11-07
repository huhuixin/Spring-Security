package com.hhx.security.browser;

import com.hhx.sercurity.core.properties.SecurityProperties;
import com.hhx.sercurity.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowserScurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;

    //属性名称与自定义的handle的Component名称一致
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandle;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        //http.httpBasic() //配置HttpBasic
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() //配置表单登录
                //.loginPage("/login.html") //重定向到页面
                .loginPage("/authentication/require") //重定向到Controller
                .loginProcessingUrl("/authentication/form") //对应login.html中的form提交路径
                .successHandler(myAuthenticationSuccessHandle) //自定义handle
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()  //配置身份认证
                .antMatchers("/authentication/require","/code/image"
                ,securityProperties.getBrowser().getLoginPage()).permitAll() //匹配到登录的请求时不做验证
                .anyRequest()  //所有身份都需要登录
                .authenticated() //所有请求都需要验证
                .and().csrf().disable() //去掉跨站防护功能
        ;
    }
}
