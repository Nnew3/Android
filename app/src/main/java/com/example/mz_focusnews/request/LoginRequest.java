package com.example.mz_focusnews.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 로그인 요청 처리
 */
public class LoginRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://43.201.173.245/login.php"; // 임시
    private Map<String, String> map;

    public LoginRequest(String UserID, String UserPw, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_id", UserID);
        map.put("user_pw", UserPw);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
