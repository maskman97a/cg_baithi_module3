package vn.codegym.cg_ketthuc_module3.model;

import vn.codegym.cg_ketthuc_module3.database.DatabaseConnection;
import vn.codegym.cg_ketthuc_module3.entity.PaymentMethodEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodModel {
    private static PaymentMethodModel inst = new PaymentMethodModel();
    private Connection connection;

    private PaymentMethodModel() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public static PaymentMethodModel getInstance() {
        return inst;
    }

    public List<PaymentMethodEntity> findAll() throws SQLException {
        List<PaymentMethodEntity> paymentMethodEntities = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("select id, name from payment_method  ");
        PreparedStatement ps = connection.prepareStatement(sb.toString());
        ResultSet rs = ps.executeQuery();
        int index = 1;
        while (rs.next()) {
            PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
            paymentMethodEntity.setIndex(index++);
            paymentMethodEntity.setId(rs.getLong("id"));
            paymentMethodEntity.setName(rs.getString("name"));
            paymentMethodEntities.add(paymentMethodEntity);
        }
        return paymentMethodEntities;
    }
}
