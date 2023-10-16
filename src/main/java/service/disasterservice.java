package service;

import databaseop.codeImpl;
import entity.disastercode;
import entity.disasterinfo;

import java.sql.Timestamp;
import java.util.List;

public class disasterservice {

    codeImpl disasterDao = new codeImpl();

    public void adddata(String Sourdata,String Infodata,String Geodata){
        disasterDao.adddisaster(Sourdata,Infodata,Geodata);

    }
    public void deletedata(int id){
        disasterDao.deletedisaster(id);
    }

    public List<disastercode> queryAllData(){

        return disasterDao.queryAlldata();

    }
    public disastercode querydataByid(int id){
        return disasterDao.querydisasterById(id);
    }
    public List<disastercode> queryDataByTime(Timestamp Ts, Timestamp Te){

        return disasterDao.querydataByTime(Ts,Te);
    }
    public void updatedata(int id, String sour,String info,String geo)
    {
        disasterDao.updatedisaster(id, sour,info, geo);
    }
}
