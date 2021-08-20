package com.trade_accounting.repositories.fias;

import com.trade_accounting.models.fias.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
