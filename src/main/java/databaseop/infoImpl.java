package databaseop;

import db.db;
import entity.disasterinfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class infoImpl {
    public boolean adddisaster(String location,String longitude,String latitude,String category,String source,int grade,double magnitude,Timestamp created){
        String sql="insert into disasterinfo (location,longitude,latitude,category,source,grade,magnitude,created) values(?,?,?,?,?,?,?,?)";
        Object[] params = {location,longitude,latitude,category,source,grade,magnitude,created};
        return db.executeUpdate(sql,params);
    }
    public boolean updatedisaster(int id, String location,String lon,String lat,double mag){
        String sql="update disasterinfo set location =?,longitude=?,latitude=? ,magnitude=? where dataid=?";
        Object[] params = {location,lon,lat,mag,id};
        return db.executeUpdate(sql,params);
    }
    public boolean deletedisaster(int id){
        String sql="delete from disasterinfo where dataid=?";
        Object[] params = {id};
        return db.executeUpdate(sql,params);
    }
    public disasterinfo querydisasterById(int id){
        PreparedStatement pstmt =null;
        disasterinfo p = null;
        ResultSet rs =null;
        try{
            String sql = "select * from disasterinfo where dataid=?";
            Object[] params= {id};
            rs = db.executeQuery(sql,params);

            if(rs.next()){
                int Did=rs.getInt("dataid");
                String location = rs.getString("location");
                String longitude = rs.getString("longitude");
                String latitude =rs.getString("latitude");
                String category=rs.getString("category");
                String source=rs.getString("source");
                int grade=rs.getInt("grade");
                double magnitude=rs.getDouble("magnitude");
                Timestamp created=rs.getTimestamp("created");
                p = new disasterinfo(Did,location,longitude,latitude,category,source,grade,magnitude,created);
            }
            return p;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally{
            db.closeAll(rs,pstmt,db.connection);
        }
    }
    public List<disasterinfo> queryAlldata(){
        PreparedStatement pstmt =null;
        disasterinfo p = null;
        List<disasterinfo> datas =new ArrayList<disasterinfo>();
        ResultSet rs =null;
        try{
            String sql = "select * from disasterinfo";
            rs = db.executeQuery(sql,null);

            while(rs.next()){
                int Did=rs.getInt("dataid");
                String location = rs.getString("location");
                String longitude = rs.getString("longitude");
                String latitude =rs.getString("latitude");
                String category=rs.getString("category");
                String source=rs.getString("source");
                int grade=rs.getInt("grade");
                double magnitude=rs.getDouble("magnitude");
                Timestamp created=rs.getTimestamp("created");
                p = new disasterinfo(Did,location,longitude,latitude,category,source,grade,magnitude,created);
                datas.add(p);
            }
            return datas;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally{
            db.closeAll(rs,pstmt,db.connection);
        }
    }
    public List<disasterinfo> querydataByTime(Timestamp Ts, Timestamp Te){
        PreparedStatement pstmt =null;
        disasterinfo p = null;
        List<disasterinfo> datas =new ArrayList<disasterinfo>();
        ResultSet rs =null;
        try{
            String sql = "select * from disasterinfo where created between ? and ?";
            Object[] params= {Ts,Te};
            rs = db.executeQuery(sql,params);

            while(rs.next()){
                int Did=rs.getInt("dataid");
                String location = rs.getString("location");
                String longitude = rs.getString("longitude");
                String latitude =rs.getString("latitude");
                String category=rs.getString("category");
                String source=rs.getString("source");
                int grade=rs.getInt("grade");
                double magnitude=rs.getDouble("magnitude");
                Timestamp created=rs.getTimestamp("created");
                p = new disasterinfo(Did,location,longitude,latitude,category,source,grade,magnitude,created);
                datas.add(p);
            }
            return datas;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally{
            db.closeAll(rs,pstmt,db.connection);
        }
    }
}
