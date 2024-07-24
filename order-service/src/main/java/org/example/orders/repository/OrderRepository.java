package org.example.orders.repository;

import org.example.orders.models.entity.OrderEntity;

import java.util.Date;
import java.util.List;

public interface OrderRepository {
    void save(OrderEntity order);
    OrderEntity findById(Integer id);
    List<OrderEntity> findByDateAndSum(Date createDate, Long sum, Long scale);
    List<OrderEntity> findWithoutOrderAndBetweenDate(String number, Date dateBefore, Date dateAfter);
}
