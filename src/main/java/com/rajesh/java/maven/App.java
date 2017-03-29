package com.rajesh.java.maven;

import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import com.rajesh.java.maven.grpc.GrpcGuavaService;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Set<Service> services = ImmutableSet.<Service>of(new GrpcGuavaService());

        final ServiceManager manager = new ServiceManager(services);
        manager.addListener(new ServiceManager.Listener() {
                                public void stopped() {

                                }
                                public void healthy() {
                                    // Services have been initialized and are healthy, start accepting requests...
                                }
                                public void failure(Service service) {
                                    // Something failed, at this point we could log it, notify a load balancer, or take
                                    // some other action.  For now we will just exit.
                                    System.out.println( "In failure!" + service);
                                    System.exit(1);
                                }
                            },
                MoreExecutors.directExecutor()
                //Executors.newSingleThreadExecutor()
        );

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                // Give the services 5 seconds to stop to ensure that we are responsive to shutdown
                // requests.
                try {
                    System.out.println("Shutting down services");
                    manager.stopAsync().awaitStopped(5, TimeUnit.SECONDS);
                    System.out.println("Successfully shut down services");
                } catch (TimeoutException timeout) {
                    System.err.println("Shutdown timeout reached");
                }
            }
        });
        manager.startAsync();  // start all the services asynchronously
    }
}
