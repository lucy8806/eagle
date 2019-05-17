package org.eagle.admin.sys.shiro.jwt;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName: JwtToken
 * @Description: TODO
 * @Author lucy
 * @Date 2019/5/15 11:27
 * @Version 1.0
 */
@Data
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = 1282057025599826155L;

    private String token;

    private String exipreAt;

    public  JwtToken(String token){
        this.token = token;
    }

    public  JwtToken(String token, String exipreAt){
        this.token = token;
        this.exipreAt = exipreAt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
