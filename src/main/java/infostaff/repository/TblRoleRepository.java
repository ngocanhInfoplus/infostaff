package infostaff.repository;

import infostaff.entity.TblStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblRoleEntity;

@Repository
public interface TblRoleRepository extends JpaRepository<TblRoleEntity, Long>{

    @Query("SELECT a FROM TblRoleEntity a "
            + " WHERE a.roleName = :rolename")
    TblRoleEntity findByRoleName(@Param("rolename") String rolename);

}
