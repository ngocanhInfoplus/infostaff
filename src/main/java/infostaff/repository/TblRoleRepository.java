package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblRoleEntity;

@Repository
public interface TblRoleRepository extends JpaRepository<TblRoleEntity, Long>{

}
