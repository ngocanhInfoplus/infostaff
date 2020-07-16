package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblStaffLeavingEntity;

@Repository
public interface TblStaffLeavingRepository extends JpaRepository<TblStaffLeavingEntity, Long>{

}
