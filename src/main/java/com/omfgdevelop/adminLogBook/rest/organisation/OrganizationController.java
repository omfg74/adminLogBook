package com.omfgdevelop.adminLogBook.rest.organisation;

import com.omfgdevelop.adminLogBook.dto.OrganizationDto;
import com.omfgdevelop.adminLogBook.model.Organization;
import com.omfgdevelop.adminLogBook.model.User;
import com.omfgdevelop.adminLogBook.rest.BaseController;
import com.omfgdevelop.adminLogBook.security.jwt.JwtTokenProvider;
import com.omfgdevelop.adminLogBook.service.OrganisationService;
import com.omfgdevelop.adminLogBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/organization")
public class OrganizationController extends BaseController {


    @Autowired
    private OrganisationService organisationService;

    @PostMapping(value = "create")
    public ResponseEntity<Organization> createOrganisation(@RequestHeader("Authorization") String authHeader, String organizationName) {
        User user = resolveUser(authHeader);
        if (user == null) {
            throw new UsernameNotFoundException("No user found");
//            return ResponseEntity.notFound();
        }
        Organization organization = organisationService.addOrganization(user, organizationName);
        return ResponseEntity.ok(organization);
    }

    @GetMapping(value = "get_all")
    public ResponseEntity<List<Organization>> getAllUserOrganizations(@RequestHeader("Authorization") String authHeader) {
        User user = resolveUser(authHeader);
        List<Organization> organizations = organisationService.getAllByUser(user);
        return ResponseEntity.ok(organizations);
    }


}
