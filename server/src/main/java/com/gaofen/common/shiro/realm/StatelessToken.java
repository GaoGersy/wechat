package com.gaofen.common.shiro.realm;

import org.apache.shiro.authc.UsernamePasswordToken;

public class StatelessToken extends UsernamePasswordToken implements java.io.Serializable {

    private static final long serialVersionUID = -6451794657814516274L;

    private String pwd;
    private String token;

    public StatelessToken(String userName, String pwd,String token) {
        super(userName, pwd);
        this.pwd = pwd;
        this.token = token;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pswd) {
        this.pwd = pswd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
