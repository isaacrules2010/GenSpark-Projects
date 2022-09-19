package org.genspark;

import org.springframework.stereotype.Component;

@Component
public class Phone {
    private String mobile;

    public Phone() {
        //System.out.println("Creating a phone number...");
    }

    public Phone(String number){
        mobile = number;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
