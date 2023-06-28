package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CardInfo {
    private String number;
    private String month;
    private String year;
    private String name;
    private String cvv;

    public CardInfo withMonth(String month) {
        this.month = month;
        return this;
    }

    public CardInfo withYear(String year) {
        this.year = year;
        return this;
    }

    public CardInfo withNumber(String number) {
        this.number = number;
        return this;
    }

    public CardInfo withName(String name) {
        this.name = name;
        return this;
    }

    public CardInfo withCVV(String cvv) {
        this.cvv = cvv;
        return this;
    }

}
