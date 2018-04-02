package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

/**
 * Created by Peste on 4/2/2018.
 */

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();
    String[] actionTypes = { "1", "10", "11", "100"};

    int first;
    int second;
    int third;
    int fourth;


    public ProcessingThread(Context context, int firstNumber, int secondNumber, int thirdNumber, int fourthNumber) {
        this.context = context;

        first = firstNumber;
        second = secondNumber;
        third = thirdNumber;
        fourth = fourthNumber;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();

        intent.setAction(actionTypes[random.nextInt(actionTypes.length)]);
        intent.putExtra("message", first + " " + second + " " + third + " " + fourth);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }

}
