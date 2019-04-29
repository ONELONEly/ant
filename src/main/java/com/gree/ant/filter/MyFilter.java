package com.gree.ant.filter;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;

public class MyFilter implements ActionFilter{

    /**
     * 过滤入口方法,属于前置过滤
     *
     * @param actionContext 执行上下文
     * @return 如果为null, 则继续执行下一个动作链, 否则将使用它渲染响应
     */
    @Override
    public View match(ActionContext actionContext) {
        return null;
    }
}
