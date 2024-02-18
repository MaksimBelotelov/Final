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


