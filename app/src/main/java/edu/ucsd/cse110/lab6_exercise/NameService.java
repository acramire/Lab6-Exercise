package edu.ucsd.cse110.lab6_exercise;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NameService extends IntentService {

    private static final String ACTION_NAME = "edu.ucsd.cse110.lab6_exercise.action.NAME";

    private static final String NAME = "edu.ucsd.cse110.lab6_exercise.extra.NAME";
    private String name;
    private static boolean isIntentServiceRunning = false;

    public NameService() {
        super("NameService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionName(Context context, String name) {
        Intent intent = new Intent(context, NameService.class);
        intent.setAction(ACTION_NAME);
        intent.putExtra(NAME, name);

        if (isIntentServiceRunning) { Toast.makeText(context, "Service currently running", Toast.LENGTH_SHORT).show(); }
        else {context.startService(intent);}
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(!isIntentServiceRunning){
            isIntentServiceRunning = true;
        if (intent != null) {
            final String action = intent.getAction();
            if (action.equals(ACTION_NAME)) {
                this.name = intent.getStringExtra(NAME);
                handleActionName(name);
            } else {
            }
        }
        }
//        else{
//            Toast.makeText(NameService.this, "Service currently running",
//                    Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public void onDestroy() {
        Toast.makeText(NameService.this, "Service stopped", Toast.LENGTH_SHORT).show();
        isIntentServiceRunning = false;
        super.onDestroy();
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionName(String name) {
        Toast.makeText(NameService.this, "Service started", Toast.LENGTH_SHORT).show();

        synchronized (this) {
            try {
                wait(5000);
                SharedPreferences prefs = getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("name", name);
                editor.apply();
            } catch (InterruptedException e) {
                e.printStackTrace();
                //Toast.makeText(NameService.this, "Service currently running", Toast.LENGTH_SHORT).show();
            }
        }

        stopSelf();
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}