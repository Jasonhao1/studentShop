package com.example.studentshop.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Intellij IDEA.
 *
 * @Author hao-sen
 * Date:  2022/12/15
 */


public class District implements Serializable {

    private static final long serialVersionUID = 3674364250490127944L;

    private Integer id;
    private String parent;
    private String code;
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        District district = (District) o;
        return id.equals(district.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", parent='" + parent + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
