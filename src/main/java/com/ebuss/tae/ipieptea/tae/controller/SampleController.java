package com.ebuss.tae.ipieptea.tae.controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/sample")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
