package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.CourseRepo;


import java.util.List;

@Repository
@Transactional
public class CourseRepoImpl implements CourseRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCourse(Course course) {
        try{
            entityManager.persist(course);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Course getById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public List<Course> getAllCourses() {
        return entityManager.createQuery("select c from Course c", Course.class).getResultList();
    }

    @Override
    public List<Course> getAllCoursesByCompanyId(Long id) {
        return entityManager.createQuery("SELECT c FROM Course c WHERE c.company.id = :id", Course.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void updateById(Long id, Course newCourse) {
        Course oldCourse = entityManager.find(Course.class, id);
        oldCourse.setCourseName(newCourse.getCourseName());
        oldCourse.setDateOfStart(newCourse.getDateOfStart());
        oldCourse.setDescription(newCourse.getDescription());
        entityManager.merge(oldCourse);
    }

    @Override
    public void deleteCourseById(Long id) {
        try {
            Course course = entityManager.find(Course.class, id);
            if (course != null) {
                for (Instructor instructor : course.getInstructors()) {
                    instructor.setCourse(null);
                    entityManager.merge(instructor);
                }
                course.getInstructors().clear();
                entityManager.remove(course);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
