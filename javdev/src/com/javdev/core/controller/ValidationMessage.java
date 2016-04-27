package com.javdev.core.controller;

import lombok.Getter;
import lombok.Setter;

public class ValidationMessage {

	@Getter @Setter private boolean valid;
	@Getter @Setter private String detail;
	@Getter @Setter private String summary;

	public ValidationMessage() {

	}

	public ValidationMessage(boolean valid, String summary, String detail) {
		this.valid = valid;
		this.detail = detail;
		this.summary = summary;
	}



}
