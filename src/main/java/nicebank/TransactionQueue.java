package nicebank;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class TransactionQueue {
    private static String MESSAGES_FOLDER = "./messages";
    private static String MESSAGE_FILE_PATH = "%s/account_%03d.txt";  // rc0199m - changed path to include "account_"
    private int nextId = 1;

    public static void clear() {
        try {
            FileUtils.deleteDirectory(new File(MESSAGES_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File(MESSAGES_FOLDER).mkdirs();
    }

    public void write(String transaction){
        System.out.println(">>>>>>>>>>>>>>>>> [TransactionQueue] > write() > Start");
        String messageFilePath = String.format(MESSAGE_FILE_PATH, MESSAGES_FOLDER, nextId);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(messageFilePath, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println(transaction);
        writer.close();
        System.out.println(">>>>>>>>>>>>>>>>> [TransactionQueue] > write() > write transaction: " + transaction + ", to queue (file).");

        nextId++;
    }

    public String read() {
        // Get files in 'messages'
        File messagesFolder = new File(MESSAGES_FOLDER);
        File[] messages = messagesFolder.listFiles();

        String message = "";

        // If message file found
        //System.out.println(">>>>>>>>>>>>>>>>> [TransactionQueue] > read() > Read from queue (file).");
        if (messages != null && messages.length > 0){
            //System.out.println(">>>>>>>>>>>>>>>>> [TransactionQueue] > read() > Message found: " + messages);
            Arrays.sort(messages, new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    return Integer.parseInt(f1.getName())
                            - Integer.parseInt(f2.getName());
                }
            });

            // Open it
            Scanner scanner = null;
            try {
                scanner = new Scanner(messages[0]);

                if (scanner.hasNextLine()) {
                    message = scanner.nextLine();
                    scanner.close();

                    // Delete it
                    messages[0].delete();
                }
                else {
                    scanner.close();
                }

            } catch (FileNotFoundException e) {
                // File has gone away!
            }
        }

        //System.out.println(">>>>>>>>>>>>>>>>> [TransactionQueue] > read() > Message to return: " + message);
        return message;
    }
}
