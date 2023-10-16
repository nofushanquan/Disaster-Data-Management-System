package db;

import java.sql.*;


public class db {
    private static final String url = "jdbc:mysql://localhost:3306/disaster?serverTimezone=GMT%2B8";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "whoareyou120621h";
    public static PreparedStatement pstmt = null;
    public static Connection connection = null;
    public static ResultSet rs = null;

    public static Connection getConnection() throws ClassNotFoundException,SQLException{
        Class.forName(driver);
        return DriverManager.getConnection(url,user,password);
    }

    public static PreparedStatement createPreparedStatement(String sql,Object[] params) throws SQLException, ClassNotFoundException {
        pstmt =getConnection().prepareStatement(sql);
        if(params!=null){
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        return pstmt;
    }

    public static boolean executeUpdate(String sql,Object[] params){

        try{
            pstmt = createPreparedStatement(sql,params);
            int count = pstmt.executeUpdate();
            if(count>0) return true;
            else return false;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            closeAll(null,pstmt,connection);
        }
    }

    public static ResultSet executeQuery(String sql, Object[] params){
        try{
            pstmt = createPreparedStatement(sql,params);
            rs = pstmt.executeQuery();
            return rs;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static void closeAll(ResultSet rs,Statement stmt,Connection connection){
        try{
            if(rs!=null) rs.close();
            if(pstmt!=null) pstmt.close();
            if(connection!=null) connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

