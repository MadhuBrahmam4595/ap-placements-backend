package com.iti.PlacementsBackend.controller;

import com.iti.PlacementsBackend.entity.hrm.CasteMaster;
import com.iti.PlacementsBackend.entity.hrm.SubCasteMaster;
import com.iti.PlacementsBackend.model.AittPvtCand.AittPrivateCandidateModel;
import com.iti.PlacementsBackend.service.AittPvtCand.AittPrivateCandidateService;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class AittPvtStdController {

    private static final Logger logger = LoggerFactory.getLogger(AittPvtStdController.class);
    private final AittPrivateCandidateService applicantService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> saveApplicant(
            @RequestPart("data") AittPrivateCandidateModel dto,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) throws IOException {

        logger.info("Fetched request to save data: AittPrivateCandidateModel:{}", dto);
        logger.info("Fetched request to save data: photo:{}", photo.getName());

        if (photo != null && !photo.isEmpty()) {
            dto.setPhoto(photo.getBytes());
            dto.setPhotoContentType(photo.getContentType());
        }

        Long id = applicantService.save(dto);
        return ResponseEntity.ok(id);
    }
}
