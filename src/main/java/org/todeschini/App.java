package org.todeschini;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;

@QuarkusMain
@Slf4j
public class App {

    public static void main(String ... args) {
        log.info("start task app");
        Quarkus.run(args);
    }
}
