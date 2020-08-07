package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblDepartmentEntity;

@Repository
public interface TblDepartmentRepository  extends JpaRepository<TblDepartmentEntity, String>{

}
