package org.acme.greeting.extension.deployment;

import be.rubus.quarkus.integration.ExampleBean;
import com.google.inject.Singleton;
import io.quarkus.arc.BeanCreator;
import io.quarkus.arc.deployment.BeanArchiveIndexBuildItem;
import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

import javax.enterprise.context.spi.CreationalContext;
import java.util.Map;

class GreetingExtensionProcessor {

    private static final String FEATURE = "greeting-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    SyntheticBeanBuildItem syntheticExample(BeanArchiveIndexBuildItem beanArchiveIndex) {

        System.out.println("Generate SyntheticBeanBuildItem");
        return SyntheticBeanBuildItem.configure(ExampleBean.class).scope(Singleton.class)
                .creator(ExampleCreator.class)
                .done();

    }

    private static class ExampleCreator implements BeanCreator<ExampleBean> {

        @Override
        public ExampleBean create(CreationalContext<ExampleBean> creationalContext, Map<String, Object> map) {
            System.out.println("Create ExampleBean");
            return new ExampleBean("X");
        }
    }
    
}
