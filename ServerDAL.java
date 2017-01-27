package com.prgguru.jersey;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ServerDAL 
{
	public void userHandler(String user, int id)
	{
		//enter info about user to database. update id in "lastLevelofUser" field if id is the biggest until now
	}
	public JSONArray getAllStages() throws JSONException
	{
		JSONObject jo;
		JSONArray ja = new JSONArray();
		int count = getCount(), i;
		for (i=0;i<count;i++)
		{
			jo = new JSONObject();
			jo.put("number", getStage(i).getNumber());
			jo.put("question", getStage(i).getQuestion());
			jo.put("clue", getStage(i).getClue());
			jo.put("answer", getStage(i).getAnswer());	
			ja.put(jo);
		}	
		return ja;
	}
	public Stage getStage (int id)
	{
		//need to be from the database
		Stage s = null;
		switch (id)
		{
		case 0:
			s = new Stage(id, "121", "aba", "аба");
			break;
		case 1:
			s = new Stage(id, "131", "aga", "ава");
			break;
		case 2:
			s = new Stage(id, "141", "ada", "ага");
			break;
		}
		return s;
	}
	public int getCount()
	{
		int ret = 0;
		//ret = something from database
		ret = 3;
		return ret;
	}
}
