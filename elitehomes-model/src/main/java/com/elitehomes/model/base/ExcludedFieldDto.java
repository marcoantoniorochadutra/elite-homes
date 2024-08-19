package com.elitehomes.model.base;

import java.time.Instant;

public interface ExcludedFieldDto {

    Long getId();

	void setId(Long id);

	Instant getCreatedAt();

	void setCreatedAt(Instant createdAt);

	Short getVersion();

	void setVersion(Short version);

}
