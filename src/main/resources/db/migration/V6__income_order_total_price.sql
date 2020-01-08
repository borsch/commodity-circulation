ALTER TABLE `income_order`
    ADD COLUMN `total_price` DECIMAL(10, 2) NOT NULL,
    ADD COLUMN `total_price_usd` DECIMAL(10, 2) NOT NULL;