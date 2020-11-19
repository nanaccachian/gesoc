package com.testigos.gesoc.model.services.budgetValidator;

import java.util.TimeZone;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class Calendarizacion {

    private Calendarizacion() {
    }

    public static void comenzar(int hora, int minutos) throws SchedulerException {

        SchedulerFactory schedFactory = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedFactory.getScheduler();
        scheduler.start();

        JobBuilder jobBuilder = JobBuilder.newJob(ValidadorPresupuestos.class);

        JobDetail jobDetail = jobBuilder.withIdentity("Validacion de presupuestos").build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("diario")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(hora, minutos).inTimeZone(TimeZone.getDefault()))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
