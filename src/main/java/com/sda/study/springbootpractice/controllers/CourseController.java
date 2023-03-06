package com.sda.study.springbootpractice.controllers;

import com.sda.study.springbootpractice.exceptions.CourseNotFoundException;
import com.sda.study.springbootpractice.exceptions.SchoolNotFoundException;
import com.sda.study.springbootpractice.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String showCourseListPage(Model model, @ModelAttribute("message")String message, @ModelAttribute("messageType") String messageType) { // to send data always need model
        model.addAttribute("schools", courseService.findAllCourses()); // assigns data to attribute name schools
        return "school/list-school";
    }
    @GetMapping("/{id}") // path variable
    public String showCourseViewPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("school", courseService.findCourseById(id));
            return "school/view-school";
        } catch (CourseNotFoundException e) {
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
