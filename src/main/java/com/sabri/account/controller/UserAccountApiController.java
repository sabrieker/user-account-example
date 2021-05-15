package com.sabri.account.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sabri.account.dto.BaseResponseDTO;
import com.sabri.account.dto.UserAccountDTO;
import com.sabri.account.entity.UserAccount;
import com.sabri.account.exception.BaseException;
import com.sabri.account.service.UserAccountCommandService;
import com.sabri.account.service.UserAccountQueryService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api", produces = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" }, consumes = {
		MediaType.APPLICATION_JSON_VALUE})
@OpenAPIDefinition(
        info = @Info(
                title = "User Account API",
                version = "0.0.1",
                description = "This API provides solution for User Account Data",
                license = @License(name = "Apache 2.0", url = "https://foo.bar"),
                contact = @Contact(url = "https://sample-server.com", name = "Sabri", email = "sabrieker@mail.com")
        )
)
@RequiredArgsConstructor
@Slf4j
public class UserAccountApiController {

	private final UserAccountQueryService userAccountQueryService;
	private final UserAccountCommandService userAccountCommandService;
	private final ModelMapper mapper;

	@GetMapping(value = "/user/accounts", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Get the list of the accounts")
	public List<UserAccountDTO> getAccounts( 
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy) {
		log.info("Get List of the User Accounts");
		
		return userAccountQueryService.findAll(pageNo,pageSize,sortBy);
	}

	@GetMapping(value = "/user/account/{id}", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Detail of the UserAccount with provided id")
	public UserAccountDTO getUserAccount(@PathVariable long id) {
		log.info("Get User Account with id:{}",id);
		Optional<UserAccount> optionalOne = userAccountQueryService.findOne(id);

		if(optionalOne.isPresent()) {
			return optionalOne.map(var-> mapper.map(var, UserAccountDTO.class)).get();
		}
		
		log.warn("Not Found User Account id:{}",id);
		throw new BaseException(HttpStatus.NOT_FOUND, String.format("Invalid UserAccount id %s", id));
	}

	@PutMapping(value = "/user/account/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Create UserAccount with valid UserAccountDTO")
	public BaseResponseDTO createUserAccountDTO(@PathVariable long id,@Valid @RequestBody UserAccountDTO userAccountDTO) {
		log.info("Create User Account id:{} dto:{}",id,userAccountDTO);
		if(userAccountQueryService.findByEmail(userAccountDTO.getEmail()).isPresent()) {
			log.warn("Email is allready exist {}",userAccountDTO.getEmail());
			throw new BaseException(HttpStatus.NOT_ACCEPTABLE, String.format("User Account allready created for email(%s)", userAccountDTO.getEmail()));
		}
		
		
		UserAccount userAccount = mapper.map(userAccountDTO, UserAccount.class);

		userAccountCommandService.save(userAccount);

		log.info("User Account succesfully created id:{} ",id,userAccountDTO);
		return BaseResponseDTO.builder().timestamp(System.currentTimeMillis()).responseMessage("Successfully saved.").responseCode("SUCCESS").build();
	}
	
	@PatchMapping(value = "/user/account/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Update UserAccount with valid UserAccountDTO")
	public BaseResponseDTO updateUserAccountDTO(@PathVariable long id,@Valid @RequestBody UserAccountDTO userAccountDTO) {
		log.info("Update User Account id:{} dto:{}",id,userAccountDTO);
		Optional<UserAccount> userByEmail = userAccountQueryService.findByEmail(userAccountDTO.getEmail());
		if(userByEmail.isPresent() && userByEmail.get().getId() != id) {
			throw new BaseException(HttpStatus.NOT_ACCEPTABLE, String.format("User Account allready created for email(%s)", userAccountDTO.getEmail()));
		}
		
		Optional<UserAccount> oldUserAccount  = userAccountQueryService.findOne(id);
		if(!oldUserAccount.isPresent()) {
			throw new BaseException(HttpStatus.NOT_ACCEPTABLE, String.format("There is no User Account with id(%s)", id));
		}
		
		mapper.map(userAccountDTO, oldUserAccount.get());
		
		userAccountCommandService.update(oldUserAccount.get());
		
		log.info("User Account succesfully updated id:{} ",id,userAccountDTO);
		return BaseResponseDTO.builder().timestamp(System.currentTimeMillis()).responseCode("SUCCESS").build();
	}
	
	@DeleteMapping(value = "/user/account/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@Operation(description = "Delete UserAccount with id")
	public BaseResponseDTO deleteUserAccountDTO(@PathVariable long id) {
		log.info("Delete User Account id:{} ",id);
		
		Optional<UserAccount> theOne = userAccountQueryService.findOne(id);
		
		if(theOne.isPresent()) {
			userAccountCommandService.delete(id);
		}
		
		log.info("User Account succesfully deleted id:{} ",id);
		return BaseResponseDTO.builder().timestamp(System.currentTimeMillis()).responseCode("SUCCESS").build();
	}
}
