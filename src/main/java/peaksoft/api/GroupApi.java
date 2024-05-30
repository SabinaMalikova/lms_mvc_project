package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

import java.util.List;

@Controller
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupApi {

    private final GroupService groupService;
    private final CourseService courseService;

    @GetMapping("/{courseId}")
    public String getAllGroupsByCourseId(@PathVariable Long courseId, Model model) {
        List<Group> groups = groupService.getAllGroupsByCourseId(courseId);
        model.addAttribute("groups", groups);
        model.addAttribute("courseId", courseId);
        return "allGroupsByCourseId";
    }

    @GetMapping("/getAll/{companyId}")
    public String getAllGroupsByCompanyId(@PathVariable Long companyId, Model model) {
        List<Group> groups = groupService.getAllGroupsByCompanyId(companyId);
        model.addAttribute("allGroups", groups);
        model.addAttribute("companyId", companyId);
        return "allGroups";
    }

    @GetMapping("/new/{companyId}")
    public String showCreateGroupForm(@PathVariable Long companyId, Model model) {
        List<Course> courses = courseService.getAllCoursesByCompanyId(companyId);
        model.addAttribute("newGroup", new Group());
        model.addAttribute("courses", courses);
        model.addAttribute("companyId", companyId);
        return "newGroup";
    }

    @PostMapping("/save")
    public String saveGroup(@ModelAttribute("newGroup") Group group, @RequestParam Long companyId) {
        groupService.saveGroup(group);
        return "redirect:/groups/getAll/{companyId}";
    }
}

