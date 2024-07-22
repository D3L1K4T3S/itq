package org.example.orders.repository.mapper;

import org.example.orders.models.entity.OrderEntity;
import org.example.orders.models.entity.base.BaseOrderEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<BaseOrderEntity> {

    @Override
    public OrderEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new OrderEntity(
                resultSet.getLong("id"),
                resultSet.getString("number"),
                resultSet.getLong("sum"),
                resultSet.getLong("scale"),
                resultSet.getDate("createDate"),
                resultSet.getString("recipient"),
                resultSet.getString("address"),
                resultSet.getString("payment"),
                resultSet.getString("delivery")
        );
    }
}
