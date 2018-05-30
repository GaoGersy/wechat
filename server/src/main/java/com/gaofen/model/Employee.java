package com.gaofen.model;

import java.math.BigDecimal;
import java.util.Date;

public class Employee {
    private String loginname;

    private String employeeNum;

    private String employeeName;

    private BigDecimal employeeAge;

    private String employeeSexual;

    private Date employeeBirthday;

    private String employeePhone;

    private String peoplesCode;

    private String employeeHometown;

    private String departmentCode;

    private String indentityCode;

    private Date employeeArrDate;

    private Date employeeWorkDate;

    private String occupationtitleCode;

    private Date occupationtitleTime;

    private String manageDutyCode;

    private Date employeeManageDutyDate;

    private String recordCode;

    private Date employeeRecordDate;

    private String degreeCode;

    private Date employeeDegreeDate;

    private Short isused;

    private String password;

    private Short parentid;

    private Short visitecount;

    private String empCheck;

    private String empCheckdetail;

    private String protocolfile;

    public Employee(String loginname, String employeeNum, String employeeName, BigDecimal employeeAge, String employeeSexual, Date employeeBirthday, String employeePhone, String peoplesCode, String employeeHometown, String departmentCode, String indentityCode, Date employeeArrDate, Date employeeWorkDate, String occupationtitleCode, Date occupationtitleTime, String manageDutyCode, Date employeeManageDutyDate, String recordCode, Date employeeRecordDate, String degreeCode, Date employeeDegreeDate, Short isused, String password, Short parentid, Short visitecount, String empCheck, String empCheckdetail, String protocolfile) {
        this.loginname = loginname;
        this.employeeNum = employeeNum;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeSexual = employeeSexual;
        this.employeeBirthday = employeeBirthday;
        this.employeePhone = employeePhone;
        this.peoplesCode = peoplesCode;
        this.employeeHometown = employeeHometown;
        this.departmentCode = departmentCode;
        this.indentityCode = indentityCode;
        this.employeeArrDate = employeeArrDate;
        this.employeeWorkDate = employeeWorkDate;
        this.occupationtitleCode = occupationtitleCode;
        this.occupationtitleTime = occupationtitleTime;
        this.manageDutyCode = manageDutyCode;
        this.employeeManageDutyDate = employeeManageDutyDate;
        this.recordCode = recordCode;
        this.employeeRecordDate = employeeRecordDate;
        this.degreeCode = degreeCode;
        this.employeeDegreeDate = employeeDegreeDate;
        this.isused = isused;
        this.password = password;
        this.parentid = parentid;
        this.visitecount = visitecount;
        this.empCheck = empCheck;
        this.empCheckdetail = empCheckdetail;
        this.protocolfile = protocolfile;
    }

    public Employee() {
        super();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum == null ? null : employeeNum.trim();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    public BigDecimal getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(BigDecimal employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeSexual() {
        return employeeSexual;
    }

    public void setEmployeeSexual(String employeeSexual) {
        this.employeeSexual = employeeSexual == null ? null : employeeSexual.trim();
    }

    public Date getEmployeeBirthday() {
        return employeeBirthday;
    }

    public void setEmployeeBirthday(Date employeeBirthday) {
        this.employeeBirthday = employeeBirthday;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone == null ? null : employeePhone.trim();
    }

    public String getPeoplesCode() {
        return peoplesCode;
    }

    public void setPeoplesCode(String peoplesCode) {
        this.peoplesCode = peoplesCode == null ? null : peoplesCode.trim();
    }

    public String getEmployeeHometown() {
        return employeeHometown;
    }

    public void setEmployeeHometown(String employeeHometown) {
        this.employeeHometown = employeeHometown == null ? null : employeeHometown.trim();
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }

    public String getIndentityCode() {
        return indentityCode;
    }

    public void setIndentityCode(String indentityCode) {
        this.indentityCode = indentityCode == null ? null : indentityCode.trim();
    }

    public Date getEmployeeArrDate() {
        return employeeArrDate;
    }

    public void setEmployeeArrDate(Date employeeArrDate) {
        this.employeeArrDate = employeeArrDate;
    }

    public Date getEmployeeWorkDate() {
        return employeeWorkDate;
    }

    public void setEmployeeWorkDate(Date employeeWorkDate) {
        this.employeeWorkDate = employeeWorkDate;
    }

    public String getOccupationtitleCode() {
        return occupationtitleCode;
    }

    public void setOccupationtitleCode(String occupationtitleCode) {
        this.occupationtitleCode = occupationtitleCode == null ? null : occupationtitleCode.trim();
    }

    public Date getOccupationtitleTime() {
        return occupationtitleTime;
    }

    public void setOccupationtitleTime(Date occupationtitleTime) {
        this.occupationtitleTime = occupationtitleTime;
    }

    public String getManageDutyCode() {
        return manageDutyCode;
    }

    public void setManageDutyCode(String manageDutyCode) {
        this.manageDutyCode = manageDutyCode == null ? null : manageDutyCode.trim();
    }

    public Date getEmployeeManageDutyDate() {
        return employeeManageDutyDate;
    }

    public void setEmployeeManageDutyDate(Date employeeManageDutyDate) {
        this.employeeManageDutyDate = employeeManageDutyDate;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode == null ? null : recordCode.trim();
    }

    public Date getEmployeeRecordDate() {
        return employeeRecordDate;
    }

    public void setEmployeeRecordDate(Date employeeRecordDate) {
        this.employeeRecordDate = employeeRecordDate;
    }

    public String getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode == null ? null : degreeCode.trim();
    }

    public Date getEmployeeDegreeDate() {
        return employeeDegreeDate;
    }

    public void setEmployeeDegreeDate(Date employeeDegreeDate) {
        this.employeeDegreeDate = employeeDegreeDate;
    }

    public Short getIsused() {
        return isused;
    }

    public void setIsused(Short isused) {
        this.isused = isused;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Short getParentid() {
        return parentid;
    }

    public void setParentid(Short parentid) {
        this.parentid = parentid;
    }

    public Short getVisitecount() {
        return visitecount;
    }

    public void setVisitecount(Short visitecount) {
        this.visitecount = visitecount;
    }

    public String getEmpCheck() {
        return empCheck;
    }

    public void setEmpCheck(String empCheck) {
        this.empCheck = empCheck == null ? null : empCheck.trim();
    }

    public String getEmpCheckdetail() {
        return empCheckdetail;
    }

    public void setEmpCheckdetail(String empCheckdetail) {
        this.empCheckdetail = empCheckdetail == null ? null : empCheckdetail.trim();
    }

    public String getProtocolfile() {
        return protocolfile;
    }

    public void setProtocolfile(String protocolfile) {
        this.protocolfile = protocolfile == null ? null : protocolfile.trim();
    }
}