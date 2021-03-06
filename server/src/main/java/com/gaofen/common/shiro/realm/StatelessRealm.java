package com.gaofen.common.shiro.realm;


import com.gaofen.common.utils.GsonQuick;
import com.gaofen.model.User;
import com.gaofen.service.IUserinfoService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


public class StatelessRealm extends AuthorizingRealm {

    @Autowired
    RedisTemplate<String,String> mRedisTemplate;

    @Autowired
    IUserinfoService mUserinfoService;

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
    }
    /**
     *  认证信息，主要针对用户登录，
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {

        StatelessToken statelessToken = (StatelessToken) authcToken;
        String username = statelessToken.getUsername();
        String pwd = statelessToken.getPwd();
        String token = statelessToken.getToken();
        User user = mUserinfoService.queryUserInfoByOpeanId(username);
        if(null == user){
            throw new AccountException("帐号或密码不正确");
        }else {
            mRedisTemplate.opsForValue().set(token, GsonQuick.toJsonFromBean(user));
        }
        return new SimpleAuthenticationInfo(username,pwd, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//
//        Long userId = TokenManager.getUserId();
//        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
//        //根据用户ID查询角色（role），放入到Authorization里。
//        Set<String> roles = roleService.findRoleByUserId(userId);
//        info.setRoles(roles);
//        //根据用户ID查询权限（permission），放入到Authorization里。
//        Set<String> permissions = permissionService.findPermissionByUserId(userId);
//        info.setStringPermissions(permissions);
        return null;
    }
    /**
     * 清空当前用户权限信息
     */
    public  void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
    /**
     * 指定principalCollection 清除
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
