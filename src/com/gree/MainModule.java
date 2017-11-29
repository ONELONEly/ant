package com.gree;

import org.nutz.integration.shiro.ShiroSessionProvider;
import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 */
@SetupBy(value = MainSetup.class)
@ChainBy(args = {"/ioc/chain.js"})
@Encoding(input = "UTF-8",output = "UTF-8")
@IocBy(type = ComboIocProvider.class,args = {"*js","ioc/","*anno","com.gree","*tx","*async","48"})
@AdaptBy(type = PairAdaptor.class)
@Filters(@By(type= CheckSession.class,args = {"usid","/"}))
@SessionBy(ShiroSessionProvider.class)
public class MainModule {

}
