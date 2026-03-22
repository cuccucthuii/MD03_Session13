package ra.kienpc.session13.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.kienpc.session13.entity.Employee;
import ra.kienpc.session13.entity.dto.EmployeeCreateDTO;
import ra.kienpc.session13.entity.dto.EmployeeUpdateDTO;
import ra.kienpc.session13.service.IEmployeeService;

@RestController
@RequestMapping("/api.kienpc.com/v1/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Employee> createEmployee(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam(required = false) String department,
            @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile) {
        EmployeeCreateDTO dto = new EmployeeCreateDTO(fullName, email, department, avatarFile);
        Employee employee = employeeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @ModelAttribute EmployeeUpdateDTO dto) {
        Employee employee = employeeService.update(id, dto);
        return ResponseEntity.ok(employee);
    }
}
