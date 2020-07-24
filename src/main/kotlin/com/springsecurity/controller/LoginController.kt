package com.springsecurity.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {

    @GetMapping("/showMyLoginPage")
    fun showMyLoginPage() : String {
        return "fancy-login"
    }

    @GetMapping("/access-denied")
    fun showAccessDeniedPage() : String {
        return "access-denied"
    }
}