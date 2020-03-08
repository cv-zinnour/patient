package ca.uqtr.patient.config;

/*
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
    private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName() );

    @Value("${scheduler.thread.pool.size}")
    private int POOL_SIZE;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler());
        taskRegistrar.addTriggerTask(
                () -> yourJob().performJob(),
                (TriggerContext triggerContext) -> yourService.getCron()
        );
    }

    @Bean(name = "taskScheduler")
    public TaskScheduler taskScheduler() {

        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

        scheduler.setPoolSize(POOL_SIZE);
        scheduler.setThreadNamePrefix("My-Scheduler-");
        scheduler.setErrorHandler(t -> LOGGER.log( Level.ALL, "Unknown error occurred while executing task."+ t));
        scheduler.setRejectedExecutionHandler((r, e) -> LOGGER.log(Level.ALL, "Execution of task {} was rejected for unknown reasons."+ r));
        scheduler.initialize();

        return scheduler;
    }
}
*/
