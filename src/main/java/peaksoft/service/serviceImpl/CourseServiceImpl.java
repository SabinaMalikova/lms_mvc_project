package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.CourseRepo;
import peaksoft.service.CourseService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;


    @Override
    public void saveCourse(Course course) {
        courseRepo.saveCourse(course);
    }

    @Override
    public Course getById(Long id) {
        return courseRepo.getById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.getAllCourses();
    }

    @Override
    public List<Course> getAllCoursesByCompanyId(Long id) {
        return courseRepo.getAllCoursesByCompanyId(id);
    }

    @Override
    public void updateById(Long id, Course newCourse) {
        courseRepo.updateById(id,newCourse);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepo.deleteCourseById(id);
    }
}
