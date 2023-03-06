package com.sda.study.springbootpractice.controllers;

import com.sda.study.springbootpractice.exceptions.CourseNotFoundException;
import com.sda.study.springbootpractice.exceptions.SchoolNotFoundException;
import com.sda.study.springbootpractice.models.School;
import com.sda.study.springbootpractice.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String showSchoolListPage(Model model, @ModelAttribute("message")String message, @ModelAttribute("messageType") String messageType) { // to send data always need model
        model.addAttribute("schools", schoolService.findAllSchools()); // assigns data to attribute name schools
        return "school/list-school";
    }
    @GetMapping("/{id}") // path variable
    public String showSchoolViewPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("school", schoolService.findSchoolById(id));
            return "school/view-school";
        } catch (SchoolNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteSchool(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            schoolService.deleteSchoolById(id);
            redirectAttributes.addFlashAttribute("message", String.format("School #%d deleted successfully", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/school"; // if you want to redirect page back
        } catch (SchoolNotFoundException | CourseNotFoundException e) {
            return handleException(redirectAttributes, e);
        }

    }

    @GetMapping("/restore/{id}")
    public String restoreSchool(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            schoolService.restoreSchoolById(id);
            redirectAttributes.addFlashAttribute("message", String.format("School #%d restored successfully", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/school"; // if you want to redirect page back
        } catch (SchoolNotFoundException | CourseNotFoundException e) {
            return handleException(redirectAttributes, e);
        }

    }


    @GetMapping("/create/{id}")
    public String createSchoolPage(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        schoolService.createSchool(new School());
        redirectAttributes.addFlashAttribute("message", String.format("School #name=%s created successfully", id));
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "/school/create-school";

    }

    /*
    @GetMapping("/update/{id}")
    public String updateSchool(@ModelAttribute School school, @PathVariable Long id,  RedirectAttributes redirectAttributes) {
        try {
            schoolService.updateSchool(school);
            redirectAttributes.addFlashAttribute("message", String.format("School #%d is updated successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/school-update";
        } catch (SchoolNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

     */
    @GetMapping("/update/{name}")
    public String updateSchoolPage(@PathVariable String name, RedirectAttributes redirectAttributes) {
        try {
            schoolService.updateSchool(schoolService.findSchoolByName(name));
            redirectAttributes.addFlashAttribute("message", String.format("School #name=%s updated successfully", name));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "/school/update-school";
        } catch (SchoolNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }



    // PRIVATE METHODS
    private String handleException(RedirectAttributes redirectAttributes, Exception e) {
        redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/school";
    }


}
