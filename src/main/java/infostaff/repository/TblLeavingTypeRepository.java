package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblLeavingTypeEntity;

@Repository
public interface TblLeavingTypeRepository extends JpaRepository<TblLeavingTypeEntity, String>{

}
