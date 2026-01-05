package com.iti.PlacementsBackend.model;

public class AdmissionModel {
	   private String roleid;
	   private String ins_code;
	   private String userName;
	   private String adm_num;
	   private String name;
	   private String iti_code;
	   private String iti_name;
	   private String dist_code;
	   private String dist_name;
	   private String year_of_admission;
	   private String trade_name;
	   private String trade_code;
	   private String secretkey;
	   private String message;
	   private String action;
	   private Long scheduleId;
	   private String scheduleType;
	   private String scheduleDate;
	   private String scheduleLocation;
	   private String scheduleDesc;
	   private String plcmtType;

	   public String getPlcmtType() {
	      return this.plcmtType;
	   }

	   public void setPlcmtType(String plcmtType) {
	      this.plcmtType = plcmtType;
	   }

	   public Long getScheduleId() {
	      return this.scheduleId;
	   }

	   public void setScheduleId(Long scheduleId) {
	      this.scheduleId = scheduleId;
	   }

	   public String getScheduleType() {
	      return this.scheduleType;
	   }

	   public void setScheduleType(String scheduleType) {
	      this.scheduleType = scheduleType;
	   }

	   public String getScheduleDate() {
	      return this.scheduleDate;
	   }

	   public void setScheduleDate(String scheduleDate) {
	      this.scheduleDate = scheduleDate;
	   }

	   public String getScheduleLocation() {
	      return this.scheduleLocation;
	   }

	   public void setScheduleLocation(String scheduleLocation) {
	      this.scheduleLocation = scheduleLocation;
	   }

	   public String getScheduleDesc() {
	      return this.scheduleDesc;
	   }

	   public void setScheduleDesc(String scheduleDesc) {
	      this.scheduleDesc = scheduleDesc;
	   }

	   public String getRoleid() {
	      return this.roleid;
	   }

	   public void setRoleid(String roleid) {
	      this.roleid = roleid;
	   }

	   public String getIns_code() {
	      return this.ins_code;
	   }

	   public void setIns_code(String ins_code) {
	      this.ins_code = ins_code;
	   }

	   public String getUserName() {
	      return this.userName;
	   }

	   public String getAction() {
	      return this.action;
	   }

	   public void setAction(String action) {
	      this.action = action;
	   }

	   public void setUserName(String userName) {
	      this.userName = userName;
	   }

	   public String getAdm_num() {
	      return this.adm_num;
	   }

	   public void setAdm_num(String adm_num) {
	      this.adm_num = adm_num;
	   }

	   public String getName() {
	      return this.name;
	   }

	   public void setName(String name) {
	      this.name = name;
	   }

	   public String getIti_code() {
	      return this.iti_code;
	   }

	   public void setIti_code(String iti_code) {
	      this.iti_code = iti_code;
	   }

	   public String getIti_name() {
	      return this.iti_name;
	   }

	   public void setIti_name(String iti_name) {
	      this.iti_name = iti_name;
	   }

	   public String getDist_code() {
	      return this.dist_code;
	   }

	   public void setDist_code(String dist_code) {
	      this.dist_code = dist_code;
	   }

	   public String getDist_name() {
	      return this.dist_name;
	   }

	   public void setDist_name(String dist_name) {
	      this.dist_name = dist_name;
	   }

	   public String getYear_of_admission() {
	      return this.year_of_admission;
	   }

	   public void setYear_of_admission(String year_of_admission) {
	      this.year_of_admission = year_of_admission;
	   }

	   public String getTrade_name() {
	      return this.trade_name;
	   }

	   public void setTrade_name(String trade_name) {
	      this.trade_name = trade_name;
	   }

	   public String getTrade_code() {
	      return this.trade_code;
	   }

	   public void setTrade_code(String trade_code) {
	      this.trade_code = trade_code;
	   }

	   public String getSecretkey() {
	      return this.secretkey;
	   }

	   public void setSecretkey(String secretkey) {
	      this.secretkey = secretkey;
	   }

	   public String getMessage() {
	      return this.message;
	   }

	   public void setMessage(String message) {
	      this.message = message;
	   }

	   public String toString() {
	      return "Admission [roleid=" + this.roleid + ", ins_code=" + this.ins_code + ", userName=" + this.userName + ", adm_num=" + this.adm_num + ", name=" + this.name + ", iti_code=" + this.iti_code + ", iti_name=" + this.iti_name + ", dist_code=" + this.dist_code + ", dist_name=" + this.dist_name + ", year_of_admission=" + this.year_of_admission + ", trade_name=" + this.trade_name + ", trade_code=" + this.trade_code + ", secretkey=" + this.secretkey + ", message=" + this.message + ", action=" + this.action + ", scheduleId=" + this.scheduleId + ", scheduleType=" + this.scheduleType + ", scheduleDate=" + this.scheduleDate + ", scheduleLocation=" + this.scheduleLocation + ", scheduleDesc=" + this.scheduleDesc + ", plcmtType=" + this.plcmtType + "]";
	   }
	}
