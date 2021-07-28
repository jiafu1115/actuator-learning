package com.example.actuatorlearning;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "myendpoint")
public class MyEndpoint {

    public final static class Student {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @ReadOperation
    public Student getData() {
        Student student = new Student();
        student.setName("fujian");
        return student;
    }
}
