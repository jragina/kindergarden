package com.example.demo.kindergarden.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.kindergarden.model.ChildrenModel;

@Repository
public
interface ChildrenModelRepository extends JpaRepository<ChildrenModel, String> {

}
