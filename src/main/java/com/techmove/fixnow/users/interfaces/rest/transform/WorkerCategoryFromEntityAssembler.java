package com.techmove.fixnow.users.interfaces.rest.transform;

import com.techmove.fixnow.users.domain.model.aggregates.WorkerCategory;
import com.techmove.fixnow.users.interfaces.rest.resources.WorkerCategoryResource;

public class WorkerCategoryFromEntityAssembler {
    public static WorkerCategoryResource toResourceFromEntity(WorkerCategory entity){
        return new WorkerCategoryResource(
                entity.getId(),
                entity.getDisplayName(),
                entity.getSlug()
        );
    }
}
