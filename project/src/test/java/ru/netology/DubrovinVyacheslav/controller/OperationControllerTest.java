package ru.netology.DubrovinVyacheslav.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.netology.DubrovinVyacheslav.OperationHistoryApiApplicationTest;
import ru.netology.DubrovinVyacheslav.config.OperationProperties;
import ru.netology.DubrovinVyacheslav.entity.Currency;
import ru.netology.DubrovinVyacheslav.entity.Operation;

import java.util.List;

@AutoConfigureMockMvc
public class OperationControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private MockMvc controller;
    @Autowired
    private ObjectMapper objMapper;
    @Autowired
    private OperationProperties property;

    public OperationControllerTest() {}

    @Test
    public void assertThatOperationControllerWorksRight() throws Exception {
        int operationId = 0;
        int customerId = 1;
        Operation operation = new Operation(operationId, 1000, Currency.USD, "Computer", customerId);

        controller.perform(MockMvcRequestBuilders.get("/api/operations/" + customerId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        controller.perform(MockMvcRequestBuilders.post("/api/operations").contentType("application/json").content(objMapper.writeValueAsString(operation)));

        Thread.sleep(property.getSleepMilliSeconds() * 5L);

        controller.perform(MockMvcRequestBuilders.get("/api/operations/" + customerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objMapper.writeValueAsString(List.of(operation))));

        controller.perform(MockMvcRequestBuilders.delete("/api/operations/" + operationId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objMapper.writeValueAsString(operation)));

        controller.perform(MockMvcRequestBuilders.delete("/api/operations/7"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}