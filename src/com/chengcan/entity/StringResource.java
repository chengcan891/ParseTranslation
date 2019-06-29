package com.chengcan.entity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StringResource {
    private String name;
    private String value;
    private boolean isOtherString;//值是@string/xxx

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


    public boolean isOtherString() {
        return isOtherString;
    }

    public void setOtherString(boolean otherString) {
        isOtherString = otherString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringResource resource = (StringResource) o;

        if (!Objects.equals(name, resource.name)) {
            return false;
        }

        if (isOtherString) {
            if (resource.isOtherString) {
                return Objects.equals(value, resource.value);
            } else {
                return false;
            }
        } else {
            if (resource.isOtherString) {
                return false;
            }
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
