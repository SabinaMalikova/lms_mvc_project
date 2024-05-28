package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Instructor;
import peaksoft.repository.InstructorRepo;
import peaksoft.service.InstructorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepo instructorRepo;

    @Override
    public void saveInstructor(Instructor instructor) {
        instructorRepo.saveInstructor(instructor);
    }

    @Override
    public Instructor getById(Long id) {
        return instructorRepo.getById(id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepo.getAllInstructors();
    }

    @Override
    public List<Instructor> getAllInstructorsByCompanyId(Long id) {
        return instructorRepo.getAllInstructorsByCompanyId(id);
    }

    @Override
    public List<Instructor> getAllInstructorsByCourseId(Long id) {
        return instructorRepo.getAllInstructorsByCourseId(id);
    }

    @Override
    public void updateById(Long id, Instructor newInstructor) {
        instructorRepo.updateById(id, newInstructor);
    }

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepo.deleteInstructorById(id);
    }

    @Override
    public void deleteInstructorFromCourse(Long InstructorId, Long CourseId) {
        instructorRepo.deleteInstructorFromCourse(InstructorId, CourseId);
    }

    @Override
    public void assignInstructorToCourse(Long courseId, Long instructorId) {
        instructorRepo.assignInstructorToCourse(courseId, instructorId);
    }
}
