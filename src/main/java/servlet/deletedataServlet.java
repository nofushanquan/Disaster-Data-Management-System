package servlet;

import entity.disasterinfo;
import service.disasterservice;
import service.infoservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "deletedataServlet",urlPatterns = "/deletedataServlet")
public class deletedataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int id =Integer.parseInt(request.getParameter("id"));
        infoservice service1 = new infoservice();
        disasterservice service2=new disasterservice();
        service1.deletedata(id);
        service2.deletedata(id);
        String stime = request.getParameter("ts");
        String etime=request.getParameter("te");
        Timestamp S =Timestamp.valueOf(stime);
        Timestamp E =Timestamp.valueOf(etime);
        List<disasterinfo> datas = service1.queryDataByTime(S,E);
        request.setAttribute("ts",S);
        request.setAttribute("te",E);
        request.setAttribute("datas",datas);
        request.getRequestDispatcher("jsp/Data.jsp").forward(request,response);
    }
}
