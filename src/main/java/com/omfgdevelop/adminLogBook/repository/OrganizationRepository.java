package com.omfgdevelop.adminLogBook.repository;

import com.omfgdevelop.adminLogBook.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findAllByUserId(Long userId);
    Organization findByNameAndUserId(String name, long userId);

}
