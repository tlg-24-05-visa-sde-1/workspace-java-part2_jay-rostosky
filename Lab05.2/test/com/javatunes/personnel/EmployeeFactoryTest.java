package com.javatunes.personnel;

import static org.junit.Assert.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class EmployeeFactoryTest {
    private Map<String,String> seMap;
    private Map<String,String> heMap;

    /**
     * client input request is a Map<String,String>, here's a sample
     * key        value
     * ---        -----
     * type       HE or SE
     * name       Jackie
     * hireDate   1990-08-24
     * 
     * salary     50000.00
     * OR
     * rate       50.0
     * hours      40.0
     * 
     * NOTES:
     * 1. yes, we have redundant setup code, and even some redundant test code (to keep it simple)
     * 2. this is representative of how UIs send user input data to an application:
     * - Java Swing UI components all return their input data as strings
     * - in webapps, HTTP requests from browsers (e.g., a form submission)
     *   carry all values as strings
     */
    @Before
    public void init() {
        seMap = new HashMap<>();
        seMap.put("type", "SE");
        seMap.put("name", "Jackie");
        seMap.put("hireDate", "1990-08-24");
        seMap.put("salary", "50000.0");

        heMap = new HashMap<>();
        heMap.put("type", "HE");
        heMap.put("name", "Jackie");
        heMap.put("hireDate", "1990-08-24");
        heMap.put("rate", "50.0");
        heMap.put("hours", "40.0");
    }

    /**
     * TASK: verify that passing seMap into your factory returns a SalariedEmployee,
     * with all properties set.
     * To check an object's type, you can use instanceof or check its Class object (preferred):
     * assertEquals(SalariedEmployee.class, emp.getClass())
     */
    @Test
    public void createEmployee_shouldReturnSalariedEmployee_whenTypeSE() {
        Employee emp = EmployeeFactory.createEmployee(seMap);

        // verity that 'emp' really is a reference to a SalariedEmployee object
        // instanceof
        // assertTrue(emp instanceof SalariedEmployee);        // IS-A match

        // Class object
        assertEquals(SalariedEmployee.class, emp.getClass());  // EXACT type match

        verifyNameAndHIreDate(emp);

        // downcast 'emp' to more specific reference type SalariedEmployee
        // this is because we need to call SalariedEmployee-specific method getSalary()
        SalariedEmployee semp = (SalariedEmployee) emp;
        assertEquals(50_000.0, semp.getSalary(), .001);
    }

    private static void verifyNameAndHIreDate(Employee emp) {
        assertEquals("Jackie", emp.getName());
        assertEquals(Date.valueOf("1990-08-24"), emp.getHireDate());
    }

    /**
     * TASK: verify that passing heMap into your factory returns a HourlyEmployee,
     * with all properties set.
     */
    @Test
    public void createEmployee_shouldReturnHourlyEmployee_whenTypeHE() {
        Employee emp = EmployeeFactory.createEmployee(heMap);

        // verify that 'emp' is a reference to an HourlyEmployee object
        assertEquals(HourlyEmployee.class, emp.getClass());

        verifyNameAndHIreDate(emp);

        // downcast 'emp' to new reference 'hemp' (type HourlyEmployee)
        // for HourlyEmployee-specific behavior, e.g., hemp.getRate()
        HourlyEmployee hemp = (HourlyEmployee) emp;
        assertEquals(50.0, hemp.getRate(), .001);
        assertEquals(40.0, hemp.getHours(), .001);
    }

    /**
     * TASK: verify that passing a map with an invalid "type" value results in
     * IllegalArgumentException.  The only valid values for "type" are "HE" or "SE".
     */
    @Test(expected=IllegalArgumentException.class)
    public void createEmployee_shouldThrowIllegalArgumentException_whenInvalidType() {
        seMap.put("type", "INVALID-TYPE");

        EmployeeFactory.createEmployee(seMap);   // trigger the exception
    }
}