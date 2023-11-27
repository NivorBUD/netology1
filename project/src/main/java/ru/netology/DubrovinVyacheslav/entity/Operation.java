package ru.netology.DubrovinVyacheslav.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation implements ConsolePrintable {
    private Integer id;
    private Integer sum;
    private Currency currency;
    private String merchant;
    private Integer customerId;

    @Override
    public void printToConsole() {
        System.out.println("id клиента: " + id + "." +
                " Сумма операции, совершенных клиентом " + sum +
                currency + "." +
                " Оператор, на которого была совершена операция" + merchant + ".");
    }
}
