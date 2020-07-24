package com.springsecurity.config

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class MySpringMvcDispatcherServletInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getRootConfigClasses(): Array<Class<*>>? {
        return null
    }

    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }

    override fun getServletConfigClasses(): Array<Class<*>>? {
        return arrayOf(DemoAppConfig::class.java)
    }
}