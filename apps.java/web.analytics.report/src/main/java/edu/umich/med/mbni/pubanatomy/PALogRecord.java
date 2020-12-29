package edu.umich.med.mbni.pubanatomy;

import org.springframework.data.annotation.Id;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/12/2014
 * Time: 3:03 PM
 */
public class PALogRecord{

    @Id
    private long time;


    private String queryStr;

    private String ipAddress;

    private String hostname;

    private String sessionId;

    private String location;

    private String operation;

    public long getTime(){
        return time;
    }

    public void setTime( long time ){
        this.time = time;
    }

    public String getQueryStr(){
        return queryStr;
    }

    public void setQueryStr( String queryStr ){
        this.queryStr = queryStr;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public void setIpAddress( String ipAddress ){
        this.ipAddress = ipAddress;
    }

    public String getHostname(){
        return hostname;
    }

    public void setHostname( String hostname ){
        this.hostname = hostname;
    }

    public String getSessionId(){
        return sessionId;
    }

    public void setSessionId( String sessionId ){
        this.sessionId = sessionId;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation( String location ){
        this.location = location;
    }

    public String getOperation(){
        return operation;
    }

    public void setOperation( String operation ){
        this.operation = operation;
    }
}
