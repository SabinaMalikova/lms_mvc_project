package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import peaksoft.entity.Group;
import peaksoft.repository.GroupRepo;
import peaksoft.service.GroupService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepo groupRepo;

    @Override
    public void saveGroup(Group group) {
        groupRepo.saveGroup(group);
    }

    @Override
    public Group getById(Long id) {
        return groupRepo.getById(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepo.getAllGroups();
    }

    @Override
    public List<Group> getAllGroupsByCourseId(Long id) {
        return groupRepo.getAllGroupsByCourseId(id);
    }

    @Override
    public List<Group> getAllGroupsByCompanyId(Long id) {
        return null;
    }

    @Override
    public void updateById(Long id, Group newGroup) {
        groupRepo.updateById(id,newGroup);
    }

    @Override
    public void deleteGroupById(Long id) {
        groupRepo.deleteGroupById(id);
    }
}
