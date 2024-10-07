package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.math.RoundingMode;  
import java.text.DecimalFormat;  

public class PaySlip {
    private static final DecimalFormat decfor = new DecimalFormat("0.00");  
    SQLRun objSQLRun = new SQLRun();
    public Employee objEmployee = new Employee();
    public Payroll objPayroll = new Payroll();

    private int totalDays = 0;
    private double salratedaily = 0.0;
    private double salratedailyformat = 0.0;
    
    private double basicpay = 0.0;
    private double basicpayformat = 0.0;
    
    private double allowanceAmount = 0.0;
    private double allowanceAmountformat = 0.0;
    
    private double nightDiffAmount = 0.0;
    private double nightDiffAmountformat = 0.0;
    
    private double specialHolidayAmount = 0.0;
    private double specialHolidayAmountformat = 0.0;
    
    private double SHNDAmount = 0.0;
    private double SHNDAmountformat = 0.0;
    
    private double LegalHolidayAmount = 0.0;
    private double LegalHolidayAmountformat = 0.0;
    
    private double LHNDamount = 0.0; 
    private double LHNDamountformat = 0.0;
    
    private double OTamount = 0.0;
    private double OTamountformat = 0.0;
    
    private double OTNDamount = 0.0;
    private double OTNDamountformat = 0.0;
    
    private double leaveothersamount = 0.0;
    private double leaveothersamountformat = 0.0;
    
    private double adjustmentamount = 0.0;
    private double adjustmentamountformat = 0.0;
    
    private double otherdeductionamount = 0.0;
    private double otherdeductionamountformat = 0.0;
    
    private double lateundertimeamount = 0.0;
    private double lateundertimeamountformat = 0.0;
    
    private double SSSamount = 0.0;
    private double SSSamountformat = 0.0;
    
    private double philhealthamount = 0.0;
    private double philhealthamountformat = 0.0;
    
    private double hdmfamount = 0.0;
    private double hdmfamountformat = 0.0;
    
    private double grossPay = 0.0;
    private double grossPayformat = 0.0;
    
    private double totalDeductions = 0.0;
    private double totalDeductionsformat = 0.0;
    
    private double netPay = 0.0;
    private double netPayformat = 0.0;
    
