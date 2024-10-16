package com.elitehomes.domain.repository;

import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.domain.entity.City;
import com.elitehomes.domain.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("""            
            select new com.elitehomes.core.entity.base.SelectableDto(
                c.id, c.name, state.uf
            )
            from City c
                inner join State state on state.id = c.state.id
            where state.active = true
                and (state.uf = :uf or :uf is null)
            order by state.uf
            """)
    List<SelectableDto> listForSelectable(@Param("uf") String uf);
}
