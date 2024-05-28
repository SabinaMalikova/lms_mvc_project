package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.repository.GroupRepo;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class GroupRepoImpl implements GroupRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveGroup(Group group) {
        try {
            for (Course course : group.getCourses()) {
                entityManager.merge(course);
            }
            entityManager.persist(group);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Group getById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public List<Group> getAllGroups() {
        return entityManager.createQuery("select g from Group g",Group.class).getResultList();
    }

    @Override
    public List<Group> getAllGroupsByCourseId(Long id) {
        List<Group> groups = new ArrayList<>();
        try {
            groups = entityManager.createQuery("select g from Group g inner join g.courses c where c.id = :id", Group.class)
                    .setParameter("id", id).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return groups;
    }

    @Override
    public List<Group> getAllGroupsByCompanyId(Long id) {
        List<Group> groups = new ArrayList<>();
        try {
            groups = entityManager.createQuery("select g from Group g inner join g.courses c where c.company.id = :companyId", Group.class)
                    .setParameter("companyId", id).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return groups;
    }

    @Override
    public void updateById(Long id, Group newGroup) {
        try {
            Group oldGroup = entityManager.find(Group.class, id);
            oldGroup.setGroupName(newGroup.getGroupName());
            oldGroup.setImageLink(newGroup.getImageLink());
            oldGroup.setDescription(newGroup.getDescription());
            entityManager.merge(oldGroup);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteGroupById(Long id) {
        try {
            Group group = entityManager.find(Group.class, id);
            if (group != null){
                for (Course course : group.getCourses()){
                    course.setGroups(null);
                    entityManager.merge(course);
                }
                group.getCourses().clear();
                entityManager.remove(group);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
