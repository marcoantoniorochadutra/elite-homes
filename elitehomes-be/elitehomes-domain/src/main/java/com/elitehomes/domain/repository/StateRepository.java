package com.elitehomes.domain.repository;

import com.elitehomes.core.entity.base.SelectableDto;
import com.elitehomes.domain.entity.City;
import com.elitehomes.domain.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long>  {

    State getReferenceByUf(String uf);

    @Query("""        
            select new com.elitehomes.core.entity.base.SelectableDto(
                state.id, state.name, state.uf, state.active
            )
            from State state
            order by state.uf
            """)
    List<SelectableDto> listForSelectable();

}
