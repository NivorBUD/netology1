package ru.netology.DubrovinVyacheslav.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.DubrovinVyacheslav.OperationHistoryApiApplicationTest;
import ru.netology.DubrovinVyacheslav.entity.Currency;
import ru.netology.DubrovinVyacheslav.entity.Operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private StatementService statementService;

    @Test
    public void assertThatStatementServiceWorksRight() {
        int operationSum = 123;
        int clientId = 1;
        int operationId = 0;
        Currency operationCurrency = Currency.RUB;
        String operationMerchant = "Pharmacy";

        Operation operation = new Operation(operationId, operationSum, operationCurrency, operationMerchant, clientId);
        statementService.setOperation(clientId, operation);
        Operation testOperation = statementService.getOperation(clientId, 0);

        assertEquals(operation, testOperation);
        assertEquals(operationId, testOperation.getId());
        assertEquals(operationSum, testOperation.getSum());
        assertEquals(operationCurrency, testOperation.getCurrency());
        assertEquals(operationMerchant, testOperation.getMerchant());
    }
}