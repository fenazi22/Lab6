package com.example.employeemanagementsystem.Service;

import com.example.employeemanagementsystem.Model.EmpMod;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class EmpService {

    ArrayList<EmpMod> empModArrayList = new ArrayList<>();

    public ArrayList<EmpMod> getEmp() {
        return empModArrayList;
    }

    public void addEmp(EmpMod empMod) {
        empModArrayList.add(empMod);
    }

    public boolean updateEmp(String id, EmpMod empMod) {
        for (int i = 0; i < empModArrayList.size(); i++) {
            if (empModArrayList.get(i).getId().equals(id)) {
                empModArrayList.set(i, empMod);
                return true;
            }
        }
        return false;
    }


    public boolean deleteEmp(String id) {
        for (int i = 0; i < empModArrayList.size(); i++) {
            if (empModArrayList.get(i).getId().equals(id)) {
                empModArrayList.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<EmpMod> EmpPosition(String position) {
        ArrayList<EmpMod> EmpPos = new ArrayList<>();
        if (position.equalsIgnoreCase("supervisor") || position.equalsIgnoreCase("coordinator")) {
            for (EmpMod searchPoition : empModArrayList) {
                if (searchPoition.getPosition().equalsIgnoreCase(position))
                    EmpPos.add(searchPoition);
            }
        }

        return EmpPos;
    }


    public ArrayList<EmpMod> getEmployeesByAgeRange(int minAge, int maxAge) {
        ArrayList<EmpMod> agesInRange = new ArrayList<>();
        for (EmpMod employee : empModArrayList) {
            if (employee.getAge() >= minAge && employee.getAge() <= maxAge) {
                agesInRange.add(employee);
            }
        }
        return agesInRange;
    }


    public void applyForLeave(String employeeId) {
        EmpMod employee = null;
        for (EmpMod emp : empModArrayList) {
            if (emp.getId().equals(employeeId)) {
                employee = emp;
                break;
            }
        }

        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        if (employee.isOnLeave()) {
            throw new IllegalStateException("Employee is already on leave");
        }

        if (employee.getAnnualLeave() <= 0) {
            throw new IllegalStateException("Employee has no annual leave remaining");
        }

        employee.setOnLeave(true);

        employee.setAnnualLeave(employee.getAnnualLeave() - 1);
    }

    public ArrayList<EmpMod> getEmployeesWithNoAnnualLeave() {
        return empModArrayList.stream()
                .filter(employee -> employee.getAnnualLeave() == 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public void promoteEmployee(String employeeId, String requesterPosition) {
        EmpMod employee = null;
        for (EmpMod emp : empModArrayList) {
            if (emp.getId().equals(employeeId)) {
                employee = emp;
                break;
            }
        }

        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        if (!"supervisor".equalsIgnoreCase(requesterPosition)) {
            throw new IllegalStateException("Requester is not a supervisor");
        }

        if (employee.getAge() < 30) {
            throw new IllegalStateException("Employee's age is less than 30 years");
        }

        if (employee.isOnLeave()) {
            throw new IllegalStateException("Employee is currently on leave");
        }

        employee.setPosition("supervisor");


    }




}

//        for (int i = 0; i < empModArrayList.size(); i++) {
//            if (empModArrayList.get(i).getPosition().equals(position)) {
//                if (position.equalsIgnoreCase("supervisor"))
//                    EmpPos.add(i,position);
//            }
//        }
//        return EmpPos;


//        for (EmpMod srarch:empModArrayList) {
//
//        //}
////            if (srarch.getPosition().equalsIgnoreCase(position));
////                if (position.equals("supervisor"))
////                    EmpPos.get()
////        }









