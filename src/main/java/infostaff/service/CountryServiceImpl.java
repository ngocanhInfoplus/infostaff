package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infostaff.entity.TblCountryEntity;
import infostaff.mapping.CountryMapping;
import infostaff.model.CountryModel;
import infostaff.repository.TblCountryRepository;

@Service
public class CountryServiceImpl implements ICountryService{
	
	@Autowired
	TblCountryRepository repo;
	
	@Autowired
	CountryMapping mapping;
	
	@Override
	public List<CountryModel> getAll() {
		
		List<TblCountryEntity> entities = repo.findAll();

		if (entities == null || entities.isEmpty())
			return null;

		CountryModel model;
		List<CountryModel> models = new ArrayList<CountryModel>();

		for (TblCountryEntity entity : entities) {
			model = mapping.entityToModel(entity);

			if (model != null) {
				models.add(model);
			}
		}

		return models;
	}

}
