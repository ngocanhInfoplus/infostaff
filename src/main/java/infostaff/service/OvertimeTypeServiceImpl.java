package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infostaff.entity.TblOvertimeTypeEntity;
import infostaff.mapping.OvertimeTypeMapping;
import infostaff.model.OvertimeTypeModel;
import infostaff.repository.TblOvertimeTypeRepository;

@Service
public class OvertimeTypeServiceImpl implements IOvertimeTypeSevice{

	@Autowired
	TblOvertimeTypeRepository repo;
	
	@Override
	public List<OvertimeTypeModel> getAll() {
		List<TblOvertimeTypeEntity> entities = repo.findAll();

		if (entities == null || entities.isEmpty())
			return null;

		OvertimeTypeModel model;
		List<OvertimeTypeModel> models = new ArrayList<OvertimeTypeModel>();
		OvertimeTypeMapping mapping = new OvertimeTypeMapping();

		for (TblOvertimeTypeEntity entity : entities) {
			model = mapping.entityToModel(entity);

			if (model != null) {
				models.add(model);
			}
		}

		return models;
	}

}
