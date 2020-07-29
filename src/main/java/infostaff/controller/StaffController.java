package infostaff.controller;

import infostaff.exception.ResourceNotFoundException;
import infostaff.model.request.AddStaffRequest;
import infostaff.service.impl.TblStaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1.0/infostaff")
public class StaffController {

    @Autowired
    TblStaffServiceImpl tblStaffService;

    @GetMapping("/get-staff/{id}")
    public ResponseEntity getOneStaffById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(tblStaffService.getStaffById(id));
    }

    @PostMapping("/add-staff")
    public ResponseEntity addStaff(@RequestBody @Valid AddStaffRequest addStaffRequest , UriComponentsBuilder uriBuilder) {
        Long id = tblStaffService.addStaff(addStaffRequest);

        URI uri = uriBuilder.path("/api/v1.0/infostaff/get-staff/" + id).build().toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body(uri);
    }


}
