package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblApproveStatusEntity;

@Repository
public interface TblApproveStatusRepository extends JpaRepository<TblApproveStatusEntity, String>{

}
