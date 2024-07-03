package com.cms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter

public class EmailRequest {
	private String recipient;
	private String msgBody;
	private String subject;
	private String attachment;
}
