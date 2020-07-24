package com.springsecurity.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DemoController {

    @GetMapping("/")
    fun showHome() : String {
        return "home"
    }

    @GetMapping("/leaders")
    fun showLeaders() : String {
        return "leaders"
    }

    @GetMapping("/systems")
    fun showSystems() : String {
        return "systems"
    }
}