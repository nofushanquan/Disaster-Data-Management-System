package servlet;

import service.disasterservice;
import service.infoservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "updateServlet",urlPatterns = "/updateServlet")
public class updateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id= Integer.parseInt(request.getParameter("id"));
        String location=request.getParameter("location");
        String lon = request.getParameter("lon");
        String lat =  request.getParameter("lat");
        double mag = Double.parseDouble(request.getParameter("mag"));
        String sour = request.getParameter("sour");
        String info = request.getParameter("info");

        String geo = request.getParameter("geo");

        disasterservice service1 = new disasterservice();
        infoservice service2 =new infoservice();
        service1.updatedata(id,sour,info,geo);
        service2.updatedata(id,location,lon,lat,mag);
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.sendRedirect("datasearchServlet");


    }
}
