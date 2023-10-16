package servlet;

import entity.disasterinfo;
import service.infoservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@WebServlet(name = "dataselectServlet",urlPatterns = "/dataselectServlet")
public class dataselectServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String stime = request.getParameter("sdate");
        String etime=request.getParameter("edate");
        stime=stime.replace('T',' ');
        etime=etime.replace('T',' ');
        stime+=":00";
        etime+=":00";
        System.out.println(stime);
        Timestamp S =Timestamp.valueOf(stime);
        Timestamp E =Timestamp.valueOf(etime);
        infoservice service = new infoservice();
        List<disasterinfo> datas = service.queryDataByTime(S,E);
        request.setAttribute("ts",S);
        request.setAttribute("te",E);
        request.setAttribute("datas",datas);
        request.getRequestDispatcher("jsp/Data.jsp").forward(request,response);
    }
}
