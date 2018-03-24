package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/get-data")
public class HelixDataIngestion {
	private String result = "success!!!";
	
	@GET
	public String getData() {
		return result;
	}
	
}
