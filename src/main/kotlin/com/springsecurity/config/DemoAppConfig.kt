package com.springsecurity.config

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.view.InternalResourceViewResolver
import javax.sql.DataSource

@Configuration
@EnableWebMvc
@ComponentScan("com.springsecurity")
@PropertySource("classpath:persistence-postgresql.properties")
open class DemoAppConfig {

    @Autowired
    lateinit var env : Environment

    private val logger = LoggerFactory.getLogger(DemoAppConfig::javaClass.name)

    @Bean
    open fun viewResolver() : ViewResolver {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/view/")
        viewResolver.setSuffix(".jsp")
        return viewResolver
    }

    @Bean
    open fun securityDataSource() : DataSource {
        val securityDataSource = ComboPooledDataSource()
        try {
            securityDataSource.driverClass = env.getProperty("jdbc.driver")

            logger.info(">>> jdbc.url = ${env.getProperty("jdbc.url")}")
            logger.info(">>> jdbc.user = ${env.getProperty("jdbc.user")}")

            securityDataSource.jdbcUrl = env.getProperty("jdbc.url")
            securityDataSource.user = env.getProperty("jdbc.user")
            securityDataSource.password = env.getProperty("jdbc.password")

            // connection pooling not set
            securityDataSource.initialPoolSize = getIntProperty("connection.pool.initialPoolSize")
            securityDataSource.minPoolSize = getIntProperty("connection.pool.minPoolSize")
            securityDataSource.maxPoolSize = getIntProperty("connection.pool.maxPoolSize")
            securityDataSource.maxIdleTime = getIntProperty("connection.pool.maxIdleTime")

        }catch (exe : Exception){
            exe.printStackTrace()
        }
        return securityDataSource
    }

    fun getIntProperty(propName : String) : Int {
        val propVal = env.getProperty(propName.toString())
        val intPropVal = propVal!!.toInt()
        return intPropVal
    }
}