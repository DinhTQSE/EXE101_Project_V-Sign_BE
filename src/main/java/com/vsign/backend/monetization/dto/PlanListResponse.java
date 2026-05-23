package com.vsign.backend.monetization.dto;

import java.util.List;

public record PlanListResponse(
        List<ActivePlanResponse> plans
) {
}
