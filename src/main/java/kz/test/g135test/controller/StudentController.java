package kz.test.g135test.controller;

import kz.test.g135test.db.DBConnector;
import kz.test.g135test.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @GetMapping(value = "/")
    public String getIndex(Model model){
        model.addAttribute("students", DBConnector.getListStudents());
        return "index";
    }

    @GetMapping(value = "/add-student")
    public String getAddPage(){
        return "add-page";
    }

    @PostMapping(value = "/add-student")
    public String addStudent(Student student){
        DBConnector.addStudent(student);
        return "redirect:/";
    }


}
