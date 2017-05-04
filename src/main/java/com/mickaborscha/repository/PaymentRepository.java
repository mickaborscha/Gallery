package com.mickaborscha.repository;

import com.mickaborscha.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Oleg on 4/25/2017.
 */
@Repository
public interface PaymentRepository extends JpaRepository <Payment, Integer> {

    Set<Payment> findPaymentById(Integer id);

   /*@Query("select 'sum' , date, comment from User ug inner join ug.user u \n" +
           "where ug.group_id = :groupId ")
    public List<Payment> findByForeignKey(@Param("email") String email);*/

   /*
   * User inner join payment
   *
   * */
}

