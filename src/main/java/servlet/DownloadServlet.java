package servlet;

import Config.JSONUtil;
import Config.XlsUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@WebServlet(name = "DownloadServlet",urlPatterns = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String stime = request.getParameter("ts");
        String etime=request.getParameter("te");
        String kind=request.getParameter("kind");
        Timestamp S =Timestamp.valueOf(stime);
        Timestamp E =Timestamp.valueOf(etime);
        infoservice service1 = new infoservice();
        disasterservice service2=new disasterservice();
        List<disasterinfo> datas = service1.queryDataByTime(S,E);
        List<disastercode> datas2 =service2.queryDataByTime(S,E);
        String time=String.valueOf(new Date().getTime());
        if(kind.equals("JSON"))
        {
            JSONObject obj=new JSONObject();
            obj.put("DATAS",datas);
            obj.put("CODE",datas2);
            String str= obj.toString();
            JSONUtil.createJsonFile(str,"C://Users//12559//Desktop//demo//src//main//webapp//JSON//"+time+".JSON");
        }
        else
        {
            XlsUtil.export("C://Users//12559//Desktop//demo//src//main//webapp//excel//"+time+".xlsx",datas,datas2);
        }
        request.setAttribute("ts",S);
        request.setAttribute("te",E);
        request.setAttribute("datas",datas);
        request.getRequestDispatcher("jsp/Data.jsp").forward(request,response);
    }
}
