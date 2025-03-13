package com.qsp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;  // No root context needed
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};  // Load Spring MVC config
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};  // Map all requests to DispatcherServlet
    }
}
