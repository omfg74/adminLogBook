package com.omfgdevelop.adminLogBook.service.impl;

import com.omfgdevelop.adminLogBook.model.Organization;
import com.omfgdevelop.adminLogBook.model.User;
import com.omfgdevelop.adminLogBook.repository.OrganizationRepository;
import com.omfgdevelop.adminLogBook.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganisationService {
    @Autowired
    private OrganizationRepository repository;

    @Override
    public List<Organization> getAllByUser(User user) {
        List<Organization> organizations = repository.findAllByUserId(user.getId());
        return organizations;
    }

    @Override
    public Organization getOrganizationByNameAndUserId(String name, long userId) {
        Organization organization = repository.findByNameAndUserId(name, userId);
        return organization;
    }

    @Override
    public Organization addOrganization(User user, String name) {
        Organization organization = new Organization();
        organization.setName(name);
        organization.setUserId(user.getId());
        Organization savedOrganization = repository.save(organization);

        return savedOrganization;
    }
}
