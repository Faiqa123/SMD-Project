package com.example.travelshare.Classes;

public class TripsData {

      private String Source_DEST;
      private String Name;
      private String date;
      private String time;
      private String Cdate;

    public TripsData()
    {
       //empty
    }
    public TripsData(String src_dst, String name, String dte, String tme,String cdate)
    {
        Source_DEST=src_dst;
        Name=name;
        date=dte;
        time=tme;
        Cdate=cdate;
    }

    public String getCdate()
    {
        return Cdate;
    }
    public void setCdate(String cdate)
    {
        Cdate=cdate;
    }
    public String getSource_DEST() {
        return Source_DEST;
    }

    public void setSource_DEST(String source) {
        Source_DEST= source;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
