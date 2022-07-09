package org.todeschini;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class App {

    public static void main(String ... args) {
        System.out.println("start task app");
        Quarkus.run(args);
    }
}
