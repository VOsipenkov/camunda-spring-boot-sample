package com.example.workflow;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class ReserveSeatOnBoat implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        var money = "0.0";
        var ticketType = "Coach";

        money = (String) delegateExecution.getVariable("money");
        var doubleMoney = Integer.parseInt(money);

        if (doubleMoney >= 10_000) {
            ticketType = "First Class";
        } else if (doubleMoney >= 5_000) {
            ticketType = "Business Class";
        } else if (doubleMoney <= 10) {
            ticketType = "Stowaway ticket";
            throw new BpmnError("my-error-code", "this not enough money for traveling");
        }

        delegateExecution.setVariable("ticketType", ticketType);
    }
}
