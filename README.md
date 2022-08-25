# Quarkus synthetic bean

How to use Synthetic Bean in Quarkus?

StackOverflow question https://stackoverflow.com/questions/73293293/when-creating-syntheticbean-using-beancreator-i-get-npe-when-bean-is-instantiat

Solution:

The Scope was Singleton from Google Juice and not the CDI one.  Problem is not reported correctly due to insufficient checks within `io.quarkus.arc.processor.BeanConfiguratorBase#scope(java.lang.Class<? extends java.lang.annotation.Annotation>)`

Executed Steps

- using maven archetype created an extension, see directory `extension`.
- Use a Synthetic bean (as I can't use producer, see final goal)
- When using synthetic bean in an app (JAX-RS resource) A NullPointerException happen (within Quarkus generated code)

The Stacktrace is different based on how the code is run.

Using test

```
Caused by: java.lang.NullPointerException: Cannot invoke "javax.enterprise.context.spi.Context.get(javax.enterprise.context.spi.Contextual, javax.enterprise.context.spi.CreationalContext)" because the return value of "io.quarkus.arc.ArcContainer.getActiveContext(java.lang.Class)" is null
	at be.rubus.quarkus.integration.ExampleBean_71a9cae578eebe74012c5e809cf1ed20f4d374a6_Synthetic_Bean.get(Unknown Source)

```

Using Quarkus dev mode and using Curl

```
Caused by: java.lang.RuntimeException: Error injecting be.rubus.quarkus.integration.ExampleBean be.rubus.microstream.quarkus.GreetingResource.exampleBean
        at be.rubus.microstream.quarkus.GreetingResource_Bean.create(Unknown Source)

```

The BeanCreator `BeanCreator` within the Extension that should create the Java instance is called (see System.out.println in output)

## Final Goal

Have a MicroStream integration within Quarkus (just like [Jakarta CDI](https://github.com/microstream-one/microstream/tree/master/integrations/cdi) and [Spring Boot](https://github.com/microstream-one/microstream/tree/master/integrations/spring-boot))

- Have `@Storage` that developer can use on a single Class
- Instantiate MicroStream _StorageManager_ and expose as Bean.
- If no root yet, create an instance of `@Storage` class, set as root and expose as Bean.
- If already a root defied within MicroStream, use this instance and expose as Bean.
