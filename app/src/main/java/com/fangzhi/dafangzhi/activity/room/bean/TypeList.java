package com.fangzhi.dafangzhi.activity.room.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smacr on 2017/3/31.
 */

public class TypeList {

    private List<StairTypeList> stairTypeList=new ArrayList<>();

    private String fixture_name;

    private int fixture_type;

    public void setStairTypeList(List<StairTypeList> stairTypeList){
        this.stairTypeList = stairTypeList;
    }
    public List<StairTypeList> getStairTypeList(){
        return this.stairTypeList;
    }
    public void setFixture_name(String fixture_name){
        this.fixture_name = fixture_name;
    }
    public String getFixture_name(){
        return this.fixture_name;
    }
    public void setFixture_type(int fixture_type){
        this.fixture_type = fixture_type;
    }
    public int getFixture_type(){
        return this.fixture_type;
    }
}
