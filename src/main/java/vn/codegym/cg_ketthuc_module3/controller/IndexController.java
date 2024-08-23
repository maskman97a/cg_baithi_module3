package vn.codegym.cg_ketthuc_module3.controller;

import vn.codegym.cg_ketthuc_module3.service.HomeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/", ""})
public class IndexController extends HttpServlet {
    private HomeService homeService;

    public void init() {
        this.homeService = HomeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            homeService.doSearch(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
