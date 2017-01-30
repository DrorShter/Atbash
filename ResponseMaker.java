package com.prgguru.jersey;
 
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

//Path: http://localhost/<appln-folder-name>/register
@Path("/AtbashServerAPI")
public class ResponseMaker 
{
	private ServerDAL dal = new ServerDAL();
    @GET
    @Path("/getAllStages")  
    @Produces(MediaType.APPLICATION_JSON) 
    public JSONArray getAllStages() throws JSONException
    {
    	JSONArray allStages = new JSONArray();
    	allStages = dal.getAllStages();
        System.out.println("Inside AtbashServerAPI: getAllStages" + allStages);   
        return allStages;
    }
    @GET
    @Path("/getCount")  
    @Produces(MediaType.APPLICATION_JSON) 
    public String getCount()
    {  
    	String ret;
        ret = String.valueOf(dal.getCount());   
        System.out.println("Inside AtbashServerAPI: getCount" + ret);
        return ret;
    }
    //getStage is just for check, not for use.
    @GET 
    @Path("/getStage")  
    @Produces(MediaType.APPLICATION_JSON) 
    public JSONObject getStage() throws JSONException
    {  
    	JSONObject json = new JSONObject();
    	json.put("number", 1);
    	json.put("question", "2");
    	json.put("clue", "3");
    	json.put("answer", "4");
    	return json;
    }
    //example for parameters input : (@QueryParam("user") String user, @QueryParam("id") int id, @QueryParam("getLastNumber") String getLastNumber, @QueryParam("getAllStages") String getAllStages
}