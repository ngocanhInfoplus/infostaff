package infostaff.mapping;

import org.apache.commons.lang3.StringUtils;

import infostaff.common.CommonFunc;
import infostaff.entity.TblLeavingTypeEntity;
import infostaff.entity.TblStaffEntity;
import infostaff.entity.TblStaffLeavingEntity;
import infostaff.entity.TblTimeOffEntity;
import infostaff.model.StaffLeavingModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaffLeavingMapping {

	public StaffLeavingModel entityToModel(TblStaffLeavingEntity entity) {

		StaffLeavingModel model = new StaffLeavingModel();
		try {
			model.setId(entity.getId());
			// set staff id
			// model.setStaffId(entity.getStaffId());
			model.setStaffId(entity.getTblStaffEntity().getStaffId());
			model.setStaffName(entity.getTblStaffEntity().getStaffName());
			// end
			// set leaving type
			// model.setLeavingType(entity.getLeavingType());
			model.setLeavingTypeCode(entity.getTblLeavingTypeEntity().getLeavingCode());
			model.setLeavingTypeName(entity.getTblLeavingTypeEntity().getLeavingName());
			// end

			// set timeoff
			// model.setTimeOffTypeCode(entity.getTimeOffType());
			model.setTimeOffCode(entity.getTblTimeOffEntity().getCode());
			model.setTimeOffName(entity.getTblTimeOffEntity().getName());
			// end
			model.setFromDate(entity.getFromDate());
			model.setToDate(entity.getToDate());
			model.setReason(entity.getReason());
			model.setManagerId01(entity.getManagerId01());
			model.setManagerId02(entity.getManagerId02());
			model.setComment(entity.getComment());
			model.setApproveStatus(entity.getApproveStatus());
			model.setApproveStatusName(CommonFunc.getApproveStatusName(entity.getApproveStatus()));
			model.setFileName(entity.getFileName());
			model.setCreatedUser(entity.getCreatedUser());
			model.setCreatedDate(entity.getCreatedDate());
			model.setChangedUser(entity.getChangedUser());
			model.setChangedDate(entity.getChangedDate());
			model.setApprovedUser(entity.getApprovedUser());
			model.setApprovedDate(entity.getApprovedDate());
			model.setRecordStatus(entity.getRecordStatus());
			
			model.setTotalDay(entity.getTotalDay());

			return model;
		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			return null;
		}
	}

	public TblStaffLeavingEntity modelToEntity(StaffLeavingModel model) {

		TblStaffLeavingEntity entity = new TblStaffLeavingEntity();
		try {
			entity.setId(model.getId());
			// set up leaving type
			TblStaffEntity tblStaffEntity = new TblStaffEntity();
			tblStaffEntity.setStaffId(model.getStaffId());
			entity.setTblStaffEntity(tblStaffEntity);
			// end

			// set up leaving type
			TblLeavingTypeEntity leavingTypeEntity = new TblLeavingTypeEntity();
			leavingTypeEntity.setLeavingName(StringUtils.EMPTY);
			leavingTypeEntity.setLeavingCode(model.getLeavingTypeCode());
			entity.setTblLeavingTypeEntity(leavingTypeEntity);
			// end

			// set up timeoff
			TblTimeOffEntity timeOffEntity = new TblTimeOffEntity();
			timeOffEntity.setName(StringUtils.EMPTY);
			timeOffEntity.setCode(model.getTimeOffCode());
			entity.setTblTimeOffEntity(timeOffEntity);
			// end

			entity.setFromDate(model.getFromDate());
			entity.setToDate(model.getToDate());
			entity.setReason(model.getReason());
			entity.setManagerId01(model.getManagerId01());
			entity.setManagerId02(model.getManagerId02());
			entity.setComment(model.getComment());
			entity.setApproveStatus(model.getApproveStatus());
			entity.setFileName(model.getFileName());
			entity.setCreatedUser(model.getCreatedUser());
			entity.setCreatedDate(model.getCreatedDate());
			entity.setChangedUser(model.getChangedUser());
			entity.setChangedDate(model.getChangedDate());
			entity.setApprovedUser(model.getApprovedUser());
			entity.setApprovedDate(model.getApprovedDate());
			entity.setRecordStatus(model.getRecordStatus());

			return entity;
		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			return null;
		}
	}
}
