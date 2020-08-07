package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infostaff.entity.TblDepartmentEntity;
import infostaff.mapping.DepartmentMapping;
import infostaff.model.DepartmentModel;
import infostaff.repository.TblDepartmentRepository;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
	TblDepartmentRepository repo;
	
	@Autowired
	DepartmentMapping mapping;
	
	@Override
	public List<DepartmentModel> getAll() {
		
		List<TblDepartmentEntity> entities = repo.findAll();

		if (entities == null || entities.isEmpty())
			return null;

		DepartmentModel model;
		List<DepartmentModel> models = new ArrayList<DepartmentModel>();

		for (TblDepartmentEntity entity : entities) {
			model = mapping.entityToModel(entity);

			if (model != null) {
				models.add(model);
			}
		}

		return models;
	}

}
