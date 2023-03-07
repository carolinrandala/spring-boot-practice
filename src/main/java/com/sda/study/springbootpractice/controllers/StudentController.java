package com.sda.study.springbootpractice.controllers;

import com.sda.study.springbootpractice.exceptions.StudentNotFoundException;
import com.sda.study.springbootpractice.models.Student;
import com.sda.study.springbootpractice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String showStudentListPage(Model model, @ModelAttribute("message")String message, @ModelAttribute("messageType") String messageType) { // to send data always need model
        model.addAttribute("students", studentService.findAllStudents()); // assigns data to attribute name schools
        return "student/list-student";
    }
    @GetMapping("/{id}") // path variable
    public String showStudentViewPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("student", studentService.findStudentById(id));
            return "student/view-student";
        } catch (StudentNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }
    // PRIVATE METHODS
    private String handleException(RedirectAttributes redirectAttributes, Exception e) {
        redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudentById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Student #%d deleted successfully", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/student"; // if you want to redirect page back
        } catch (StudentNotFoundException e) {
            return handleException(redirectAttributes, e);
        }

    }

    @GetMapping("/restore/{id}")
    public String restoreStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.restoreStudentById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Student #%d restored successfully", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/student"; // if you want to redirect page back
        } catch (StudentNotFoundException e) {
            return handleException(redirectAttributes, e);
        }

    }

    // To show the create student form page
    @GetMapping("/create")
    public String showCreateStudentPage(@ModelAttribute("student") Student student, @ModelAttribute("message") String message, @ModelAttribute("messageType") String messageType) {
        return "student/create-student";
    }


    @PostMapping
    public String createStudentPage(Student student, RedirectAttributes redirectAttributes) {
        try {
            Student searchStudent = studentService.findStudentByName(student.getName());
            redirectAttributes.addFlashAttribute("message", String.format("Student (%s) already exists!", student.getName()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/student/create-student";
        } catch (StudentNotFoundException e) {
            studentService.createStudent(student);
            redirectAttributes.addFlashAttribute("message", String.format("Student (%s) has been created successfully!", student.getName()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/student";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateStudentPage(@PathVariable Long id, RedirectAttributes redirectAttributes,
                                       @RequestParam(value = "student", required = false) Student student, // only if you update the record
                                       Model model){
        if (student == null) {
            try {
                model.addAttribute("student", studentService.findStudentById(id));
            } catch (StudentNotFoundException e) {
                return handleException(redirectAttributes, e);
            }
        }
        return "student/update-student";
    }

    @PostMapping("/update")
    public String updateStudent(Student student, RedirectAttributes redirectAttributes) {
        try {
            studentService.updateStudent(student);
            redirectAttributes.addFlashAttribute("message", String.format("Student (id=%d) has been updated successfully!", student.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/student";
        } catch (StudentNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }


}
