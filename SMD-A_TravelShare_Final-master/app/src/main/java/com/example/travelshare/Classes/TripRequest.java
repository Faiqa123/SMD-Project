package com.example.travelshare.Classes;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripRequest implements Serializable
{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("trip_id")
    @Expose
    private String tripId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    private final static long serialVersionUID = 1546171964540457910L;

    /**
     * No args constructor for use in serialization
     *
     */
    public TripRequest() {
    }

    /**
     *
     * @param name
     * @param tripId
     * @param id
     * @param status
     */
    public TripRequest(String id, String tripId, String name, String status) {
        super();
        this.id = id;
        this.tripId = tripId;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
