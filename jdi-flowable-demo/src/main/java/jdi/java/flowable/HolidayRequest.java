package jdi.java.flowable;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author 刘洪伟
 */
public class HolidayRequest {

  public static void main(String[] args) {
    //      2.创建流程引擎
    ProcessEngineConfiguration cfg =
        new StandaloneProcessEngineConfiguration()
            .setJdbcUrl("jdbc:h2:mem:floable;DB_CLOSE_DELAY=1")
            .setJdbcUsername("sa")
            .setJdbcPassword("")
            .setJdbcDriver("org.h2.Driver")
            .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    ProcessEngine processEngine = cfg.buildProcessEngine();
    //      3.创建流程定义文件
    //      4.部署流程定义
    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deployment =
        repositoryService
            .createDeployment()
            .addClasspathResource("holiday-request.bpmn20.xml")
            .deploy();

    ProcessDefinition processDefinition =
        repositoryService
            .createProcessDefinitionQuery()
            .deploymentId(deployment.getId())
            .singleResult();
    System.out.println("Found process definition: " + processDefinition.getName());
    //    5.启动流程实例
    Scanner scanner = new Scanner(System.in);
    System.out.println("Who are you?");
    String employee = scanner.nextLine();

    System.out.println("How many holidays do you want to request?");
    Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());

    System.out.println("Why do you need them?");
    String description = scanner.nextLine();

    RuntimeService runtimeService = processEngine.getRuntimeService();

    Map<String, Object> variables = new HashMap<>();
    variables.put("employee", employee);
    variables.put("nrOfHolidays", nrOfHolidays);
    variables.put("description", description);
    ProcessInstance processInstance =
        runtimeService.startProcessInstanceByKey("holidayRequest", variables);

    //    6.查询与完成任务
    TaskService taskService = processEngine.getTaskService();
    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
    System.out.println("You have " + tasks.size() + " tasks");
    for (Task task : tasks) {
      System.out.println(task.getName() + " task");
    }

    System.out.println("Which task would you like to complete?");
    int taskIndex = Integer.valueOf(scanner.nextLine());
    Task task = tasks.get(taskIndex - 1);
    Map<String, Object> processVariables = taskService.getVariables(task.getId());
    System.out.println(
        processVariables.get("employee")
            + " wants "
            + processVariables.get("nrOfHolidays")
            + " of holidays. Do you approve this?");
    boolean approved = scanner.nextLine().toLowerCase().equals("y");
    variables = new HashMap<String, Object>();
    variables.put("approved", approved);
    taskService.complete(task.getId(), variables);
//    7.实现JavaDelegate
//    8.使用历史数据
    HistoryService historyService = processEngine.getHistoryService();
    List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery()
            .processInstanceId(processInstance.getId())
            .finished()
            .orderByHistoricActivityInstanceEndTime().asc().list();

    for (HistoricActivityInstance activityInstance : activityInstances) {
      System.out.println(activityInstance.getActivityId() + " took " + activityInstance.getDurationInMillis() + " milliseconds");
    }
  }
}
