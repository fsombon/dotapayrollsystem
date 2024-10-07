package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Payroll {
    private static final DecimalFormat decfor = new DecimalFormat("0.00");
    private int totalDays = 0;//
    private double salratedaily = 0.0;//
    private double basicSalary = 0.0;//
    private double allowanceAmount = 0.0;
    private double nightDiffHours = 0.0; //
    private double nightDiffAmount = 0.0; 
    private double specialHolidaydays = 0.0;//
    private double specialHolidayAmount = 0.0;
    private double SHNDhours = 0.0;//
    private double SHNDAmount = 0.0;
    private double LegalHolidayDays = 0.0;//
    private double LegalHolidayAmount = 0.0;
    private double LHNDhours = 0.0;//
    private double LHNDamount = 0.0;
    private double OThours = 0.0;//
    private double OTamount = 0.0;
    private double OTNDhours = 0.0;//
    private double OTNDamount = 0.0;
    private double leaveothersamount = 0.0;
    private double adjustmentamount = 0.0;
    private double otherdeductionamount = 0.0;
    private double lateundertimemins = 0.0;//
    private double lateundertimerate = 0.0;//
    private double lateundertimeamount = 0.0;
    private double SSSamount = 0.0;
    private double philhealthamount = 0.0;
    private double hdmfamount = 0.0;
    private double totaldeductions = 0.0;
   
    private String date = " ";
    private String fromcutoffdate = " ";
    private String tocutoffdate = " ";
    
    
    
    SQLRun objSQLRun = new SQLRun();
    public Employee objEmployee = new Employee();
    public Leave objLeave = new Leave();
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getfromcutoffdate() {
        return fromcutoffdate;
    }

    public void setfromcutoffdate(String fromcutoffdate) {
        this.fromcutoffdate = fromcutoffdate;
    }
    public String gettocutoffdate() {
        return tocutoffdate;
    }

    public void settocutoffdate(String tocutoffdate) {
        this.tocutoffdate = tocutoffdate;
    }
    public int gettotalDays() {
        return totalDays;
    }

    public void settotalDays(int totalDays) {
        this.totalDays = totalDays;
    }
    
    public double getsalratedaily() {
        return salratedaily;
    }

    public void setsalratedaily(double salratedaily) {
        this.salratedaily = salratedaily;
    }
    
    public double getbasicSalary() {
        return basicSalary;
    }

    public void setbasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }
    
    public double getallowanceAmount() {
        return allowanceAmount;
    }

    public void setallowanceAmount(double allowanceAmount) {
        this.allowanceAmount = allowanceAmount;
    }

    public double getnightDiffHours() {
        return nightDiffHours;
    }

    public void setnightDiffHours(double nightDiffHours) {
        this.nightDiffHours = nightDiffHours;
    }
    
    public double getnightDiffAmount() {
        return nightDiffAmount;
    }

    public void setnightDiffAmount(double nightDiffAmount) {
        this.nightDiffAmount = nightDiffAmount;
    }

    public double getspecialHolidaydays() {
        return specialHolidaydays;
    }

    public void setspecialHolidaydays(double specialHolidaydays) {
        this.specialHolidaydays = specialHolidaydays;
    }
    
    public double getspecialHolidayAmount() {
        return specialHolidayAmount;
    }

    public void setspecialHolidayAmount(double specialHolidayAmount) {
        this.specialHolidayAmount = specialHolidayAmount;
    }

    public double getSHNDhours() {
        return SHNDhours;
    }

    public void setSHNDhours(double SHNDhours) {
        this.SHNDhours = SHNDhours;
    }
    
    public double getSHNDAmount() {
        return SHNDAmount;
    }

    public void setSHNDAmount(double SHNDAmount) {
        this.SHNDAmount = SHNDAmount;
    }

    public double getLegalHolidayDays() {
        return LegalHolidayDays;
    }

    public void setLegalHolidayDays(double LegalHolidayDays) {
        this.LegalHolidayDays = LegalHolidayDays;
    }
    
    public double getLegalHolidayAmount() {
        return LegalHolidayAmount;
    }

    public void setLegalHolidayAmount(double LegalHolidayAmount) {
        this.LegalHolidayAmount = LegalHolidayAmount;
    }

    public double getLHNDhours() {
        return LHNDhours;
    }

    public void setLHNDhours(double LHNDhours) {
        this.LHNDhours = LHNDhours;
    }
    
    public double getLHNDamount() {
        return LHNDamount;
    }

    public void setLHNDamount(double LHNDamount) {
        this.LHNDamount = LHNDamount;
    }
    
    public double getOThours() {
        return OThours;
    }

    public void setOThours(double OThours) {
        this.OThours = OThours;
    }
    
    public double getOTamount() {
        return OTamount;
    }

    public void setOTamount(double OTamount) {
        this.OTamount = OTamount;
    }
    
    public double getOTNDhours() {
        return OTNDhours;
    }

    public void setOTNDhours(double OTNDhours) {
        this.OTNDhours = OTNDhours;
    }
    
    public double getOTNDamount() {
        return OTNDamount;
    }

    public void setOTNDamount(double OTNDamount) {
        this.OTNDamount = OTNDamount;
    }
    
    public double getleaveothersamount() {
        return leaveothersamount;
    }

    public void setleaveothersamount(double leaveothersamount) {
        this.leaveothersamount = leaveothersamount;
    }
    
    public double getadjustmentamount() {
        return adjustmentamount;
    }

    public void setadjustmentamount(double adjustmentamount) {
        this.adjustmentamount = adjustmentamount;
    }
    
    public double getotherdeductionamount() {
        return otherdeductionamount;
    }
    
    public void setotherdeductionamount(double otherdeductionamount) {
        this.otherdeductionamount = otherdeductionamount;
    }
    
    public double getlateundertimemins() {
        return lateundertimemins;
    }

    public void setlateundertimemins(double lateundertimemins) {
        this.lateundertimemins = lateundertimemins;
    }
    
    public double getlateundertimerate() {
        return lateundertimerate;
    }

    public void setlateundertimerate(double lateundertimerate) {
        this.lateundertimerate = lateundertimerate;
    }
    
    public double getlateundertimeamount() {
        return lateundertimeamount;
    }

    public void setlateundertimeamount(double lateundertimeamount) {
        this.lateundertimeamount = lateundertimeamount;
    }
    
    public double getSSSamount() {
        return SSSamount;
    }

    public void setSSSamount(double SSSamount) {
        this.SSSamount = SSSamount;
    }
    
    public double getphilhealthamount() {
        return philhealthamount;
    }

    public void setphilhealthamount(double philhealthamount) {
        this.philhealthamount = philhealthamount;
    }
    
    public double gethdmfamount() {
        return hdmfamount;
    }

    public void sethdmfamount(double hdmfamount) {
        this.hdmfamount = hdmfamount;
    }
    
    public double getTotalDeductions() {
        return totaldeductions;
    }

    public void setTotalDeductions(double totaldeductions) {
        this.totaldeductions = totaldeductions;
    }
    
  
    //get salary details - if no details found, return 0
    public boolean getSalaryDetails(String empId) {
        try {

            String sql = "SELECT * FROM employee e,salary_details s WHERE e.empId=" + empId + " AND e.empId=s.empId";
            String sql2 = "SELECT * FROM employee WHERE empId=" + empId;
            ResultSet resSet = objSQLRun.sqlQuery(sql);
            ResultSet resSet2 = objSQLRun.sqlQuery(sql2);

            if (resSet.next()) {
                objEmployee.setEmpId(Integer.parseInt(empId));
                objEmployee.setFname(resSet.getString("fname"));
                objEmployee.setLname(resSet.getString("lname"));
                objEmployee.setDesignation(resSet.getString("designation"));
                objEmployee.setDepartment(resSet.getString("department"));
                objLeave.setVacationleave(resSet.getDouble("vacation_leave"));
                objLeave.setSickleave(resSet.getDouble("sick_leave"));
                //objEmployee.setSalType(resSet.getString("salType"));
                this.totalDays = resSet.getInt("totalDays");//
                this.salratedaily = resSet.getDouble("SalRateDaily");//
                this.basicSalary = resSet.getDouble("basicSalary");//
                this.allowanceAmount = resSet.getDouble("Allowance");
                this.nightDiffHours = resSet.getDouble("nightDiffhours");//
                this.nightDiffAmount = resSet.getDouble("nightDiffamount");
                this.specialHolidaydays = resSet.getDouble("specialHolidaydays");
                this.specialHolidayAmount = resSet.getDouble("specialHolidayamount");
                this.SHNDhours = resSet.getDouble("SHNDhours");//
                this.SHNDAmount = resSet.getDouble("SHNDamount");
                this.LegalHolidayDays = resSet.getDouble("legalHolidaydays");//
                this.LegalHolidayAmount = resSet.getDouble("legalHolidayamount");
                this.LHNDhours = resSet.getDouble("LHNDhours");//
                this.LHNDamount = resSet.getDouble("LHNDamount");
                this.OThours = resSet.getDouble("Overtimehours");//
                this.OTamount = resSet.getDouble("Overtimeamount");
                this.OTNDhours = resSet.getDouble("OTnightdiffhours");//
                this.OTNDamount = resSet.getDouble("OTnightdiffamount");
                this.leaveothersamount = resSet.getDouble("LeaveOthers");
                this.adjustmentamount = resSet.getDouble("Adjustment");
                this.lateundertimemins = resSet.getDouble("lateundertimemins");//
                this.lateundertimerate = resSet.getDouble("lateundertimerate");//
                this.lateundertimeamount = resSet.getDouble("lateundertimeamount");
                this.SSSamount = resSet.getDouble("SSSamount");
                this.philhealthamount = resSet.getDouble("PhilHealthamount");
                this.hdmfamount = resSet.getDouble("HDMFamount");
                this.otherdeductionamount = resSet.getDouble("OtherDeduction");
                this.date = String.valueOf(resSet.getDate("date"));
                this.fromcutoffdate = String.valueOf(resSet.getDate("fromcutoffdate"));
                this.tocutoffdate = String.valueOf(resSet.getDate("tocutoffdate"));
                this.totaldeductions = resSet.getDouble("total_deductions");

                return true;

            } else if (resSet2.next()) {                
                objEmployee.setEmpId(Integer.parseInt(empId));
                objEmployee.setFname(resSet2.getString("fname"));
                objEmployee.setLname(resSet2.getString("lname"));
                objEmployee.setDesignation(resSet2.getString("designation"));
                objEmployee.setDepartment(resSet2.getString("department"));
                objLeave.setVacationleave(resSet2.getDouble("vacation_leave"));
                objLeave.setSickleave(resSet2.getDouble("sick_leave"));
                //objLeave.setVacationleave(resSet.getDouble("vacation_leave"));
                //objLeave.setSickleave(resSet.getDouble("sick_leave"));
                //objLeave.setVacationleave(0.0);
                //objLeave.setSickleave(0.0);
                //objEmployee.setSalType(resSet2.getString("salType"));
                this.date = " ";
                this.fromcutoffdate = " ";
                this.tocutoffdate = " ";
                this.totalDays = 0;//
                this.salratedaily = 0.0;//
                this.basicSalary = 0.0;//
                this.allowanceAmount = 0.0;
                this.nightDiffHours = 0.0; //
                this.nightDiffAmount = 0.0;
                this.specialHolidaydays = 0.0;//
                this.specialHolidayAmount = 0.0;
                this.SHNDhours = 0.0;//
                this.SHNDAmount = 0.0;
                this.LegalHolidayDays = 0.0;//
                this.LegalHolidayAmount = 0.0;
                this.LHNDhours = 0.0;//
                this.LHNDamount = 0.0;
                this.OThours = 0.0;//
                this.OTamount = 0.0;
                this.OTNDhours = 0.0;//
                this.OTNDamount = 0.0;
                this.leaveothersamount = 0.0;
                this.adjustmentamount = 0.0;  
                this.lateundertimemins = 0.0;//
                this.lateundertimerate = 0.0;//
                this.lateundertimeamount = 0.0;
                this.SSSamount = 0.0;
                this.philhealthamount = 0.0;
                this.hdmfamount = 0.0;
                this.otherdeductionamount = 0.0;
                this.totaldeductions = 0.0;
                
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No record found for Employee ID : " + empId, "ERROR", 0);
                return false;
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);
            return false;

        }
    }
    public boolean getSalaryDetailsViaDate(String empId, String date) {
        try {

            String sql = "SELECT * FROM employee e,salary_details s WHERE e.empId=" + empId + " AND e.empId=s.empId AND date = '" + date + "'";
            String sql2 = "SELECT * FROM employee WHERE empId=" + empId;
            ResultSet resSet = objSQLRun.sqlQuery(sql);
            ResultSet resSet2 = objSQLRun.sqlQuery(sql2);

            if (resSet.next()) {
                objEmployee.setEmpId(Integer.parseInt(empId));
                objEmployee.setFname(resSet.getString("fname"));
                objEmployee.setLname(resSet.getString("lname"));
                objEmployee.setDesignation(resSet.getString("designation"));
                objEmployee.setDepartment(resSet.getString("department"));
                objLeave.setVacationleave(resSet.getDouble("vacation_leave"));
                objLeave.setSickleave(resSet.getDouble("sick_leave"));
                //objEmployee.setSalType(resSet.getString("salType"));
                this.totalDays = resSet.getInt("totalDays");//
                this.salratedaily = resSet.getDouble("SalRateDaily");//
                this.basicSalary = resSet.getDouble("basicSalary");//
                this.allowanceAmount = resSet.getDouble("Allowance");
                this.nightDiffHours = resSet.getDouble("nightDiffhours");//
                this.nightDiffAmount = resSet.getDouble("nightDiffamount");
                this.specialHolidaydays = resSet.getDouble("specialHolidaydays");
                this.specialHolidayAmount = resSet.getDouble("specialHolidayamount");
                this.SHNDhours = resSet.getDouble("SHNDhours");//
                this.SHNDAmount = resSet.getDouble("SHNDamount");
                this.LegalHolidayDays = resSet.getDouble("legalHolidaydays");//
                this.LegalHolidayAmount = resSet.getDouble("legalHolidayamount");
                this.LHNDhours = resSet.getDouble("LHNDhours");//
                this.LHNDamount = resSet.getDouble("LHNDamount");
                this.OThours = resSet.getDouble("Overtimehours");//
                this.OTamount = resSet.getDouble("Overtimeamount");
                this.OTNDhours = resSet.getDouble("OTnightdiffhours");//
                this.OTNDamount = resSet.getDouble("OTnightdiffamount");
                this.leaveothersamount = resSet.getDouble("LeaveOthers");
                this.adjustmentamount = resSet.getDouble("Adjustment");
                this.lateundertimemins = resSet.getDouble("lateundertimemins");//
                this.lateundertimerate = resSet.getDouble("lateundertimerate");//
                this.lateundertimeamount = resSet.getDouble("lateundertimeamount");
                this.SSSamount = resSet.getDouble("SSSamount");
                this.philhealthamount = resSet.getDouble("PhilHealthamount");
                this.hdmfamount = resSet.getDouble("HDMFamount");
                this.otherdeductionamount = resSet.getDouble("OtherDeduction");
                this.date = String.valueOf(resSet.getDate("date"));
                this.fromcutoffdate = String.valueOf(resSet.getDate("fromcutoffdate"));
                this.tocutoffdate = String.valueOf(resSet.getDate("tocutoffdate"));
                this.totaldeductions = resSet.getDouble("total_deductions");

                return true;

            } else if (resSet2.next()) {                
                objEmployee.setEmpId(Integer.parseInt(empId));
                objEmployee.setFname(resSet2.getString("fname"));
                objEmployee.setLname(resSet2.getString("lname"));
                objEmployee.setDesignation(resSet2.getString("designation"));
                objEmployee.setDepartment(resSet2.getString("department"));
                objLeave.setVacationleave(resSet2.getDouble("vacation_leave"));
                objLeave.setSickleave(resSet2.getDouble("sick_leave"));
                //objLeave.setVacationleave(resSet.getDouble("vacation_leave"));
                //objLeave.setSickleave(resSet.getDouble("sick_leave"));
                //objLeave.setVacationleave(0.0);
                //objLeave.setSickleave(0.0);
                //objEmployee.setSalType(resSet2.getString("salType"));
                this.date = " ";
                this.fromcutoffdate = " ";
                this.tocutoffdate = " ";
                this.totalDays = 0;//
                this.salratedaily = 0.0;//
                this.basicSalary = 0.0;//
                this.allowanceAmount = 0.0;
                this.nightDiffHours = 0.0; //
                this.nightDiffAmount = 0.0;
                this.specialHolidaydays = 0.0;//
                this.specialHolidayAmount = 0.0;
                this.SHNDhours = 0.0;//
                this.SHNDAmount = 0.0;
                this.LegalHolidayDays = 0.0;//
                this.LegalHolidayAmount = 0.0;
                this.LHNDhours = 0.0;//
                this.LHNDamount = 0.0;
                this.OThours = 0.0;//
                this.OTamount = 0.0;
                this.OTNDhours = 0.0;//
                this.OTNDamount = 0.0;
                this.leaveothersamount = 0.0;
                this.adjustmentamount = 0.0;  
                this.lateundertimemins = 0.0;//
                this.lateundertimerate = 0.0;//
                this.lateundertimeamount = 0.0;
                this.SSSamount = 0.0;
                this.philhealthamount = 0.0;
                this.hdmfamount = 0.0;
                this.otherdeductionamount = 0.0;
                this.totaldeductions = 0.0;
                
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No record found for Employee ID : " + empId, "ERROR", 0);
                return false;
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);
            return false;

        }
    }

    public boolean insertSalaryDetails() {
        double grossPayadd = (basicSalary + allowanceAmount + nightDiffAmount + specialHolidayAmount + SHNDAmount + LegalHolidayAmount + LHNDamount + OTamount + OTNDamount + leaveothersamount + adjustmentamount); 
        double totaldedeductionadd = (lateundertimeamount + SSSamount + philhealthamount + hdmfamount + otherdeductionamount);
        double netPayadd = grossPayadd - totaldedeductionadd;
        double netPayformatadd = Double.parseDouble(decfor.format(netPayadd));
        String sql = "INSERT INTO `salary_details`(`empId`, `totalDays`, `SalRateDaily`, `basicSalary`, `Allowance`, `nightDiffhours`, `nightDiffamount`, `specialHolidaydays`, `specialHolidayamount`, `SHNDhours`, `SHNDamount`, `legalHolidaydays`, `legalHolidayamount`, `LHNDhours`, `LHNDamount`, `Overtimehours`, `Overtimeamount`, `OTnightdiffhours`, `OTnightdiffamount`, `LeaveOthers`, `Adjustment`, `lateundertimemins`, `lateundertimerate`, `lateundertimeamount`, `SSSamount`, `PhilHealthamount`, `HDMFamount`, `OtherDeduction`, `fromcutoffdate`, `tocutoffdate`, `date`, `total_deductions`, `net_pay`) VALUES "
                + "('" + objEmployee.getEmpId() + "'," + totalDays + "," + salratedaily + "," + basicSalary + "," + allowanceAmount + ","
            +  nightDiffHours + "," + nightDiffAmount + "," + specialHolidaydays + "," + specialHolidayAmount + ","
            +  SHNDhours + "," + SHNDAmount + "," + LegalHolidayDays + "," + LegalHolidayAmount + ","
            + LHNDhours + "," + LHNDamount + "," + OThours + "," + OTamount + ","
            + OTNDhours + "," + OTNDamount + "," + leaveothersamount + ","
            + adjustmentamount + "," + lateundertimemins + ","
            + lateundertimerate + "," + lateundertimeamount + "," + SSSamount + ","
            + philhealthamount + "," + hdmfamount + "," + otherdeductionamount + ", '" + fromcutoffdate + "', '" + tocutoffdate + "', '" + date + "', " + totaldeductions + ", " + netPayformatadd +")";
        
        int inserted = objSQLRun.sqlUpdate(sql);
         
        if (inserted > 0) {
            JOptionPane.showMessageDialog(null, "Salary Details for Employee ID : " + objEmployee.getEmpId() + " has been Added Successfully!", "", 1);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error!\nCould not Add Salary Details for Employee ID : " + objEmployee.getEmpId(), "ERROR", 0);
            return false;
        }

    }

    public boolean updateSalaryDetails(String empId, String date) {
        double grossPayupdate = (basicSalary + allowanceAmount + nightDiffAmount + specialHolidayAmount + SHNDAmount + LegalHolidayAmount + LHNDamount + OTamount + OTNDamount + leaveothersamount + adjustmentamount); 
        double totaldedeductionupdate = (lateundertimeamount + SSSamount + philhealthamount + hdmfamount + otherdeductionamount);
        double netPayupdate = grossPayupdate - totaldedeductionupdate;
        double netPayformatupdate = Double.parseDouble(decfor.format(netPayupdate));
        String sql = "UPDATE `salary_details` SET " +
            "`totalDays` = " + totalDays + "," +
            "`SalRateDaily` = " + salratedaily + "," +
            "`basicSalary` = " + basicSalary + "," +
            "`Allowance` = " + allowanceAmount + "," +
            "`nightDiffhours` = " + nightDiffHours + "," +
            "`nightDiffamount` = " + nightDiffAmount + "," +
            "`specialHolidaydays` = " + specialHolidaydays + "," +
            "`specialHolidayamount` = " + specialHolidayAmount + "," +
            "`SHNDhours` = " + SHNDhours + "," +
            "`SHNDamount` = " + SHNDAmount + "," +
            "`legalHolidaydays` = " + LegalHolidayDays + "," +
            "`legalHolidayamount` = " + LegalHolidayAmount + "," +
            "`LHNDhours` = " + LHNDhours + "," +
            "`LHNDamount` = " + LHNDamount + "," +
            "`Overtimehours` = " + OThours + "," +
            "`Overtimeamount` = " + OTamount + "," +
            "`OTnightdiffhours` = " + OTNDhours + "," +
            "`OTnightdiffamount` = " + OTNDamount + "," +
            "`LeaveOthers` = " + leaveothersamount + "," +
            "`Adjustment` = " + adjustmentamount + "," +
            "`lateundertimemins` = " + lateundertimemins + "," +
            "`lateundertimerate` = " + lateundertimerate + "," +
            "`lateundertimeamount` = " + lateundertimeamount + "," +
            "`SSSamount` = " + SSSamount + "," +
            "`PhilHealthamount` = " + philhealthamount + "," +
            "`HDMFamount` = " + hdmfamount + "," +
            "`OtherDeduction` = " + otherdeductionamount + "," +
            "`date` = '" + date + 
            "', `fromcutoffdate` = '" + fromcutoffdate +
            "', `tocutoffdate` = '" + tocutoffdate +
            "', `net_pay` = " + netPayformatupdate +
            ", `total_deductions` = " + totaldeductions + 
                " WHERE `empId` = '" + objEmployee.getEmpId() + "' AND `date` ='" + date + "'";
       
        int updated = objSQLRun.sqlUpdate(sql);

        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean deleteSalaryDetails(String empId,String date) {

        String sql = "DELETE FROM salary_details WHERE empId='" + empId + "' AND date='" + date + "';";

        int deleted = objSQLRun.sqlUpdate(sql);

        if (deleted > 0) {
            JOptionPane.showMessageDialog(null, "Employee ID:" + empId + " has been deleted successfully", "ERROR", 1);
            return true;

        } else {
            if (empId == null) {
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Employee ID:" + empId + " does not exist", "ERROR", 0);
                return false;
            }
        }

    }
    
    public double calculatebasicsal(String totaldays, String saldailyrate) {
        try {

            double td = Double.parseDouble(totaldays);
            double srd = Double.parseDouble(saldailyrate);
            double amount = td * srd;
            return amount;

        } catch (Exception e) {
            
            return 0;
        }
    }
    
    public double calculatenightdiff(String hours, String saldailyrate) {
        try {
            double hrs = Double.parseDouble(hours);
            double srd = Double.parseDouble(saldailyrate);
            double quo = srd/8;
            double p1 = quo*0.1;
            double amount = p1 * hrs;
            return amount;

        } catch (Exception e) {
            
            return 0;
        }
    }
    
    public double calculatespecialholiday(String days, String saldailyrate) {
        try {
            double dys = Double.parseDouble(days);
            double srd = Double.parseDouble(saldailyrate);
            double p1 = srd*0.3;
            double amount = p1 * dys;
            return amount;

        } catch (Exception e) {
            
            return 0;
        }
    }
    
    public double calculateshnd(String hours) {
        try {
            double hrs = Double.parseDouble(hours);
            double amount = hrs * 8.72;
            return amount;

        } catch (Exception e) {
            
            return 0;
        }
    }
    
    public double calculatelegalholiday(String days, String saldailyrate) {
        try {
            double dys = Double.parseDouble(days);
            double srd= Double.parseDouble(saldailyrate);
            double amount = dys * srd;
            return amount;

        } catch (Exception e) {
          
            return 0;
        }
    }
    
    public double calculateLHND(String hours) {
        try {
            double hrs = Double.parseDouble(hours);
            double amount = hrs * 13.42;
            return amount;

        } catch (Exception e) {
         
            return 0;
        }
    }
    
    public double calculateOT(String hours, String saldailyrate) {
        try {
            double hrs = Double.parseDouble(hours);
            double srd = Double.parseDouble(saldailyrate);
            double quo = srd/8;
            double p1 = quo * hrs;
            double amount = p1 * 1.25;
            return amount;

        } catch (Exception e) {
           
            return 0;
        }
    }
    
    public double calculateOTND(String hours, String saldailyrate) {
        try {
            double hrs = Double.parseDouble(hours);
            double srd = Double.parseDouble(saldailyrate);
            double quo = srd/8;
            double p1 = quo * 1.25;
            double p2 = p1 * 0.1;
            double amount = p2 * hrs;
            return amount;

        } catch (Exception e) {
            
            return 0;
        }
    }
    
    public double calculatelateundertime(String mins) {
        try {

            double ms = Double.parseDouble(mins);
            double rt = 1.27;
            double amount = ms * rt;
            return amount;

        } catch (Exception e) {
         
            return 0;
        }
    }
    
    /*public double calculatetotaldeduction(String mins, String rate) {
        try {

            double ms = Double.parseDouble(mins);
            double rt = Double.parseDouble(rate);
            double amount = ms * rt;
            return amount;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!\n"
                    + "Check to see whether you have Selected an employee or Entered a valid value", "ERROR", 0);
            return 0;
        }
    }*/
    
    public double calculaterate(String mins){
        try {
            double lateRate=0.0;
            int minutesLate = Integer.parseInt(mins);

            if (minutesLate >= 1 && minutesLate <= 15) {
            lateRate = 0.25;
            } else if (minutesLate >= 16 && minutesLate <= 30) {
            lateRate = 0.5;
            } else if (minutesLate >= 31 && minutesLate <= 45) {
            lateRate = 0.75;
            } else if (minutesLate >= 46 && minutesLate <= 60) {
            lateRate = 1.0;
            }
            return lateRate;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error!\n" + "Check to see whether you have Selected an employee or Entered a valid value", "ERROR", 0);
            return 0;
        }
        
    }
    
    public double calculateleave(double vl, double sl, String saldailyrate){
        try {
            double leave = vl + sl;
            double srd= Double.parseDouble(saldailyrate);
            double amount = leave * srd;
            return amount;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!\n"
                    + "Check to see whether you have Selected an employee or Entered a valid value", "ERROR", 0);
            return 0;
        }
    }
    
    public double calculatetotaldeduction(String lateundertime, String sss, String philhealth, String hdmf, String Otherdeduction){
        try {
     
            double lu = Double.parseDouble(lateundertime);
            double ss = Double.parseDouble(sss);
            double ph = Double.parseDouble(philhealth);
            double hd = Double.parseDouble(hdmf);
            double od = Double.parseDouble(Otherdeduction);
            double amount = lu + ss + ph + hd + od;
            return amount;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!\n"
                    + "Check to see whether you have Selected an employee or Entered a valid value", "ERROR", 0);
            return 0;
        }
    }
}
    
    
    

