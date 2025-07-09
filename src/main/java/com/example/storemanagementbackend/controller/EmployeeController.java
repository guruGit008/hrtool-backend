// package com.example.storemanagementbackend.controller;
 
// import com.example.storemanagementbackend.model.Employee;
// import com.example.storemanagementbackend.service.EmployeeService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.example.storemanagementbackend.service.FileStorageService;
 
// import java.time.LocalDate;
// import java.util.List;
// import java.util.Map;
// import java.util.NoSuchElementException;
// import java.util.Optional;
// import com.example.storemanagementbackend.dto.EmployeeRegistrationRequest;
 
// @RestController // Marks this class as a REST controller
// @RequestMapping("/api/employees") // Base path for all endpoints in this controller
// @CrossOrigin(origins = "https://hrtool-frontend-two.vercel.app") // Allows requests from the specified origin (for frontend development)
// public class EmployeeController {
 
//     private final EmployeeService employeeService;
//     private final FileStorageService fileStorageService;
//     private final ObjectMapper objectMapper;
 
//     @Autowired // Injects the dependencies
//     public EmployeeController(EmployeeService employeeService, FileStorageService fileStorageService, ObjectMapper objectMapper) {
//         this.employeeService = employeeService;
//         this.fileStorageService = fileStorageService;
//         this.objectMapper = objectMapper;
//     }
 
//     /**
//      * Endpoint to create a new employee.
//      * @param employee The employee object to create.
//      * @return ResponseEntity with the created employee and HTTP status.
//      */
//     @PostMapping // Handles HTTP POST requests to /api/employees
//     public ResponseEntity<Employee> createEmployee(@RequestParam("employee") String employeeStr, @RequestParam(name= "photo", required = false) MultipartFile photo) {
//         // @RequestBody maps the JSON request body to the Employee object
//         Employee employee;
//         try {
//             employee = objectMapper.readValue(employeeStr, Employee.class);
//         } catch (Exception e) {
//             e.printStackTrace();
//             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//         }

//         if (photo != null && !photo.isEmpty()) {
//             String fileName = fileStorageService.storeFile(photo);
//             String fileDownloadUri = "/api/employees/download/" + fileName;
//             employee.setProfilePhotoUrl(fileDownloadUri);
//         }

//         Employee createdEmployee = employeeService.createEmployee(employee);
//         return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED); // Returns 201 Created status
//     }
 
//     /**
//      * Endpoint to get an employee by their internal ID.
//      * @param id The internal ID of the employee.
//      * @return ResponseEntity with the employee and HTTP status.
//      */
//     @GetMapping("/{id}") // Handles HTTP GET requests to /api/employees/{id}
//     public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
//         // @PathVariable extracts the ID from the URL path
//         try {
//             Employee employee = employeeService.getEmployeeById(id);
//             return new ResponseEntity<>(employee, HttpStatus.OK); // Returns 200 OK status
//         } catch (NoSuchElementException e) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found if employee doesn't exist
//         }
//     }
 
//     /**
//      * Endpoint to get an employee by their employee ID (e.g., EMP001).
//      * @param employeeId The unique employee ID.
//      * @return ResponseEntity with the employee and HTTP status.
//      */
//     @GetMapping("/byEmployeeId/{employeeId}") // Handles HTTP GET requests to /api/employees/byEmployeeId/{employeeId}
//     public ResponseEntity<Employee> getEmployeeByEmployeeId(@PathVariable String employeeId) {
//         try {
//             Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
//             return new ResponseEntity<>(employee, HttpStatus.OK);
//         } catch (NoSuchElementException e) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }
 
//     /**
//      * Endpoint to get all employee records.
//      * @return ResponseEntity with a list of all employees and HTTP status.
//      */
//     @GetMapping // Handles HTTP GET requests to /api/employees
//     public ResponseEntity<List<Employee>> getAllEmployees() {
//         List<Employee> employees = employeeService.getAllEmployees();
//         return new ResponseEntity<>(employees, HttpStatus.OK);
//     }
 
//     /**
//      * Endpoint to update an existing employee record.
//      * @param id The internal ID of the employee to update.
//      * @param employeeStr The updated employee details as JSON string.
//      * @param photo Optional updated profile photo.
//      * @return ResponseEntity with the updated employee and HTTP status.
//      */
//     @PutMapping("/{id}") // Handles HTTP PUT requests to /api/employees/{id}
//     public ResponseEntity<Employee> updateEmployee(
//             @PathVariable Long id,
//             @RequestParam("employee") String employeeStr,
//             @RequestParam(name = "photo", required = false) MultipartFile photo) {
//         try {
//             Employee employeeDetails = objectMapper.readValue(employeeStr, Employee.class);
            
