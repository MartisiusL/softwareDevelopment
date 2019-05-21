package vu.lt.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimpleNumberGenerator implements NumberGenerator{

    public int generateNumber(int from) {
        return 1000;
    }
}
