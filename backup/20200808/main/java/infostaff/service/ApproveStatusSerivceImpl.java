package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infostaff.entity.TblApproveStatusEntity;
import infostaff.mapping.ApproveStatusMapping;
import infostaff.model.ApproveStatusModel;
import infostaff.repository.TblApproveStatusRepository;

@Service
public class ApproveStatusSerivceImpl implements IApproveStatusService{
	
	@Autowired
	TblApproveStatusRepository approveStatusRepo;
	
	@Override
	public List<ApproveStatusModel> getAll() {
		
		List<TblApproveStatusEntity> entities = approveStatusRepo.findAll();
		
		if(entities == null || entities.size() == 0)
			return null;
		
		
		List<ApproveStatusModel> models = new ArrayList<ApproveStatusModel>();
		ApproveStatusModel model = null;
		ApproveStatusMapping mapping = new ApproveStatusMapping();
		
		for(TblApproveStatusEntity entity: entities) {
			model = mapping.entityToModel(entity);
			
			if(model != null)
				models.add(model);
		}
		
		return models;
	}
	
	

}
