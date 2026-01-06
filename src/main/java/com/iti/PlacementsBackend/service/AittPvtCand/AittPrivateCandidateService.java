package com.iti.PlacementsBackend.service.AittPvtCand;

import com.iti.PlacementsBackend.entity.aittpvtcand.*;
import com.iti.PlacementsBackend.model.AittPvtCand.AittPrivateCandidateModel;
import com.iti.PlacementsBackend.model.AittPvtCand.AittPrivateCandidateReportModel;
import com.iti.PlacementsBackend.model.AittPvtCand.CandidateQualificationModel;
import com.iti.PlacementsBackend.model.AittPvtCand.WorkExperienceModel;
import com.iti.PlacementsBackend.repo.AittPvtCand.AittPrivateCandidateRepo;
import com.iti.PlacementsBackend.repo.hrm.CasteMasterRepository;
import com.iti.PlacementsBackend.repo.hrm.SubCasteMasterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AittPrivateCandidateService {


    private final AittPrivateCandidateRepo applicantRepository;
    private final CasteMasterRepository casteRepo;
    private final SubCasteMasterRepository subCasteRepo;

    @Transactional
    public Long save(AittPrivateCandidateModel dto) {

        AittPrivateCandidateEntity applicant = new AittPrivateCandidateEntity();

        // ========= PERSONAL DETAILS =========
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

        // ===== ADDRESS & CONTACT DETAILS =====
        CandidateAddressContactEntity address = new CandidateAddressContactEntity();
        address.setPermanentAddress(dto.getPermanentAddress());
        address.setCorrespondenceAddress(dto.getCorrespondenceAddress());
        address.setMobile(dto.getMobile());
        address.setAadhar(dto.getAadhar());
        address.setEmail(dto.getEmail());
        address.setTrade(dto.getTradeApplied());
        // Bi-directional linking
        address.setCandidate(applicant);
        applicant.setAddressContact(address);


        // ======== PRESENT WORKING DETAILS
        CandidateWorkDetailsEntity workDetails = new CandidateWorkDetailsEntity();
        workDetails.setOfficeAddress(dto.getOfficeAddress());
        workDetails.setEmployeeIdNumber(dto.getEmployeeIdNumber());
        workDetails.setEmployerMobile(dto.getEmployerMobile());
        workDetails.setEmployerEmail(dto.getEmployerEmail());
        workDetails.setIndustryRegistrationDetails(dto.getIndustryRegistrationDetails());
        // link both sides
        workDetails.setCandidate(applicant);
        applicant.setWorkDetails(workDetails);

        // ===== ESTABLISHMENT & STATUTORY DETAILS =====
        CandidateEstablishmentStatutoryEntity est = new CandidateEstablishmentStatutoryEntity();

        est.setAtsRegistered(dto.getAtsRegistered());
        est.setMsmeRegistered(dto.getMsmeRegistered());
        est.setFactoriesAct(dto.getFactoriesAct());
        est.setShopsAct(dto.getShopsAct());

        est.setApprenticeActDate(dto.getApprenticeActDate());

        est.setExperienceCert(dto.getExperienceCert());
        est.setCharacterCert(dto.getCharacterCert());

        est.setGpfEpfNo(dto.getGpfEpfNo());
        est.setGpfEpfDate(dto.getGpfEpfDate());

        est.setEsiNo(dto.getEsiNo());
        est.setEsiDate(dto.getEsiDate());

        // bi-directional link
        est.setCandidate(applicant);
        applicant.setEstablishmentDetails(est);

        //======= EDUCATIONAL & TECHNICAL QUALIFICATIONS
        List<CandidateQualificationEntity> qualificationEntities = new ArrayList<>();

        for (CandidateQualificationModel q : dto.getQualifications()) {

            CandidateQualificationEntity entity = new CandidateQualificationEntity();

            entity.setFromYear(q.getFromYear());
            entity.setToYear(q.getToYear());
            entity.setInstituteName(q.getInstituteName());
            entity.setTradeName(q.getTradeName());
            entity.setExamName(q.getExamName());
            entity.setSscMarks(q.getSscMarks());

            entity.setCandidate(applicant);
            qualificationEntities.add(entity);
        }
        applicant.setQualifications(qualificationEntities);

        // =========== WORK EXPERIENCE DETAILS

        if (dto.getWorkExperiences() != null) {

            for (WorkExperienceModel wm : dto.getWorkExperiences()) {

                CandidateWorkExperienceEntity exp =
                        new CandidateWorkExperienceEntity();

                exp.setIndustryName(wm.getIndustryName());
                exp.setDesignation(wm.getDesignation());
                exp.setFromDate(wm.getFromDate());
                exp.setToDate(wm.getToDate());
                exp.setYearsMonths(wm.getYearsMonths());

                exp.setCandidate(applicant);
                applicant.getWorkExperiences().add(exp);
            }
        }

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
