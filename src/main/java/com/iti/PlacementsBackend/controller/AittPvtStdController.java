package com.iti.PlacementsBackend.controller;

import com.iti.PlacementsBackend.model.AittPvtCand.AittPrivateCandidateModel;
import com.iti.PlacementsBackend.model.AittPvtCand.AittPrivateCandidateReportModel;
import com.iti.PlacementsBackend.service.AittPvtCand.AittPrivateCandidateService;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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

    @GetMapping("/report/{id}")
    public ResponseEntity<AittPrivateCandidateReportModel> getApplicantReport(
            @PathVariable Long id) {
        logger.info("Fetched request for candidate id:{}", id);
        AittPrivateCandidateReportModel report =
                applicantService.getById(id);
        logger.info("Candidate details:{}", report);

        return ResponseEntity.ok(report);
    }


}
