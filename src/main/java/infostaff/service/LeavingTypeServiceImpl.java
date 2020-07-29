package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infostaff.entity.TblLeavingTypeEntity;
import infostaff.mapping.LeavingTypeMapping;
import infostaff.model.LeavingTypeModel;
import infostaff.repository.TblLeavingTypeRepository;

@Service
public class LeavingTypeServiceImpl implements ILeavingType{

	@Autowired
	TblLeavingTypeRepository leavingTypeRepo;
	
	@Override
	public List<LeavingTypeModel> getAll() {
		List<TblLeavingTypeEntity> entities = leavingTypeRepo.findAll();

		if (entities == null || entities.isEmpty())
			return null;

		LeavingTypeModel leavingTypeModel;
		List<LeavingTypeModel> lstLeavingType = new ArrayList<LeavingTypeModel>();
		LeavingTypeMapping mapping = new LeavingTypeMapping();

		for (TblLeavingTypeEntity entity : entities) {
			leavingTypeModel = mapping.entityToModel(entity);

			if (leavingTypeModel != null) {
				lstLeavingType.add(leavingTypeModel);
			}
		}

		return lstLeavingType;
	}

}
