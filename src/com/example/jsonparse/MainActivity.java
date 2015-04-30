package com.example.jsonparse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {

	private String TAG = "MainAc";
	TextView tv_content;

	String jsonStr = "{ \"age\": 18, \"name\": \"jack\", \"address\": \"china\", \"phoneNum\": \"13120457560\"}";
	String jsonStr2 = "{ \"age\": 18, \"name\": \"jack\", \"address\": \"china\"}";
	String jsonMulti="{ \"type\": 1, \"users\": [  {\"age\": 18,\"name\": \"jack\",\"address\": \"china\",\"phoneNum\": \"13120457560\"  },  {\"age\": 18,\"name\": \"jack\",\"address\": \"china\",\"phoneNum\": \"13120457560\"  } ]}";
	StringBuilder builder = new StringBuilder();
	String jsonArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		try {
//			jsonArray = generateUserJson(10000);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		tv_content = (TextView) findViewById(R.id.tv_result);
//		builder.append(gsonParseArray(jsonArray) + "\n"
//				+ jsonParseArray(jsonArray));
//		builder.append(gsonToString(generateUsers(10)));
//		builder.append(gsonMulitiToString());
		builder.append(gsonParseMulti(jsonMulti));
		tv_content.setText(builder.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public String generateUserJson(int size) throws JSONException {
		JSONArray array = new JSONArray();
		for (int i = 0; i < size; i++) {
			JSONObject obj = new JSONObject();
			obj.put("name", "name" + i);
			obj.put("phoneNum", "131" + i);
			obj.put("address", "china");
			obj.put("age", i);
			array.put(obj);
		}
		return array.toString();
	}
	public List<UserEntity> generateUsers(int size){
		ArrayList<UserEntity> entitys = new ArrayList<UserEntity>();
		for (int i = 0; i < size; i++) {
			UserEntity entity=new UserEntity();
			entity.setAddress("china");
			entity.setAge(i);
			entity.setName("jack"+i);
			entity.setPhoneNum("13131"+i);
			entitys.add(entity);
		}
		return entitys;
	}

	public String gsonParse(String json) {
		long start = System.currentTimeMillis();
		Gson gson = new Gson();
		UserEntity user = gson.fromJson(json, UserEntity.class);

		return user.phoneNum + "\n time:"
				+ (System.currentTimeMillis() - start);

	}

	public String gsonParseArray(String json) {
		long start = System.currentTimeMillis();
		Gson gson = new Gson();
		List<UserEntity> people = gson.fromJson(json,
				new TypeToken<List<UserEntity>>() {
				}.getType());
		return people.get(0).name + "\n time:"
				+ (System.currentTimeMillis() - start);

	}
	public String gsonParseMulti(String json) {
		long start = System.currentTimeMillis();
		Gson gson = new Gson();
//		TasKEntity entity = gson.fromJson(json,
//				new TypeToken<TasKEntity>() {
//				}.getType());
		TasKEntity entity = gson.fromJson(json,TasKEntity.class);
		return entity.type+ "\n"+entity.users.get(0).address+"\n time:"
				+ (System.currentTimeMillis() - start);

	}
	public String gsonToString(List<UserEntity> users){
		return new Gson().toJson(users);
	}
	public String gsonMulitiToString(){
		TasKEntity entity=new TasKEntity();
		entity.type=1;
		entity.users=generateUsers(3);
		return new Gson().toJson(entity);
	}

	public String jsonParse(String json) {
		try {
			long start = System.currentTimeMillis();

			JSONObject obj = new JSONObject(json);
			UserEntity entity = new UserEntity();
			entity.setAddress(obj.getString("address"));
			entity.setAge(obj.getInt("age"));
			entity.setName(obj.getString("name"));
			entity.setPhoneNum(obj.getString("phoneNum"));
			return entity.phoneNum + "\n time:"
					+ (System.currentTimeMillis() - start);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	public String jsonParseArray(String json) {
		try {
			long start = System.currentTimeMillis();
			List<UserEntity> entityArray = new ArrayList<UserEntity>();
			JSONArray array = new JSONArray(json);
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				UserEntity entity = new UserEntity();
				entity.setAddress(obj.getString("address"));
				entity.setAge(obj.getInt("age"));
				entity.setName(obj.getString("name"));
				entity.setPhoneNum(obj.getString("phoneNum"));
				entityArray.add(entity);
			}

			return entityArray.get(1).phoneNum + "\n time:"
					+ (System.currentTimeMillis() - start);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
}
