package dev.berserk.firststeps.jobscheduller;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.Util;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobSchedullerActivity extends AppCompatActivity {

    private static String TAG = JobSchedullerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduller);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonStartJob) public void startJobClick(View view) {
        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Util.showLog(TAG, "Job schedule success");
        }
        else {
            Util.showLog(TAG, "===> Job scheduling failed <===");
        }
    }

    @OnClick(R.id.buttonStopJob) public void stopJobClick(View view) {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(123);

        Util.showLog(TAG, "Job cancelled");
    }
}
