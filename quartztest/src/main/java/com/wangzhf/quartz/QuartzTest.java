package com.wangzhf.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzTest {

    private static Logger logger = LoggerFactory.getLogger(QuartzTest.class);

    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();

            JobDetail job = JobBuilder.newJob(HelloJob.class)
                        .withIdentity("myjob", "group1")
                        .build();
            job.getJobDataMap().put("hello", "0001");

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3).repeatForever())
                    .build();
            System.out.printf("========>>>> " + trigger.getJobDataMap().get("hello"));
            trigger.getJobDataMap().put("world", "002");

            // job2
            JobDetail job2 = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("myjob2", "group2")
                    .build();

            Trigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger2", "group2")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5).repeatForever())
                    .build();



            scheduler.scheduleJob(job, trigger);

            scheduler.scheduleJob(job2, trigger2);

            //scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}