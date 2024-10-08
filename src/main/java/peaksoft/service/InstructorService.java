package peaksoft.service;

import peaksoft.entity.Company;
import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {
    void saveInstructor(Instructor instructor);
    Instructor getById(Long id);
    List<Instructor> getAllInstructors();
    List<Instructor> getAllInstructorsByCompanyId(Long id);
    List<Instructor> getAllInstructorsByCourseId(Long id);
    void  updateById(Long id, Instructor newInstructor);
    void deleteInstructorById(Long id);
    void deleteInstructorFromCourse(Long InstructorId, Long CourseId);
    void assignInstructorToCourse(Long courseId, Long instructorId);


}
