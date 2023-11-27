package ru.netology.DubrovinVyacheslav.service;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.DubrovinVyacheslav.config.OperationProperties;
import ru.netology.DubrovinVyacheslav.entity.Operation;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class AsyncInputOperationService {
    private final Queue<Operation> queue = new LinkedList<>();
    private final StatementService statementService;
    private final OperationProperties properties;

    public AsyncInputOperationService(StatementService statementService, OperationProperties properties) {
        this.statementService = statementService;
        this.properties = properties;
    }

    public boolean offerOperation(Operation operation) {
        return queue.offer(operation);
    }

    @PostConstruct
    private void postInit() {
        this.startAsyncOperationProcessing();
    }

    private void startAsyncOperationProcessing() {
        new Thread(this::processQueue).start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = queue.poll();

            if (operation == null) {
                try {
                    System.out.println("Waiting for next operation in queue");
                    Thread.sleep(properties.getSleepMilliSeconds());
                } catch (InterruptedException exc) {
                    throw new RuntimeException(exc);
                }
            } else {
                System.out.println("Processing operation:" + operation);
                statementService.setOperation(operation.getCustomerId(), operation);
            }
        }
    }
}
