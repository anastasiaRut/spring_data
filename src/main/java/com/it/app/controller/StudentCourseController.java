package com.it.app.controller;

import com.it.app.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentCourseController {
    @Autowired
    StudentCourseService studentCourseService;
}
