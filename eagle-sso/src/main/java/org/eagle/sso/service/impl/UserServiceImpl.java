package org.eagle.sso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.eagle.sso.core.model.UserInfo;
import org.eagle.sso.core.result.ReturnT;
import org.eagle.sso.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private static List<UserInfo> mockUserList = new ArrayList<>();
	static {
		for (int i = 0; i < 5; i++) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserid(1000 + i);
			userInfo.setUsername("user" + (i > 0 ? String.valueOf(i) : ""));
			userInfo.setPassword("123456");
			mockUserList.add(userInfo);
		}
	}

	@Override
	public ReturnT<UserInfo> findUser(String username, String password) {

		if (username == null || username.trim().length() == 0) {
			return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "Please input username.");
		}
		if (password == null || password.trim().length() == 0) {
			return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "Please input password.");
		}

		// mock user
		for (UserInfo mockUser : mockUserList) {
			if (mockUser.getUsername().equals(username) && mockUser.getPassword().equals(password)) {
				return new ReturnT<UserInfo>(mockUser);
			}
		}

		return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "username or password is invalid.");
	}

}
