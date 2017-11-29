package com.gree.ant.shiro;

import com.gree.ant.exception.KellyException;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Cbase002VO;
import com.gree.ant.vo.Cbase007VO;
import com.gree.ant.vo.enumVO.ResultEnum;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.nutz.dao.Dao;
import org.nutz.integration.shiro.SimpleShiroToken;
import org.nutz.mvc.Mvcs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * The type Kelly authorizing realm.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 用一句话描述这个类的作用.
 * @title KellyAuthorizingRealm
 * @createTime 2017 :11:13 02:11:23.
 */
public class KellyAuthorizingRealm extends AuthorizingRealm{

    private Logger logger = LoggerFactory.getLogger(KellyAuthorizingRealm.class);

    /**
     * The Dao.
     *
     * @description 变量的说明
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :11:13 02:11:23.
     */
    protected Dao dao; //ShiroFilter先于NutzFilter初始化,所以无法使用注入功能


    public KellyAuthorizingRealm() {
        this(null,null);
    }

    public KellyAuthorizingRealm(CacheManager cacheManager) {
        this(cacheManager,null);
    }

    public KellyAuthorizingRealm(CredentialsMatcher matcher) {
        this(null,matcher);
    }

    public KellyAuthorizingRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);

        // 非常非常重要,与SecurityUtils.getSubject().login是对应关系!!!
        setAuthenticationTokenClass(SimpleShiroToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        logger.info("进入doGetAuthorizationInfo方法...................");

        if(principal == null){
            throw new KellyException(ResultEnum.PRINCIPAL_NULL);
        }
        String usid = principal.getPrimaryPrincipal().toString().trim();
        Cbase000VO cbase000VO = dao().fetch(Cbase000VO.class,usid);
        if(cbase000VO == null){
            return null;
        }
        SimpleAuthorizationInfo author  = new SimpleAuthorizationInfo();
        cbase000VO = dao().fetchLinks(cbase000VO,"cbase007VOS");
        List<Cbase007VO> cbase007VOList = cbase000VO.getCbase007VOS();
        if(cbase007VOList != null){
            for(Cbase007VO cbase007VO:cbase007VOList){
                author.addRole(cbase007VO.getDsca());
                cbase007VO = dao().fetchLinks(cbase007VO,"cbase002VOS");
                List<Cbase002VO> cbase002VOList = cbase007VO.getCbase002VOS();
                if(cbase002VOList != null) {
                    for (Cbase002VO cbase002VO : cbase002VOList) {
                        author.addStringPermission(cbase002VO.getDsca());
                    }
                }
            }
        }
        return author;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authorToken) throws AuthenticationException {

        logger.info("进入doGetAuthenticationInfo方法...................");

        SimpleShiroToken token = (SimpleShiroToken)authorToken;

        Cbase000VO cbase000VO = null;

        if (token != null) {
            cbase000VO = dao().fetch(Cbase000VO.class,token.getPrincipal().toString().trim());
        }

        if(cbase000VO == null){
            return null;
        }

        return new SimpleAccount(cbase000VO.getUSID(),cbase000VO.getPAWD(),getName());
    }

    /**
     *覆盖父类的验证，直接PASS
     */
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
    }


    public Dao dao(){
        if(dao == null){
            dao = Mvcs.ctx().getDefaultIoc().get(Dao.class,"daoFX");
            return dao;
        }
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
}
