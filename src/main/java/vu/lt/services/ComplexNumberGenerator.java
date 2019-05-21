package vu.lt.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Random;

@ApplicationScoped
@Alternative
public class ComplexNumberGenerator implements NumberGenerator {

    public int generateNumber(int from) {
        return new Random().nextInt(40) + from;
    }
}
