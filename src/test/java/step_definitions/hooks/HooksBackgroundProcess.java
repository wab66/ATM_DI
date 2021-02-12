package step_definitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import nicebank.TransactionProcessor;

public class HooksBackgroundProcess {
    private Thread transactionProcessorThread;

    @Before
    public void startBackgroundThread() {
        System.out.println("*********** [HooksBackgroundProcess] > [@Before] > [startBackgroundThread()] >: 1. About to start Hook.");
        transactionProcessorThread = new Thread(() -> {
            System.out.println("1*********** [HooksBackgroundProcess] > [@Before] > [startBackgroundThread()] > [run()] >: 1a. About to create new TransactionProcessor called (processor).");
            TransactionProcessor processor = new TransactionProcessor();
            System.out.println("2*********** [HooksBackgroundProcess] > [@Before] > [startBackgroundThread()] > [run()] >: 1b. about to start - processor.process()");
            processor.process();
            System.out.println("3*********** [HooksBackgroundProcess] > [@Before] > [startBackgroundThread()] > [run()] >: 1c. After : processor should be started");
        });

        transactionProcessorThread.start();
    }

    @After
    public void stopBackgroundThread() {
        transactionProcessorThread.interrupt();
    }
}
