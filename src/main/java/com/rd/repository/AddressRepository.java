package com.rd.repository;

import com.rd.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.country = :country AND a.city = :city AND a.street = :street AND a.postalCode = :postalCode")
    Optional<Address> findByCountryCityStreetAndPostalCode(@Param("country") String country, @Param("city") String city, @Param("street") String street, @Param("postalCode") String postalCode);;
}
