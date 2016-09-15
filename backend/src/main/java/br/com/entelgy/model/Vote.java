package br.com.entelgy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "votes")
public class Vote {

	@Id
	private String id;
	@DBRef
	@Indexed
	private String idCandidate;
	private String reCaptchaResponse;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCandidate() {
		return idCandidate;
	}

	public void setIdCandidate(String idCandidate) {
		this.idCandidate = idCandidate;
	}

	public String getReCaptchaResponse() {
		return reCaptchaResponse;
	}

	public void setReCaptchaResponse(String reCaptchaResponse) {
		this.reCaptchaResponse = reCaptchaResponse;
	}

}
