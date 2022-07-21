package hr.tvz.zendeli.vaxapp;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.CronScheduleBuilder.cronSchedule;


@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail objavaJobDetail(){
        return JobBuilder.newJob(SampleJob.class).withIdentity("objavaJob").storeDurably().build();
    }

    @Bean
    public Trigger objavaJobTrigger(){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger().forJob(objavaJobDetail()).withIdentity("objavaTrigger").withSchedule(simpleScheduleBuilder).build();



    }
    @Bean
    public Trigger objavaJobTrigger2(){


        return TriggerBuilder.newTrigger().withIdentity("objavaTrigger2")
                .withSchedule(cronSchedule("0 0 12 ? * MON,FRI *"))
                .forJob(objavaJobDetail())
                .build();



    }
}
