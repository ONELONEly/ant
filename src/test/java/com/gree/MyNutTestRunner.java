package com.gree;

import com.gree.MainModule;
import org.junit.runners.model.InitializationError;
import org.nutz.ioc.Ioc;
import org.nutz.mock.NutTestRunner;

public class MyNutTestRunner extends NutTestRunner{

    public MyNutTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected Class<?> getMainModule() {
        return MainModule.class;
    }

    @Override
    protected Ioc createIoc() {
        return super.createIoc();
    }
}
