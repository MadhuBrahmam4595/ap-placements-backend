package com.iti.PlacementsBackend.service.impl.labs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iti.PlacementsBackend.entity.labs.LabEntity;
import com.iti.PlacementsBackend.entity.labs.LabItemsEntity;
import com.iti.PlacementsBackend.repo.labs.LabItemsRepository;
import com.iti.PlacementsBackend.repo.labs.LabRepository;
import com.iti.PlacementsBackend.service.labs.LabsService;

import java.util.List;

@Service
public class LabsServiceImpl implements LabsService{

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private LabItemsRepository labItemsRepository;

    @Transactional
    @Override
    public LabEntity saveLabWithItems(LabEntity labEntity, List<LabItemsEntity> labItemsEntities) {
        // Save the LabEntity first
        LabEntity savedLab = labRepository.save(labEntity);
        
        // For each lab item, associate it with the saved lab and then save it
        for (LabItemsEntity item : labItemsEntities) {
            item.setLabEntity(savedLab);
            labItemsRepository.save(item);
        }

        return savedLab;
    }

    // Save LabEntity individually
    @Override
    public LabEntity saveLab(LabEntity labEntity) {
        return labRepository.save(labEntity);
    }

    // Save LabItemsEntity individually
    @Override
    public LabItemsEntity saveLabItem(LabItemsEntity labItem) {
        return labItemsRepository.save(labItem);
    }

	@Override
	public List<LabEntity> getByItiCode(String itiCode) {
		// TODO Auto-generated method stub
		return labRepository.findByItiCode(itiCode);
	}

	@Override
	public List<LabEntity> getAll() {
		// TODO Auto-generated method stub
		return labRepository.findAll();
	}
}
