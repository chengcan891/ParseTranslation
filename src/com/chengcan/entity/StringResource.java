package com.chengcan.entity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StringResource {
    private String name;
    private String value;
    private List<String> regs;

    public List<String> getRegs() {
        return regs;
    }

    public void setRegs(List<String> regs) {
        this.regs = regs;
    }

    public StringResource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringResource resource = (StringResource) o;

        if (!Objects.equals(name, resource.name)) {
            return false;
        }

        if (regs.size() != resource.regs.size()) {
            return false;
        }

        Collections.sort(regs);
        Collections.sort(resource.regs);
        for (int i = 0; i < regs.size(); i++) {
            if (!regs.get(i).equals(resource.regs.get(i))) {
                return false;
            }
        }

        return true;

    }

    @Override
    public String toString() {
        return "com.chengcan.entity.StringResource{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", regs=" + regs +
                '}';
    }
}
