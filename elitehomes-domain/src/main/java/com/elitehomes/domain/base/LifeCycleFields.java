package com.elitehomes.domain.base;

import java.time.Instant;

public interface LifeCycleFields {

    Instant getCreatedAt();

    void setCreatedAt(Instant createdAt);

    Long getId();

}
