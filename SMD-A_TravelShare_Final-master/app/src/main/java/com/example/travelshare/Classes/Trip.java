package com.example.travelshare.Classes;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("source_loc_id")
    @Expose
    private String sourceLocId;
    @SerializedName("destination_loc_id")
    @Expose
    private String destinationLocId;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("user_id")
    @Expose
    private String userId;
    private final static long serialVersionUID = -2840365693866861966L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Trip() {
    }

    /**
     *
     * @param dateTime
     * @param sourceLocId
     * @param destinationLocId
     * @param id
     * @param userId
     */
    public Trip(String id, String sourceLocId, String destinationLocId, String dateTime, String userId) {
        super();
        this.id = id;
        this.sourceLocId = sourceLocId;
        this.destinationLocId = destinationLocId;
        this.dateTime = dateTime;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceLocId() {
        return sourceLocId;
    }

    public void setSourceLocId(String sourceLocId) {
        this.sourceLocId = sourceLocId;
    }

    public String getDestinationLocId() {
        return destinationLocId;
    }

    public void setDestinationLocId(String destinationLocId) {
        this.destinationLocId = destinationLocId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}