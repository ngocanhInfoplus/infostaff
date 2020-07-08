package infostaff.service;

import infostaff.common.CommonFunc;
import infostaff.common.CommonParam;
import infostaff.entity.TblStaffTimeCardEntity;
import infostaff.mapping.StaffTimeCardMapping;
import infostaff.model.ResponseModel;
import infostaff.model.StaffTimeCardModel;
import infostaff.repository.TblStaffTimeCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StaffTimeCardServiceImpl implements IStaffTimeCardService{

    @Autowired
    TblStaffTimeCardRepository repo;
    @Override
    public ResponseModel insert(StaffTimeCardModel model, User user) {

        StaffTimeCardMapping mapping = new StaffTimeCardMapping();
        TblStaffTimeCardEntity entity = mapping.modelToEntity(model);
        String code = StringUtils.EMPTY;
        log.info("Login user: " + user.getUsername());
        if(entity != null){
            try {

                TblStaffTimeCardEntity result = repo.save(entity);
                code = (result != null)? CommonParam.CODE_SUCCESS : CommonParam.CODE_FAILED;

            } catch (Exception ex) {
                code = CommonParam.CODE_FAILED;
                log.error("Insert StaffTimeCard error: " + ex.toString());
            }
        }
        else {
            code = CommonParam.CODE_CONVERT_ERROR;
        }
        return CommonFunc.createResponseModelByCode(code);
    }

    @Override
    public ResponseModel update(StaffTimeCardModel model, User user) {
        StaffTimeCardMapping mapping = new StaffTimeCardMapping();
        TblStaffTimeCardEntity entity = mapping.modelToEntity(model);
        String code = StringUtils.EMPTY;
        log.info("Login user: " + user.getUsername());
        if(entity != null){
            try {

                TblStaffTimeCardEntity result = repo.save(entity);
                code = (result != null)? CommonParam.CODE_SUCCESS : CommonParam.CODE_FAILED;

            } catch (Exception ex) {
                code = CommonParam.CODE_FAILED;
                log.error("Update StaffTimeCard error: " + ex.toString());
            }
        }
        else {
            code = CommonParam.CODE_CONVERT_ERROR;
        }
        return CommonFunc.createResponseModelByCode(code);
    }

    @Override
    public List<StaffTimeCardModel> getAll() {
        return null;
    }
}
