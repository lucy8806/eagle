package org.eagle.admin.sys.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.eagle.core.utils.UsingAesUtil;

/**
 * @ClassName: CredentialsMatcher
 * @Description: TODO
 * @Author HX
 * @Date 2019/5/10 11:18
 * @Version 1.0
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken intoken = (UsernamePasswordToken) token;
        //获取用户输入的密码
        String inPassword = new String(intoken.getPassword());
        //获得数据库的密码
        String dbPassword = (String) info.getCredentials();
        try {
            dbPassword = UsingAesUtil.decrypt(dbPassword, intoken.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //验证密码是否一致
        return this.equals(dbPassword, inPassword);
    }
}
