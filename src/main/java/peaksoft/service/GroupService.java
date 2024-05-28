package peaksoft.service;

import peaksoft.entity.Company;
import peaksoft.entity.Group;

import java.util.List;

public interface GroupService {

    void saveGroup(Group group);
    Group getById(Long id);
    List<Group> getAllGroups();
    List<Group> getAllGroupsByCourseId(Long id);
    List<Group> getAllGroupsByCompanyId(Long id);
    void updateById(Long id, Group newGroup);
    void deleteGroupById(Long id);
}
