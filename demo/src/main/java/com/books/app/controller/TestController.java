package com.books.app.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.books.app.pojo.StringResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TestController {
	
	@RequestMapping(value = "/api/test/hello", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<StringResponse> getEBPPlansByScope(HttpServletRequest request)
			throws Exception {
		StringResponse stringResponse = new StringResponse("hello");
		 return ResponseEntity.ok(stringResponse);
		
	}

}
