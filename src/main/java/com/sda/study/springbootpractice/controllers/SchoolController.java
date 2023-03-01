package com.sda.study.springbootpractice.controllers;

import com.sda.study.springbootpractice.exceptions.SchoolNotFoundException;
import com.sda.study.springbootpractice.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/school")
public class SchoolController {

    // 1) STEP - binding service to controller
    @Autowired
    private SchoolService schoolService;

    // Method that will retrieve all the data from database
    // MVC - Model View Controller

    // GET operation
    @GetMapping
    public String showSchoolListPage(Model model) { // to send data always need model
        model.addAttribute("schools", schoolService.findAllSchools()); // assigns data to attribute name schools
        return "school/list-school";
    }
    @GetMapping("/{id}") // path variable
    public String showSchoolViewPage(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("school", schoolService.findSchoolById(id));
            return "school/view-school";
        } catch (SchoolNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
