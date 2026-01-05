package com.iti.PlacementsBackend.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iti.PlacementsBackend.entity.labs.LabEntity;
import com.iti.PlacementsBackend.entity.labs.LabItemsEntity;
import com.iti.PlacementsBackend.model.ClaimsModel;
import com.iti.PlacementsBackend.service.labs.LabItemsService;
import com.iti.PlacementsBackend.service.labs.LabsService;
import com.iti.PlacementsBackend.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("labs")
@CrossOrigin(origins = "*")
public class LabController {

    @Autowired
    private LabsService labService;
    @Autowired
	private MyUtil myUtil;
    @Autowired
	private LabItemsService labItemsService;

    // Endpoint to save LabEntity with LabItemsEntities
    @PostMapping("/save")
    public ResponseEntity<?> saveLabWithItems(
            @RequestParam("industryName") String industryName,
            @RequestParam("tradeShort") String tradeShort,
            @RequestParam("description") String description,
            @RequestParam("itemNames[]") List<String> itemNames,
            @RequestParam("itemCosts[]") List<String> itemCosts,
            @RequestParam("itemPhotos[]") List<MultipartFile> itemPhotos,
            HttpServletRequest httpServletRequest) throws IOException {
    	
    	System.out.println("itemNames"+itemNames.size());
    	System.out.println("itemCosts"+itemCosts.size());
    	System.out.println("itemPhotos"+itemNames.size());
    	
    	ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());
		
		if(!claimsFromToken.getRoleId().equalsIgnoreCase("4")) {
			return new ResponseEntity<String>("Your not authorized to enter Lab data.", HttpStatus.BAD_REQUEST);
		}

        try {
			// Create LabEntity
			LabEntity labEntity = new LabEntity();
			labEntity.setIndustryName(industryName);
			labEntity.setTradeShort(tradeShort);
			labEntity.setItiCode(claimsFromToken.getInsCode());
			labEntity.setEntryBy(claimsFromToken.getUsername());
			labEntity.setEntryDate(LocalDateTime.now());
			labEntity.setDescription(description);

			// Create LabItemsEntity for each item
			List<LabItemsEntity> labItemsEntities = new ArrayList<>();
			for (int i = 0; i < itemNames.size(); i++) {
			    LabItemsEntity labItem = new LabItemsEntity();
			    labItem.setItemName(itemNames.get(i));
			    labItem.setItemCost(Double.valueOf(itemCosts.get(i)));
			    labItem.setItemPhoto(itemPhotos.get(i).getBytes());
			    labItem.setItiCode(claimsFromToken.getInsCode());
			    labItemsEntities.add(labItem);
			}

			// Save LabEntity with LabItemsEntity
			LabEntity savedLab = labService.saveLabWithItems(labEntity, labItemsEntities);

			return ResponseEntity.ok("Lab saved with ID: " + savedLab.getLabId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("Exception arised"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("getLabsByIticode")
    public ResponseEntity<?> getLabsByIticode(HttpServletRequest httpServletRequest){
    	ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());
		
		if(!claimsFromToken.getRoleId().equalsIgnoreCase("4")) {
			return new ResponseEntity<String>("Your not authorized to enter Lab data.", HttpStatus.BAD_REQUEST);
		}
		List<LabEntity> byItiCode = labService.getByItiCode(claimsFromToken.getInsCode());
		return new ResponseEntity<List<LabEntity>>(byItiCode, HttpStatus.OK);
    }
    
    @GetMapping("getLabsItemsByIticode")
    public ResponseEntity<?> getLabsItemsByIticode(HttpServletRequest httpServletRequest){
    	ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());
		
		if(!claimsFromToken.getRoleId().equalsIgnoreCase("4")) {
			return new ResponseEntity<String>("Your not authorized to enter Lab data.", HttpStatus.BAD_REQUEST);
		}
		List<LabItemsEntity> byItiCode = labItemsService.getByItiCode(claimsFromToken.getInsCode());
		
		return new ResponseEntity<List<LabItemsEntity>>(byItiCode, HttpStatus.OK);
    }
    
    @GetMapping("getAllLabItems")
	public ResponseEntity<?> getAllLabItems(HttpServletRequest httpServletRequest){
    	ClaimsModel claimsFromToken = myUtil.getClaimsFromToken(httpServletRequest);
		System.out.println("claims=>" + claimsFromToken.toString());
		
		if(!claimsFromToken.getRoleId().equalsIgnoreCase("10") && !claimsFromToken.getRoleId().equalsIgnoreCase("11")) {
			return new ResponseEntity<String>("Your not authorized to view Lab data.", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<LabItemsEntity>>(labItemsService.getAll(), HttpStatus.OK);
	}
    
}

