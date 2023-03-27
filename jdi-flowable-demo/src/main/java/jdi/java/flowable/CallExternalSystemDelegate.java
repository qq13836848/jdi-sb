package jdi.java.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @Author 刘洪伟
 */
public class CallExternalSystemDelegate implements JavaDelegate {
  @Override
  public void execute(DelegateExecution delegateExecution) {
    System.out.println(
        "Calling the external system for employee " + delegateExecution.getVariable("employee"));
  }
}
