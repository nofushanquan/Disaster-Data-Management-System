package databaseop;
import db.db;
import entity.disastercode;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
public class codeImpl {


    public boolean adddisaster(String Sourdata,String Infodata,String Geodata){
        String sql="insert into disastercode (Sourdata,Infodata,Geodata) values(?,?,?)";
        Object[] params = {Sourdata,Infodata,Geodata};
        return db.executeUpdate(sql,params);
    }
    public boolean deletedisaster(int id){
        String sql="delete from disastercode where dataid=?";
        Object[] params = {id};
        return db.executeUpdate(sql,params);
    }
    public disastercode querydisasterById(int id){
        PreparedStatement pstmt =null;
        disastercode p = null;
        ResultSet rs =null;
        try{
            String sql = "select * from disastercode where dataid=?";
            Object[] params= {id};
            rs = db.executeQuery(sql,params);

            if(rs.next()){
                int Did=rs.getInt("dataid");
                String Sourdata = rs.getString("Sourdata");
                String Infodata = rs.getString("Infodata");
                String Geodata =rs.getString("Geodata");
                p = new disastercode(Did,Sourdata,Infodata,Geodata);
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
    public List<disastercode> queryAlldata(){
        PreparedStatement pstmt =null;
        disastercode p = null;
        List<disastercode> datas =new ArrayList<disastercode>();
        ResultSet rs =null;
        try{
            String sql = "select * from disastercode";
            rs = db.executeQuery(sql,null);

            while(rs.next()){
                int Did=rs.getInt("dataid");
                String Sourdata = rs.getString("Sourdata");
                String Infodata = rs.getString("Infodata");
                String Geodata =rs.getString("Geodata");
                p = new disastercode(Did,Sourdata,Infodata,Geodata);
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
    public List<disastercode>querydataByTime(Timestamp ts,Timestamp te)
    {
        PreparedStatement pstmt =null;
        disastercode p = null;
        List<disastercode> datas =new ArrayList<disastercode>();
        ResultSet rs =null;
        try{
            String sql = "select * from disastercode where dataid in (select dataid from disasterinfo where created between ? and ?)";
            Object[] params= {ts,te};
            rs = db.executeQuery(sql,params);
            while(rs.next()){
                int Did=rs.getInt("dataid");
                String Sourdata = rs.getString("Sourdata");
                String Infodata = rs.getString("Infodata");
                String Geodata =rs.getString("Geodata");
                p = new disastercode(Did,Sourdata,Infodata,Geodata);
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
    public boolean updatedisaster(int id, String sour,String info,String geo){
        String sql="update disastercode set Sourdata =?,Infodata=?,Geodata=? where dataid=?";
        Object[] params = {sour,info,geo,id};
        return db.executeUpdate(sql,params);
    }
}
