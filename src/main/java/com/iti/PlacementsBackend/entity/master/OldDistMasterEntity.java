package com.iti.PlacementsBackend.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "dist_mst"
)
public class OldDistMasterEntity {
   private String statecode;
   @Id
   private String dist_code;
   private String dist_name;
   private String itidist_code;
   private String new_dist;

   public OldDistMasterEntity() {
   }

   public OldDistMasterEntity(String statecode, String dist_code, String dist_name, String itidist_code, String new_dist) {
      this.statecode = statecode;
      this.dist_code = dist_code;
      this.dist_name = dist_name;
      this.itidist_code = itidist_code;
      this.new_dist = new_dist;
   }

   public String getStatecode() {
      return this.statecode;
   }

   public void setStatecode(String statecode) {
      this.statecode = statecode;
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

   public String getItidist_code() {
      return this.itidist_code;
   }

   public void setItidist_code(String itidist_code) {
      this.itidist_code = itidist_code;
   }

   public String getNew_dist() {
      return this.new_dist;
   }

   public void setNew_dist(String new_dist) {
      this.new_dist = new_dist;
   }

   public String toString() {
      return "OldDistMaster [statecode=" + this.statecode + ", dist_code=" + this.dist_code + ", dist_name=" + this.dist_name + ", itidist_code=" + this.itidist_code + ", new_dist=" + this.new_dist + "]";
   }
}
