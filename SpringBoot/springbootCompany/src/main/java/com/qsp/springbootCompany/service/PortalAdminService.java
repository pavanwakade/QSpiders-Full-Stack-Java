
package com.qsp.springbootCompany.service;

import com.qsp.springbootCompany.dao.PortalAdminDao;
import com.qsp.springbootCompany.dto.PortalAdmin;
import com.qsp.springbootCompany.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PortalAdminService {

    @Autowired
    private PortalAdminDao dao;

    public ResponseEntity<PortalAdmin> savePortalAdmin(PortalAdmin portalAdmin) {
        PortalAdmin saved = dao.savePortalAdmin(portalAdmin);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    public ResponseEntity<PortalAdmin> login(String username, String password) {
        Optional<PortalAdmin> optional = dao.findByUsernameAndPassword(username, password);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        throw new IdNotFoundException("Invalid username or password");
    }
}