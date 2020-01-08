CREATE TABLE `outcome_order` (
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `comment` TEXT DEFAULT NULL,
    `date_created` TIMESTAMP NOT NULL,
    `total_price` DECIMAL(10, 2) NOT NULL,
    `total_profit` DECIMAL(10, 2) NOT NULL
);

CREATE TABLE `outcome` (
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `product_id` INT(11) NOT NULL,
    `outcome_order_id` INT(11) NOT NULL,
    `amount` DECIMAL(10, 2) NOT NULL,
    `sale_price` DECIMAL(10, 2) NOT NULL,
    CONSTRAINT `fk_outcome_to_product` FOREIGN KEY (`product_id`) REFERENCES `products`(`id`),
    CONSTRAINT `fk_outcome_to_outcome_order` FOREIGN KEY (`outcome_order_id`) REFERENCES `outcome_order`(`id`)
);

CREATE TABLE `outcome_to_income` (
  `outcome_id` INT(11) NOT NULL,
  `income_id` INT(11) NOT NULL,
  `amount` DECIMAL(10, 2) NOT NULL,
  UNIQUE `pk_outcome_to_income` (`outcome_id`, `income_id`),
  CONSTRAINT `fk_outcome_id_to_outcome` FOREIGN KEY (`outcome_id`) REFERENCES `outcome`(`id`),
  CONSTRAINT `fk_income_id_to_income` FOREIGN KEY (`income_id`) REFERENCES `income`(`id`)
);