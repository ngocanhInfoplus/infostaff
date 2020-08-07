package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infostaff.entity.TblTimeOffEntity;
import infostaff.mapping.TimeOffMapping;
import infostaff.model.TimeOffModel;
import infostaff.repository.TblTimeOffRepository;

@Service
public class TimeOffServiceImpl implements ITimeOffService{

	@Autowired
	TblTimeOffRepository repo;
	
	@Override
	public List<TimeOffModel> getAll() {
		
		List<TblTimeOffEntity> entities = repo.findAll();

		if (entities == null || entities.isEmpty())
			return null;

		TimeOffModel model;
		List<TimeOffModel> models = new ArrayList<TimeOffModel>();
		TimeOffMapping mapping = new TimeOffMapping();

		for (TblTimeOffEntity entity : entities) {
			model = mapping.entityToModel(entity);

			if (model != null) {
				models.add(model);
			}
		}

		return models;
	}

}
