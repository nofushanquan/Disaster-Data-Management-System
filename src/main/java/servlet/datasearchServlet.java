package servlet;

import entity.disastercode;
import entity.disasterinfo;
import jnr.posix.Times;
import org.apache.poi.ss.formula.functions.T;
import service.disasterservice;
import service.infoservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@WebServlet(name = "datasearchServlet",urlPatterns = "/datasearchServlet")

public class datasearchServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //int id = Integer.parseInt(request.getAttribute("id").toString());
        infoservice service = new infoservice();
        List<disasterinfo> datas = service.queryAllData();
        Timestamp now=new Timestamp(new Date().getTime());
        String present=now.toString();
        String past=(Integer.parseInt(now.toString().substring(0, 4)) - 50) +now.toString().substring(4);
        String info="查询范围为 "+past+" 至 "+present;
        String s="1970-01-01 00:00:00";
        String e="2021-12-12 00:00:00";
        Timestamp ts=Timestamp.valueOf(s);
        Timestamp te=Timestamp.valueOf(e);
        request.setAttribute("datas",datas);
        request.setAttribute("ts",ts);
        request.setAttribute("te",te);
        request.setAttribute("error", info);
        request.getRequestDispatcher("jsp/Data.jsp").forward(request,response);
    }
}
