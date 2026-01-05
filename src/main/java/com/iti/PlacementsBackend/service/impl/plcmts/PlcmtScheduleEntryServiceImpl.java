package com.iti.PlacementsBackend.service.impl.plcmts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iti.PlacementsBackend.entity.plcmts.PlcmtScheduleEntryEntity;
import com.iti.PlacementsBackend.projection.DistPlcmtSchedulesProj;
import com.iti.PlacementsBackend.projection.GetNextValProje;
import com.iti.PlacementsBackend.projection.PlcmtSchedulesNodalModel;
import com.iti.PlacementsBackend.projection.UniversalProjection;
import com.iti.PlacementsBackend.repo.plcmts.PlcmtScheduleEntryRepo;
import com.iti.PlacementsBackend.service.plcmts.PlcmtScheduleEntryService;
@Service
public class PlcmtScheduleEntryServiceImpl implements PlcmtScheduleEntryService{
	
	@Autowired
	private PlcmtScheduleEntryRepo repo;

	public PlcmtScheduleEntryEntity savePSE(PlcmtScheduleEntryEntity pse) {
		return (PlcmtScheduleEntryEntity) this.repo.save(pse);
	}

	public PlcmtScheduleEntryEntity getPlcmtScheduleEntry(Long scheduleId) {
		return this.repo.findByScheduleId(scheduleId);
	}

	public List<PlcmtScheduleEntryEntity> getAllPlcmtScheduleEntry() {
		return this.repo.findAll();
	}

	@Override
	public List<PlcmtScheduleEntryEntity> getByScheduleLocation(String scheduleLocation) {
		// TODO Auto-generated method stub
		return repo.findByScheduleLocation(scheduleLocation);
	}

	@Override
	public PlcmtScheduleEntryEntity getByPlcmtId(Long plcmtId) {
		// TODO Auto-generated method stub
		return repo.findByPlcmtId(plcmtId);
	}

	@Override
	public List<PlcmtSchedulesNodalModel> getPlcmtSchedulesNodalReport() {
		// TODO Auto-generated method stub
		return repo.findPlcmtSchedulesNodalReport();
	}
	
	@Override
	public List<DistPlcmtSchedulesProj> getDistPlcmtSchedules(String distCode) {
		// TODO Auto-generated method stub
		return repo.getDistPlcmtSchedules(distCode);
	}
	@Override
	public PlcmtScheduleEntryEntity findByPlcmtId(Long plcmtId) {
		// TODO Auto-generated method stub
		return repo.findByPlcmtId(plcmtId);
	}

	@Override
	public List<UniversalProjection> getSchedulesCountDistwise() {
		// TODO Auto-generated method stub
		return repo.getSchedulesCountDistwise();
	}

	@Override
	public List<PlcmtScheduleEntryEntity> findByDistCode(String dist_code) {
		// TODO Auto-generated method stub
		return repo.findByDistCode(dist_code);
	}
	@Override
	public List<PlcmtScheduleEntryEntity> findByDistCodeAndScheduleType(String distCode, String scheduleType) {
		// TODO Auto-generated method stub
		return repo.findByDistCodeAndScheduleType(distCode, scheduleType);
	}

	@Override
	public GetNextValProje getNextVal() {
		// TODO Auto-generated method stub
		return repo.getNextVal();
	}

	@Override
	public List<PlcmtScheduleEntryEntity> findByScheduleTypeAndScheduleLocation(String scheduleType,
			String scheduleLocation) {
		// TODO Auto-generated method stub
		return repo.findByScheduleTypeAndScheduleLocation(scheduleType, scheduleLocation);
	}

}
