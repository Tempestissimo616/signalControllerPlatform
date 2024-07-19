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

--CREATE TABLE t_phase_control(
--    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--    parent_plan_id BIGINT NOT NULL,
--    phase_number INT NULL,
--    phase_duration INT NULL,
--    green_ratio VARCHAR(255) NULL,
--    phase_type VARCHAR(255) NULL,
--    traffic_phase VARCHAR(255) NULL,
--    min_green_duration INT NULL,
--    max_green_duration INT NULL,
--    yellow_duration INT NULL,
--    all_red_duration INT NULL,
--    phase_image_data BLOB NULL
--    )engine=innodb auto_increment=1;

CREATE TABLE t_phase_control(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    device_id BIGINT NOT NULL,
    plan_number INT NOT NULL,
    phase_number INT NOT NULL,
    phase_duration INT NOT NULL,
    green_ratio VARCHAR(255) NOT NULL,
    phase_type VARCHAR(255) NOT NULL,
    traffic_phase VARCHAR(255) NOT NULL,
    phase_image_data BLOB NULL
    )engine=innodb auto_increment=1;

CREATE TABLE t_phase_parameter(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    device_id BIGINT NOT NULL,
    plan_number INT NOT NULL,
    phase_number INT NOT NULL,
    phase_para_number INT NOT NULL,
    phase_name VARCHAR(255) NOT NULL,
    min_green_duration INT NOT NULL,
    max_green_duration INT NOT NULL,
    yellow_duration INT NOT NULL,
    all_red_duration INT NOT NULL
    )engine=innodb auto_increment=1;

CREATE TABLE t_intersection (
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    intersection_name VARCHAR(255) NOT NULL COMMENT '路口名称',
    intersection_type VARCHAR(255) NOT NULL COMMENT '路口类型',
    longitude DECIMAL(10, 8) NOT NULL COMMENT '经度',
    latitude DECIMAL(10, 8) NOT NULL COMMENT '纬度',
    administrative_region VARCHAR(255) NOT NULL COMMENT '行政区域',
    intersection_level INT NOT NULL COMMENT '路口等级',
    created_by BIGINT(20) NOT NULL COMMENT '创建人',
    updated_by BIGINT(20) DEFAULT NULL COMMENT '更新人',
    created_time DATETIME NOT NULL COMMENT '创建时间',
    updated_time DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='路口信息表';

INSERT INTO t_intersection (
    id,
    intersection_name,
    intersection_type,
    longitude,
    latitude,
    administrative_region,
    intersection_level,
    created_by,
    created_time
) VALUES (
    1234567890123456789, 'First Street & Main Avenue', 'Urban', 34.052235, -118.243683, 'Los Angeles, CA', 5, 1001, '2024-07-19 12:00:00'
), (
    9876543210987654321, 'Second Street & Market Road', 'Suburban', 37.7749, -122.4194, 'San Francisco, CA', 4, 1002, '2024-07-19 12:01:00'
);

}