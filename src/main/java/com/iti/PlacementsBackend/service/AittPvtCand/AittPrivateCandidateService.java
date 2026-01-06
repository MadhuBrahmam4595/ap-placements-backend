package com.iti.PlacementsBackend.service.AittPvtCand;

import com.iti.PlacementsBackend.entity.aittpvtcand.AittPrivateCandidateEntity;
import com.iti.PlacementsBackend.model.AittPvtCand.AittPrivateCandidateModel;
import com.iti.PlacementsBackend.model.AittPvtCand.AittPrivateCandidateReportModel;
import com.iti.PlacementsBackend.repo.AittPvtCand.AittPrivateCandidateRepo;
import com.iti.PlacementsBackend.repo.hrm.CasteMasterRepository;
import com.iti.PlacementsBackend.repo.hrm.SubCasteMasterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AittPrivateCandidateService {


    private final AittPrivateCandidateRepo applicantRepository;
    private final CasteMasterRepository casteRepo;
    private final SubCasteMasterRepository subCasteRepo;

    @Transactional
    public Long save(AittPrivateCandidateModel dto) {

        AittPrivateCandidateEntity applicant = new AittPrivateCandidateEntity();

        applicant.setCategory(dto.getCategory());
        applicant.setApplicantName(dto.getApplicantName());
        applicant.setFatherName(dto.getFatherName());
        applicant.setFatherOccupation(dto.getFatherOccupation());
        applicant.setMotherName(dto.getMotherName());
        applicant.setDob(dto.getDob());
        applicant.setAge(dto.getAge());
        applicant.setGender(dto.getGender());

        applicant.setPwdFlag(dto.getPwdFlag());
        applicant.setPwdCategory(dto.getPwdCategory());
        applicant.setEwsFlag(dto.getEwsFlag());

        applicant.setPhoto(dto.getPhoto());
        applicant.setPhotoContentType(dto.getPhotoContentType());

        applicant.setCaste(
                casteRepo.findById(dto.getCasteId())
                        .orElseThrow(() -> new RuntimeException("Invalid caste"))
        );

        applicant.setSubCaste(
                subCasteRepo.findById(dto.getSubCasteId())
                        .orElseThrow(() -> new RuntimeException("Invalid sub caste"))
        );

        return applicantRepository.save(applicant).getId();
    }

    public AittPrivateCandidateReportModel getById(Long id) {

        AittPrivateCandidateEntity entity =
                applicantRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Candidate not found"));

        AittPrivateCandidateReportModel model =
                new AittPrivateCandidateReportModel();

        model.setId(entity.getId());
        model.setCategory(entity.getCategory());
        model.setApplicantName(entity.getApplicantName());
        model.setFatherName(entity.getFatherName());
        model.setFatherOccupation(entity.getFatherOccupation());
        model.setMotherName(entity.getMotherName());
        model.setDob(entity.getDob());
        model.setAge(entity.getAge());
        model.setGender(entity.getGender());

        if (entity.getCaste() != null) {
            model.setCasteName(entity.getCaste().getCasteCategory());
        }

        if (entity.getSubCaste() != null) {
            model.setSubCasteName(entity.getSubCaste().getSubCaste());
        }

        model.setPwdFlag(entity.getPwdFlag());
        model.setPwdCategory(entity.getPwdCategory());
        model.setEwsFlag(entity.getEwsFlag());

        if (entity.getPhoto() != null) {
            model.setPhotoBase64(
                    Base64.getEncoder().encodeToString(entity.getPhoto())
            );
            model.setPhotoContentType(entity.getPhotoContentType());
        }

        return model;
    }

}
