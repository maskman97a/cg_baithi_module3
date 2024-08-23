package vn.codegym.cg_ketthuc_module3.service;

import vn.codegym.cg_ketthuc_module3.dto.HomeDto;
import vn.codegym.cg_ketthuc_module3.entity.PaymentMethodEntity;
import vn.codegym.cg_ketthuc_module3.model.HomeModel;
import vn.codegym.cg_ketthuc_module3.model.PaymentMethodModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HomeService {
    private static final HomeService inst = new HomeService();
    private HomeModel homeModel;
    private PaymentMethodModel paymentMethodModel;

    private HomeService() {
        this.homeModel = HomeModel.getInstance();
        this.paymentMethodModel = PaymentMethodModel.getInstance();
    }

    public static HomeService getInstance() {
        return inst;
    }

    public void doSearch(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<HomeDto> homeDtoList = homeModel.findByCondition(keyword);
        req.setAttribute("dataList", homeDtoList);
        getAllPaymentMethod(req, resp);
        renderPage(req, resp);
    }

    public void create(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException, ParseException {
        String homeCode = req.getParameter("homeCode");
        String customerName = req.getParameter("customerName");
        String phoneNumber = req.getParameter("phoneNumber");
        String startDateStr = req.getParameter("startDate");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String paymentMethodIdStr = req.getParameter("paymentMethodId");
        Long paymentMethodId = Long.parseLong(paymentMethodIdStr);
        String note = req.getParameter("note");
        homeModel.create(homeCode, customerName, phoneNumber, simpleDateFormat.parse(startDateStr), paymentMethodId, note);
        renderPage(req, resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException, ParseException {
        for (int i = 1; i <= 10; i++) {
            String select = req.getParameter("select" + i);
            if ("true".equals(select)) {

            }
        }
        
        renderPage(req, resp);
    }

    public void renderPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<PaymentMethodEntity> getAllPaymentMethod(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        List<PaymentMethodEntity> paymentMethodEntities = paymentMethodModel.findAll();
        req.setAttribute("paymentMethodList", paymentMethodEntities);
        return paymentMethodEntities;
    }

}
