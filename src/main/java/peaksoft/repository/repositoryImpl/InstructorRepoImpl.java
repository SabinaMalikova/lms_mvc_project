package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.InstructorRepo;

import java.util.List;

@Repository
@Transactional
public class InstructorRepoImpl implements InstructorRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveInstructor(Instructor instructor) {
        try{
            entityManager.persist(instructor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Instructor getById(Long id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return entityManager.createQuery("select i from Instructor i", Instructor.class).getResultList();
    }

    @Override
    public List<Instructor> getAllInstructorsByCompanyId(Long id) {
            return entityManager.createQuery("select i from Instructor i inner join i.companies c where c.id = :id",Instructor.class)
                    .setParameter("id", id).getResultList();
    }

    @Override
    public List<Instructor> getAllInstructorsByCourseId(Long id) {
        return entityManager.createQuery("select i from Instructor i inner join Course c on i.course.id = c.id where c.id = :id")
                .setParameter("id", id).getResultList();
    }

    @Override
    public void updateById(Long id, Instructor newInstructor) {
        try {
            Instructor oldInstructor = entityManager.find(Instructor.class, id);
            oldInstructor.setFirstName(newInstructor.getFirstName());
            oldInstructor.setLastName(newInstructor.getLastName());
            oldInstructor.setPhoneNumber(newInstructor.getPhoneNumber());
            oldInstructor.setSpecialization(newInstructor.getSpecialization());
            entityManager.merge(oldInstructor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteInstructorById(Long id) {
        try {
            entityManager.remove(entityManager.find(Instructor.class,id));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteInstructorFromCourse(Long InstructorId, Long CourseId) {
        try{
            Instructor instructor = entityManager.find(Instructor.class, InstructorId);
            Course course = entityManager.find(Course.class, CourseId);
            instructor.setCourse(null);
            course.getInstructors().remove(instructor);
            entityManager.merge(course);
            entityManager.merge(instructor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void assignInstructorToCourse(Long courseId, Long instructorId) {
        try {
            Course course = entityManager.find(Course.class, courseId);
            Instructor instructor = entityManager.find(Instructor.class, instructorId);

            course.getInstructors().add(instructor);
            instructor.setCourse(course);

            Company company = course.getCompany();
            if (company != null) {
                instructor.getCompanies().add(company);
            }

            entityManager.merge(instructor);
            entityManager.merge(course);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
