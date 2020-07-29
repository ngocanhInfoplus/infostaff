package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblUserEntity;

@Repository
public interface TblUserRepository extends JpaRepository<TblUserEntity, String> {

	TblUserEntity findByUserName(String userName);
	boolean existsByUserName(String userName);
}
