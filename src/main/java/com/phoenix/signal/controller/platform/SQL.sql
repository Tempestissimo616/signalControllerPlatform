CREATE TABLE t_original_product (
    product_name VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL UNIQUE,
    product_brand VARCHAR(255) NOT NULL,
    producer VARCHAR(255) NOT NULL,
    product_type VARCHAR(255) NOT NULL,
    product_num INT NOT NULL
    )engine=innodb;

CREATE TABLE t_device (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    device_name VARCHAR(255) NOT NULL,
    device_id BIGINT NOT NULL UNIQUE,
    product_type VARCHAR(255) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    device_producer VARCHAR(255) NOT NULL,
    product_model VARCHAR(255) NOT NULL,
    status TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0:离线, 1:在线',
    intersection_id BIGINT NOT NULL
    )engine=innodb;
