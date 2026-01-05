package com.iti.PlacementsBackend.entity.hrm;

import java.sql.Date;


import jakarta.persistence.*;

@Entity
@Table(name = "dept_test", schema = "hrms")
public class DeptTest {

    @EmbeddedId
    private DeptTestId id;
    
    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "test_id", insertable = false, updatable = false)
    private DeptTestMaster deptTestMaster;
	@Column(name = "pass_date", nullable = false)
    private Date passDate;

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
	 public DeptTestMaster getDeptTestMaster() {
			return deptTestMaster;
		}

		public void setDeptTestMaster(DeptTestMaster deptTestMaster) {
			this.deptTestMaster = deptTestMaster;
		}
	public DeptTest() {}

    public DeptTest(DeptTestId id,  Date passDate, Employee employee) {
        this.id = id;
        this.passDate = passDate;
        this.employee = employee;
    }

    public DeptTestId getId() {
        return id;
    }

    public void setId(DeptTestId id) {
        this.id = id;
    }

  

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
