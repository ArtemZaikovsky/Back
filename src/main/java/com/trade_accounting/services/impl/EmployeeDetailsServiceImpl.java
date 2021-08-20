package com.trade_accounting.services.impl;

import com.trade_accounting.models.Employee;
import com.trade_accounting.repositories.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String employeeEmail) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByEmail(employeeEmail);
        return employee.orElseThrow(() -> new UsernameNotFoundException(employeeEmail));
    }
}
