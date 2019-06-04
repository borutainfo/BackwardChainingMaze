package com.boruta.backwardchaining;

import com.boruta.backwardchaining.engine.service.ApplicationService;

/**
 * Main class of application.
 *
 * @author Sebastian Boruta <sebastian@boruta.info>
 */
public class App {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ApplicationService app = new ApplicationService(args);
        app.run();
    }
}
