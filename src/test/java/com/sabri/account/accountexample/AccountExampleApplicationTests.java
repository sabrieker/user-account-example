package com.sabri.account.accountexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sabri.account.dto.UserAccountDTO;
import com.sabri.account.entity.UserAccount;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountExampleApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private ModelMapper mapper;
	

    @Test
    public void getsAllUserAccounts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/accounts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void returnsNotFoundForInvalidSingleUserAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/account/100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void addsNewUserAccount() throws Exception {
        String newUserAccount = "{\"name\":\"Generic Name\",\"phone\":\"222222222\",\"email\":\"genericname@company.com\",\"address\":\"Generic Street 42 Earth\",\"country\":\"Navarro\",\"department\":\"T21R\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/account/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserAccount)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/account/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void addsSecondUserAccount() throws Exception {
        String newUserAccount = "{\"name\":\"Kuill\",\"phone\":\"99999999999999\",\"email\":\"genericname@company.com\",\"address\":\"Generic Street 42 Hoth\",\"country\":\"Arvala\",\"department\":\"T21R\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/account/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserAccount)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void update_First_UserAccount() throws Exception {
    	String updateUserAccount = "{\"name\":\"Kuill\",\"phone\":\"99999999999999\",\"email\":\"genericname@company.com\",\"address\":\"Generic Street 42 Hoth\",\"country\":\"Arvala\",\"department\":\"T21R\"}";
    	mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/account/1")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(updateUserAccount)
    			.accept(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andReturn();
    }
 
    @Test
    public void delete_First_UserAccount() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/account/1")
    			.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andReturn();
    }
    
    @Test
    public void testModelMapper_dTO_to_entity() {
    	UserAccountDTO dto = UserAccountDTO.builder().name("Generic Name")
    			.phone("222222222").email("genericname@company.com").address("Generic Street 42 Earth").country("Navarro").department("T21R").build();
    	
    	UserAccount entity = mapper.map(dto, UserAccount.class);
    	
    	assertEquals(dto.getName(), entity.getName());
    	assertEquals(dto.getAddress(), entity.getAddress());
    	assertEquals(dto.getCountry(), entity.getCountry());
    	assertEquals(dto.getDepartment(), entity.getDepartment());
    	assertEquals(dto.getPhone(), entity.getPhone());
    }
    
    @Test
    public void testModelMapper_entity_to_dto() {
    	UserAccount entity = UserAccount.builder().id(1L).name("Generic Name")
    			.phone("222222222").email("genericname@company.com").address("Generic Street 42 Earth").country("Navarro").department("T21R").build();
    	
    	UserAccount dto = mapper.map(entity, UserAccount.class);
    	
    	assertEquals(entity.getId(), dto.getId());
    	assertEquals(entity.getName(), dto.getName());
    	assertEquals(entity.getAddress(), dto.getAddress());
    	assertEquals(entity.getCountry(), dto.getCountry());
    	assertEquals(entity.getDepartment(), dto.getDepartment());
    	assertEquals(entity.getPhone(), dto.getPhone());
    }


}
