package com.wangzhf.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.jobs.NoOpJob;

import java.util.Date;

@DisallowConcurrentExecution
public class HelloJob extends NoOpJob {

    private Date now;

    public HelloJob() {
        System.out.println("Hello job is created ... ");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("HelloJob running ... ");
        Date previousDate = (Date) context.get("now");
        if(previousDate != null){
            System.out.println(((Date)context.get("now")).getTime());
        }else{
            System.out.println("previousDate is null");
        }
        System.out.println(context.getMergedJobDataMap().get("hello"));
        System.out.println(context.getMergedJobDataMap().get("world"));
        this.setNow(new Date());
    }

    public void setNow(Date now){
        this.now = now;
    }
}
