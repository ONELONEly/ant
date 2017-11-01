package com.gree;

import org.nutz.mvc.adaptor.PairAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * @author Created by ${user}
 * @date ${.now?string["yyyy-MM-dd HH:mm:ss"]}.
 */
@SetupBy(value = MainSetup.class)
@ChainBy(args = {"/ioc/chain.js"})
@Encoding(input = "UTF-8",output = "UTF-8")
@IocBy(type = ComboIocProvider.class,args = {"*js","ioc/","*anno","com.gree","*tx","*async","48"})
@AdaptBy(type = PairAdaptor.class)
@Filters(@By(type= CheckSession.class,args = {"usid","/"}))
public class MainModule {

}
