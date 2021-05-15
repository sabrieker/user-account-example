package com.sabri.account.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseResponseDTO {

	@Builder.Default
	private boolean success = true;
	private String responseMessage;
	private String responseCode;
	
	@NotNull
	@Builder.Default
	private Long timestamp = System.currentTimeMillis();
}
