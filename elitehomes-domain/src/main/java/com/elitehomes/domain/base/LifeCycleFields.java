package com.elitehomes.domain.base;

import java.time.Instant;

public interface LifeCycleFields {

    Long getId();

    void setId(Long id);

    Instant getCreatedAt();

    void setCreatedAt(Instant createdAt);

    Short getVersion();

    void setVersion(Short version);

}
