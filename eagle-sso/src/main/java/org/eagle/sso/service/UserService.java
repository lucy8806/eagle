package org.eagle.sso.service;

import org.eagle.sso.core.model.UserInfo;
import org.eagle.sso.core.result.ReturnT;

public interface UserService {

	public ReturnT<UserInfo> findUser(String username, String password);

}
