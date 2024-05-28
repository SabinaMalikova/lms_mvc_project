package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Instructor;
import peaksoft.entity.Student;

import peaksoft.service.StudentService;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;
//
//    @GetMapping
//    public String getAllStudents(Model model){
//        model.addAttribute("allStudents", studentService.getAllStudents());
//        return "allStudents";
//    }

    @GetMapping("/{id}/getStudentsByCompanyId")
    public String getAllStudentsByCompanyId(Model model, @PathVariable("id") Long companyId){
        model.addAttribute("companyId", companyId);
        model.addAttribute("companyStudents", studentService.getAllStudentsByCompanyId(companyId));
        return "allStudents";
    }

    @GetMapping("/new")
    public String addStudent(Model model) {
        model.addAttribute("newStudent", new Student());
        return "/newStudent";
    }

    @PostMapping("/save")
    private String saveStudent(@ModelAttribute("newStudent") Student Student) {
        studentService.saveStudent(Student);
        return "redirect:/students/{id}/getStudentsByCompanyId";
    }

    @PostMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudentById(studentId);
        return "redirect:/students";
    }


    @GetMapping("/students/{id}/update")
    public String showUpdateForm(@PathVariable("id") Long studentId, Model model) {
        Student student = studentService.getById(studentId);
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @PostMapping("/{id}/updateStudent")
    public String update(@ModelAttribute("student") Student student, @PathVariable Long id) {
        studentService.updateById(id, student);
        return "redirect:/students/{id}/getStudentsByCompanyId";
    }
}
