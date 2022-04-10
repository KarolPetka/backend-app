package com.example.backend_lab2.task3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;

@RestController
public class Files {

    @GetMapping(value = "/png")
    @ResponseBody
    public ModelAndView png() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("png");
        return modelAndView;
    }

    @GetMapping(value = "/jpg")
    @ResponseBody
    public ModelAndView jpg() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jpg");
        return modelAndView;
    }
}
