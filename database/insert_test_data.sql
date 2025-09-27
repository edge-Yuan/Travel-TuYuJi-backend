-- 为测试用户ID=1插入数据

-- 插入测试用户
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`) VALUES
(1, 'testuser', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '测试用户', '13900139000', 'test@example.com', 1, 1)
ON DUPLICATE KEY UPDATE username = 'testuser';

-- 插入测试订单数据
INSERT INTO `tour_order` (`user_id`, `product_id`, `guide_id`, `order_amount`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `pay_time`, `cancel_time`) VALUES
(1, 40001, 20001, 899.00, 1, 1, 1, '2024-12-15', 2, '测试订单1', '2024-11-10 09:30:00', NULL),
(1, 40003, 20002, 3699.00, 2, 1, 2, '2024-11-20', 1, '测试订单2', '2024-11-15 14:20:00', NULL),
(1, 40002, NULL, 190.00, 1, 1, 3, '2024-11-25', 1, '测试订单3', '2024-11-20 10:15:00', '2024-11-22 09:20:00');

-- 插入订单状态变更记录
INSERT INTO `order_status_log` (`order_id`, `from_status`, `to_status`, `reason`, `operator_id`, `operator_type`, `remark`) VALUES
(60001, NULL, 0, '订单创建', 1, 1, '用户创建订单'),
(60001, 0, 1, '支付完成', 1, 1, '用户完成支付'),
(60002, NULL, 0, '订单创建', 1, 1, '用户创建订单'),
(60002, 0, 1, '支付完成', 1, 1, '用户完成支付'),
(60002, 1, 2, '行程完成', 20002, 2, '导游确认行程完成'),
(60003, NULL, 0, '订单创建', 1, 1, '用户创建订单'),
(60003, 0, 1, '支付完成', 1, 1, '用户完成支付'),
(60003, 1, 3, '用户取消', 1, 1, '用户申请取消订单');
