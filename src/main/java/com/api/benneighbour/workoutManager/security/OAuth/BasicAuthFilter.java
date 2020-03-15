package com.api.benneighbour.workoutManager.security.OAuth;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BasicAuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, X-Requested-With, Access-Control-Request-Method, Access-Control-Request-Headers, Access-Control-Allow-Origin, Authorization");
        response.setHeader("Max-Age", "3000");

        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }

        filterChain.doFilter(request, servletResponse);

    }

}
