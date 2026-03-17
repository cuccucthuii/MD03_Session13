package ra.kienpc.session13.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.kienpc.session13.entity.Employee;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api.kienpc.com/v1/employees")
public class EnployeeController {

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"Nguyen Van A", 1200));
        list.add(new Employee(2,"Nguyen Van B", 1300));
        list.add(new Employee(3,"Nguyen Van C", 1400));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
