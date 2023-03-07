package com.sda.study.springbootpractice.controllers;


import com.sda.study.springbootpractice.exceptions.TeacherNotFoundException;
import com.sda.study.springbootpractice.models.Teacher;
import com.sda.study.springbootpractice.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String showTeacherListPage(Model model, @ModelAttribute("message")String message, @ModelAttribute("messageType") String messageType) { // to send data always need model
        model.addAttribute("teachers", teacherService.findAllTeachers()); // assigns data to attribute name schools
        return "teacher/list-teacher";
    }
    @GetMapping("/{id}") // path variable
    public String showTeacherViewPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("teacher", teacherService.findTeacherById(id));
            return "teacher/view-teacher";
        } catch (TeacherNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }
    // PRIVATE METHODS
    private String handleException(RedirectAttributes redirectAttributes, Exception e) {
        redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/teacher";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
           teacherService.deleteTeacherById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Teacher #%d deleted successfully", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/teacher";
        } catch (TeacherNotFoundException e) {
            return handleException(redirectAttributes, e);
        }

    }

    @GetMapping("/restore/{id}")
    public String restoreTeacher(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            teacherService.restoreTeacherById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Teacher #%d restored successfully", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/teacher"; // if you want to redirect page back
        } catch (TeacherNotFoundException e) {
            return handleException(redirectAttributes, e);
        }

    }

    // To show the create student form page
    @GetMapping("/create")
    public String showCreateTeacherPage(@ModelAttribute("teacher") Teacher teacher, @ModelAttribute("message") String message, @ModelAttribute("messageType") String messageType) {
        return "teacher/create-teacher";
    }


    @PostMapping
    public String createTeacherPage(Teacher teacher, RedirectAttributes redirectAttributes) {
        try {
            Teacher searchTeacher = teacherService.findTeacherByName(teacher.getName());
            redirectAttributes.addFlashAttribute("message", String.format("Teacher (%s) already exists!", teacher.getName()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/teacher/create-teacher";
        } catch (TeacherNotFoundException e) {
            teacherService.createTeacher(teacher);
            redirectAttributes.addFlashAttribute("message", String.format("Teacher (%s) has been created successfully!", teacher.getName()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/teacher";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateTeacherPage(@PathVariable Long id, RedirectAttributes redirectAttributes,
                                        @RequestParam(value = "teacher", required = false) Teacher teacher,
                                        Model model){
        if (teacher == null) {
            try {
                model.addAttribute("teacher", teacherService.findTeacherById(id));
            } catch (TeacherNotFoundException e) {
                return handleException(redirectAttributes, e);
            }
        }
        return "teacher/update-teacher";
    }

    @PostMapping("/update")
    public String updateTeacher(Teacher teacher, RedirectAttributes redirectAttributes) {
        try {
            teacherService.updateTeacher(teacher);
            redirectAttributes.addFlashAttribute("message", String.format("Teacher (id=%d) has been updated successfully!", teacher.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/teacher";
        } catch (TeacherNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

}
