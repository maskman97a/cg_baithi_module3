package vn.codegym.cg_ketthuc_module3.model;

import vn.codegym.cg_ketthuc_module3.database.DatabaseConnection;
import vn.codegym.cg_ketthuc_module3.dto.HomeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeModel {
    private static HomeModel inst = new HomeModel();
    private Connection connection;

    private HomeModel() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public static HomeModel getInstance() {
        return inst;
    }

    public List<HomeDto> findByCondition(String keyword) throws SQLException {
        List<HomeDto> homeDtos = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("select h.id as home_id, h.home_code as home_code, customer_name, phone_number, start_date, pm.name ,note " + "from home h " + "         inner join payment_method pm on h.payment_method_id = pm.id " + "where h.home_code like ? " + "   or customer_name like ? " + "   or phone_number like ? ");
        PreparedStatement ps = connection.prepareStatement(sb.toString());
        ps.setString(1, "%" + (keyword == null ? "" : keyword) + "%");
        ps.setString(2, "%" + (keyword == null ? "" : keyword) + "%");
        ps.setString(3, "%" + (keyword == null ? "" : keyword) + "%");
        ResultSet rs = ps.executeQuery();
        int index = 1;
        while (rs.next()) {
            HomeDto homeDto = new HomeDto();
            homeDto.setIndex(index++);
            homeDto.setHomeId(rs.getLong("home_id"));
            homeDto.setHomeCode(rs.getString("home_code"));
            homeDto.setCustomerName(rs.getString("customer_name"));
            homeDto.setPhoneNumber(rs.getString("phone_number"));
            homeDto.setStartDate(rs.getDate("start_date"));
            homeDto.setPaymentMethodName(rs.getString("name"));
            homeDto.setNote(rs.getString("note"));
            homeDtos.add(homeDto);
        }
        return homeDtos;
    }

    public int create(String homeCode, String customerName, String phoneNumber, Date startDate, Long paymentMethodId, String note) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into home (id, home_code, customer_name, phone_number," +
                " start_date, payment_method_id, note) ");
        sb.append("values (null,?, ?,?,?,?,?) ");
        PreparedStatement ps = connection.prepareStatement(sb.toString());
        ps.setString(1, homeCode);
        ps.setString(2, customerName);
        ps.setString(3, phoneNumber);
        ps.setDate(4, new java.sql.Date(startDate.getTime()));
        ps.setLong(5, paymentMethodId);
        ps.setString(6, note);
        return ps.executeUpdate();
    }
}
