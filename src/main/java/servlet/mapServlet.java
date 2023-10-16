package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.disasterinfo;
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

@WebServlet(name = "mapServlet",urlPatterns = "/mapServlet")
public class mapServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        infoservice service = new infoservice();
        List<disasterinfo> datas = service.queryAllData();
        String str= JSON.toJSONString(datas);
        System.out.println(str);
        request.setAttribute("str",str);
        request.getRequestDispatcher("jsp/Search.jsp").forward(request,response);
    }
}
