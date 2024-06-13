package com.mycompany.qrscanner.controller;

import com.google.zxing.WriterException;
import com.mycompany.qrscanner.model.Employee;
import com.mycompany.qrscanner.service.EmployeeService;
import com.mycompany.qrscanner.util.QRCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() throws IOException, WriterException {
        List<Employee> employees = employeeService.getEmployees();
        if (employees.size() != 0){
            for (Employee employee : employees){
                QRCodeGenerator.generateQRCode(employee);
            }
        }

        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/id")
    public Employee findById(@PathVariable("id") Long id){
        return employeeService.findById(id);
    }
}
