package ru.netology.DubrovinVyacheslav.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.netology.DubrovinVyacheslav.OperationHistoryApiApplicationTest;
import ru.netology.DubrovinVyacheslav.entity.Customer;
import ru.netology.DubrovinVyacheslav.entity.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
public class CustomerControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private MockMvc controller;
    @Autowired
    private ObjectMapper objMapper;

    @Test
    public void assertThatControllerWorksRight() throws Exception {
        List<CustomerDTO> customers = new ArrayList<>();
        customers.add(new CustomerDTO(1, "Spring"));
        customers.add(new CustomerDTO(2, "Boot"));

        controller.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objMapper.writeValueAsString(customers)));

        controller.perform(MockMvcRequestBuilders.get("/api/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Spring"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));

        controller.perform(MockMvcRequestBuilders.get("/api/customers/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Boot"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"));

        controller.perform(MockMvcRequestBuilders.get("/api/customers/7"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Customer vadim = new Customer(9, "Vadim", "qwertyuiop");
        controller.perform(MockMvcRequestBuilders.post("/api/customers").contentType("application/json").content(objMapper.writeValueAsString(vadim)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(vadim.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(String.valueOf(vadim.getId())));

        customers.add(new CustomerDTO(vadim.getId(), vadim.getName()));
        controller.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objMapper.writeValueAsString(customers)));
    }
}