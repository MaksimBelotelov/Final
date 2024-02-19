CREATE TABLE `categories` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `title` varchar(45) NOT NULL,
                              PRIMARY KEY (`id`)
);


CREATE TABLE `nomenclature` (
                                `code` int NOT NULL,
                                `category_id` int DEFAULT NULL,
                                `title` varchar(45) DEFAULT NULL,
                                `opt_price` double DEFAULT NULL,
                                `price` double DEFAULT NULL,
                                PRIMARY KEY (`code`),
                                CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
);

CREATE TABLE `market` (
                          `id` int NOT NULL,
                          `address` varchar(45) DEFAULT NULL,
                          `empoyee` varchar(45) DEFAULT NULL,
                          PRIMARY KEY (`id`)
);

CREATE TABLE `stores` (
                          `market_id` int NOT NULL,
                          `nom_id` int NOT NULL,
                          `amount` int DEFAULT NULL,
                          PRIMARY KEY (`market_id`,`nom_id`),
                          CONSTRAINT `marketid` FOREIGN KEY (`market_id`) REFERENCES `market` (`id`),
                          CONSTRAINT `nomen_code` FOREIGN KEY (`nom_id`) REFERENCES `nomenclature` (`code`)
);

