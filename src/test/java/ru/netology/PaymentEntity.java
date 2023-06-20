package ru.netology;

import lombok.Data;

@Data
public class PaymentEntity {
    private String status;
    private Integer amount;
}
