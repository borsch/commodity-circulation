package ua.net.kurpiak.commoditycirculation.exceptions;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class AmountExceedLimitException extends BaseException {

    private String productName;
    private String productCode;
    private double existingAmount;
    private double wantedAmount;

    @Override
    public String formMessage() {
        return String.format(
            "Перевищено ліміт для товару %s(код: %s). Наявна кількість: %s, потрібна кількість: %s",
            productName, productCode, existingAmount, wantedAmount
        );
    }

    @Override
    public List<String> formListErrors() {
        return null;
    }
}