    private String date;
    private String fromcutoffdate;
    private String tocutoffdate;
    
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
        return salratedailyformat;
    }

    public void setsalratedaily(double salratedailyformat) {
        this.salratedailyformat = salratedailyformat;
    }
    
    public double getbasicpay() {
        return basicpayformat;
    }

    public void setbasicpay(double basicpayformat) {
        this.basicpayformat = basicpayformat;
    }
    
    public double getallowanceAmount() {
        return allowanceAmountformat;
    }

    public void setallowanceAmount(double allowanceAmountformat) {
        this.allowanceAmountformat = allowanceAmountformat;
    }
    
    public double getnightDiffAmount() {
        return nightDiffAmountformat;
    }

    public void setnightDiffAmount(double nightDiffAmountformat) {
        this.nightDiffAmountformat = nightDiffAmountformat;
    }

    public double getspecialHolidayAmount() {
        return specialHolidayAmountformat;
    }

    public void setspecialHolidayAmount(double specialHolidayAmountformat) {
        this.specialHolidayAmountformat = specialHolidayAmountformat;
    }
    
    public double getSHNDAmount() {
        return SHNDAmountformat;
    }

    public void setSHNDAmount(double SHNDAmountformat) {
        this.SHNDAmountformat = SHNDAmountformat;
    }

    public double getLegalHolidayAmount() {
        return LegalHolidayAmountformat;
    }

    public void setLegalHolidayAmount(double LegalHolidayAmountformat) {
        this.LegalHolidayAmountformat = LegalHolidayAmountformat;
    }

    
    public double getLHNDamount() {
        return LHNDamountformat;
    }

    public void setLHNDamount(double LHNDamountformat) {
        this.LHNDamountformat = LHNDamountformat;
    }
    
    public double getOTamount() {
        return OTamountformat;
    }

    public void setOTamount(double OTamountformat) {
        this.OTamountformat = OTamountformat;
    }
    
    public double getOTNDamount() {
        return OTNDamountformat;
    }

    public void setOTNDamount(double OTNDamountformat) {
        this.OTNDamountformat = OTNDamountformat;
    }
    
    public double getleaveothersamount() {
        return leaveothersamountformat;
    }

    public void setleaveothersamount(double leaveothersamountformat) {
        this.leaveothersamountformat = leaveothersamountformat;
    }
    
    public double getadjustmentamount() {
        return adjustmentamountformat;
    }

    public void setadjustmentamount(double adjustmentamountformat) {
        this.adjustmentamountformat = adjustmentamountformat;
    }
    
    public double getotherdeductionamount() {
        return otherdeductionamountformat;
    }
    
    public void setotherdeductionamount(double otherdeductionamountformat) {
        this.otherdeductionamountformat = otherdeductionamountformat;
    }
    
    public double getlateundertimeamount() {
        return lateundertimeamountformat;
    }

    public void setlateundertimeamount(double lateundertimeamountformat) {
        this.lateundertimeamountformat = lateundertimeamountformat;
    }
    
    public double getSSSamount() {
        return SSSamountformat;
    }

    public void setSSSamount(double SSSamountformat) {
        this.SSSamountformat = SSSamountformat;
    }
    
    public double getphilhealthamount() {
        return philhealthamountformat;
    }

    public void setphilhealthamount(double philhealthamountformat) {
        this.philhealthamountformat = philhealthamountformat;
    }
    
    public double gethdmfamount() {
        return hdmfamountformat;
    }

    public void sethdmfamount(double hdmfamountformat) {
        this.hdmfamountformat = hdmfamountformat;
    }
    
    public double getGrossPay() {
        return grossPayformat;
    }

    public void setGrossPay(double grossPayformat) {
        this.grossPayformat = grossPayformat;
    }

    public double getTotalDeductions() {
        return totalDeductionsformat;
    }

    public void setTotalDeductions(double totalDeductionsformat) {
        this.totalDeductionsformat = totalDeductionsformat;
    }

    public double getNetPay() {
        return netPayformat;
    }

    public void setNetPay(double netPayformat) {
        this.netPayformat = netPayformat;
    }
    //public boolean getPayDetails(String empId)
    public boolean getPayDetails(String empId, String printdate) 
    {
        try {

            //String sql = "SELECT * FROM employee e,salary_details s WHERE e.empId='" + empId + "' AND e.empId=s.empId;";
             String sql = "SELECT * FROM employee e,salary_details s WHERE e.empId='" + empId + "' AND e.empId=s.empId AND date ='" + printdate + "';";
        
            ResultSet resSet = objSQLRun.sqlQuery(sql);

            if (resSet.next()) {

                objEmployee.setEmpId(Integer.parseInt(empId));
                objEmployee.setFname(resSet.getString("fname"));
                objEmployee.setLname(resSet.getString("lname"));
                objEmployee.setDesignation(resSet.getString("designation"));
                objEmployee.setDepartment(resSet.getString("department"));
                objPayroll.settotalDays(resSet.getInt("totalDays"));
                objPayroll.setsalratedaily(resSet.getDouble("SalRateDaily"));
                objPayroll.setbasicSalary(resSet.getDouble("basicSalary"));
                objPayroll.setallowanceAmount(resSet.getDouble("Allowance"));
                objPayroll.setnightDiffAmount(resSet.getDouble("nightDiffamount"));
                objPayroll.setspecialHolidayAmount(resSet.getDouble("specialHolidayamount"));
                objPayroll.setSHNDAmount(resSet.getDouble("SHNDamount"));//
                objPayroll.setLegalHolidayAmount(resSet.getDouble("legalHolidayamount"));//
                objPayroll.setLHNDamount(resSet.getDouble("LHNDamount"));//
                objPayroll.setOTamount(resSet.getDouble("Overtimeamount"));//
                objPayroll.setOTNDamount(resSet.getDouble("OTnightdiffamount"));//
                objPayroll.setleaveothersamount(resSet.getDouble("LeaveOthers"));//
                objPayroll.setadjustmentamount(resSet.getDouble("Adjustment"));//
                objPayroll.setSSSamount(resSet.getDouble("SSSamount"));
                objPayroll.setphilhealthamount(resSet.getDouble("PhilHealthamount"));
                objPayroll.sethdmfamount(resSet.getDouble("HDMFamount"));
                objPayroll.setlateundertimeamount(resSet.getDouble("lateundertimeamount"));
                objPayroll.setotherdeductionamount(resSet.getDouble("OtherDeduction"));
                objPayroll.setfromcutoffdate(String.valueOf(resSet.getDate("fromcutoffdate")));
                objPayroll.settocutoffdate(String.valueOf(resSet.getDate("tocutoffdate")));
                objPayroll.setDate(String.valueOf(resSet.getDate("date")));
                
                totalDays = objPayroll.gettotalDays();
                salratedaily = objPayroll.getsalratedaily();
                salratedailyformat = Double.parseDouble(decfor.format(salratedaily));
                
                date = String.valueOf(objPayroll.getDate());
                fromcutoffdate=String.valueOf(objPayroll.getfromcutoffdate());
                tocutoffdate=String.valueOf(objPayroll.gettocutoffdate());
                
                basicpay = Double.parseDouble(decfor.format(objPayroll.getbasicSalary()));
                basicpayformat = Double.parseDouble(decfor.format(basicpay));
                
                allowanceAmount = objPayroll.getallowanceAmount();
                allowanceAmountformat = Double.parseDouble(decfor.format(allowanceAmount));
                
                nightDiffAmount = objPayroll.getnightDiffAmount(); 
                nightDiffAmountformat = Double.parseDouble(decfor.format(nightDiffAmount));
                
                specialHolidayAmount = objPayroll.getspecialHolidayAmount();
                specialHolidayAmountformat = Double.parseDouble(decfor.format(specialHolidayAmount));
                
                SHNDAmount = objPayroll.getSHNDAmount();
                SHNDAmountformat = Double.parseDouble(decfor.format(SHNDAmount));
                
                LegalHolidayAmount = objPayroll.getLegalHolidayAmount();
                LegalHolidayAmountformat = Double.parseDouble(decfor.format(LegalHolidayAmount));
                
                LHNDamount = objPayroll.getLHNDamount();
                LHNDamountformat = Double.parseDouble(decfor.format(LHNDamount));
                
                OTamount = objPayroll.getOTamount();
                OTamountformat = Double.parseDouble(decfor.format(OTamount));
                
                OTNDamount = objPayroll.getOTNDamount();
                OTNDamountformat = Double.parseDouble(decfor.format(OTNDamount));
                
                leaveothersamount = objPayroll.getleaveothersamount();
                leaveothersamountformat = Double.parseDouble(decfor.format(leaveothersamount));
                
                adjustmentamount = objPayroll.getadjustmentamount();
                adjustmentamountformat = Double.parseDouble(decfor.format(adjustmentamount));
                
                otherdeductionamount = objPayroll.getotherdeductionamount();
                otherdeductionamountformat = Double.parseDouble(decfor.format(otherdeductionamount));
                
                lateundertimeamount = objPayroll.getlateundertimeamount();
                lateundertimeamountformat = Double.parseDouble(decfor.format(lateundertimeamount));
                
                SSSamount = objPayroll.getSSSamount();
                SSSamountformat = Double.parseDouble(decfor.format(SSSamount));
                
                philhealthamount = objPayroll.getphilhealthamount();
                philhealthamountformat = Double.parseDouble(decfor.format(philhealthamount));
                
                hdmfamount = objPayroll.gethdmfamount();
                hdmfamountformat = Double.parseDouble(decfor.format(hdmfamount));
                
                grossPay = (objPayroll.getbasicSalary() + objPayroll.getallowanceAmount() + objPayroll.getnightDiffAmount() + objPayroll.getspecialHolidayAmount() + objPayroll.getSHNDAmount() + objPayroll.getLegalHolidayAmount() + objPayroll.getLHNDamount() + objPayroll.getOTamount() + objPayroll.getOTNDamount() + objPayroll.getleaveothersamount() + objPayroll.getadjustmentamount());
                grossPayformat = Double.parseDouble(decfor.format(grossPay));
                
                totalDeductions = (objPayroll.getSSSamount() + objPayroll.getphilhealthamount() + objPayroll.gethdmfamount() + objPayroll.getlateundertimeamount() + objPayroll.getotherdeductionamount());
                totalDeductionsformat = Double.parseDouble(decfor.format(totalDeductions));

                netPay = (grossPay - totalDeductions);
                netPayformat = Double.parseDouble(decfor.format(netPay));
                

                return true;

            } else {
                JOptionPane.showMessageDialog(null, "No Record Found for Employee ID : " + empId, "ERROR", 0);
                return false;

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "No Record Found for Employee ID : " + empId + "\n\n" + ex.getMessage(), "ERROR", 0);
            return false;

        }
    }

    public boolean getPayDetailsvianame(String empfname, String emplname, String printdate) 
    {
        try {

            //String sql = "SELECT * FROM employee e,salary_details s WHERE e.empId='" + empId + "' AND e.empId=s.empId;";
             String sql = "SELECT * FROM employee e,salary_details s WHERE e.fname='" + empfname + "'AND e.lname ='" + emplname + "' AND e.empId=s.empId AND date ='" + printdate + "';";
             System.out.println(sql);
            ResultSet resSet = objSQLRun.sqlQuery(sql);

            if (resSet.next()) {

                objEmployee.setEmpId(resSet.getInt("empId"));
                objEmployee.setFname(resSet.getString("fname"));
                objEmployee.setLname(resSet.getString("lname"));
                objEmployee.setDesignation(resSet.getString("designation"));
                objEmployee.setDepartment(resSet.getString("department"));
                objPayroll.settotalDays(resSet.getInt("totalDays"));
                objPayroll.setsalratedaily(resSet.getDouble("SalRateDaily"));
                objPayroll.setbasicSalary(resSet.getDouble("basicSalary"));
                objPayroll.setallowanceAmount(resSet.getDouble("Allowance"));
                objPayroll.setnightDiffAmount(resSet.getDouble("nightDiffamount"));
                objPayroll.setspecialHolidayAmount(resSet.getDouble("specialHolidayamount"));
                objPayroll.setSHNDAmount(resSet.getDouble("SHNDamount"));//
                objPayroll.setLegalHolidayAmount(resSet.getDouble("legalHolidayamount"));//
                objPayroll.setLHNDamount(resSet.getDouble("LHNDamount"));//
                objPayroll.setOTamount(resSet.getDouble("Overtimeamount"));//
                objPayroll.setOTNDamount(resSet.getDouble("OTnightdiffamount"));//
                objPayroll.setleaveothersamount(resSet.getDouble("LeaveOthers"));//
                objPayroll.setadjustmentamount(resSet.getDouble("Adjustment"));//
                objPayroll.setSSSamount(resSet.getDouble("SSSamount"));
                objPayroll.setphilhealthamount(resSet.getDouble("PhilHealthamount"));
                objPayroll.sethdmfamount(resSet.getDouble("HDMFamount"));
                objPayroll.setlateundertimeamount(resSet.getDouble("lateundertimeamount"));
                objPayroll.setotherdeductionamount(resSet.getDouble("OtherDeduction"));
                objPayroll.setfromcutoffdate(String.valueOf(resSet.getDate("fromcutoffdate")));
                objPayroll.settocutoffdate(String.valueOf(resSet.getDate("tocutoffdate")));
                objPayroll.setDate(String.valueOf(resSet.getDate("date")));
                
                totalDays = objPayroll.gettotalDays();
                salratedaily = objPayroll.getsalratedaily();
                salratedailyformat = Double.parseDouble(decfor.format(salratedaily));
                
                date = String.valueOf(objPayroll.getDate());
                fromcutoffdate=String.valueOf(objPayroll.getfromcutoffdate());
                tocutoffdate=String.valueOf(objPayroll.gettocutoffdate());
                
                basicpay = Double.parseDouble(decfor.format(objPayroll.getbasicSalary()));
                basicpayformat = Double.parseDouble(decfor.format(basicpay));
                
                allowanceAmount = objPayroll.getallowanceAmount();
                allowanceAmountformat = Double.parseDouble(decfor.format(allowanceAmount));
                
                nightDiffAmount = objPayroll.getnightDiffAmount(); 
                nightDiffAmountformat = Double.parseDouble(decfor.format(nightDiffAmount));
                
                specialHolidayAmount = objPayroll.getspecialHolidayAmount();
                specialHolidayAmountformat = Double.parseDouble(decfor.format(specialHolidayAmount));
                
                SHNDAmount = objPayroll.getSHNDAmount();
                SHNDAmountformat = Double.parseDouble(decfor.format(SHNDAmount));
                
                LegalHolidayAmount = objPayroll.getLegalHolidayAmount();
                LegalHolidayAmountformat = Double.parseDouble(decfor.format(LegalHolidayAmount));
                
                LHNDamount = objPayroll.getLHNDamount();
                LHNDamountformat = Double.parseDouble(decfor.format(LHNDamount));
                
                OTamount = objPayroll.getOTamount();
                OTamountformat = Double.parseDouble(decfor.format(OTamount));
                
                OTNDamount = objPayroll.getOTNDamount();
                OTNDamountformat = Double.parseDouble(decfor.format(OTNDamount));
                
                leaveothersamount = objPayroll.getleaveothersamount();
                leaveothersamountformat = Double.parseDouble(decfor.format(leaveothersamount));
                
                adjustmentamount = objPayroll.getadjustmentamount();
                adjustmentamountformat = Double.parseDouble(decfor.format(adjustmentamount));
                
                otherdeductionamount = objPayroll.getotherdeductionamount();
                otherdeductionamountformat = Double.parseDouble(decfor.format(otherdeductionamount));
                
                lateundertimeamount = objPayroll.getlateundertimeamount();
                lateundertimeamountformat = Double.parseDouble(decfor.format(lateundertimeamount));
                
                SSSamount = objPayroll.getSSSamount();
                SSSamountformat = Double.parseDouble(decfor.format(SSSamount));
                
                philhealthamount = objPayroll.getphilhealthamount();
                philhealthamountformat = Double.parseDouble(decfor.format(philhealthamount));
                
                hdmfamount = objPayroll.gethdmfamount();
                hdmfamountformat = Double.parseDouble(decfor.format(hdmfamount));
                
                grossPay = (objPayroll.getbasicSalary() + objPayroll.getallowanceAmount() + objPayroll.getnightDiffAmount() + objPayroll.getspecialHolidayAmount() + objPayroll.getSHNDAmount() + objPayroll.getLegalHolidayAmount() + objPayroll.getLHNDamount() + objPayroll.getOTamount() + objPayroll.getOTNDamount() + objPayroll.getleaveothersamount() + objPayroll.getadjustmentamount());
                grossPayformat = Double.parseDouble(decfor.format(grossPay));
                
                totalDeductions = (objPayroll.getSSSamount() + objPayroll.getphilhealthamount() + objPayroll.gethdmfamount() + objPayroll.getlateundertimeamount() + objPayroll.getotherdeductionamount());
                totalDeductionsformat = Double.parseDouble(decfor.format(totalDeductions));

                netPay = (grossPay - totalDeductions);
                netPayformat = Double.parseDouble(decfor.format(netPay));
                

                return true;

            } else {
                JOptionPane.showMessageDialog(null, "No Record Found for Employee Name : " + empfname + emplname, "ERROR", 0);
                return false;

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "No Record Found for Employee Name : " + empfname + emplname + "\n\n" + ex.getMessage(), "ERROR", 0);
            return false;

        }
    }

}
