package com.it.app.controller;

import com.it.app.service.TypeOfCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class TypeOfCourseController {
    @Autowired
    TypeOfCourseService typeOfCourseService;

}
