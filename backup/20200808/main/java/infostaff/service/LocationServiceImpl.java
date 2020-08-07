package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infostaff.entity.TblLocationEntity;
import infostaff.mapping.LocationMapping;
import infostaff.model.LocationModel;
import infostaff.repository.TblLocationRepository;

@Service
public class LocationServiceImpl implements ILocationService{

	@Autowired
	TblLocationRepository repo;
	
	@Autowired
	LocationMapping mapping;
	
	@Override
	public List<LocationModel> getAll() {
		
		List<TblLocationEntity> entities = repo.findAll();

		if (entities == null || entities.isEmpty())
			return null;

		LocationModel model;
		List<LocationModel> models = new ArrayList<LocationModel>();

		for (TblLocationEntity entity : entities) {
			model = mapping.entityToModel(entity);

			if (model != null) {
				models.add(model);
			}
		}

		return models;
	}

}
