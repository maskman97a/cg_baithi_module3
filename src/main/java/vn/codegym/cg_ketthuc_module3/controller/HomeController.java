package vn.codegym.cg_ketthuc_module3.controller;

import vn.codegym.cg_ketthuc_module3.service.HomeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(urlPatterns = {"/home/*"})
public class HomeController extends HttpServlet {
    private HomeService homeService;

    @Override
    public void init() {
        this.homeService = HomeService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getPathInfo() == null || req.getPathInfo().isEmpty()) {
            try {
                this.homeService.doSearch(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        switch (req.getPathInfo()) {
            case "/search":
                try {
                    homeService.doSearch(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null) {
            try {
                homeService.doSearch(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        switch (req.getPathInfo()) {
            case "/create":
                try {
                    homeService.create(req, resp);
                    return;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            case "/delete":
                try {
                    homeService.delete(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
