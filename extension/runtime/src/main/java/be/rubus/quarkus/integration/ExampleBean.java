package be.rubus.quarkus.integration;

public class ExampleBean {

    private String value;

    public ExampleBean(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
