package com.anf.core.services;

import org.json.JSONException;
import org.json.JSONObject;

import com.anf.core.dataobjects.UserVO;

public interface ContentService {

	JSONObject validateNewUserDetails(UserVO userVO) throws JSONException;

	boolean commitUserDetails(UserVO userVO);
}
