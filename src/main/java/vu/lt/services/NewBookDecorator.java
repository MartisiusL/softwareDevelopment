package vu.lt.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.Random;
import java.util.concurrent.Future;

@Decorator
public abstract class NewBookDecorator implements NumberGenerator{

    @Inject @Delegate @Any NumberGenerator numberGenerator;

    public int generateNumber(int from) {
        int generatedNumber = new Random().nextInt(40) + from;
        if(generatedNumber > 2000) {
            System.out.println("Book is new, written in " + generatedNumber);
        }

        return generatedNumber;
    }
}
