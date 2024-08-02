package com.elitehomes.domain.base;

import com.elitehomes.core.entity.base.Versionable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class EntityLifeCycle implements LifeCycleFields, Versionable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "integer unsigned")
	private Long id;

	@NotNull
	@Column(name = "created_at")
	private Instant createdAt;

	@NotNull
	@Version
	@ColumnDefault("0")
	private Short version;

}
