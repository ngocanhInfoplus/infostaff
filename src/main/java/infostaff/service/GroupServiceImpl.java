package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infostaff.entity.TblGroupEntity;
import infostaff.mapping.GroupMapping;
import infostaff.model.GroupModel;
import infostaff.repository.TblGroupRepository;

@Service
public class GroupServiceImpl implements IGroupService{
	
	@Autowired
	TblGroupRepository repo;
	
	@Autowired
	GroupMapping mapping;

	@Override
	public List<GroupModel> getAll() {
		List<TblGroupEntity> entities = repo.findAll();

		if (entities == null || entities.isEmpty())
			return null;

		GroupModel model;
		List<GroupModel> models = new ArrayList<GroupModel>();

		for (TblGroupEntity entity : entities) {
			model = mapping.entityToModel(entity);

			if (model != null) {
				models.add(model);
			}
		}

		return models;
	}

	@Override
	public List<GroupModel> getAllByDepartment(String departmentCode) {
		
		List<TblGroupEntity> entities = repo.findByDepartmentCode(departmentCode);

		if (entities == null || entities.isEmpty())
			return null;

		GroupModel model;
		List<GroupModel> models = new ArrayList<GroupModel>();

		for (TblGroupEntity entity : entities) {
			model = mapping.entityToModel(entity);

			if (model != null) {
				models.add(model);
			}
		}

		return models;
	}

}
