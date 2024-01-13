package io.javabrains.reactiveworkshop;

import java.io.IOException;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;

public class Exercise5 {

    /** 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        // TODO: Write code here

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().log().subscribe(new MySubscriber<>());

        System.out.println("Press a key to end");
        System.in.read();
    }
}

class MySubscriber<T> extends BaseSubscriber<T> {

    @Override
    protected void hookOnNext(Object value) {
    	System.out.println(value);
        System.out.println(value + " " +Thread.currentThread().getName());
        request(1);
    }

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("subscribed on " + Thread.currentThread().getName());
        subscription.request(5);
    }
    
}