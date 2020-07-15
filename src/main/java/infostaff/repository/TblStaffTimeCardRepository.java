package infostaff.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblStaffTimeCardEntity;

@Repository
public interface TblStaffTimeCardRepository extends JpaRepository<TblStaffTimeCardEntity, Long> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE TblStaffTimeCardEntity SET checkOut = :checkOut WHERE id = :id AND staffId = :staffId AND workingDate = :workingDate")
	void updateTimeCard(@Param("id") Long id, @Param("checkOut") String checkOut, @Param("staffId") Long staffId,
			@Param("workingDate") Date workingDate);

	@Query("SELECT a FROM TblStaffTimeCardEntity a "
			+ "WHERE a.staffId = :staffId "
			+ "AND a.isChecked = :isChecked "
			+ "AND a.workingDate = :workingDate "
			+ "AND a.recordStatus = :recordStatus")
	TblStaffTimeCardEntity findStaffDailyChecking(@Param("staffId") Long staffId,
			@Param("isChecked") boolean isChecked, @Param("workingDate") Date workingDate,
			@Param("recordStatus") String recordStatus);

}
