package org.acme.greeting.extension.deployment;

import be.rubus.quarkus.integration.ExampleBean;
import be.rubus.quarkus.integration.impl.ExampleCreator;
import io.quarkus.arc.deployment.BeanArchiveIndexBuildItem;
import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

import javax.inject.Singleton;

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
    
}
