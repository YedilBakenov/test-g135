package kz.test.g135test.controller;

import kz.test.g135test.db.DBConnector;
import kz.test.g135test.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @GetMapping(value = "/")
    public String getIndex(Model model){
        model.addAttribute("students", DBConnector.getListStudents());
        model.addAttribute("marks", DBConnector.getAllMarks());
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

    @GetMapping(value = "/details-student/{id}")
    public String getStudent(@PathVariable long id, Model model){
        model.addAttribute("st", DBConnector.getStudent(id));
        model.addAttribute("marks", DBConnector.getAllMarks());
        return "details-st";
    }

    @PostMapping(value = "/update-student")
    public String updateStudent(Student student){
        DBConnector.updateSt(student);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-st")
    public String deleteSt(@RequestParam int id){
        DBConnector.deleteSt(id);
        return "redirect:/";
    }


}
