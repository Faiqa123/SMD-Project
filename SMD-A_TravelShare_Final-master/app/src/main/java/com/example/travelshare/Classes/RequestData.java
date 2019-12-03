package com.example.travelshare.Classes;

public class RequestData {

    String ReqName;
    String ReqPath;
    String ReqPh;


    public RequestData()
    {
        //empty
    }
    public RequestData(String rn, String rq,String rph)
    {
        ReqName=rn;
        ReqPath=rq;
        ReqPh=rph;
    }

    public String getReqName() {
        return ReqName;
    }

    public void setReqName(String reqName) {
        ReqName = reqName;
    }

    public String getReqPath() {
        return ReqPath;
    }

    public void setReqPath(String reqPath) {
        ReqPath = reqPath;
    }

    public String getReqPh() {
        return ReqPh;
    }

    public void setReqPh(String reqPh) {
        ReqPh = reqPh;
    }
}
