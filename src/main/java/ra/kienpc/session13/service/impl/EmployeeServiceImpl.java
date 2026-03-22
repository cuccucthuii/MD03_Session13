package ra.kienpc.session13.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.kienpc.session13.entity.Employee;
import ra.kienpc.session13.entity.dto.EmployeeCreateDTO;
import ra.kienpc.session13.entity.dto.EmployeeUpdateDTO;
import ra.kienpc.session13.repository.EmployeeRepository;
import ra.kienpc.session13.service.IEmployeeService;
import ra.kienpc.session13.service.IUploadService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private static final String AVATAR_FOLDER = "employees";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IUploadService uploadService;

    @Override
    public Employee create(EmployeeCreateDTO dto) {
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại: " + dto.getEmail());
        }

        String avatarUrl = null;
        if (dto.getAvatarFile() != null && !dto.getAvatarFile().isEmpty()) {
            avatarUrl = uploadService.uploadFile(dto.getAvatarFile(), AVATAR_FOLDER);
        }

        Employee employee = Employee.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .department(dto.getDepartment())
                .avatarUrl(avatarUrl)
                .build();

        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, EmployeeUpdateDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên với ID: " + id));

        if (employeeRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new IllegalArgumentException("Email đã tồn tại: " + dto.getEmail());
        }

        if (dto.getAvatarFile() != null && !dto.getAvatarFile().isEmpty()) {
            String newAvatarUrl = uploadService.uploadFile(dto.getAvatarFile(), AVATAR_FOLDER);
            employee.setAvatarUrl(newAvatarUrl);
        }

        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());

        return employeeRepository.save(employee);
    }
}
