package com.it.app.controller;

import com.it.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;
}
