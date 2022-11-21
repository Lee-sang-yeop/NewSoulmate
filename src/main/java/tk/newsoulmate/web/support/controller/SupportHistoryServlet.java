package tk.newsoulmate.web.support.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.SupportCompleteResponse;
import tk.newsoulmate.web.support.service.SupportService;

@WebServlet(name = "SupportHistoryServlet", value = "/supportHistoryPage")
public class SupportHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("supports?page=1").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
