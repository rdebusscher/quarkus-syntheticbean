package be.rubus.quarkus.integration.impl;

import be.rubus.quarkus.integration.ExampleBean;
import io.quarkus.arc.BeanCreator;

import javax.enterprise.context.spi.CreationalContext;
import java.util.Map;

public class ExampleCreator implements BeanCreator<ExampleBean> {

    @Override
    public ExampleBean create(CreationalContext<ExampleBean> creationalContext, Map<String, Object> map) {
        System.out.println("Create ExampleBean");
        return new ExampleBean("X");
    }

}
