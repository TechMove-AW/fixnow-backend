package com.techmove.fixnow.users.interfaces.rest.transform;

import com.techmove.fixnow.users.domain.model.commands.CreateWorkerCategoryCommand;
import com.techmove.fixnow.users.interfaces.rest.resources.CreateWorkerCategoryResource;

public class CreateWorkerCategoryFromSourceAssembler {
    public static CreateWorkerCategoryCommand toCommandFormSource(CreateWorkerCategoryResource resource){
        return new CreateWorkerCategoryCommand(
          resource.displayName()
        );
    }
}
