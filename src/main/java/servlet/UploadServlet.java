package servlet;

import Config.Code;
import Config.JSONUtil;
import Config.XlsUtil;
import Config.XmlUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.disastercode;
import entity.disasterinfo;

import org.python.antlr.ast.Str;
import service.disasterservice;
import service.infoservice;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "UploadServlet",urlPatterns = "/UploadServlet")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String src = request.getParameter("inputfile");
        String kind=request.getParameter("kind");
        String []check=src.split("\\.");
        String suffix=check[check.length-1];
        String mention="上传成功";
        if(kind.equals("JSON"))
        {
            if(!suffix.equals("JSON"))
            {
                mention="需上传JSON文件";
            }
            else {
                JSONObject obj = JSONUtil.file2Json("C://Users//12559//Desktop//demo//src//main//webapp//JSON//" + src);
                JSONArray arr = obj.getJSONArray("DATAS");
                for (int i = 0; i < arr.size(); i++) {
                    JSONObject subObj = arr.getJSONObject(i);
                    save(subObj);
                }
            }
        }
        else
        {
            if(!suffix.equals("xlsx"))
            {
                mention="需上传xlsx文件";
            }
            else {
                String filename = "C://Users//12559//Desktop//demo//src//main//webapp//excel//" + src;
                try {
                    List<disasterinfo> list = XlsUtil.readExcel(filename);
                    saveinfo(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        request.setAttribute("error", mention);
        request.getRequestDispatcher("jsp/Upload.jsp").forward(request,response);
       /* if (!result1) {
            response.sendRedirect("jsp/picturechoose.jsp?lesion="+leison+"&id="+Did);
        } else {
            response.sendRedirect("jsp/picturechoose.jsp?lesion="+leison+"&id="+Did);
        }*/

    }
    protected String[] change(String location,String category,String source,int grade,Timestamp created)
    {
        String []ans=new String[3];
        String []init={"00","00","00","000","000"};
        String []loc=location.split("&");
        for (int i=0;i<loc.length;i++) {
            init[i]= Code.myMap.get(loc[i]);
        }
        String L="";
        for (String s : init) {
            L += s;
        }
        String cate=Code.myMap.get(category);
        String order="000";
        String sourdata=Code.myMap.get(source);
        String timestamp=created.toString();
        String response = timestamp.substring(0,timestamp.length()-2).replaceAll("[[\\s-:punct:]]","");
        ans[0]=sourdata;
        ans[1]=L+cate+order+String.valueOf(grade);
        ans[2]=L+response;
        return ans;
    }
    protected void saveinfo(List<disasterinfo> list)
    {
        for (disasterinfo p:list)
        {
            String location=p.getLocation();
            String category=p.getCategory();
            int depth=p.getGrade();
            String source=p.getSource();
            String lon=p.getLongitude();
            String lat=p.getLatitude();
            double mag=p.getMagnitude();
            Timestamp stamp=p.getCreated();
            String[] res=change(location,category,source,depth,stamp);
            disasterservice disasterservice=new disasterservice();
            infoservice infoservice=new infoservice();
            disasterservice.adddata(res[0],res[1],res[2]);
            infoservice.adddata(location,lon,lat,category,source,depth,mag,stamp);
        }
    }
    protected void save(JSONObject obj)
    {

        String location=obj.getString("Location");
        String category=obj.getString("kind");
        int depth=obj.getInteger("grade");
        String source=obj.getString("Source");
        String lon=obj.getString("longitude");
        String lat=obj.getString("latitude");
        double mag=obj.getDouble("magnitude");
        long time=obj.getLong("Date");
        Timestamp stamp=new Timestamp(time);
        String[] res=change(location,category,source,depth,stamp);
        disasterservice disasterservice=new disasterservice();
        infoservice infoservice=new infoservice();
        disasterservice.adddata(res[0],res[1],res[2]);
        infoservice.adddata(location,lon,lat,category,source,depth,mag,stamp);
    }
}
