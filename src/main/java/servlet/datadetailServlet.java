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

@WebServlet(name = "datadetailServlet",urlPatterns = "/datadetailServlet")
public class datadetailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        int id =Integer.parseInt(request.getParameter("id"));
        infoservice service1 = new infoservice();
        disasterservice service2=new disasterservice();
        disasterinfo info1= service1.querydataByid(id);
        disastercode info2=service2.querydataByid(id);
        request.setAttribute("info1",info1);
        request.setAttribute("info2",info2);
        request.getRequestDispatcher("jsp/DataDetail.jsp").forward(request,response);


    }

}
