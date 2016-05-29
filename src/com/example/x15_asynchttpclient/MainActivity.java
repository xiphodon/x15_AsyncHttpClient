package com.example.x15_asynchttpclient;

import java.net.URLEncoder;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	/**
	 * get方式提交
	 * @param v
	 */
	public void get(View v) {

		EditText et_name = (EditText) findViewById(R.id.et_name);
		EditText et_pass = (EditText) findViewById(R.id.et_password);

		String name = et_name.getText().toString();
		String pass = et_pass.getText().toString();

		String url = "http://192.168.1.101:8080/user/loginservlet?name="
				+ URLEncoder.encode(name) + "&pass=" + pass;

		// 创建异步HttpClient对象
		AsyncHttpClient ahc = new AsyncHttpClient();

		// 发送get请求提交数据
		ahc.get(url, new MyResponseHandler());
	}

	
	/**
	 * post方式提交
	 * @param v
	 */
	public void post(View v){

		EditText et_name = (EditText) findViewById(R.id.et_name);
		EditText et_pass = (EditText) findViewById(R.id.et_password);

		String name = et_name.getText().toString();
		String pass = et_pass.getText().toString();

		String url = "http://192.168.1.101:8080/user/loginservlet";

		// 创建异步HttpClient对象
		AsyncHttpClient ahc = new AsyncHttpClient();
		
		//发送post请求提交数据
		//把要请求的对象封装至RequestParams对象中
		RequestParams params = new RequestParams();
		params.add("name", name);
		params.add("pass", pass);
		ahc.post(url, params, new MyResponseHandler());

	}
	
	
	
	class MyResponseHandler extends AsyncHttpResponseHandler {
		//请求服务器成功时，此方法调用
		@Override
		public void onSuccess(int statusCode, Header[] headers,
				byte[] responseBody) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, new String(responseBody), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onFailure(int statusCode, Header[] headers,
				byte[] responseBody, Throwable error) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "请求失败！！！", Toast.LENGTH_SHORT).show();
		}

	}

	
	
}
