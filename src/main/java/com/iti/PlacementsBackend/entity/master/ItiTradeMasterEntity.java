package com.iti.PlacementsBackend.entity.master;

import java.util.HashMap;
import java.util.Map;

import com.iti.PlacementsBackend.util.HstoreDataType;

import jakarta.persistence.*;

@Entity
@Table(
   name = "ititrade_master"
)
public class ItiTradeMasterEntity {
   @Id
   private String tradeShort;
   private String tradeName;

   private Integer durationyrs;
   private String engNonengg;
   private String minQual;
   private Integer tradeFreeze;
   private Integer convApproval;
   private Integer tradeCode;
   @Column(
      columnDefinition = "hstore"
   )
   @Convert(
      converter = HstoreDataType.class
   )
   private Map<String, String> maxInternalMarks = new HashMap();
   private String typeAdmission;
   private String drNondr;
   private Integer unitStrength;
   private Integer displayOrder;

   public ItiTradeMasterEntity() {
   }

   public ItiTradeMasterEntity(String tradeShort, String tradeName, Integer durationyrs, String engNonengg, String minQual, Integer tradeFreeze, Integer convApproval, Integer tradeCode, Map<String, String> maxInternalMarks, String typeAdmission, String drNondr, Integer unitStrength, Integer displayOrder) {
      this.tradeShort = tradeShort;
      this.tradeName = tradeName;
      this.durationyrs = durationyrs;
      this.engNonengg = engNonengg;
      this.minQual = minQual;
      this.tradeFreeze = tradeFreeze;
      this.convApproval = convApproval;
      this.tradeCode = tradeCode;
      this.maxInternalMarks = maxInternalMarks;
      this.typeAdmission = typeAdmission;
      this.drNondr = drNondr;
      this.unitStrength = unitStrength;
      this.displayOrder = displayOrder;
   }

   public String getTradeShort() {
      return this.tradeShort;
   }

   public void setTradeShort(String tradeShort) {
      this.tradeShort = tradeShort;
   }

   public String getTradeName() {
      return this.tradeName;
   }

   public void setTradeName(String tradeName) {
      this.tradeName = tradeName;
   }

   public Integer getDurationyrs() {
      return this.durationyrs;
   }

   public void setDurationyrs(Integer durationyrs) {
      this.durationyrs = durationyrs;
   }

   public String getEngNonengg() {
      return this.engNonengg;
   }

   public void setEngNonengg(String engNonengg) {
      this.engNonengg = engNonengg;
   }

   public String getMinQual() {
      return this.minQual;
   }

   public void setMinQual(String minQual) {
      this.minQual = minQual;
   }

   public Integer getTradeFreeze() {
      return this.tradeFreeze;
   }

   public void setTradeFreeze(Integer tradeFreeze) {
      this.tradeFreeze = tradeFreeze;
   }

   public Integer getConvApproval() {
      return this.convApproval;
   }

   public void setConvApproval(Integer convApproval) {
      this.convApproval = convApproval;
   }

   public Integer getTradeCode() {
      return this.tradeCode;
   }

   public void setTradeCode(Integer tradeCode) {
      this.tradeCode = tradeCode;
   }

   public Map<String, String> getMaxInternalMarks() {
      return this.maxInternalMarks;
   }

   public void setMaxInternalMarks(Map<String, String> maxInternalMarks) {
      this.maxInternalMarks = maxInternalMarks;
   }

   public String getTypeAdmission() {
      return this.typeAdmission;
   }

   public void setTypeAdmission(String typeAdmission) {
      this.typeAdmission = typeAdmission;
   }

   public String getDrNondr() {
      return this.drNondr;
   }

   public void setDrNondr(String drNondr) {
      this.drNondr = drNondr;
   }

   public Integer getUnitStrength() {
      return this.unitStrength;
   }

   public void setUnitStrength(Integer unitStrength) {
      this.unitStrength = unitStrength;
   }

   public Integer getDisplayOrder() {
      return this.displayOrder;
   }

   public void setDisplayOrder(Integer displayOrder) {
      this.displayOrder = displayOrder;
   }

   public String toString() {
      return "ItiTradeMaster [tradeShort=" + this.tradeShort + ", tradeName=" + this.tradeName + ", durationyrs=" + this.durationyrs + ", engNonengg=" + this.engNonengg + ", minQual=" + this.minQual + ", tradeFreeze=" + this.tradeFreeze + ", convApproval=" + this.convApproval + ", tradeCode=" + this.tradeCode + ", maxInternalMarks=" + this.maxInternalMarks + ", typeAdmission=" + this.typeAdmission + ", drNondr=" + this.drNondr + ", unitStrength=" + this.unitStrength + ", displayOrder=" + this.displayOrder + "]";
   }
}

