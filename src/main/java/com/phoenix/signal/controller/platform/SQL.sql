CREATE TABLE t_original_product (
    product_name VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL PRIMARY KEY,
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
    )engine=innodb auto_increment=1;

--CREATE TABLE t_device_status_plan_management(
--    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--    sub_area VARCHAR(255) NOT NULL,
--    device_id BIGINT NOT NULL,
--    intersection_id BIGINT NOT NULL,
--    intersection_name VARCHAR(255) NOT NULL
--)engine=innodb auto_increment=1;

CREATE TABLE t_intersection_plan(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    parent_plan_device_id BIGINT NOT NULL,
    plan_number INT NOT NULL,
    phase_difference INT NULL,
    coordinated_phase_number INT NULL,
    period INT NULL,
    note VARCHAR(255) NULL
    )engine=innodb auto_increment=1;

CREATE TABLE t_phase_control(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    parent_plan_id BIGINT NOT NULL,
    phase_number INT NULL,
    phase_duration INT NULL,
    green_ratio VARCHAR(255) NULL,
    phase_type VARCHAR(255) NULL,
    traffic_phase VARCHAR(255) NULL,
    min_green_duration INT NULL,
    max_green_duration INT NULL,
    yellow_duration INT NULL,
    all_red_duration INT NULL,
    phase_image_data BLOB NULL
    )engine=innodb auto_increment=1;



}