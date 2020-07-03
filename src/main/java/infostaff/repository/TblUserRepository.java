package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblUserEntity;

@Repository
public interface TblUserRepository extends JpaRepository<TblUserEntity, Long> {

	TblUserEntity findByUserName(String userName);
}
