package com.sabri.account.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="user_accounts")
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String phone;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String country;
	
	@NotEmpty
	private String department;
	
}
