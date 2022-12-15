package com.company.balanceservice;

import com.company.balanceservice.controller.BalanceController;
import com.company.balanceservice.entity.BankAccount;
import com.company.balanceservice.entity.MessageResponse;
import com.company.balanceservice.service.BalanceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceControllerTest {
    @Mock
    private BalanceServiceImpl balanceService;
    private BankAccount bankAccount;
    private List<BankAccount> bankAccountList;

    @InjectMocks
    private BalanceController balanceController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        bankAccount = new BankAccount(1,1000);
        mockMvc = MockMvcBuilders.standaloneSetup(balanceController).build();
    }

    @AfterEach
    void tearDown() {
        bankAccount = null;
    }

    @Test
    public void PostMappingCreateBalance() throws Exception {
        Long longValue = 1000L;
        when(balanceService.createBalance(any())).thenReturn(bankAccount);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/account").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(longValue))).
                andExpect(status().isCreated());
        verify(balanceService, times(1)).createBalance(any());
    }

    @Test
    public void PostMappingChangeBalance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/account/1").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(bankAccount))).
                andExpect(status().isOk());
        verify(balanceService, times(1)).changeBalance(any(), any());
    }

    @Test
    public void GetMappingBalance() throws Exception {
        when(balanceService.getBalance(any())).thenReturn(Optional.of(1L));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/account/1").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(bankAccount))).
                andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
