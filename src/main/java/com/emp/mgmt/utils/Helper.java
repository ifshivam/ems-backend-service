package com.emp.mgmt.utils;

import com.emp.mgmt.co_dto.dto.AbstractEmployeeDTO;
import com.emp.mgmt.constants.AppResponse;
import com.emp.mgmt.exception.GlobalException;
import com.emp.mgmt.pojos.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Helper {


    /**
     * Method to Filter the Result according to given parameters.
     * @param max       maximum records on a page
     * @param offset    page number
     * @param order     order of records ( ascending or descending )
     * @param sort      sort page based on this param
     */
    public Pageable filterResultPageable(Integer max, Integer offset, String sort, String order) {
        if (max == null) {
            max = 10;
        }
        if (offset == null) {
            offset = 0;
        }
        if (sort == null) {
            sort = "id";
        }
        if (order == null) {
            order = "asc";
        }
        Pageable pageable = null;

        if (order.toLowerCase().contains("desc")) {
            pageable = PageRequest.of(offset, max, Sort.by(sort).descending());
        } else {
            pageable = PageRequest.of(offset, max, Sort.by(sort).ascending());
        }
        return pageable;
    }

    public List<AbstractEmployeeDTO> convertToAbstractEmployeeDTOList(List<Employee> employeeList) {

        List<AbstractEmployeeDTO> abstractEmployeeDTOS = new ArrayList<>();

        for (Employee emp: employeeList) {
            AbstractEmployeeDTO dto = new AbstractEmployeeDTO();
            dto.setName(emp.getUser().getName());
            dto.setContact(emp.getUser().getContact());
            dto.setDepartment(emp.getDepartment());
            dto.setEmail(emp.getUser().getEmail());
            dto.setId(emp.getId());
            dto.setExperienceInYears(emp.getExperienceInYears());

            abstractEmployeeDTOS.add(dto);
        }

        return abstractEmployeeDTOS;
    }

    public void isCurrentUser(String email) throws GlobalException {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (!currentUserEmail.equals(email)){
            throw new GlobalException(AppResponse.RESTRICTED);
        }
    }
}
