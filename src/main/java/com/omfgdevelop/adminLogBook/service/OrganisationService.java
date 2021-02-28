package com.omfgdevelop.adminLogBook.service;

import com.omfgdevelop.adminLogBook.model.Organization;
import com.omfgdevelop.adminLogBook.model.User;

import java.util.List;

public interface OrganisationService {

    public List<Organization> getAllByUser(User user);

    public Organization getOrganizationByNameAndUserId(String name, long userId);

    public Organization addOrganization(User user,String name);


}
