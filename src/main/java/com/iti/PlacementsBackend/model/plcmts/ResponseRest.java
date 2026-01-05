package com.iti.PlacementsBackend.model.plcmts;

public class ResponseRest {
	   private String msg;

	   public ResponseRest() {
	   }

	   public ResponseRest(String msg) {
	      this.msg = msg;
	   }

	   public String getMsg() {
	      return this.msg;
	   }

	   public void setMsg(String msg) {
	      this.msg = msg;
	   }

	   public String toString() {
	      return "ResponseRest [msg=" + this.msg + "]";
	   }
	}

