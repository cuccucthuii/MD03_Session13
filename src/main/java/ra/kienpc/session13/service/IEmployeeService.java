package ra.kienpc.session13.service;

import ra.kienpc.session13.entity.Employee;
import ra.kienpc.session13.entity.dto.EmployeeCreateDTO;
import ra.kienpc.session13.entity.dto.EmployeeUpdateDTO;

public interface IEmployeeService {
    Employee create(EmployeeCreateDTO dto);

    Employee update(Long id, EmployeeUpdateDTO dto);
}
