package com.example.employeemanagementsystem.Controller;

import com.example.employeemanagementsystem.Model.EmpMod;
import com.example.employeemanagementsystem.Service.EmpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/emp")
@RequiredArgsConstructor
public class EmpController {
    private final EmpService empService;

    @GetMapping("/get")
    public ResponseEntity getEmp() {
        empService.getEmpModArrayList();
        return ResponseEntity.status(HttpStatus.OK).body(empService.getEmpModArrayList());
    }

    @PostMapping("add")
    public ResponseEntity addEmp(@Valid @RequestBody EmpMod empMod, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        empService.addEmp(empMod);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully  add Employee");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmp(@PathVariable String id, @Valid @RequestBody EmpMod empMod, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }


        boolean isUpdate = empService.updateEmp(id, empMod);
        if (isUpdate) {
            return ResponseEntity.status(HttpStatus.OK).body("updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry  Found");

        }


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable String id) {
        boolean isDeleted = empService.deleteEmp(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(" Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry not found");
        }

    }


    @GetMapping("/sreach/{position}")
    public ResponseEntity EmpPosition(@PathVariable String position) {

        ArrayList<EmpMod> searching = empService.EmpPosition(position);

            return ResponseEntity.status(HttpStatus.OK).body(empService.EmpPosition(position));


        }

//        @GetMapping("/viewAge/{min}/{max}")
//public ResponseEntity getAgesInRange(@PathVariable int min,@PathVariable int max){
//
//        return  ResponseEntity.status(HttpStatus.OK).body(empService.getEmployeesByAgeRange(min,max));
//
//        }


    @GetMapping("/viewAge/{min}/{max}")
    public ResponseEntity<ArrayList<EmpMod>> getAgesInRange(@PathVariable int min, @PathVariable int max) {
        ArrayList<EmpMod> agesInRange = empService.getEmployeesByAgeRange(min, max);
        return ResponseEntity.status(HttpStatus.OK).body(agesInRange);
    }


    @PostMapping("/applyForLeave/{employeeId}")
    public ResponseEntity<String> applyForLeave(@PathVariable String employeeId) {
        try {
            empService.applyForLeave(employeeId);
            return ResponseEntity.status(HttpStatus.OK).body("Leave application successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @GetMapping("/noAnnualLeave")
    public ResponseEntity<ArrayList<EmpMod>> getEmployeesWithNoAnnualLeave() {
        ArrayList<EmpMod> employees = empService.getEmployeesWithNoAnnualLeave();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    public ResponseEntity<String> promoteEmployee(@PathVariable String employeeId, @PathVariable String requesterPosition) {
        try {
            empService.promoteEmployee(employeeId, requesterPosition);
            return ResponseEntity.status(HttpStatus.OK).body("Employee promoted successfully");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
