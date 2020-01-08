CREATE TABLE `products` (
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `code` VARCHAR (25) NOT NULL,
    `name` VARCHAR (250) NOT NULL,
    `unit` VARCHAR (10) NOT NULL,
    `residual` DECIMAL(10, 2) NOT NULL DEFAULT 0,
    `default_purchase_price` DECIMAL (10, 2) NOT NULL DEFAULT 0,
    `default_purchase_price_usd` DECIMAL (10, 2) NOT NULL DEFAULT 0,
    `default_sale_price` DECIMAL (10, 2) NOT NULL DEFAULT 0,
    UNIQUE `unique_product_code` (`code`),
    UNIQUE `unique_product_name` (`name`)
);