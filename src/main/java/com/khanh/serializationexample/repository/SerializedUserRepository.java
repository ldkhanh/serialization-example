package com.khanh.serializationexample.repository;

import com.khanh.serializationexample.model.SerializedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerializedUserRepository extends JpaRepository<SerializedUser, Integer> {

}
