package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;
    private final CompanyService companyService;

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("allCourses", courseService.getAllCourses());
        return "allCourses";
    }


    @GetMapping("/{id}/getCoursesByCompanyId")
    public String getAllCoursesByCompanyId(Model model, @PathVariable("id") Long comId) {
        model.addAttribute("companyId", comId);
        model.addAttribute("companyCourses", courseService.getAllCoursesByCompanyId(comId));
        return "allCompaniesCourses";
    }

    @GetMapping("/new/{companyId}")
    public String addCourse(@PathVariable Long companyId, Model model) {
        model.addAttribute("newCourse", new Course());
        model.addAttribute("companyId", companyId);
        return "newCourse";
    }

    @PostMapping("/save/{companyId}")
    public String saveCourse(@ModelAttribute("newCourse") Course course, @PathVariable Long companyId) {
        Company company = companyService.getById(companyId);
        course.setCompany(company);
        courseService.saveCourse(course);
        return "redirect:/companies/" + companyId;
    }

    @GetMapping("/{id}/delete")
    public String deleteCourse(@PathVariable("id") Long courseId){
        courseService.deleteCourseById(courseId);
        return "redirect:/courses";
    }

    @GetMapping("/{id}/update")
    public String getByIdToUpdate(Model model, @PathVariable("id") Long courseId) {
        model.addAttribute("upCourse", courseService.getById(courseId));
        return "updateCourse";
    }

    @PostMapping("/{id}/updateCourse")
    public String update(@ModelAttribute("upCourse") Course course, @PathVariable Long id) {
        courseService.updateById(id, course);
        return "redirect:/courses";
    }

}
