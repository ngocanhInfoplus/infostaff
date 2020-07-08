package infostaff.repository;

import infostaff.entity.TblStaffTimeCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblStaffTimeCardRepository extends JpaRepository<TblStaffTimeCardEntity, Long> {
}
