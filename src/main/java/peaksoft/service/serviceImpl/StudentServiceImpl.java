package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepo;
import peaksoft.service.StudentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;

    @Override
    public void saveStudent(Student Student) {
    studentRepo.saveStudent(Student);
    }

    @Override
    public Student getById(Long id) {
        return studentRepo.getById(id);
    }


    @Override
    public List<Student> getAllStudentsByCompanyId(Long id) {
        return studentRepo.getAllStudentsByCompanyId(id);
    }

    @Override
    public void updateById(Long id, Student newStudent) {
        studentRepo.updateById(id, newStudent);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepo.deleteStudentById(id);
    }

    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) {
        studentRepo.assignStudentToGroup(studentId, groupId);
    }
}
