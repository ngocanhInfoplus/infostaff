package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblUserEntity;

@Repository
public interface TblUserRepository extends JpaRepository<TblUserEntity, String> {

	@Query("SELECT a FROM TblUserEntity a WHERE a.enabled = true AND a.userName = :userName")
	TblUserEntity findByUserName(String userName);
}
