package com.oleg348.quadraticequationresolver.storage.spring;

import com.oleg348.quadraticequationresolver.storage.spring.entity.EquationSolutionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquationSolutionRepository extends ListCrudRepository<EquationSolutionEntity, Long> {

    @Query(value = "select top :amount * from SOLUTION order by CREATION_TIME desc",
            nativeQuery = true)
    List<EquationSolutionEntity> getLastRecords(int amount);
}
