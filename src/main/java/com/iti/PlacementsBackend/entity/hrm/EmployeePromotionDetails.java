package com.iti.PlacementsBackend.entity.hrm;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "employee_promotion_details", schema = "hrms")
public class EmployeePromotionDetails {

    @EmbeddedId
    private EmployeePromotionId id;
    
    @ManyToOne
    @JoinColumn(name = "promotion_id", referencedColumnName = "desig_code", insertable = false, updatable = false)
    private DesignationMaster designationMaster;
  
    public DesignationMaster getDesignationMaster() {
		return designationMaster;
	}

	public void setDesignationMaster(DesignationMaster designationMaster) {
		this.designationMaster = designationMaster;
	}

	@Column(nullable = false)
    private Date reportingDate;

    @ManyToOne
    @JoinColumn(name = "employee_code", insertable = false, updatable = false)
    private Employee employee;
    
    @Column(name = "iti_code")
    private String itiCode;

    public String getItiCode() {
		return itiCode;
	}

	public void setItiCode(String itiCode) {
		this.itiCode = itiCode;
	}

	@Column(nullable = false)
    private String placeOfReporting;

    @Column(nullable = false)
    private String certificatePath;  // Store file path
    

    public EmployeePromotionDetails() {}

    public EmployeePromotionDetails(EmployeePromotionId id,  Date reportingDate, Employee employee,
                                    String placeOfReporting, String certificatePath) {
        this.id = id;
        
        this.reportingDate = reportingDate;
        this.employee = employee;
        this.placeOfReporting = placeOfReporting;
        this.certificatePath = certificatePath;
    }

    public EmployeePromotionId getId() {
        return id;
    }

    public void setId(EmployeePromotionId id) {
        this.id = id;
    }

  

    public Date getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPlaceOfReporting() {
        return placeOfReporting;
    }

    public void setPlaceOfReporting(String placeOfReporting) {
        this.placeOfReporting = placeOfReporting;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }
}
