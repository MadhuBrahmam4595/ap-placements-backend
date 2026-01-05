package com.iti.PlacementsBackend.service.plcmts;

import java.util.List;

import com.iti.PlacementsBackend.entity.plcmts.PlcmtScheduleEntryEntity;
import com.iti.PlacementsBackend.projection.DistPlcmtSchedulesProj;
import com.iti.PlacementsBackend.projection.GetNextValProje;
import com.iti.PlacementsBackend.projection.PlcmtSchedulesNodalModel;
import com.iti.PlacementsBackend.projection.UniversalProjection;


public interface PlcmtScheduleEntryService {
	
	PlcmtScheduleEntryEntity savePSE(PlcmtScheduleEntryEntity pse);
    PlcmtScheduleEntryEntity getPlcmtScheduleEntry(Long scheduleId);
    List<PlcmtScheduleEntryEntity> getAllPlcmtScheduleEntry();
    List<PlcmtScheduleEntryEntity> getByScheduleLocation(String scheduleLocation);
    PlcmtScheduleEntryEntity getByPlcmtId(Long plcmtId);
    List<PlcmtSchedulesNodalModel> getPlcmtSchedulesNodalReport();
    
    List<DistPlcmtSchedulesProj> getDistPlcmtSchedules(String distCode);
    PlcmtScheduleEntryEntity findByPlcmtId(Long plcmtId);
    
    List<UniversalProjection> getSchedulesCountDistwise();
    
    List<PlcmtScheduleEntryEntity> findByDistCode(String distCode);
    List<PlcmtScheduleEntryEntity> findByDistCodeAndScheduleType(String distCode,String scheduleType);
    
    GetNextValProje getNextVal();
    List<PlcmtScheduleEntryEntity> findByScheduleTypeAndScheduleLocation(String scheduleType,String scheduleLocation);

}