//             if (photo != null && !photo.isEmpty()) {
//                 String fileName = fileStorageService.storeFile(photo);
//                 String fileDownloadUri = "/api/employees/download/" + fileName;
//                 employeeDetails.setProfilePhotoUrl(fileDownloadUri);
//             }
            
//             Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
//             return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
//         } catch (Exception e) {
//             e.printStackTrace();
//             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//         }
//     }
 
//     /**
//      * Endpoint to delete an employee record.
//      * @param id The internal ID of the employee to delete.
//      * @return ResponseEntity with HTTP status.
//      */
//     @DeleteMapping("/{id}") // Handles HTTP DELETE requests to /api/employees/{id}
//     public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
//         try {
//             employeeService.deleteEmployee(id);
//             return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content (successful deletion)
//         } catch (NoSuchElementException e) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found
//         }
//     }
 
//     /**
//      * Endpoint to update an employee's status.
//      * @param id The internal ID of the employee to update.
//      * @param status The new status for the employee.
//      * @return ResponseEntity with the updated employee and HTTP status.
//      */
//     @PutMapping("/{id}/status") // Handles HTTP PUT requests to /api/employees/{id}/status
//     public ResponseEntity<Employee> updateEmployeeStatus(@PathVariable Long id, @RequestParam String status) {
//         try {
//             Employee updatedEmployee = employeeService.updateEmployeeStatus(id, status);
//             return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
//         } catch (NoSuchElementException e) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }
 
//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
//         String email = loginData.get("email");
//         String password = loginData.get("password");
//         Optional<Employee> employeeOpt = employeeService.getAllEmployees().stream()
//             .filter(emp -> emp.getEmail().equals(email) && emp.getPassword().equals(password))
//             .findFirst();
//         if (employeeOpt.isPresent()) {
//             return ResponseEntity.ok(employeeOpt.get());
//         } else {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//         }
//     }
 
//     /**
//      * Endpoint to register a new employee account.
//      * Accepts employeeName, employeeId, email, password, phoneNumber, and roles (as ["EMPLOYEE"]).
//      * Uses the first role in the list for the Employee entity.
//      * @param request The registration request DTO.
//      * @return ResponseEntity with the created employee and HTTP status.
//      */
//     @PostMapping("/register")
//     public ResponseEntity<Employee> registerEmployee(@RequestBody com.example.storemanagementbackend.dto.RegisterRequest request) {
//         Employee employee = new Employee();
//         employee.setEmployeeName(request.getFullName() != null ? request.getFullName() : request.getUsername());
//         employee.setEmployeeId(request.getUsername()); // Assuming username is used as employeeId
//         employee.setEmail(request.getEmail());
//         employee.setPassword(request.getPassword());
//         employee.setPhoneNumber(null); // RegisterRequest does not have phoneNumber, set to null or extend DTO if needed
//         // Set role if Employee model supports it, otherwise ignore or extend Employee model
//         // employee.setRole(request.getRoles() != null && !request.getRoles().isEmpty() ? request.getRoles().get(0) : null);
//         Employee createdEmployee = employeeService.createEmployee(employee);
//         return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
//     }
 
//     /**
//      * HR endpoint to register a new employee account with all fields.
//      * @param request The registration request DTO.
//      * @return ResponseEntity with the created employee and HTTP status.
//      */
//     @PostMapping("/hr/register")
//     public ResponseEntity<Employee> hrRegisterEmployee(@RequestBody EmployeeRegistrationRequest request) {
//         Employee employee = new Employee();
//         employee.setEmployeeName(request.getEmployeeName());
//         employee.setEmployeeId(request.getEmployeeId());
//         employee.setEmail(request.getEmail());
//         employee.setPassword(request.getPassword());
//         employee.setPhoneNumber(request.getPhoneNumber());
//         // If Employee has a role field, set it here:
//         // employee.setRole(request.getRoles() != null && !request.getRoles().isEmpty() ? request.getRoles().get(0) : null);
//         Employee createdEmployee = employeeService.createEmployee(employee);
//         return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
//     }

//     @GetMapping("/download/{fileName:.+}")
//     public ResponseEntity<org.springframework.core.io.Resource> downloadFile(@PathVariable String fileName) {
//         org.springframework.core.io.Resource resource = fileStorageService.loadFileAsResource(fileName);
//         return ResponseEntity.ok()
//                 .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                 .body(resource);
//     }
// }
 
 
 package com.example.storemanagementbackend.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.storemanagementbackend.dto.EmployeeRegistrationRequest;
