package com.kayromino.orderservice.repository;

import com.kayromino.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.dateCreated between ?1 and ?2")
    Optional<List<Order>> getOrdersBetweenTwoDates(LocalDate fromDate, LocalDate toDate);

}
