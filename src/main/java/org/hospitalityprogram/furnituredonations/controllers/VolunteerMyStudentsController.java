package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.StudentRepository;
import org.hospitalityprogram.furnituredonations.data.VolunteerRepository;
import org.hospitalityprogram.furnituredonations.models.StudentProfile;
import org.hospitalityprogram.furnituredonations.models.VolunteerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("volunteer/students")
public class VolunteerMyStudentsController {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public String viewMyStudents(HttpSession session, Model model) {
        int userId = (int)session.getAttribute("user");
        VolunteerProfile volunteer = volunteerRepository.findByUserId(userId);

        model.addAttribute("myStudents", volunteer.getStudents());
        model.addAttribute("allStudents", studentRepository.findAll());
        model.addAttribute("title", "My Students");
        return "volunteer/students/index";
    }

    @GetMapping("edit")
    public String editMyStudents(HttpSession session, Model model) {
        int userId = (int)session.getAttribute("user");
        VolunteerProfile volunteer = volunteerRepository.findByUserId(userId);
        List<StudentProfile> selectableStudents = studentRepository.findUnclaimedAndMine(volunteer.getId());
        Collections.reverse(selectableStudents);

        model.addAttribute("selectableStudents", selectableStudents);
        model.addAttribute("allStudents", studentRepository.findAll());
        model.addAttribute("title", "Manage Students");
        return "volunteer/students/edit";
    }

    @PostMapping("edit")
    public String processMyStudents(@RequestParam(required = false) int[] selectedStudents, HttpSession session, Model model) {
        int userId = (int)session.getAttribute("user");
        VolunteerProfile volunteer = volunteerRepository.findByUserId(userId);
        List<StudentProfile> students = volunteer.getStudents();
        for (StudentProfile student : students) {
            student.setVolunteer(null);
//            studentRepository.save(student);
        }
        volunteer.removeAllStudents();

        for (int i=0; i < selectedStudents.length ; i++) {
            Optional<StudentProfile> result = studentRepository.findById(selectedStudents[i]);
            StudentProfile claimedStudent = result.get();
            claimedStudent.setVolunteer(volunteer);
            studentRepository.save(claimedStudent);
            volunteer.addStudent(claimedStudent);
        }
        volunteerRepository.save(volunteer);

        model.addAttribute("title", "Manage Students");
        return "redirect:/volunteer/students";
    }
}
