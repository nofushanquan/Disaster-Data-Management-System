package servlet;

import entity.disastercode;
import entity.disasterinfo;
import service.disasterservice;
import service.infoservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "changedataServlet",urlPatterns = "/changedataServlet")
public class changedataServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int id =Integer.parseInt(request.getParameter("id"));
        infoservice service1 = new infoservice();
        disasterservice service2=new disasterservice();
        disasterinfo d1=service1.querydataByid(id);
        disastercode d2=service2.querydataByid(id);
        request.setAttribute("id",id);
        request.setAttribute("d1",d1);
        request.setAttribute("d2",d2);
        request.getRequestDispatcher("jsp/update.jsp").forward(request,response);
    }
}
