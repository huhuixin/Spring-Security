package com.hhx.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhx.sercurity.core.properties.LoginType;
import com.hhx.sercurity.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//SavedRequestAwareAuthenticationSuccessHandler 是spring 对AuthenticationSuccessHandle 接口的默认实现
@Component("myAuthenticationSuccessHandle")
public class MyAuthenticationSuccessHandle extends SavedRequestAwareAuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("login success");
        //根据配置的登录类型决定是跳转还是返回json
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            //转换为json字符串输出
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}
