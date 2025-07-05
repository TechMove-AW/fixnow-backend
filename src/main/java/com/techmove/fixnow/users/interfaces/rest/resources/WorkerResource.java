package com.techmove.fixnow.users.interfaces.rest.resources;

import java.util.List;

public record WorkerResource(
        Long workerId,
        Long userId,
        Long workerCategoryId,
        String availability,
        Float hourlyRateAmount,
        Integer projectsCompleted,
        List<String> skills,
        List<WorkerServiceResource> services
) {
}