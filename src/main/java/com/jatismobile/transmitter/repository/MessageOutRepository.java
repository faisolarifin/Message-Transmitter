package com.jatismobile.transmitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jatismobile.transmitter.model.MessageOut;


@Repository
public interface MessageOutRepository extends JpaRepository<MessageOut,Long>{

}