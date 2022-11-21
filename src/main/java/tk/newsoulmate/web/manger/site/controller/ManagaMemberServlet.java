package tk.newsoulmate.web.manger.site.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ManagaMemberPage", value = "/manageMemberPage")
public class ManagaMemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("manageMember?page=1").forward(request,response);

        /*request.getRequestDispatcher("views/manager/managerMemberList.jsp").forward(request,response);*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
