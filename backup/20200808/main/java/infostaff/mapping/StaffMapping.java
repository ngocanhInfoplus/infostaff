package infostaff.mapping; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import infostaff.entity.TblCountryEntity;
import infostaff.entity.TblDepartmentEntity;
import infostaff.entity.TblGroupEntity;
import infostaff.entity.TblJobTitleEntity;
import infostaff.entity.TblLocationEntity;
import infostaff.entity.TblStaffEntity;
import infostaff.model.StaffModel;
import lombok.extern.slf4j.Slf4j; 


@Slf4j 
@Component 
public class StaffMapping{ 
	public StaffModel entityToModel(TblStaffEntity entity){ 
	
		StaffModel model  = new StaffModel(); 
		try{ 
			model.setStaffId(entity.getStaffId()); 
			model.setStaffCode(entity.getStaffCode()); 
			model.setStaffName(entity.getStaffName()); 
			model.setJoinDate(entity.getJoinDate()); 
			model.setDob(entity.getDob()); 
			model.setGender(entity.getGender()); 
			model.setLocationCode(entity.getLocationEntity().getCode());
			model.setLocationName(entity.getLocationEntity().getName());
			model.setFileName(entity.getFileName()); 
			model.setCreatedUser(entity.getCreatedUser()); 
			model.setCreatedDate(entity.getCreatedDate()); 
			model.setChangedUser(entity.getChangedUser()); 
			model.setChangedDate(entity.getChangedDate()); 
			model.setRecordStatus(entity.getRecordStatus()); 
			model.setJobTitleCode(entity.getJobTitleEntity().getCode());
			model.setJobTitleName(entity.getJobTitleEntity().getName());
			model.setAddress01(entity.getAddress01());
			model.setAddress02(entity.getAddress02());
			model.setPhoneNo(entity.getPhoneNo());
			model.setIdentityCardNo(entity.getIdentityCardNo());
			model.setIdentityCardDate(entity.getIdentityCardDate());
			model.setIdentityCardLocation(entity.getIdentityCardLocation());
			model.setCountryCode(entity.getCountryEntity().getCode());
			model.setCountryName(entity.getCountryEntity().getName());
			model.setVisaType(entity.getVisaType());
			model.setVisaNo(entity.getVisaNo());
			model.setInvoledName(entity.getInvoledName());
			model.setInvoledPhone(entity.getInvoledPhone());
			model.setGroupCode(entity.getGroupEntity().getGroupCode());
			model.setGroupName(entity.getGroupEntity().getGroupName());
			model.setDepartmentCode(entity.getDepartmentEntity().getDepartmentCode());
			model.setDepartmentName(entity.getDepartmentEntity().getDepartmentName());
			model.setEmail(entity.getEmail());
			model.setUserName(entity.getUserName());
			model.setManagerId(getManagerIdModel(entity.getManagerId()));
			model.setAdvanceAnnualLeave((entity.getAdvanceAnnualLeave() == null)? null : entity.getAdvanceAnnualLeave());
	
			return model; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	
	public TblStaffEntity modelToEntity(StaffModel model){ 
	
		TblStaffEntity entity  = new TblStaffEntity(); 
		try{ 
			entity.setStaffId(model.getStaffId()); 
			entity.setStaffCode(model.getStaffCode()); 
			entity.setStaffName(model.getStaffName().toUpperCase()); 
			entity.setJoinDate(model.getJoinDate()); 
			entity.setDob(model.getDob()); 
			entity.setGender(model.getGender()); 
			
			TblLocationEntity locationEntity = new TblLocationEntity();
			locationEntity.setCode(model.getLocationCode());
			locationEntity.setName(model.getLocationName());
			entity.setLocationEntity(locationEntity);
			
			entity.setFileName(model.getFileName()); 
			entity.setCreatedUser(model.getCreatedUser()); 
			entity.setCreatedDate(model.getCreatedDate()); 
			entity.setChangedUser(model.getChangedUser()); 
			entity.setChangedDate(model.getChangedDate()); 
			entity.setRecordStatus(model.getRecordStatus()); 
			entity.setManagerId(getManagerIdEntity(model.getManagerId()));
			
			TblJobTitleEntity jobTitleEntity = new TblJobTitleEntity();
			jobTitleEntity.setCode(model.getJobTitleCode());
			jobTitleEntity.setName(model.getJobTitleName());
			entity.setJobTitleEntity(jobTitleEntity);
			
			entity.setAddress01(model.getAddress01());
			entity.setAddress02(model.getAddress02());
			entity.setPhoneNo(model.getPhoneNo());
			entity.setIdentityCardNo(model.getIdentityCardNo());
			entity.setIdentityCardDate(model.getIdentityCardDate());
			entity.setIdentityCardLocation(model.getIdentityCardLocation());
			
			TblCountryEntity countryEntity = new TblCountryEntity();
			countryEntity.setCode(model.getCountryCode());
			countryEntity.setName(model.getCountryName());
			entity.setCountryEntity(countryEntity);
			
			entity.setVisaType(model.getVisaType());
			entity.setVisaNo(model.getVisaNo());
			entity.setInvoledName(model.getInvoledName());
			entity.setInvoledPhone(model.getInvoledPhone());
			
			TblGroupEntity groupEntity = new TblGroupEntity();
			groupEntity.setGroupCode(model.getGroupCode());
			groupEntity.setGroupName(model.getGroupName());
			entity.setGroupEntity(groupEntity);
			
			TblDepartmentEntity departmentEntity = new TblDepartmentEntity();
			departmentEntity.setDepartmentCode(model.getDepartmentCode());
			departmentEntity.setDepartmentName(model.getDepartmentName());
			entity.setDepartmentEntity(departmentEntity);
	
			entity.setEmail(model.getEmail());
			entity.setUserName(model.getUserName());
			entity.setAdvanceAnnualLeave(model.getAdvanceAnnualLeave());
			
			return entity; 
		} catch(Exception ex){ 
			log.error("Error: " + ex.toString()); 
			return null; 
		} 
	} 
	
	public List<StaffModel> entitiesToModels(List<TblStaffEntity> entities){
		
		if(entities == null || entities.isEmpty())
			return null;
		
		List<StaffModel> models = new ArrayList<StaffModel>();
		
		for(TblStaffEntity entity: entities) {
			StaffModel model = entityToModel(entity);
			
			if(model != null)
				models.add(model);
		}
		return models;
	}
	
	private Long getManagerIdEntity(Long managerId) {
		if(managerId == null || managerId == -1)
			return null;
		else
			return managerId;
	}
	
	private Long getManagerIdModel(Long managerId) {
		if(managerId == null || managerId == -1 )
			return -1L;
		else
			return managerId;
	}
}