package io.javabrains.reactiveworkshop;

import java.io.IOException;

import reactor.core.scheduler.Schedulers;

public class Exercise2 {

    
    /** 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux() and ReactiveSources.userFlux()
    	
        // Print all numbers in the ReactiveSources.intNumbersFlux stream
        ReactiveSources.intNumbersFlux().log()

                // side effect of Subscriber.OnSubcribe  in callbback
                .doOnSubscribe((e) -> System.out.println("subscribed on thread ------" + Thread.currentThread().getName()))
                .doOnRequest((e) -> System.out.println(e+" request made on thread ------" + Thread.currentThread().getName()))
                
                // side effect of Subscriber.onNext(data) in callback
                .doOnNext(data -> System.out.println(data + " emmited on thread "+ Thread.currentThread().getName()))
                .map((e)->{System.out.println(e+" mappiung on thread "+ Thread.currentThread().getName()); return e+100;})
                .subscribe(
                		// like hookOnNext()
                        (e) -> System.out.println(e + "printed on thread " + Thread.currentThread().getName()),
                        
                        // like hookOnError()
                        (err) -> System.out.println(err),
                        
                        // like hookOnComplete()
                        () -> System.out.println("complete on thread ---------- " + Thread.currentThread().getName()));
                            
        // Print all users in the ReactiveSources.userFlux stream
        // TODO: Write code here

        System.out.println("Press a key to end");
        System.in.read();
    }

}
