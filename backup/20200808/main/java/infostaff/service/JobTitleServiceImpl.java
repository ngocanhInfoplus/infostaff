package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infostaff.entity.TblJobTitleEntity;
import infostaff.mapping.JobTitleMapping;
import infostaff.model.JobTitleModel;
import infostaff.repository.TblJobTitleRepository;
import lombok.extern.slf4j.Slf4j;

@Service
public class JobTitleServiceImpl implements IJobTitleService{
	
	@Autowired
	TblJobTitleRepository repo;
	
	@Autowired
	JobTitleMapping mapping;
	
	@Override
	public List<JobTitleModel> getAll() {

		List<TblJobTitleEntity> entities = repo.findAll();

		if (entities == null || entities.isEmpty())
			return null;

		JobTitleModel model;
		List<JobTitleModel> models = new ArrayList<JobTitleModel>();

		for (TblJobTitleEntity entity : entities) {
			model = mapping.entityToModel(entity);

			if (model != null) {
				models.add(model);
			}
		}

		return models;
	}

}
