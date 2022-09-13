package com.example.bank.account.adapter.rest.in;

import com.example.bank.account.common.command.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    public AccountController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }

    @PostMapping
    public void creatingAccount(@RequestBody CreateAccountCommand command){
        commandGateway.send(command);
    }

    @PatchMapping("/deposit")
    public void depositingAmount(@RequestBody DepositAmountCommand command){
        commandGateway.send(command);
    }
    @PatchMapping("/withdraw")
    public void withdrawingAmount(@RequestBody WithdrawAmountCommand command){
         commandGateway.send(command);
    }

    @PatchMapping("/{id}/activate")
    public void activateAccount(@PathVariable String id){
         commandGateway.send(new ActivateAccountCommand(id));

    }


    @PatchMapping("/{id}/deactivate")
    public void deactivateAccount(@PathVariable String id){
        commandGateway.send(new DeactivateAccountCommand(id));

    }
    @GetMapping("/{id}/history")
    public List<? extends DomainEventMessage<?>> history(@PathVariable String id){
        return eventStore.readEvents(id)
                .asStream()
                .collect(Collectors.toList());
    }
}
