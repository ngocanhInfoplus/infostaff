package infostaff.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblStaffLeavingEntity;

@Repository
public interface TblStaffLeavingRepository extends JpaRepository<TblStaffLeavingEntity, Long> {

	@Query("SELECT a FROM TblStaffLeavingEntity a WHERE a.staffId = :staffId "
			+ "AND a.fromDate BETWEEN :fromDate AND :toDate " + "AND a.recordStatus = :recordStatus")
	List<TblStaffLeavingEntity> findActiveRequestByStaffIdAndDate(Long staffId, Date fromDate, Date toDate,
			String recordStatus);

	@Query("SELECT a FROM TblStaffLeavingEntity a WHERE a.fromDate BETWEEN :fromDate AND :toDate "
			+ "AND a.recordStatus = :recordStatus")
	List<TblStaffLeavingEntity> findActiveRequestByDate(Date fromDate, Date toDate, String recordStatus);
	
	@Query("SELECT a FROM TblStaffLeavingEntity a WHERE a.id = :id AND a.approveStatus = :approveStatus")
	TblStaffLeavingEntity findByIdAndApproveStatus(Long id, String approveStatus);

}
