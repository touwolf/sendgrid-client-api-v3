package com.touwolf.sendgrid3.model.bounces.data;

import java.util.List;

public class BouncesResponse
{
    List<Bounces> bounces;

    public List<Bounces> getBounces() {
        return bounces;
    }

    public void setBounces(List<Bounces> bounces) {
        this.bounces = bounces;
    }
}
