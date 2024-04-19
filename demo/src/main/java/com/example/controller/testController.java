package com.example.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.response.StringResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class testController {
	
	@RequestMapping(value = "/hello", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<StringResponse> getEBPPlansByScope(HttpServletRequest request)
			throws Exception {
		StringResponse stringResponse = new StringResponse("hello");
		 return ResponseEntity.ok(stringResponse);
		
	}

}