package br.com.entelgy.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Vote submodel.
 * 
 * @author falvojr
 */
public class Vote {

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate = new Date();
	private String reCaptchaResponse;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getReCaptchaResponse() {
		return reCaptchaResponse;
	}

	public void setReCaptchaResponse(String reCaptchaResponse) {
		this.reCaptchaResponse = reCaptchaResponse;
	}

}
