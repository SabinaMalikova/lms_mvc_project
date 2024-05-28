package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepo;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class StudentRepoImpl implements StudentRepo {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveStudent(Student Student) {
        try {
            entityManager.persist(Student);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Student getById(Long id) {
        return entityManager.find(Student.class, id);
    }


    @Override
    public List<Student> getAllStudentsByCompanyId(Long id) {
        try {
            String jpql = "select s from Student s " +
                    "join s.group g " +
                    "join g.courses c " +
                    "join c.company co " +
                    "where co.id = :companyId";
            TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
            query.setParameter("companyId", id);
            return query.getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void updateById(Long id, Student newStudent) {
        try {
            Student oldStudent = entityManager.find(Student.class, id);
            oldStudent.setFirstName(newStudent.getFirstName());
            oldStudent.setLastName(newStudent.getLastName());
            oldStudent.setPhoneNumber(newStudent.getPhoneNumber());
            oldStudent.setEmail(newStudent.getEmail());
            oldStudent.setStudyFormat(newStudent.getStudyFormat());
            entityManager.merge(oldStudent);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteStudentById(Long id) {
        try {
            Student student = entityManager.find(Student.class, id);
            List<Course> courses = student.getGroup().getCourses();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) {

    }
}
