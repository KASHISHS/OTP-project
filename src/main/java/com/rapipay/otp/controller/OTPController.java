package com.rapipay.otp.controller;

import com.rapipay.otp.app.OTP;
import com.rapipay.otp.app.Services.OTPService;
import com.rapipay.otp.exception.EmailNotFoundException;
import com.rapipay.otp.exception.InvalidEmailException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class OTPController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OTPController.class);

	@Autowired
	OTPService otpService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "OTP api up and running";
	}

	public boolean verifyEmail(String email) {
		boolean isValid=false;
		
 		return isValid;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendOTP")
	public String sendOTP(@RequestBody OTP otp) {
		LOGGER.info("Inside send OTP Controller");
		System.out.println("Inside Email");
		try {
			if(otp.getChannelName().equals("email")) {
				otpService.sendOTP(otp);
				
			}else {
				otpService.sendOTPSMS(otp);
			}
				
			
		}catch(Exception e){
			return e.toString();
		}
		
		return "successfully";
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/verifyOTP")
	public String verifyOTP(@RequestBody OTP otp){
		LOGGER.info("Inside verify OTP Controller");
		try {
			return otpService.verifyOTP(otp);
		}catch(Exception e) {
			return e.toString();
		}
		
	}

}