import com.example.storemanagementbackend.dto.RegisterRequest;
import com.example.storemanagementbackend.model.Employee;
import com.example.storemanagementbackend.service.EmployeeService;
import com.example.storemanagementbackend.service.FileStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "https://hrtool-frontend-two.vercel.app")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final FileStorageService fileStorageService;
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, FileStorageService fileStorageService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.fileStorageService = fileStorageService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestParam("employee") String employeeStr, @RequestParam(name = "photo", required = false) MultipartFile photo) {
        Employee employee;
        try {
            employee = objectMapper.readValue(employeeStr, Employee.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (photo != null && !photo.isEmpty()) {
            String fileName = fileStorageService.storeFile(photo);
            String fileDownloadUri = "/api/employees/download/" + fileName;
            employee.setProfilePhotoUrl(fileDownloadUri);
        }

        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byEmployeeId/{employeeId}")
    public ResponseEntity<Employee> getEmployeeByEmployeeId(@PathVariable String employeeId) {
        try {
            Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestParam("employee") String employeeStr,
            @RequestParam(name = "photo", required = false) MultipartFile photo) {
        try {
            Employee employeeDetails = objectMapper.readValue(employeeStr, Employee.class);

            if (photo != null && !photo.isEmpty()) {
                String fileName = fileStorageService.storeFile(photo);
                String fileDownloadUri = "/api/employees/download/" + fileName;
                employeeDetails.setProfilePhotoUrl(fileDownloadUri);
            }

            Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Employee> updateEmployeeStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Employee updatedEmployee = employeeService.updateEmployeeStatus(id, status);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");
        Optional<Employee> employeeOpt = employeeService.getAllEmployees().stream()
                .filter(emp -> emp.getEmail() != null && emp.getEmail().equals(email)
                        && emp.getPassword() != null && emp.getPassword().equals(password))
                .findFirst();
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employee.setPassword(null); // Hide password in response
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    /**
     * ✅ Fixed: Register a new employee with role from RegisterRequest
     */
    // @PostMapping("/register")
    // public ResponseEntity<Employee> registerEmployee(@RequestBody RegisterRequest request) {
    //     Employee employee = new Employee();
    //     employee.setEmployeeName(request.getFullName() != null ? request.getFullName() : request.getUsername());
    //     employee.setEmployeeId(request.getUsername());
    //     employee.setEmail(request.getEmail());
    //     employee.setPassword(request.getPassword());
    //     employee.setPhoneNumber(null);
    //     employee.setCurrentAddress(null);
    //     employee.setPermanentAddress(null);
    //     employee.setBloodGroup(null);
    //     employee.setPosition(null);
    //     employee.setDepartment(null);
    //     employee.setJoiningDate(null);
    //     employee.setRelievingDate(null);
    //     employee.setStatus("Active");
    //     // Removed setRole as Employee does not have a role field
    //     Employee createdEmployee = employeeService.createEmployee(employee);
    //     return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    // }
    @PostMapping("/register")
public ResponseEntity<Employee> rregisterEmployee(@RequestBody RegisterRequest request) {
    Employee employee = new Employee();
    employee.setEmployeeName(request.getFullName() != null ? request.getFullName() : request.getUsername());
    employee.setEmployeeId(request.getUsername());
    employee.setEmail(request.getEmail());
    employee.setPassword(request.getPassword());
    employee.setPhoneNumber(null);
    employee.setCurrentAddress(null);
    employee.setPermanentAddress(null);
    employee.setBloodGroup(null);
    employee.setPosition(null);
    employee.setDepartment(null);
    employee.setJoiningDate(null);
    employee.setRelievingDate(null);
    employee.setStatus("Active");

    // ✅ This is critical
    employee.setRole(
        (request.getRoles() != null && !request.getRoles().isEmpty())
            ? request.getRoles().get(0)
            : "EMPLOYEE"
    );

    System.out.println("Creating employee with role: " + employee.getRole()); // Debug line

    Employee createdEmployee = employeeService.createEmployee(employee);
    return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
}


    @PostMapping("/hr/register")
    public ResponseEntity<Employee> hrRegisterEmployee(@RequestBody EmployeeRegistrationRequest request) {
        Employee employee = new Employee();
        employee.setEmployeeName(request.getEmployeeName());
        employee.setEmployeeId(request.getEmployeeId());
        employee.setEmail(request.getEmail());
        employee.setPassword(request.getPassword());
        employee.setPhoneNumber(request.getPhoneNumber());
        // Removed all fields that do not exist in EmployeeRegistrationRequest
        employee.setCurrentAddress(null);
        employee.setPermanentAddress(null);
        employee.setBloodGroup(null);
        employee.setPosition(null);
        employee.setDepartment(null);
        employee.setJoiningDate(null);
        employee.setRelievingDate(null);
        employee.setStatus("Active");
        // Removed setRole as Employee does not have a role field
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<org.springframework.core.io.Resource> downloadFile(@PathVariable String fileName) {
        org.springframework.core.io.Resource resource = fileStorageService.loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}