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
    }

    public static final class Income {
        public static final String PRODUCT_ID = "productId";
        public static final String PRODUCT_INFO = "productInfo";
        public static final String INCOME_ORDER_ID = "incomeOrderId";
        public static final String RESIDUAL = "residual";
        public static final String INCOME_PRICE = "incomePrice";
        public static final String INCOME_PRICE_USD = "incomePriceUsd";
        public static final String AMOUNT = "amount";
    }

    public static final class IncomeOrder {
        public static final String DATE_CREATED = "dateCreated";
        public static final String INCOMES_ID = "incomes_id";
        public static final String COMMENT = "comment";
    }

    public static final class OutcomeOrder {
        public static final String DATE_CREATED = "dateCreated";
        public static final String COMMENT = "comment";
    }


    public static final String ID = "id";

}
