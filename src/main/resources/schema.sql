CREATE TABLE IF NOT EXISTS `categories`
(
    `id`    int         NOT NULL AUTO_INCREMENT,
    `title` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `nomenclature`
(
    `code`        int NOT NULL,
    `category_id` int         DEFAULT NULL,
    `title`       varchar(45) DEFAULT NULL,
    `opt_price`   double      DEFAULT NULL,
    `price`       double      DEFAULT NULL,
    PRIMARY KEY (`code`),
    CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
);

CREATE TABLE IF NOT EXISTS `market`
(
    `id`      int NOT NULL,
    `address` varchar(45) DEFAULT NULL,
    employee  varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `supply`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `supplydate` DATE,
    `market_id`  INT,
    `processed`  BOOLEAN,
    CONSTRAINT fk_supply_market FOREIGN KEY (`market_id`) REFERENCES `market` (`id`)
);

CREATE TABLE IF NOT EXISTS `supply_item`
(
    `id`                INT AUTO_INCREMENT PRIMARY KEY,
    `supply_id`         INT,
    `nomenclature_code` INT,
    `quantity`          INT,
    CONSTRAINT fk_supply_items_supply FOREIGN KEY (`supply_id`) REFERENCES `supply` (`id`),
    CONSTRAINT fk_supply_items_product FOREIGN KEY (`nomenclature_code`) REFERENCES `nomenclature` (`code`)
);

CREATE TABLE IF NOT EXISTS stock
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    market_id         INT,
    nomenclature_code INT,
    quantity          INT,
    CONSTRAINT fk_stock_market FOREIGN KEY (market_id) REFERENCES `market` (`id`),
    CONSTRAINT fk_stock_nomenclature FOREIGN KEY (nomenclature_code) REFERENCES `nomenclature` (`code`)
);

CREATE TABLE IF NOT EXISTS `sale`
(
    `id`        INT AUTO_INCREMENT PRIMARY KEY,
    `saledate`  DATE,
    `market_id` INT,
    `processed` BOOLEAN,
    CONSTRAINT fk_supply_market FOREIGN KEY (`market_id`) REFERENCES `market` (`id`)
);

CREATE TABLE IF NOT EXISTS `sale_item`
(
    `id`                INT AUTO_INCREMENT PRIMARY KEY,
    `sale_id`           INT,
    `nomenclature_code` INT,
    `quantity`          INT,
    CONSTRAINT fk_sale_items_sale FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`),
    CONSTRAINT fk_sale_items_nom FOREIGN KEY (`nomenclature_code`) REFERENCES `nomenclature` (`code`)
);

CREATE TABLE IF NOT EXISTS `writeoff`
(
    `id`              INT AUTO_INCREMENT PRIMARY KEY,
    `writeoffdate`    DATE,
    `market`          INT,
    `nomenclature`    INT,
    `reason`          VARCHAR(50),
    FOREIGN KEY (`market`) REFERENCES `market` (`id`),
    FOREIGN KEY (`nomenclature`) REFERENCES `nomenclature` (`code`)
);

CREATE TABLE IF NOT EXISTS `inventory`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `inventorydate` DATE,
    `market_id`  INT,
    `processed`  BOOLEAN,
    CONSTRAINT fk_inventory_market FOREIGN KEY (`market_id`) REFERENCES `market` (`id`)
);

CREATE TABLE IF NOT EXISTS `inventory_item`
(
    `id`                INT AUTO_INCREMENT PRIMARY KEY,
    `inventory_id`      INT,
    `nomenclature_code` INT,
    `current_quantity`  INT,
    `collected_quantity` INT,
    CONSTRAINT fk_inventory_items_supply FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`),
    CONSTRAINT fk_inventory_items_nom FOREIGN KEY (`nomenclature_code`) REFERENCES `nomenclature` (`code`)
);