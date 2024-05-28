package peaksoft.repository;

import peaksoft.entity.Company;
import peaksoft.entity.Student;

import java.util.List;

public interface StudentRepo {
    void saveStudent(Student Student);
    Student getById(Long id);
    List<Student> getAllStudentsByCompanyId(Long id);
    void  updateById(Long id, Student newStudent);
    void deleteStudentById(Long id);
    void assignStudentToGroup(Long studentId, Long groupId);
}
