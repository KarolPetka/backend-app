package com.example.backend_lab2.task2_task4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class View {

    @GetMapping("/example")
    @ResponseBody
    public ModelAndView example(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("example");
        return modelAndView;
    }
}
