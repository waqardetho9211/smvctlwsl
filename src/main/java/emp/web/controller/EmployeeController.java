package emp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import emp.web.dto.EmployeeDto;
import emp.web.dto.input.NewEmpInput;
import emp.web.service.EmpService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmpService empService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmp(@RequestBody NewEmpInput emp) {
        EmployeeDto employeeDto = empService.addEmp(objectMapper.convertValue(emp, EmployeeDto.class));
//        LOG.info("Add Employee: " + employeeDto);
        return ResponseEntity.ok(employeeDto);
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
//        LOG.info("Get All Employees: " + empService.getAllEmployees());
        System.out.println(getPrincipal());
        return ResponseEntity.ok(empService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmp(@PathVariable("id") Long id) {
//        LOG.info("Get Employee: " + empService.getEmpById(id));
        return ResponseEntity.ok(empService.getEmpById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEmp(@PathVariable("id") Long id) {
//        LOG.info("Remove Employee: " + empService.getEmpById(id));
        empService.removeEmp(id);
        return ResponseEntity.ok(String.format("Employee with ID %s removed", id));
    }

    private String getPrincipal(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}