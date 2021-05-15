package com.sabri.account.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class UserAccountDTO {
	@NotNull
	private String name;
	
	@NotNull
	private String phone;
	
	@NotNull
	private String email;
	
	@NotNull
	private String address;
	
	@NotNull
	private String country;
	
	@NotNull
	private String department;
}
