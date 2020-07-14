package infostaff.repository;

import infostaff.entity.TblStaffTimeCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface TblStaffTimeCardRepository extends JpaRepository<TblStaffTimeCardEntity, Long> {

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("UPDATE TblStaffTimeCardEntity SET checkOut = :checkOut WHERE id = :id AND staffId = :staffId AND workingDate = :workingDate")
    void updateTimeCard(@Param("id") Long id, @Param("checkOut") String checkOut, @Param("staffId") Long staffId, @Param("workingDate") Date workingDate);
}
