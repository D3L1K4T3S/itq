package org.example.orders.repository.mapper;

import org.example.orders.models.entity.OrderEntity;
import org.example.orders.models.enums.TypeDelivery;
import org.example.orders.models.enums.TypePayment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<OrderEntity> {

    @Override
    public OrderEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrderEntity entity = new OrderEntity();
        entity.setId(resultSet.getInt("id"));
        entity.setNumber(resultSet.getString("number"));
        entity.setSum(resultSet.getLong("sum"));
        entity.setScale(resultSet.getLong("scale"));
        entity.setOrderDate(resultSet.getDate("order_date"));
        entity.setRecipient(resultSet.getString("recipient"));
        entity.setAddress(resultSet.getString("address"));
        entity.setPayment(TypePayment.valueOf(resultSet.getString("payment")));
        entity.setDelivery(TypeDelivery.valueOf(resultSet.getString("delivery")));
        return entity;
    }
}
