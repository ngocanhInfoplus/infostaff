package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblTimeOffEntity;

@Repository
public interface TblTimeOffRepository extends JpaRepository<TblTimeOffEntity, String> {

}
