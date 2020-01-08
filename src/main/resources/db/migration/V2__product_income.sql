CREATE TABLE `income_order` (
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `comment` TEXT DEFAULT NULL,
    `date_created` TIMESTAMP NOT NULL
);

CREATE TABLE `income` (
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `product_id` INT(11) NOT NULL,
    `income_order_id` INT(11) NOT NULL,
    `income_price` DECIMAL(10, 2) NOT NULL,
    `income_price_usd` DECIMAL(10, 2) NOT NULL,
    `amount` DECIMAL(10, 2) NOT NULL,
    `residual` DECIMAL(10, 2) NOT NULL DEFAULT 0,
    `HAS_MoRE` TINYINT(1) DEFAULT 1,
    CONSTRAINT `fk_income_to_product` FOREIGN KEY (`product_id`) REFERENCES `products`(`id`),
    CONSTRAINT `fk_income_to_income_order` FOREIGN KEY (`income_order_id`) REFERENCES `income_order`(`id`)
);