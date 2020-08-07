package infostaff.repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infostaff.entity.TblOvertimeTypeEntity; 


//NOTE: Please change the JpaRepository entity type, defaule is String 
@Repository 
public interface TblOvertimeTypeRepository extends JpaRepository<TblOvertimeTypeEntity, String> { 

} 
