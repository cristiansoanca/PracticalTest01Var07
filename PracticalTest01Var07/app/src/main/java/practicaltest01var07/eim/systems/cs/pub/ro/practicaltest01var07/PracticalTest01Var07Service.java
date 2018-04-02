package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Random;

public class PracticalTest01Var07Service extends Service {

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int firstNumber = intent.getIntExtra("first", -1);
        int secondNumber = intent.getIntExtra("second", -1);
        int thirdNumber = intent.getIntExtra("third", -1);
        int fourthNumber = intent.getIntExtra("fourth", -1);

        processingThread = new ProcessingThread(this, firstNumber, secondNumber, thirdNumber, fourthNumber);
        processingThread.start();

        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
