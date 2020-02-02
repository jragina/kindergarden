package com.example.demo.kindergarden.repos;

import com.example.demo.kindergarden.model.KindergardenModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface KindergardenRepository extends JpaRepository<KindergardenModel, Long> {

}
