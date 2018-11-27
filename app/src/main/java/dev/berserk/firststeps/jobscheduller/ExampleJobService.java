package dev.berserk.firststeps.jobscheduller;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dev.berserk.firststeps.util.Util;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ExampleJobService extends JobService {

    private static String TAG = ExampleJobService.class.getSimpleName();

    private Boolean jobHashCancel = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Util.showLog(TAG, "Start job");
        backGroundWork(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Util.showLog(TAG, "On job cancelled");
        jobHashCancel = true;
        return true;
    }

    private void backGroundWork(JobParameters jobParameters) {
        new Thread(() -> {
           for (int i = 0; i < 15; i++) {
               DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
               String date = dateFormat.format(Calendar.getInstance().getTime());
               Date currentTime = Calendar.getInstance().getTime();
               Util.showLog(TAG, "Value hell yeah "+i+" Date "+date+" currentTime "+currentTime);

               //oreoNotification("Hora "+date+" numero "+i);
               if (jobHashCancel) {
                    return;
               }
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           Util.showLog(TAG, "Job finished");
           jobFinished(jobParameters, false);
        }).start();
    }
}
