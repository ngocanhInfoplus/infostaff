package infostaff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblEmailStaffEntity;
import infostaff.entity.TblStaffEntity;

@Repository
public interface TblEmailStaffRepository extends JpaRepository<TblEmailStaffEntity, Long> {

	List<TblEmailStaffEntity> findByStaffId(@Param("staffId") Long staffId);

	@Query("SELECT a.tblStaffEntity FROM TblEmailStaffEntity a "
			+ "WHERE a.TblEmailTemplateEntity.templateCode = :templateCode "
			+ "AND a.recordStatus = a.tblStaffEntity.recordStatus " 
			+ "AND a.recordStatus = :recordStatus")
	List<TblStaffEntity> findStaffByEmailTemplateCode(@Param("templateCode") String templateCode
			, @Param("recordStatus") String recrodStatus);

}
