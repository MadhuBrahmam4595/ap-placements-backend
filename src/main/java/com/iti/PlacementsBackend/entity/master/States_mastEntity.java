package com.iti.PlacementsBackend.entity.master;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "states_mast"
)
public class States_mastEntity {
   @Id
   private String statecode;
   private String statename;

   public States_mastEntity() {
   }

   public States_mastEntity(String statecode, String statename) {
      this.statecode = statecode;
      this.statename = statename;
   }

   public String getStatecode() {
      return this.statecode;
   }

   public void setStatecode(String statecode) {
      this.statecode = statecode;
   }

   public String getStatename() {
      return this.statename;
   }

   public void setStatename(String statename) {
      this.statename = statename;
   }

   public String toString() {
      return "States_mast [statecode=" + this.statecode + ", statename=" + this.statename + "]";
   }
}

