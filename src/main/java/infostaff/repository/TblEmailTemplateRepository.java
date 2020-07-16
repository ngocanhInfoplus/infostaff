package infostaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblEmailTemplateEntity;

@Repository
public interface TblEmailTemplateRepository extends JpaRepository<TblEmailTemplateEntity, String>{

}
