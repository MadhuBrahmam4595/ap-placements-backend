package com.iti.PlacementsBackend.repo.plcmts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iti.PlacementsBackend.entity.plcmts.PlcmtScheduleEntryEntity;
import com.iti.PlacementsBackend.projection.DistPlcmtSchedulesProj;
import com.iti.PlacementsBackend.projection.GetNextValProje;
import com.iti.PlacementsBackend.projection.PlcmtSchedulesNodalModel;
import com.iti.PlacementsBackend.projection.UniversalProjection;


public interface PlcmtScheduleEntryRepo extends JpaRepository<PlcmtScheduleEntryEntity, Long>{
	
	@Query(value = "select * from placements.placements_schedules where schedule_type=:scheduleType and schedule_location=:ins_code",nativeQuery = true)
	   List<PlcmtScheduleEntryEntity> getAllByScheduleType(String scheduleType, String ins_code);
	   
	   List<PlcmtScheduleEntryEntity> findByScheduleTypeAndScheduleLocation(String scheduleType,String scheduleLocation);

	   @Query(value = "select * from placements.placements_schedules where dist_code=:dist_code", nativeQuery = true)
	   List<PlcmtScheduleEntryEntity> getAllSchedulesByDistcode(String dist_code);

	   PlcmtScheduleEntryEntity findByScheduleId(Long scheduleId);

	   @Query(value = "select cast(nextval('placements.plcmtscheduleseq ') as character varying) as nextval", nativeQuery = true)
	   GetNextValProje getNextVal();
	   
	   @Query(value="select * from placements.placements_schedules where schedule_location=:scheduleLocation order by schedule_type,schedule_date desc",nativeQuery = true)
	   List<PlcmtScheduleEntryEntity> findByScheduleLocation(String scheduleLocation);
	   
	   PlcmtScheduleEntryEntity findByPlcmtId(Long plcmtId);
	   
	   @Query(value="select plcmt_id,b.dist_name,c.iti_name,schedule_date,\r\n" + 
	   		"no_of_vacancies,no_of_attended_candidates,no_of_selected_candidates\r\n" + 
	   		" from placements.placements_schedules a inner join dist_mst b on a.dist_code=b.dist_code \r\n" + 
	   		" inner join iti c on a.schedule_location=c.iti_code\r\n" + 
	   		" order by b.dist_name,c.iti_name", nativeQuery = true)
	   List<PlcmtSchedulesNodalModel> findPlcmtSchedulesNodalReport();
	   
	   @Query(value = "select cast(plcmt_id as character varying),a.schedule_location ||'-'||b.iti_name as iti_name,\r\n" + 
	   		"schedule_date,schedule_type,schedule_desc\r\n" + 
	   		" from placements.placements_schedules a\r\n" + 
	   		" inner join iti b on a.schedule_location=b.iti_code\r\n" + 
	   		" where a.dist_code=:distCode order by schedule_date",nativeQuery = true)
	   List<DistPlcmtSchedulesProj> getDistPlcmtSchedules(String distCode);
	   
	   @Query(value="select a.dist_code as strCol1,b.dist_name as strCol2, count(*) as longCol1, " + 
	   		"sum(case when a.schedule_type = 'Job' then 1 else 0 end)as longCol2, " + 
	   		"sum(case when a.schedule_type = 'Apprenticeship' then 1 else 0 end)as longCol3 " + 
	   		"from placements.placements_schedules a " + 
	   		"inner join dist_mst b on a.dist_code = b.dist_code " + 
	   		"group by a.dist_code,b.dist_name " + 
	   		"order by a.dist_code",nativeQuery = true)
	   List<UniversalProjection> getSchedulesCountDistwise();
	   
	   List<PlcmtScheduleEntryEntity> findByDistCode(String distCode);
	   List<PlcmtScheduleEntryEntity> findByDistCodeAndScheduleType(String distCode,String scheduleType);

}
