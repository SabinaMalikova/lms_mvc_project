package peaksoft.service;

import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import java.util.List;

public interface CourseService {
    void saveCourse(Course course);
    Course getById(Long id);
    List<Course> getAllCourses();
    List<Course> getAllCoursesByCompanyId(Long id);
    void  updateById(Long id, Course newCourse);
    void deleteCourseById(Long id);
}
