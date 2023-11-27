package ru.netology.DubrovinVyacheslav.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.DubrovinVyacheslav.OperationHistoryApiApplicationTest;
import ru.netology.DubrovinVyacheslav.config.OperationProperties;
import ru.netology.DubrovinVyacheslav.entity.Currency;
import ru.netology.DubrovinVyacheslav.entity.Operation;

import static org.junit.jupiter.api.Assertions.*;

public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;
    @Autowired
    private StatementService statementService;
    @Autowired
    private OperationProperties property;

    @Test
    public void assertThatAsyncInputOperationServiceWorksRight() throws InterruptedException {
        Operation operation = new Operation(0, 2000, Currency.RUB, "Shoes Shop", 0);

        asyncInputOperationService.offerOperation(operation);
        Thread.sleep(property.getSleepMilliSeconds() * 5L);

        Operation testOperation = statementService.getOperation(operation.getCustomerId(), 0);
        assertEquals(operation, testOperation);
        assertEquals(operation.getId(), testOperation.getId());
        assertEquals(operation.getSum(), testOperation.getSum());
        assertEquals(operation.getCurrency(), testOperation.getCurrency());
        assertEquals(operation.getMerchant(), testOperation.getMerchant());
    }
}