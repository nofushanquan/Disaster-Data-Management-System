package service;

import databaseop.codeImpl;
import databaseop.infoImpl;
import entity.disastercode;
import entity.disasterinfo;

import java.sql.Timestamp;
import java.util.List;

public class infoservice {

    infoImpl disasterDao = new infoImpl();

    public void adddata(String location,String longitude,String latitude,String category,String source,int grade,double magnitude,Timestamp created){
        disasterDao.adddisaster(location,longitude,latitude,category,source,grade,magnitude,created);

    }
    public void deletedata(int id){
        disasterDao.deletedisaster(id);
    }

    public List<disasterinfo> queryAllData(){

        return disasterDao.queryAlldata();

    }
    public List<disasterinfo> queryDataByTime(Timestamp Ts, Timestamp Te){

        return disasterDao.querydataByTime(Ts,Te);
    }
    public disasterinfo querydataByid(int id){
        return disasterDao.querydisasterById(id);
    }
    public void updatedata(int id, String location,String lon,String lat,double mag)
    {
        disasterDao.updatedisaster(id, location,lon,lat,mag);
    }
}
