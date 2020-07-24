package com.springsecurity.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
open class DemoSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var securityDataSource: DataSource

    override fun configure(auth: AuthenticationManagerBuilder?) {
        /*
        val users : User.UserBuilder = User.withDefaultPasswordEncoder()
        auth?.inMemoryAuthentication()?.withUser(users.username("John").password("test123").roles("EMPLOYEE"))
                ?.withUser(users.username("Mary").password("test456").roles("EMPLOYEE", "MANAGER"))
                ?.withUser(users.username("SUSAN").password("test789").roles("EMPLOYEE", "ADMIN"))

         */
        auth?.jdbcAuthentication()?.dataSource(securityDataSource)
    }

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
                ?.antMatchers("/")?.hasRole("EMPLOYEE")
                ?.antMatchers("/leaders/**")?.hasRole("MANAGER")
                ?.antMatchers("/systems/**")?.hasRole("ADMIN")
                ?.and()
                ?.formLogin()
                    ?.loginPage("/showMyLoginPage")
                    ?.loginProcessingUrl("/authenticateTheUser")
                    ?.permitAll()
                ?.and()
                ?.logout()
                ?.permitAll()
                ?.and()
                ?.exceptionHandling()?.accessDeniedPage("/access-denied")
    }
}