package peaksoft.service;

import peaksoft.entity.Student;

import java.util.List;

public interface StudentService {
    void saveStudent(Student Student);
    Student getById(Long id);
    List<Student> getAllStudentsByCompanyId(Long id);
    void  updateById(Long id, Student newStudent);
    void deleteStudentById(Long id);
    void assignStudentToGroup(Long studentId, Long groupId);
}
