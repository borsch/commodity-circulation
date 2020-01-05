package ua.net.kurpiak.commoditycirculation.convertors;

public class Fields {

    public static final class Product {
        public static final String CODE = "code";
        public static final String NAME = "name";
        public static final String UNIT = "unit";
        public static final String RESIDUAL = "residual";
        public static final String DEFAULT_PURCHASE_PRICE = "defaultPurchasePrice";
        public static final String DEFAULT_PURCHASE_PRICE_USD = "defaultPurchasePriceUsd";
        public static final String DEFAULT_SALE_PRICE = "defaultSalePrice";
        public static final String INCOMES_INFO = "incomesInfo";
        public static final String OUTCOMES_INFO = "outcomesInfo";
    }

    public static final class IncomeOrder {
        public static final String DATE_CREATED = "dateCreated";
        public static final String COMMENT = "comment";
        public static final String TOTAL_PRICE = "totalPrice";
        public static final String TOTAL_PRICE_USD = "totalPriceUsd";
    }

    public static final class OutcomeOrder {
        public static final String DATE_CREATED = "dateCreated";
        public static final String COMMENT = "comment";
        public static final String TOTAL_PRICE = "totalPrice";
        public static final String TOTAL_PROFIT = "totalProfit";
    }


    public static final String ID = "id";

}
