package be.rubus.payara.accelerator.hazelcast;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
