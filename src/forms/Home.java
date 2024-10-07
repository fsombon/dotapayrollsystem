package forms;

import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserAdapter;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Employee;
import model.Person;
import model.Leave;
import model.PaySlip;
import model.Payroll;
import model.Print;
import model.SingletonConnection;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.awt.print.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.SQLRun;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.imageio.ImageIO;

 
class EmptyStringException extends Exception {
    public EmptyStringException(String message) {
        super(message);
    }
}        
public class Home extends javax.swing.JFrame {
//Creating Objects
    private DateChooser chDate = new DateChooser();
    private DateChooser chDate2 = new DateChooser();
    private DateChooser chDate3 = new DateChooser();
    private DateChooser chDate4 = new DateChooser();
    private DateChooser chDate5 = new DateChooser();
    private DateChooser chDate6 = new DateChooser();
    private DateChooser chDate7 = new DateChooser();
    private DateChooser chDate8 = new DateChooser();
    private DateChooser searchSalaryDetailsDate = new DateChooser();
    private DefaultTableModel model;
    private DefaultTableModel model2;
    Employee objEmployee = new Employee();
    Payroll objPayroll = new Payroll();
    PaySlip objPaySlip = new PaySlip();
    Leave objLeave = new Leave();
    Person objPerson = new Person();
    JFrame frame = new JFrame("Confirmation Dialog Example");
     private static final DecimalFormat decfor = new DecimalFormat("0.00");
    double defaultSSS = 0.0;
    double defaultPhilhealth = 0.0;
    double defaultHDMF = 0.0;
    
    
    
    PrinterJob printJob = PrinterJob.getPrinterJob();
    
    BufferedImage image1;
    BufferedImage image2;
    
    String payslipNote;

    /**
     * Creates new form Home
     */
    public Home() {
        
        initComponents();
        addButtonGroup();
        changeIcon();
        
        txt_totaldays.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                basicsalupdateflds();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                basicsalupdateflds();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                basicsalupdateflds();
            }
        });
        txt_salratedaily.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                basicsalupdateflds();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                basicsalupdateflds();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                basicsalupdateflds();
            }
        });
        txt_nightdiffhours.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                txt_nightdiffamount.setText(String.valueOf(objPayroll.calculatenightdiff(txt_nightdiffhours.getText(), txt_salratedaily.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txt_nightdiffamount.setText(String.valueOf(objPayroll.calculatenightdiff(txt_nightdiffhours.getText(), txt_salratedaily.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                txt_nightdiffamount.setText(String.valueOf(objPayroll.calculatenightdiff(txt_nightdiffhours.getText(), txt_salratedaily.getText())));
            }
        });
        txt_specialholidayhours.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                txt_specialholidayamount.setText(String.valueOf(objPayroll.calculatespecialholiday(txt_specialholidayhours.getText(), txt_salratedaily.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txt_specialholidayamount.setText(String.valueOf(objPayroll.calculatespecialholiday(txt_specialholidayhours.getText(), txt_salratedaily.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                txt_specialholidayamount.setText(String.valueOf(objPayroll.calculatespecialholiday(txt_specialholidayhours.getText(), txt_salratedaily.getText())));
            }
        });
        txt_SHNDhours.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                txt_SHNDamount.setText(String.valueOf(objPayroll.calculateshnd(txt_SHNDhours.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txt_SHNDamount.setText(String.valueOf(objPayroll.calculateshnd(txt_SHNDhours.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                txt_SHNDamount.setText(String.valueOf(objPayroll.calculateshnd(txt_SHNDhours.getText())));
            }
        });
        txt_LegalHolidayhours.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                txt_LegalHolidayamount.setText(String.valueOf(objPayroll.calculatelegalholiday(txt_LegalHolidayhours.getText(),txt_salratedaily.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txt_LegalHolidayamount.setText(String.valueOf(objPayroll.calculatelegalholiday(txt_LegalHolidayhours.getText(),txt_salratedaily.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                txt_LegalHolidayamount.setText(String.valueOf(objPayroll.calculatelegalholiday(txt_LegalHolidayhours.getText(),txt_salratedaily.getText())));
            }
        });
        txt_LHNDhours.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                txt_LHNDamount.setText(String.valueOf(objPayroll.calculateLHND(txt_LHNDhours.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txt_LHNDamount.setText(String.valueOf(objPayroll.calculateLHND(txt_LHNDhours.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                txt_LHNDamount.setText(String.valueOf(objPayroll.calculateLHND(txt_LHNDhours.getText())));
            }
        });
        txt_overtimehours.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                txt_overtimeamount.setText(String.valueOf(objPayroll.calculateOT(txt_overtimehours.getText(),txt_salratedaily.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txt_overtimeamount.setText(String.valueOf(objPayroll.calculateOT(txt_overtimehours.getText(),txt_salratedaily.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                txt_overtimeamount.setText(String.valueOf(objPayroll.calculateOT(txt_overtimehours.getText(),txt_salratedaily.getText())));
            }
        });
        txt_OTNDhours.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                txt_OTNDamount.setText(String.valueOf(objPayroll.calculateOTND(txt_OTNDhours.getText(),txt_salratedaily.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txt_OTNDamount.setText(String.valueOf(objPayroll.calculateOTND(txt_OTNDhours.getText(),txt_salratedaily.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                txt_OTNDamount.setText(String.valueOf(objPayroll.calculateOTND(txt_OTNDhours.getText(),txt_salratedaily.getText())));
            }
        });
        txt_lateundertimemins.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                txt_lateundertimeamount.setText(String.valueOf(objPayroll.calculatelateundertime(txt_lateundertimemins.getText())));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txt_lateundertimeamount.setText(String.valueOf(objPayroll.calculatelateundertime(txt_lateundertimemins.getText())));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                txt_lateundertimeamount.setText(String.valueOf(objPayroll.calculatelateundertime(txt_lateundertimemins.getText())));
            }
        });
        /*
        // Create a common DocumentListener for all text fields
        DocumentListener commonListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalDeduction();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalDeduction();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalDeduction();
            }

            private void updateTotalDeduction() {
                double total = 0.0;
                try {
                    total += Double.parseDouble(txt_lateundertimeamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    total += Double.parseDouble(txt_SSSamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    total += Double.parseDouble(txt_PHILHEALTHamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    total += Double.parseDouble(txt_HDMFamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    total += Double.parseDouble(txt_otherdeductionamount.getText());
                } catch (NumberFormatException ignored) {}

                // Set the total in the total deduction field
                totaldeductiontxt.setText(Double.toString(total));
            }
        };

        // Add the common listener to each text field
        txt_lateundertimeamount.getDocument().addDocumentListener(commonListener);
        txt_SSSamount.getDocument().addDocumentListener(commonListener);
        txt_PHILHEALTHamount.getDocument().addDocumentListener(commonListener);
        txt_HDMFamount.getDocument().addDocumentListener(commonListener);
        txt_otherdeductionamount.getDocument().addDocumentListener(commonListener);
        
        DocumentListener commonEarningsListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalEarnings();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalEarnings();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalEarnings();
            }

            private void updateTotalEarnings() {
                double totalEarnings = 0.0;
                try {
                    totalEarnings += Double.parseDouble(txt_basicsalary.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_allowance.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_nightdiffamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_specialholidayamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_SHNDamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_LegalHolidayamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_LHNDamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_overtimeamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_OTNDamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_leaveothersamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_adjustmentamount.getText());
                } catch (NumberFormatException ignored) {}
                
                Double totalearningsformat = Double.parseDouble(decfor.format(totalEarnings));
                // Set the total in the total earnings field
                totalearningstxt.setText(Double.toString(totalearningsformat));
            }
        };

        // Add the common listener to each text field
        txt_basicsalary.getDocument().addDocumentListener(commonEarningsListener);
        txt_allowance.getDocument().addDocumentListener(commonEarningsListener);
        txt_nightdiffamount.getDocument().addDocumentListener(commonEarningsListener);
        txt_specialholidayamount.getDocument().addDocumentListener(commonEarningsListener);
        txt_SHNDamount.getDocument().addDocumentListener(commonEarningsListener);
        txt_LegalHolidayamount.getDocument().addDocumentListener(commonEarningsListener);
        txt_LHNDamount.getDocument().addDocumentListener(commonEarningsListener);
        txt_overtimeamount.getDocument().addDocumentListener(commonEarningsListener);
        txt_OTNDamount.getDocument().addDocumentListener(commonEarningsListener);
        txt_leaveothersamount.getDocument().addDocumentListener(commonEarningsListener);
        txt_adjustmentamount.getDocument().addDocumentListener(commonEarningsListener);
        */
        // Assuming netpaytxt is the name of JTextField representing the net pay

        // Create a common DocumentListener for all text fields
        DocumentListener commonTotalListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotals();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotals();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotals();
            }

            private void updateTotals() {
                double totalEarnings = 0.0;
                try {
                    totalEarnings += Double.parseDouble(txt_basicsalary.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_allowance.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_nightdiffamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_specialholidayamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_SHNDamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_LegalHolidayamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_LHNDamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_overtimeamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_OTNDamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_leaveothersamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalEarnings += Double.parseDouble(txt_adjustmentamount.getText());
                } catch (NumberFormatException ignored) {}

                // Set the total in the total earnings field
                //totalearningstxt.setText(Double.toString(totalEarnings));
                totalearningstxt.setText(decfor.format(totalEarnings));

                double totalDeductions = 0.0;
                try {
                    totalDeductions += Double.parseDouble(txt_lateundertimeamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalDeductions += Double.parseDouble(txt_SSSamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalDeductions += Double.parseDouble(txt_PHILHEALTHamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalDeductions += Double.parseDouble(txt_HDMFamount.getText());
                } catch (NumberFormatException ignored) {}

                try {
                    totalDeductions += Double.parseDouble(txt_otherdeductionamount.getText());
                } catch (NumberFormatException ignored) {}

                // Set the total in the total deduction field
                //totaldeductiontxt.setText(Double.toString(totalDeductions));
                totaldeductiontxt.setText(decfor.format(totalDeductions));

                // Calculate net pay
                double netPay = totalEarnings - totalDeductions;
                //netpaytxt.setText(Double.toString(netPay));
                netpaytxt.setText(decfor.format(netPay));
            }
        };
        
        

        // Add the common listener to each text field
        txt_basicsalary.getDocument().addDocumentListener(commonTotalListener);
        txt_allowance.getDocument().addDocumentListener(commonTotalListener);
        txt_nightdiffamount.getDocument().addDocumentListener(commonTotalListener);
        txt_specialholidayamount.getDocument().addDocumentListener(commonTotalListener);
        txt_SHNDamount.getDocument().addDocumentListener(commonTotalListener);
        txt_LegalHolidayamount.getDocument().addDocumentListener(commonTotalListener);
        txt_LHNDamount.getDocument().addDocumentListener(commonTotalListener);
        txt_overtimeamount.getDocument().addDocumentListener(commonTotalListener);
        txt_OTNDamount.getDocument().addDocumentListener(commonTotalListener);
        txt_leaveothersamount.getDocument().addDocumentListener(commonTotalListener);
        txt_adjustmentamount.getDocument().addDocumentListener(commonTotalListener);

        txt_lateundertimeamount.getDocument().addDocumentListener(commonTotalListener);
        txt_SSSamount.getDocument().addDocumentListener(commonTotalListener);
        txt_PHILHEALTHamount.getDocument().addDocumentListener(commonTotalListener);
        txt_HDMFamount.getDocument().addDocumentListener(commonTotalListener);
        txt_otherdeductionamount.getDocument().addDocumentListener(commonTotalListener);
        
        jTextArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Get the text from the text field
                String userInput = jTextArea1.getText();
                
                
                payslipNote_lbl.setText("<html><div style='width: 200px; white-space: pre-wrap; vertical-align: top;'>" + userInput + "</div></html>");
            }
        
        });    
        
        chDate.setTextField(txtDate1);
        chDate.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        chDate.setLabelCurrentDayVisible(false);
        chDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
      
        chDate3.setTextField(fromcutofftxt);
        chDate3.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        chDate3.setLabelCurrentDayVisible(false);
        chDate3.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        chDate4.setTextField(tocutofftxt);
        chDate4.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        chDate4.setLabelCurrentDayVisible(false);
        chDate4.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        chDate5.setTextField(deletesalarydeets_date);
        chDate5.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        chDate5.setLabelCurrentDayVisible(false);
        chDate5.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        chDate6.setTextField(print_date1);
        chDate6.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        chDate6.setLabelCurrentDayVisible(false);
        chDate6.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        chDate7.setTextField(print_datevianame);
        chDate7.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        chDate7.setLabelCurrentDayVisible(false);
        chDate7.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        chDate8.setTextField(txt_pydt);
        chDate8.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        chDate8.setLabelCurrentDayVisible(false);
        chDate8.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        searchSalaryDetailsDate.setTextField(getsalarydetailsviadate_date1);
        searchSalaryDetailsDate.setDateSelectionMode(DateChooser.DateSelectionMode.SINGLE_DATE_SELECTED);
        searchSalaryDetailsDate.setLabelCurrentDayVisible(false);
        searchSalaryDetailsDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        
        
        chDate2.setTextField(txtDate2);
        chDate2.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        chDate2.setLabelCurrentDayVisible(false);
        chDate2.setDateFormat(new SimpleDateFormat("dd-MMMM-yyyy"));
        model = (DefaultTableModel) jTable1.getModel();  
        chDate2.addActionDateChooserListener(new DateChooserAdapter(){
               @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action){  
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dateFrom =  df.format(date.getFromDate());
                String toDate =  df.format(date.getToDate());
                //loadData("SELECT * FROM `salary_details` WHERE date BETWEEN '" + dateFrom + "' and '" +toDate+"'");
                loadData("SELECT * FROM employee e,salary_details s WHERE e.empId=s.empId AND fromcutoffdate = '" + dateFrom + "' AND tocutoffdate = '" +toDate+"'");
                txt_pydt.setText(null);  
                
            }
            
        });
        try {
            SingletonConnection.getInstance().connectDatabase();
            
        }catch(Exception e){
            System.err.println(e);
        }
        chDate2.setSelectedDateBetween(new DateBetween(getLast28Day(), new Date()),true);

    }
    private String totalDecCal(String lateUndertimeAmount, String sssAmount, String philhealthAmount, String hdmfAmount, String otherDeducAmount){
        
        try{
            if (lateUndertimeAmount.isEmpty()) {
                throw new EmptyStringException("String is empty");
            }
        Double dbl_lateUndertimeAmount = Double.parseDouble(lateUndertimeAmount);
        Double dbl_sssAmount = Double.parseDouble(sssAmount);
        Double dbl_philhealthAmount = Double.parseDouble(philhealthAmount);
        Double dbl_hdmfAmount = Double.parseDouble(hdmfAmount);
        Double dbl_otherDeducAmount = Double.parseDouble(otherDeducAmount);
        
        Double totalDeductions = dbl_lateUndertimeAmount + dbl_sssAmount + dbl_philhealthAmount + dbl_hdmfAmount + dbl_otherDeducAmount;
        String str_totalDeductions = String.valueOf(totalDeductions);
        return str_totalDeductions;
        }catch (EmptyStringException e) {
            String empty ="0.0";
            return empty;
        }
    }
    private void basicsalupdateflds() {
                int basicsalnonEmptyFields = 0;
                if (!txt_totaldays.getText().isEmpty()) basicsalnonEmptyFields++;
                if (!txt_salratedaily.getText().isEmpty()) basicsalnonEmptyFields++;

                // Check if all text fields have entries
                if (basicsalnonEmptyFields == 2) {
                    double basicsal = objPayroll.calculatebasicsal(txt_totaldays.getText(), txt_salratedaily.getText());
                    txt_basicsalary.setText(String.valueOf(basicsal));
                    // You can trigger any action you want here
                }
            }

    private void loadData(String sql){
        try{
            
            model.setRowCount(0);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
            PreparedStatement p = SingletonConnection.getInstance().openConnection().prepareStatement(sql);
            ResultSet r = p.executeQuery();
            while(r.next()){
                String empId = String.valueOf(r.getInt("empId"));
                String fname = r.getString("fname");
                String lname = r.getString("lname");
                String totaldays = String.valueOf(r.getInt("totalDays"));
                String salratedaily = decfor.format(r.getDouble("SalRateDaily"));
                //String totalDays = String.valueOf(r.getDouble("totalDays"));//
                //String salratedaily = String.valueOf(r.getDouble("SalRateDaily"));//
                String basicSalary = decfor.format(r.getDouble("basicSalary"));//
                String allowanceAmount = decfor.format(r.getDouble("Allowance"));
                //String nightDiffHours = String.valueOf(r.getDouble("nightDiffhours")); //
                String nightDiffAmount = decfor.format(r.getDouble("nightDiffamount"));
                //String specialHolidaydays = String.valueOf(r.getDouble("specialHolidaydays"));
                String specialHolidayAmount = decfor.format(r.getDouble("specialHolidayamount"));
                //String SHNDhours = String.valueOf(r.getDouble("SHNDhours"));//
                String SHNDAmount = decfor.format(r.getDouble("SHNDamount"));
                //String LegalHolidayDays = String.valueOf(r.getDouble("legalHolidaydays"));//
                String LegalHolidayAmount = decfor.format(r.getDouble("legalHolidayamount"));
                //String LHNDhours = String.valueOf(r.getDouble("LHNDhours"));//
                String LHNDamount = decfor.format(r.getDouble("LHNDamount"));
                //String OThours = String.valueOf(r.getDouble("Overtimehours"));//
                String OTamount = decfor.format(r.getDouble("Overtimeamount"));
               // String OTNDhours = String.valueOf(r.getDouble("OTnightdiffhours"));//
                String OTNDamount = decfor.format(r.getDouble("OTnightdiffamount"));
                String leaveothersamount = decfor.format(r.getDouble("LeaveOthers"));
                String adjustmentamount = decfor.format(r.getDouble("Adjustment"));
                //String lateundertimemins = String.valueOf(r.getDouble("lateundertimemins"));//
                //String lateundertimerate = String.valueOf(r.getDouble("lateundertimerate"));//
                String lateundertimeamount = decfor.format(r.getDouble("lateundertimeamount"));
                String SSSamount = decfor.format(r.getDouble("SSSamount"));
                String philhealthamount = decfor.format(r.getDouble("PhilHealthamount"));
                String hdmfamount = decfor.format(r.getDouble("HDMFamount"));
                String otherdeductionamount = decfor.format(r.getDouble("OtherDeduction"));
                String datef = df.format(r.getDate("date"));
                String totaldeductions = decfor.format(r.getDouble("total_deductions"));
                String netpay = decfor.format(r.getBigDecimal("net_pay"));
                model.addRow(new Object[]{empId, fname, lname, totaldays, salratedaily, basicSalary, allowanceAmount, nightDiffAmount, specialHolidayAmount, SHNDAmount, LegalHolidayAmount, LHNDamount, OTamount, OTNDamount, leaveothersamount, adjustmentamount, lateundertimeamount, SSSamount, philhealthamount, hdmfamount, otherdeductionamount, datef, totaldeductions, netpay});
            }
            r.close();
            p.close();
        } catch (Exception e){
            System.err.println(e);
        }        
    }
    
    private Date getLast28Day(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -28);
        return cal.getTime();
    }
    
     public void openFile(String file){
        try{
            File path = new File(file);
            Desktop.getDesktop().open(path);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }
     
    public void exportarExcel(JTable jt){
        try{
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(jt);
            File saveFile= jFileChooser.getSelectedFile();
            if(saveFile != null){
                saveFile = new File(saveFile.toString()+".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("payroll");
                Row rowCol = sheet.createRow(0); 
                
                for(int i=0;i<jt.getColumnCount();i++){
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(jt.getColumnName(i));
                }
                /*
                for(int j=0;j<jt.getRowCount();j++){
                    Row row = sheet.createRow(j+1);
                    for(int k=0;k<jt.getColumnCount();k++){
                        Cell cell = row.createCell(k);
                        if(jt.getValueAt(j, k) != null){
                            cell.setCellValue(jt.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
                */
            double totalovertime = 0.0; // Variable to store total special holiday
            double totalovertimeND = 0.0; // Variable to store total legal holiday
            double totalSpecialHoliday = 0.0; // Variable to store total special holiday
            double totalLegalHoliday = 0.0; // Variable to store total legal holiday
            double totalSpecialHolidayND = 0.0; // Variable to store total special holiday
            double totalLegalHolidayND = 0.0; // Variable to store total legal holiday
            double totalNetPay = 0.0; // Variable to store total net pay
            double totaltotalDeductions = 0.0; 
            double totalsss = 0.0; 
            double totalphilhealth = 0.0; 
            double totalhdmf = 0.0; 
            
            String netPayColumnName = "Net Pay"; // Replace with the actual name of your net pay column
            int netPayColumnIndex = findNetPayColumnIndex(jt, netPayColumnName);
            // Before exporting Excel file
            String totalDeductionsColumnName = "Total Deductions"; // Replace with the actual name of your total deductions column
            int totalDeductionsColumnIndex = findTotalDeductionsColumnIndex(jt, totalDeductionsColumnName);
            
            String sssColumnName = "SSS"; 
            int sssColumnIndex = findTotalDeductionsColumnIndex(jt, sssColumnName);
            String philhealthColumnName = "PHIL HEALTH"; 
            int philhealthColumnIndex = findTotalDeductionsColumnIndex(jt, philhealthColumnName);
            String hdmfColumnName = "HDMF"; 
            int hdmfColumnIndex = findTotalDeductionsColumnIndex(jt, hdmfColumnName);
            // Before exporting Excel file
            String specialHolidayColumnName = "Special Holiday"; // Replace with the actual name of the "Special Holiday" column
            String legalHolidayColumnName = "Legal Holiday"; // Replace with the actual name of the "Legal Holiday" column
            String specialHolidayNDColumnName = "SH ND"; // Replace with the actual name of the "Special Holiday" column
            String legalHolidayNDColumnName = "LH ND"; // Replace with the actual name of the "Legal Holiday" column
            String OvertimeColumnName = "Overtime"; // Replace with the actual name of the "Special Holiday" column
            String OvertimeNDColumnName = "OT Night Diff"; // Replace with the actual name of the "Legal Holiday" column

            int specialHolidayColumnIndex = findColumnIndexByName(jt, specialHolidayColumnName);
            int legalHolidayColumnIndex = findColumnIndexByName(jt, legalHolidayColumnName);
            int specialHolidayNDColumnIndex = findColumnIndexByName(jt, specialHolidayNDColumnName);
            int legalHolidayNDColumnIndex = findColumnIndexByName(jt, legalHolidayNDColumnName);
            int OvertimeColumnIndex = findColumnIndexByName(jt, OvertimeColumnName);
            int OvertimeNDColumnIndex = findColumnIndexByName(jt, OvertimeNDColumnName);

            //double totalSpecialHoliday = calculateTotalColumnSum(jt, specialHolidayColumnIndex);
            //double totalLegalHoliday = calculateTotalColumnSum(jt, legalHolidayColumnIndex);
            
            // Adding data rows
            for(int j=0;j<jt.getRowCount();j++){
                Row row = sheet.createRow(j+1);
                double netPay = 0.0; 
                double totalDeductions = 0.0;// Variable to store net pay for each row
                double specialHoliday = 0.0; // Variable to store special holiday for each row
                double legalHoliday = 0.0; // Variable to store legal holiday for each row
                double specialHolidayND = 0.0; // Variable to store special holiday for each row
                double legalHolidayND = 0.0; // Variable to store legal holiday for each row
                double ot = 0.0; // Variable to store special holiday for each row
                double otnd = 0.0; // Variable to store legal holiday for each row
                double sssExport = 0.0; 
                double philhealthExport = 0.0; 
                double hdmfExport = 0.0; 
                for(int k=0;k<jt.getColumnCount();k++){
                    Cell cell = row.createCell(k);
                    if(jt.getValueAt(j, k) != null){
                        cell.setCellValue(jt.getValueAt(j, k).toString());
                        // Assuming net pay is in a specific column, adjust this index accordingly
                        if (k == netPayColumnIndex) {
                            netPay = Double.parseDouble(jt.getValueAt(j, k).toString());
                        }
                        // Inside the export method
                        if (k == totalDeductionsColumnIndex) {
                            totalDeductions = Double.parseDouble(jt.getValueAt(j, k).toString());
                         }
                        // Assuming special holiday is in a specific column, adjust this index accordingly
                        if (k == specialHolidayColumnIndex) {
                            specialHoliday = Double.parseDouble(jt.getValueAt(j, k).toString());
                        }
                        
                        // Assuming legal holiday is in a specific column, adjust this index accordingly
                        if (k == legalHolidayColumnIndex) {
                            legalHoliday = Double.parseDouble(jt.getValueAt(j, k).toString());
                        }
                        
                        if (k == specialHolidayNDColumnIndex) {
                            specialHolidayND = Double.parseDouble(jt.getValueAt(j, k).toString());
                        }
                        
                        // Assuming legal holiday is in a specific column, adjust this index accordingly
                        if (k == legalHolidayNDColumnIndex) {
                            legalHolidayND = Double.parseDouble(jt.getValueAt(j, k).toString());
                        }
                        
                        if (k == OvertimeColumnIndex) {
                            ot = Double.parseDouble(jt.getValueAt(j, k).toString());
                        }
                        
                        // Assuming legal holiday is in a specific column, adjust this index accordingly
                        if (k == OvertimeNDColumnIndex) {
                            otnd = Double.parseDouble(jt.getValueAt(j, k).toString());
                        }
                        
                        if (k == sssColumnIndex) {
                            sssExport = Double.parseDouble(jt.getValueAt(j, k).toString());
                        }
                       
                        if (k == philhealthColumnIndex) {
                            philhealthExport = Double.parseDouble(jt.getValueAt(j, k).toString());
                        }
                        
                        if (k == hdmfColumnIndex) {
                            hdmfExport = Double.parseDouble(jt.getValueAt(j, k).toString());
                        }

                    }
                }
                totalNetPay += netPay;
                totaltotalDeductions += totalDeductions;// Add net pay of this row to total net pay
                totalSpecialHoliday += specialHoliday; // Add special holiday of this row to total special holiday
                totalLegalHoliday += legalHoliday;
                totalSpecialHolidayND += specialHolidayND; // Add special holiday of this row to total special holiday
                totalLegalHolidayND += legalHolidayND;
                totalovertime += ot; // Add special holiday of this row to total special holiday
                totalovertimeND += otnd;
                totalsss += sssExport;
                totalphilhealth += philhealthExport;
                totalhdmf += hdmfExport;
                
                
            }
            
            // Adding total net pay row
            Row totalRow = sheet.createRow(jt.getRowCount() + 1);
            Cell totalLabelCell = totalRow.createCell(0);
            totalLabelCell.setCellValue("Cash Total:");
            Cell totalNetPayCell = totalRow.createCell(1);
            totalNetPayCell.setCellValue(totalNetPay);
            
            // Adding total net pay row
            Row GrandtotalRow = sheet.createRow(jt.getRowCount() + 2);
            Cell GrandtotalLabelCell = GrandtotalRow.createCell(0);
            GrandtotalLabelCell.setCellValue("Grand Total:");
            Cell GrandtotalNetPayCell = GrandtotalRow.createCell(1);
            GrandtotalNetPayCell.setCellValue(totalNetPay);
            
            // Adding total deductions row
            double governmentTotal = totalsss + totalphilhealth + totalhdmf;
            Row totalDeductionsRow = sheet.createRow(jt.getRowCount() + 3); // Ensure it's after the total net pay row
            Cell totalDeductionsLabelCell = totalDeductionsRow.createCell(0);
            totalDeductionsLabelCell.setCellValue("Government Total:");
            Cell totalDeductionsCell = totalDeductionsRow.createCell(1);
            totalDeductionsCell.setCellValue(governmentTotal);
            
            double totalHoliday = totalLegalHoliday + totalSpecialHoliday;
            Row totalSpecialHolidayRow = sheet.createRow(jt.getRowCount() + 4);
            Cell totalSpecialHolidayLabelCell = totalSpecialHolidayRow.createCell(0);
            totalSpecialHolidayLabelCell.setCellValue("Total Holiday:");
            Cell totalSpecialHolidayCell = totalSpecialHolidayRow.createCell(1);
            totalSpecialHolidayCell.setCellValue(totalHoliday);
            
            double totalHolidayND = totalLegalHolidayND + totalSpecialHolidayND;
            Row totalSpecialHolidayNDRow = sheet.createRow(jt.getRowCount() + 5);
            Cell totalSpecialHolidayNDLabelCell = totalSpecialHolidayNDRow.createCell(0);
            totalSpecialHolidayNDLabelCell.setCellValue("Total Holiday Night Diff:");
            Cell totalSpecialHolidayNDCell = totalSpecialHolidayNDRow.createCell(1);
            totalSpecialHolidayNDCell.setCellValue(totalHolidayND);
            
            double totaloovertimee = totalovertime + totalovertimeND;
            Row totaloovertimeeRow = sheet.createRow(jt.getRowCount() + 6);
            Cell totaloovertimeeLabelCell = totaloovertimeeRow.createCell(0);
            totaloovertimeeLabelCell.setCellValue("Total Overtime:");
            Cell totaloovertimeeCell = totaloovertimeeRow.createCell(1);
            totaloovertimeeCell.setCellValue(totaloovertimee);
            
            
            
            FileOutputStream out = new FileOutputStream(saveFile);
            wb.write(out);
            wb.close();
            out.close();
            openFile(saveFile.toString());
            }else{
                JOptionPane.showMessageDialog(null, "Error generating file");
            }
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        catch(IOException io){
            System.out.println(io);
        }
    }
    public int findNetPayColumnIndex(JTable jt, String netPayColumnName) {
    int columnIndex = -1; // Default value if column is not found

    // Iterate over column headers to find the net pay column
    for (int i = 0; i < jt.getColumnCount(); i++) {
        String columnName = jt.getColumnName(i);
        if (columnName.equals(netPayColumnName)) {
            columnIndex = i;
            break; // Column found, exit loop
        }
    }

    return columnIndex;
}
    public int findTotalDeductionsColumnIndex(JTable jt, String totalDeductionsColumnName) {
    int columnIndex = -1; // Default value if column is not found

    // Iterate over column headers to find the total deductions column
    for (int i = 0; i < jt.getColumnCount(); i++) {
        String columnName = jt.getColumnName(i);
        if (columnName.equals(totalDeductionsColumnName)) {
            columnIndex = i;
            break; // Column found, exit loop
        }
    }
    
    

    return columnIndex;
}
    
    
public int findColumnIndexByName(JTable jt, String columnName) {
    int columnIndex = -1; // Default value if column is not found

    // Iterate over column headers to find the column by name
    for (int i = 0; i < jt.getColumnCount(); i++) {
        String columnHeaderText = jt.getColumnName(i);
        if (columnHeaderText.equals(columnName)) {
            columnIndex = i;
            break; // Column found, exit loop
        }
    }

    return columnIndex;
}

public double calculateTotalColumnSum(JTable jt, int columnIndex) {
    double totalSum = 0.0;

    // Iterate over rows to calculate the total sum of the column
    for (int j = 0; j < jt.getRowCount(); j++) {
        Object value = jt.getValueAt(j, columnIndex);
        if (value != null && value instanceof Number) {
            totalSum += ((Number) value).doubleValue();
        }
    }

    return totalSum;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup_rd = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        deletesalarydeets_empid = new javax.swing.JTextField();
        deletesalarydeets_date = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        print_empid1 = new javax.swing.JTextField();
        print_date1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jDialog3 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        print_empfname = new javax.swing.JTextField();
        print_datevianame = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        print_emplname = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jFrame1 = new javax.swing.JFrame();
        panel_details1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbl_pay_name1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbl_pay_empId1 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        lbl_pay_desig1 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        lbl_pay_depart1 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        fromcutoffdatelabel1 = new javax.swing.JLabel();
        tocutoffdatelabel1 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        paydatelabel1 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        lbl_pay_basic1 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        lbl_allowance1 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        lbl_lateundertime1 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        lbl_SSS1 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        lbl_philhealth1 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        lbl_HDMF1 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        lbl_otherdeduction1 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        lbl_pay_deduct1 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        lbl_nightdiff1 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        lbl_specialholiday1 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        lbl_SHND1 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        lbl_LegalHoliday1 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        lbl_LHND1 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        lbl_OT1 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        lbl_OTND1 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        lbl_leaveothers1 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        lbl_adjustment1 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        lbl_pay_gross1 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        lbl_pay_net1 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        dailyratelbl1 = new javax.swing.JLabel();
        numdayslbl1 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        payslipNote_lbl = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        btn_pay_search1 = new javax.swing.JButton();
        btn_pay_exit1 = new javax.swing.JButton();
        btn_pay_print1 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jDialog4 = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        defSSS = new javax.swing.JTextField();
        defHDMF = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        defPhilhealth = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jDialog5 = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        getsalarydetailsviadate_empid1 = new javax.swing.JTextField();
        getsalarydetailsviadate_date1 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jDialog6 = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jDialog7 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        print_empid2 = new javax.swing.JTextField();
        print_date2 = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        jDialog8 = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel200 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        print_empid3 = new javax.swing.JTextField();
        print_date3 = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        intFrame_cutoff = new javax.swing.JInternalFrame();
        btn_exit_payroll1 = new javax.swing.JButton();
        txtDate2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        txt_pydt = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        intFrame_employee_new = new javax.swing.JInternalFrame();
        btn_exit = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        panel_empDetails = new javax.swing.JPanel();
        lbl_empId = new javax.swing.JLabel();
        lbl_fname = new javax.swing.JLabel();
        lbl_lname = new javax.swing.JLabel();
        txt_empID = new javax.swing.JTextField();
        txt_fname = new javax.swing.JTextField();
        txt_lname = new javax.swing.JTextField();
        lbl_designation = new javax.swing.JLabel();
        txt_designation = new javax.swing.JTextField();
        lbl_department = new javax.swing.JLabel();
        txt_deparment = new javax.swing.JTextField();
        rd_male = new javax.swing.JRadioButton();
        rd_female = new javax.swing.JRadioButton();
        jLabel46 = new javax.swing.JLabel();
        intFrame_employee_update = new javax.swing.JInternalFrame();
        btn_update = new javax.swing.JButton();
        btn_exit_update = new javax.swing.JButton();
        btn_search_update = new javax.swing.JButton();
        panel_empUpdate = new javax.swing.JPanel();
        lbl_empId1 = new javax.swing.JLabel();
        lbl_fname1 = new javax.swing.JLabel();
        lbl_lname1 = new javax.swing.JLabel();
        txt_empID_update = new javax.swing.JTextField();
        txt_fname_update = new javax.swing.JTextField();
        txt_lname_update = new javax.swing.JTextField();
        lbl_designation1 = new javax.swing.JLabel();
        txt_designation_update = new javax.swing.JTextField();
        lbl_department1 = new javax.swing.JLabel();
        txt_deparment_update = new javax.swing.JTextField();
        intFrame_employee_search = new javax.swing.JInternalFrame();
        jScrollPane_tableContainer = new javax.swing.JScrollPane();
        btn_searchEmp = new javax.swing.JButton();
        intFrame_payroll = new javax.swing.JInternalFrame();
        panel_empDetails_payroll = new javax.swing.JPanel();
        lbl_empId_allowance = new javax.swing.JLabel();
        txt_empId_allowance = new javax.swing.JTextField();
        lbl_fname_allowance = new javax.swing.JLabel();
        lbl_lname_allowance = new javax.swing.JLabel();
        lbl_desig_allowance = new javax.swing.JLabel();
        lbl_depart_allowance = new javax.swing.JLabel();
        lbl_sal_allowance = new javax.swing.JLabel();
        txt_fname_allowance = new javax.swing.JTextField();
        txt_lname_allowance = new javax.swing.JTextField();
        txt_desig_allowance = new javax.swing.JTextField();
        txt_depart_allowance = new javax.swing.JTextField();
        lbl_sal_allowance1 = new javax.swing.JLabel();
        lbl_sal_allowance2 = new javax.swing.JLabel();
        txt_totaldays = new javax.swing.JTextField();
        txt_salratedaily = new javax.swing.JTextField();
        txt_basicsalary = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtDate1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        fromcutofftxt = new javax.swing.JTextField();
        tocutofftxt = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        panel_salAllow_payroll = new javax.swing.JPanel();
        lbl_travel = new javax.swing.JLabel();
        lbl_food = new javax.swing.JLabel();
        lbl_performance = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nightdiffhours = new javax.swing.JTextField();
        txt_specialholidayhours = new javax.swing.JTextField();
        txt_SHNDhours = new javax.swing.JTextField();
        txt_nightdiffamount = new javax.swing.JTextField();
        txt_specialholidayamount = new javax.swing.JTextField();
        txt_SHNDamount = new javax.swing.JTextField();
        lbl_performance2 = new javax.swing.JLabel();
        lbl_performance3 = new javax.swing.JLabel();
        txt_LegalHolidayhours = new javax.swing.JTextField();
        txt_LegalHolidayamount = new javax.swing.JTextField();
        lbl_performance4 = new javax.swing.JLabel();
        txt_LHNDhours = new javax.swing.JTextField();
        txt_LHNDamount = new javax.swing.JTextField();
        lbl_performance5 = new javax.swing.JLabel();
        txt_overtimehours = new javax.swing.JTextField();
        txt_overtimeamount = new javax.swing.JTextField();
        lbl_performance6 = new javax.swing.JLabel();
        txt_OTNDhours = new javax.swing.JTextField();
        txt_OTNDamount = new javax.swing.JTextField();
        lbl_performance7 = new javax.swing.JLabel();
        lbl_performance8 = new javax.swing.JLabel();
        txt_leaveothersamount = new javax.swing.JTextField();
        txt_adjustmentamount = new javax.swing.JTextField();
        txt_allowance = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        panel_salDeduct_payroll = new javax.swing.JPanel();
        lbl_epf = new javax.swing.JLabel();
        lbl_tax = new javax.swing.JLabel();
        lbl_paye = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_lateundertimemins = new javax.swing.JTextField();
        txt_lateundertimeamount = new javax.swing.JTextField();
        lbl_paye1 = new javax.swing.JLabel();
        lbl_paye2 = new javax.swing.JLabel();
        txt_SSSamount = new javax.swing.JTextField();
        txt_PHILHEALTHamount = new javax.swing.JTextField();
        txt_HDMFamount = new javax.swing.JTextField();
        txt_otherdeductionamount = new javax.swing.JTextField();
        btn_search_payroll = new javax.swing.JButton();
        btn_add_payroll = new javax.swing.JButton();
        btn_exit_payroll = new javax.swing.JButton();
        btn_update_payroll = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        totaldeductiontxt = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        totalearningstxt = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        netpaytxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        intFrame_print = new javax.swing.JInternalFrame();
        panel_details = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_pay_name = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_pay_empId = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_pay_desig = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_pay_depart = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        fromcutoffdatelabel = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        tocutoffdatelabel = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        paydatelabel = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_pay_basic = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_allowance = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbl_lateundertime = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbl_SSS = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lbl_philhealth = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lbl_HDMF = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lbl_otherdeduction = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lbl_pay_deduct = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_nightdiff = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbl_specialholiday = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_SHND = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_LegalHoliday = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_LHND = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbl_OT = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lbl_OTND = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        lbl_leaveothers = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lbl_adjustment = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbl_pay_gross = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lbl_pay_net = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        dailyratelbl = new javax.swing.JLabel();
        numdayslbl = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        btn_pay_search = new javax.swing.JButton();
        btn_pay_exit = new javax.swing.JButton();
        btn_pay_print = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        intFrame_leave = new javax.swing.JInternalFrame();
        panel_empDetails_payroll1 = new javax.swing.JPanel();
        lbl_empId_allowance1 = new javax.swing.JLabel();
        txt_empId_leave = new javax.swing.JTextField();
        lbl_fname_allowance1 = new javax.swing.JLabel();
        lbl_lname_allowance1 = new javax.swing.JLabel();
        lbl_desig_allowance1 = new javax.swing.JLabel();
        lbl_depart_allowance1 = new javax.swing.JLabel();
        txt_fname_leave = new javax.swing.JTextField();
        txt_lname_leave = new javax.swing.JTextField();
        txt_desig_leave = new javax.swing.JTextField();
        txt_depart_leave = new javax.swing.JTextField();
        panel_salAllow_payroll1 = new javax.swing.JPanel();
        lbl_travel1 = new javax.swing.JLabel();
        lbl_food1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_vl_count = new javax.swing.JTextField();
        txt_sl_count = new javax.swing.JTextField();
        txt_vl_apply = new javax.swing.JTextField();
        txt_sl_apply = new javax.swing.JTextField();
        btn_search_leave = new javax.swing.JButton();
        btn_apply_leave = new javax.swing.JButton();
        btn_exit_leave = new javax.swing.JButton();
        btn_reset_leave = new javax.swing.JButton();
        btn_update_leave = new javax.swing.JButton();
        lbl_pms = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();
        menu_menuBar = new javax.swing.JMenuBar();
        menuBar_file = new javax.swing.JMenu();
        menuBar_file_logout = new javax.swing.JMenuItem();
        menuBar_file_exit = new javax.swing.JMenuItem();
        menuBar_employee = new javax.swing.JMenu();
        menuBar_employee_new = new javax.swing.JMenuItem();
        menuBar_employee_update = new javax.swing.JMenuItem();
        menuBar_employee_delete = new javax.swing.JMenuItem();
        menuBar_employee_search = new javax.swing.JMenuItem();
        menuBar_payroll = new javax.swing.JMenu();
        menuBar_payroll_add = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuBar_leave = new javax.swing.JMenu();
        menuBar_leave_apply = new javax.swing.JMenuItem();
        menuBar_paySlip = new javax.swing.JMenu();
        menuBar_payslip_print = new javax.swing.JMenuItem();

        jDialog1.setLocation(new java.awt.Point(500, 250));
        jDialog1.setResizable(false);
        jDialog1.setSize(new java.awt.Dimension(500, 250));

        jLabel52.setText("Employee ID:");

        jLabel53.setText("Pay Date:");

        deletesalarydeets_empid.setText(" ");
        deletesalarydeets_empid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletesalarydeets_empidActionPerformed(evt);
            }
        });

        deletesalarydeets_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletesalarydeets_dateActionPerformed(evt);
            }
        });

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel53))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deletesalarydeets_empid, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(deletesalarydeets_date))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(deletesalarydeets_empid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deletesalarydeets_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(254, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog2.setLocation(new java.awt.Point(500, 250));
        jDialog2.setResizable(false);
        jDialog2.setSize(new java.awt.Dimension(500, 250));

        jLabel54.setText("Employee ID:");

        jLabel55.setText("Pay Date:");

        print_empid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_empid1ActionPerformed(evt);
            }
        });

        print_date1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_date1ActionPerformed(evt);
            }
        });

        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(jLabel55))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(print_empid1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(print_date1))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(print_empid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(print_date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(254, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog3.setLocation(new java.awt.Point(500, 250));
        jDialog3.setResizable(false);
        jDialog3.setSize(new java.awt.Dimension(500, 250));

        jLabel62.setText("Employee First Name:");

        jLabel63.setText("Pay Date:");

        print_empfname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_empfnameActionPerformed(evt);
            }
        });

        print_datevianame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_datevianameActionPerformed(evt);
            }
        });

        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        print_emplname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_emplnameActionPerformed(evt);
            }
        });

        jLabel64.setText("Employee Last Name:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(print_empfname, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(print_datevianame)
                            .addComponent(print_emplname, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(print_empfname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(print_emplname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(print_datevianame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jFrame1.setSize(new java.awt.Dimension(500, 500));

        panel_details1.setBackground(new java.awt.Color(255, 255, 255));
        panel_details1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        panel_details1.setToolTipText("");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("EMPLOYEE NAME:");

        lbl_pay_name1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("EMPLOYEE NO.:");

        lbl_pay_empId1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel66.setText("DESIGNATION:");

        lbl_pay_desig1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel67.setText("DEPARTMENT:");

        lbl_pay_depart1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel68.setText("CUTOFF PERIOD:");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel69.setText("-");

        fromcutoffdatelabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fromcutoffdatelabel1.setText(" ");

        tocutoffdatelabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setText("DAILY RATE:");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setText("NUMBER OF DAYS:");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel73.setText("PAYSLIP FOR");

        paydatelabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        paydatelabel1.setText(" ");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel74.setText("PARANAQUE CITY");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel75.setText("7-I A. MAYUGA STREET, MIAA ROAD, TAMBO, ");

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel76.setText("DOTA AERO AVIATION SERVICES INC.");

        jLabel77.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel77.setText("EARNINGS");

        jLabel78.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel78.setText("AMOUNT (PHP)");
        jLabel78.setAlignmentY(0.0F);

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel79.setText("BASIC SALARY");

        lbl_pay_basic1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel80.setText("ALLOWANCE");

        lbl_allowance1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel81.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel81.setText("DEDUCTIONS");

        jLabel82.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel82.setText("AMOUNT (PHP)");

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel83.setText("LATE/UNDERTIME");

        lbl_lateundertime1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel84.setText("SSS");

        lbl_SSS1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel85.setText("PHILHEALTH");

        lbl_philhealth1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel86.setText("HDMF");

        lbl_HDMF1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_HDMF1.setText(" ");

        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel87.setText("OTHER DEDUCTION");

        lbl_otherdeduction1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel89.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel89.setText("TOTAL DEDUCTIONS");

        lbl_pay_deduct1.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel90.setText("NIGHT DIFFERENTIAL");

        lbl_nightdiff1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel91.setText("SPECIAL HOLIDAY");

        lbl_specialholiday1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel92.setText("SH ND");

        lbl_SHND1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel93.setText("LEGAL HOLIDAY");

        lbl_LegalHoliday1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_LegalHoliday1.setText(" ");

        jLabel94.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel94.setText("LH ND");

        lbl_LHND1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel95.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel95.setText("OVERTIME");

        lbl_OT1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel96.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel96.setText("OT ND");

        lbl_OTND1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel97.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel97.setText("LEAVE/OTHERS");

        lbl_leaveothers1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_leaveothers1.setText(" ");

        jLabel98.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel98.setText("ADJUSTMENT");

        lbl_adjustment1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_adjustment1.setText(" ");

        jLabel99.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel99.setText("TOTAL EARNINGS");

        lbl_pay_gross1.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N

        jLabel100.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel100.setText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        lbl_pay_net1.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N

        jLabel101.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel101.setText("NET PAY");

        jLabel102.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel102.setText("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        dailyratelbl1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dailyratelbl1.setText(" ");

        numdayslbl1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        numdayslbl1.setText(" ");

        jLabel103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/forms/images/doavicon.png"))); // NOI18N
        jLabel103.setText(" ");

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel113.setText("NOTE:");

        payslipNote_lbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        payslipNote_lbl.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel114.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel114.setText("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel88.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel88.setText("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        javax.swing.GroupLayout panel_details1Layout = new javax.swing.GroupLayout(panel_details1);
        panel_details1.setLayout(panel_details1Layout);
        panel_details1Layout.setHorizontalGroup(
            panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_details1Layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(jLabel101)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_pay_net1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_details1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panel_details1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_details1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jLabel76))
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jLabel73)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paydatelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel75))
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(jLabel74))))
                    .addGroup(panel_details1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79)
                            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_details1Layout.createSequentialGroup()
                                    .addComponent(jLabel99)
                                    .addGap(174, 174, 174)
                                    .addComponent(lbl_pay_gross1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel_details1Layout.createSequentialGroup()
                                    .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panel_details1Layout.createSequentialGroup()
                                            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel92)
                                                .addComponent(jLabel80)
                                                .addComponent(jLabel97)
                                                .addComponent(jLabel98)
                                                .addComponent(jLabel91))
                                            .addGap(208, 208, 208))
                                        .addGroup(panel_details1Layout.createSequentialGroup()
                                            .addComponent(jLabel94)
                                            .addGap(281, 281, 281))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_details1Layout.createSequentialGroup()
                                            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel93, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel96, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel95, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbl_pay_basic1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_nightdiff1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_OT1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_LegalHoliday1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_LHND1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_SHND1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_allowance1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_leaveothers1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_adjustment1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_specialholiday1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel90))
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel84)
                                    .addComponent(jLabel85)
                                    .addComponent(jLabel86)))
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel87))
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel113))
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel89))))
                    .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panel_details1Layout.createSequentialGroup()
                            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel67)
                                .addComponent(jLabel77))
                            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel_details1Layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(lbl_pay_depart1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(panel_details1Layout.createSequentialGroup()
                                    .addGap(224, 224, 224)
                                    .addComponent(jLabel78)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel81)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel82))))
                        .addComponent(lbl_pay_deduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_SSS1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_philhealth1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_HDMF1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel_details1Layout.createSequentialGroup()
                            .addComponent(lbl_OTND1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(212, 212, 212)
                            .addComponent(lbl_lateundertime1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lbl_otherdeduction1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_details1Layout.createSequentialGroup()
                            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel_details1Layout.createSequentialGroup()
                                    .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel66))
                                    .addGap(27, 27, 27))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_details1Layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl_pay_empId1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_pay_name1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                                .addComponent(lbl_pay_desig1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(73, 73, 73)
                            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel_details1Layout.createSequentialGroup()
                                    .addComponent(jLabel71)
                                    .addGap(49, 49, 49)
                                    .addComponent(dailyratelbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel_details1Layout.createSequentialGroup()
                                    .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel72)
                                        .addComponent(jLabel68))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(numdayslbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panel_details1Layout.createSequentialGroup()
                                            .addComponent(fromcutoffdatelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel69)
                                            .addGap(18, 18, 18)
                                            .addComponent(tocutoffdatelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(9, 9, 9)))
                    .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_details1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_details1Layout.createSequentialGroup()
                    .addContainerGap(449, Short.MAX_VALUE)
                    .addComponent(payslipNote_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(62, 62, 62)))
        );
        panel_details1Layout.setVerticalGroup(
            panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_details1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_details1Layout.createSequentialGroup()
                        .addComponent(jLabel103)
                        .addGap(12, 12, 12))
                    .addGroup(panel_details1Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(paydatelabel1))))
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_details1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(dailyratelbl1))
                        .addGap(4, 4, 4)
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72)
                            .addComponent(numdayslbl1)))
                    .addGroup(panel_details1Layout.createSequentialGroup()
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_pay_empId1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_pay_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_details1Layout.createSequentialGroup()
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lbl_pay_desig1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tocutoffdatelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel68)
                                        .addComponent(fromcutoffdatelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel69)))))
                        .addGap(11, 11, 11)
                        .addComponent(lbl_pay_depart1, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_details1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel67)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel78)
                    .addComponent(jLabel81)
                    .addComponent(jLabel82))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_details1Layout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel90)
                        .addGap(10, 10, 10)
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_OT1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addComponent(jLabel83)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_details1Layout.createSequentialGroup()
                                        .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(74, 74, 74))
                                    .addGroup(panel_details1Layout.createSequentialGroup()
                                        .addComponent(lbl_LHND1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_specialholiday1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(lbl_allowance1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_leaveothers1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))))
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel96)
                                    .addComponent(lbl_OTND1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panel_details1Layout.createSequentialGroup()
                                        .addComponent(lbl_LegalHoliday1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(57, 57, 57)
                                        .addComponent(lbl_SHND1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel_details1Layout.createSequentialGroup()
                                        .addComponent(jLabel93)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel94)
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel91)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel92)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel80)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel97))))
                    .addGroup(panel_details1Layout.createSequentialGroup()
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel84)
                                    .addComponent(lbl_pay_basic1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_nightdiff1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel85)))
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addComponent(lbl_SSS1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_philhealth1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lbl_HDMF1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_lateundertime1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_details1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_otherdeduction1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel98)
                    .addComponent(lbl_adjustment1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_details1Layout.createSequentialGroup()
                        .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel99)
                                .addComponent(jLabel89))
                            .addComponent(lbl_pay_deduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_pay_gross1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel101))
                    .addComponent(lbl_pay_net1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(panel_details1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel_details1Layout.createSequentialGroup()
                    .addGap(426, 426, 426)
                    .addComponent(payslipNote_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(92, Short.MAX_VALUE)))
        );

        btn_pay_search1.setText("Search Employee ID");
        btn_pay_search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pay_search1ActionPerformed(evt);
            }
        });

        btn_pay_exit1.setText("Exit");
        btn_pay_exit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pay_exit1ActionPerformed(evt);
            }
        });

        btn_pay_print1.setText("Print Pay Slip");
        btn_pay_print1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pay_print1ActionPerformed(evt);
            }
        });

        jButton14.setText("Search Employee Name");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Capture Payslip 1");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Capture Payslip 2");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Add Note");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap(871, Short.MAX_VALUE)
                .addComponent(panel_details1, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14)
                    .addComponent(btn_pay_search1)
                    .addComponent(jButton15)
                    .addComponent(jButton16)
                    .addComponent(btn_pay_print1)
                    .addComponent(btn_pay_exit1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17))
                .addGap(119, 119, 119))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addComponent(panel_details1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 248, Short.MAX_VALUE))
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pay_search1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17)
                .addGap(5, 5, 5)
                .addComponent(btn_pay_print1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pay_exit1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialog4.setLocation(new java.awt.Point(500, 250));
        jDialog4.setResizable(false);
        jDialog4.setSize(new java.awt.Dimension(500, 250));

        jLabel104.setText("SSS:");

        jLabel105.setText("HDMF:");

        defSSS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defSSSActionPerformed(evt);
            }
        });

        defHDMF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defHDMFActionPerformed(evt);
            }
        });

        jButton19.setText("Update");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        defPhilhealth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defPhilhealthActionPerformed(evt);
            }
        });

        jLabel106.setText("Philhealth:");

        jLabel107.setText("Default Deductions");

        jButton20.setText("Exit");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton20))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel104)
                                    .addComponent(jLabel105)
                                    .addComponent(jLabel106))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(defSSS, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                    .addComponent(defHDMF)
                                    .addComponent(defPhilhealth, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel107)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel107)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(defSSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106)
                    .addComponent(defPhilhealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(defHDMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel105))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19)
                    .addComponent(jButton20))
                .addContainerGap(233, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog5.setLocation(new java.awt.Point(500, 250));
        jDialog5.setResizable(false);
        jDialog5.setSize(new java.awt.Dimension(500, 250));

        jLabel110.setText("Employee ID:");

        jLabel111.setText("Pay Date:");

        getsalarydetailsviadate_empid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getsalarydetailsviadate_empid1ActionPerformed(evt);
            }
        });

        getsalarydetailsviadate_date1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getsalarydetailsviadate_date1ActionPerformed(evt);
            }
        });

        jButton13.setText("Search");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel110)
                            .addComponent(jLabel111))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(getsalarydetailsviadate_empid1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(getsalarydetailsviadate_date1))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(getsalarydetailsviadate_empid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getsalarydetailsviadate_date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel111))
                .addGap(18, 18, 18)
                .addComponent(jButton13)
                .addContainerGap(254, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog5Layout = new javax.swing.GroupLayout(jDialog5.getContentPane());
        jDialog5.getContentPane().setLayout(jDialog5Layout);
        jDialog5Layout.setHorizontalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog5Layout.setVerticalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog6.setLocation(new java.awt.Point(500, 250));
        jDialog6.setResizable(false);
        jDialog6.setSize(new java.awt.Dimension(500, 500));

        jLabel112.setText("Note:");

        jButton23.setText("Add");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112)
                    .addComponent(jButton23)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel112)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton23)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog6Layout = new javax.swing.GroupLayout(jDialog6.getContentPane());
        jDialog6.getContentPane().setLayout(jDialog6Layout);
        jDialog6Layout.setHorizontalGroup(
            jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog6Layout.setVerticalGroup(
            jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog7.setLocation(new java.awt.Point(500, 250));
        jDialog7.setResizable(false);
        jDialog7.setSize(new java.awt.Dimension(500, 250));

        jLabel198.setText("Employee ID:");

        jLabel199.setText("Pay Date:");

        print_empid2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_empid2ActionPerformed(evt);
            }
        });

        print_date2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_date2ActionPerformed(evt);
            }
        });

        jButton28.setText("Search");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton28)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel198)
                            .addComponent(jLabel199))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(print_empid2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(print_date2))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel198)
                    .addComponent(print_empid2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(print_date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel199))
                .addGap(18, 18, 18)
                .addComponent(jButton28)
                .addContainerGap(254, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog7Layout = new javax.swing.GroupLayout(jDialog7.getContentPane());
        jDialog7.getContentPane().setLayout(jDialog7Layout);
        jDialog7Layout.setHorizontalGroup(
            jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog7Layout.setVerticalGroup(
            jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog8.setLocation(new java.awt.Point(500, 250));
        jDialog8.setResizable(false);
        jDialog8.setSize(new java.awt.Dimension(500, 250));

        jLabel200.setText("Employee ID:");

        jLabel201.setText("Pay Date:");

        print_empid3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_empid3ActionPerformed(evt);
            }
        });

        print_date3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_date3ActionPerformed(evt);
            }
        });

        jButton29.setText("Search");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton29)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel200)
                            .addComponent(jLabel201))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(print_empid3, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(print_date3))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel200)
                    .addComponent(print_empid3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(print_date3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel201))
                .addGap(18, 18, 18)
                .addComponent(jButton29)
                .addContainerGap(254, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog8Layout = new javax.swing.GroupLayout(jDialog8.getContentPane());
        jDialog8.getContentPane().setLayout(jDialog8Layout);
        jDialog8Layout.setHorizontalGroup(
            jDialog8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog8Layout.setVerticalGroup(
            jDialog8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Payroll Management System | Home");
        setLocation(new java.awt.Point(100, 0));
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setName("Home"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(null);

        intFrame_cutoff.setClosable(true);
        intFrame_cutoff.setVisible(false);

        btn_exit_payroll1.setText("Exit");
        btn_exit_payroll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exit_payroll1ActionPerformed(evt);
            }
        });

        txtDate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDate2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "First Name", "Last Name", "Total Days", "Sal. Rate Daily", "Basic Salary", "Allowance", "Night Diff", "Special Holiday", "SH ND", "Legal Holiday", "LH ND", "Overtime", "OT Night Diff", "Leave/Others", "	Adjustment", "Late/Undertime", "SSS", "PHIL HEALTH", "HDMF", "Other Deduction", "Pay Date", "Total Deductions", "Net Pay"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(0);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(13).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(14).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(16).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(18).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(20).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(21).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(22).setPreferredWidth(100);
        }

        jLabel51.setText("Sort by Cutoff:");

        jButton3.setText("Search by Employee ID");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Display All");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel65.setText("Search by PayDate:");

        jButton10.setText("Export");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Search");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout intFrame_cutoffLayout = new javax.swing.GroupLayout(intFrame_cutoff.getContentPane());
        intFrame_cutoff.getContentPane().setLayout(intFrame_cutoffLayout);
        intFrame_cutoffLayout.setHorizontalGroup(
            intFrame_cutoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intFrame_cutoffLayout.createSequentialGroup()
                .addGroup(intFrame_cutoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(intFrame_cutoffLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(intFrame_cutoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(intFrame_cutoffLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(intFrame_cutoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel65)
                                    .addGroup(intFrame_cutoffLayout.createSequentialGroup()
                                        .addComponent(jLabel51)
                                        .addGap(6, 6, 6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(intFrame_cutoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(intFrame_cutoffLayout.createSequentialGroup()
                                        .addComponent(txt_pydt, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton11))
                                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(intFrame_cutoffLayout.createSequentialGroup()
                                .addGroup(intFrame_cutoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jButton4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(intFrame_cutoffLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_exit_payroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
            .addGroup(intFrame_cutoffLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1172, Short.MAX_VALUE)
                .addContainerGap())
        );
        intFrame_cutoffLayout.setVerticalGroup(
            intFrame_cutoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intFrame_cutoffLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(intFrame_cutoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(intFrame_cutoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(txt_pydt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11))
                .addGap(6, 6, 6)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(intFrame_cutoffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_exit_payroll1)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        getContentPane().add(intFrame_cutoff);
        intFrame_cutoff.setBounds(0, 0, 1190, 680);

        intFrame_employee_new.setClosable(true);
        intFrame_employee_new.setTitle("Enter Employee Details");
        intFrame_employee_new.setToolTipText("");
        intFrame_employee_new.setMaximumSize(new java.awt.Dimension(800, 500));
        intFrame_employee_new.setMinimumSize(new java.awt.Dimension(800, 500));
        intFrame_employee_new.setPreferredSize(new java.awt.Dimension(800, 500));
        intFrame_employee_new.setVisible(false);
        intFrame_employee_new.getContentPane().setLayout(null);

        btn_exit.setText("Exit");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });
        intFrame_employee_new.getContentPane().add(btn_exit);
        btn_exit.setBounds(400, 290, 120, 23);

        btn_add.setText("Add Employee");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        intFrame_employee_new.getContentPane().add(btn_add);
        btn_add.setBounds(270, 290, 120, 23);

        panel_empDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "New Employee Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        lbl_empId.setText("Employee ID*");

        lbl_fname.setText("First Name*");

        lbl_lname.setText("Last Name*");

        txt_empID.setToolTipText("Eg: 0001");

        lbl_designation.setText("Designation*");

        lbl_department.setText("Department*");

        rd_male.setText("Male");

        rd_female.setText("Female");

        jLabel46.setText("Gender");

        javax.swing.GroupLayout panel_empDetailsLayout = new javax.swing.GroupLayout(panel_empDetails);
        panel_empDetails.setLayout(panel_empDetailsLayout);
        panel_empDetailsLayout.setHorizontalGroup(
            panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_empDetailsLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_empDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(136, 136, 136)
                        .addComponent(rd_male)
                        .addGap(42, 42, 42)
                        .addComponent(rd_female)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_empDetailsLayout.createSequentialGroup()
                        .addGroup(panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_empId)
                            .addComponent(lbl_fname)
                            .addComponent(lbl_lname)
                            .addComponent(lbl_designation)
                            .addComponent(lbl_department))
                        .addGap(89, 89, 89)
                        .addGroup(panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_empDetailsLayout.createSequentialGroup()
                                .addComponent(txt_deparment, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel_empDetailsLayout.createSequentialGroup()
                                .addGroup(panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_empID, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_designation, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(36, Short.MAX_VALUE))))))
        );
        panel_empDetailsLayout.setVerticalGroup(
            panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_empDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_empId)
                    .addComponent(txt_empID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_fname)
                    .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_lname)
                    .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_empDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(7, 7, 7)
                        .addComponent(lbl_designation))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_empDetailsLayout.createSequentialGroup()
                        .addGroup(panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rd_male)
                            .addComponent(rd_female))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_designation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_empDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_department)
                    .addComponent(txt_deparment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        intFrame_employee_new.getContentPane().add(panel_empDetails);
        panel_empDetails.setBounds(60, 40, 470, 240);

        getContentPane().add(intFrame_employee_new);
        intFrame_employee_new.setBounds(0, 0, 1200, 680);
        try {
            intFrame_employee_new.setMaximum(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        intFrame_employee_update.setClosable(true);
        intFrame_employee_update.setTitle("Update Employee Details");
        intFrame_employee_update.setMaximumSize(new java.awt.Dimension(800, 500));
        intFrame_employee_update.setMinimumSize(new java.awt.Dimension(800, 500));
        intFrame_employee_update.setPreferredSize(new java.awt.Dimension(800, 500));
        intFrame_employee_update.setVisible(false);
        intFrame_employee_update.getContentPane().setLayout(null);

        btn_update.setText("Update Employee");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        intFrame_employee_update.getContentPane().add(btn_update);
        btn_update.setBounds(200, 420, 150, 23);

        btn_exit_update.setText("Exit");
        btn_exit_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exit_updateActionPerformed(evt);
            }
        });
        intFrame_employee_update.getContentPane().add(btn_exit_update);
        btn_exit_update.setBounds(360, 420, 150, 23);

        btn_search_update.setText("Search");
        btn_search_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_updateActionPerformed(evt);
            }
        });
        intFrame_employee_update.getContentPane().add(btn_search_update);
        btn_search_update.setBounds(40, 420, 150, 23);

        panel_empUpdate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Employee Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        lbl_empId1.setText("Employee ID");

        lbl_fname1.setText("First Name*");

        lbl_lname1.setText("Last Name*");

        txt_empID_update.setFocusable(false);

        lbl_designation1.setText("Designation*");

        lbl_department1.setText("Department*");

        javax.swing.GroupLayout panel_empUpdateLayout = new javax.swing.GroupLayout(panel_empUpdate);
        panel_empUpdate.setLayout(panel_empUpdateLayout);
        panel_empUpdateLayout.setHorizontalGroup(
            panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_empUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_fname1)
                    .addComponent(lbl_lname1)
                    .addComponent(lbl_empId1)
                    .addComponent(lbl_designation1)
                    .addComponent(lbl_department1))
                .addGap(104, 104, 104)
                .addGroup(panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_empUpdateLayout.createSequentialGroup()
                        .addGroup(panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_lname_update, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fname_update, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_empID_update, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(44, Short.MAX_VALUE))
                    .addGroup(panel_empUpdateLayout.createSequentialGroup()
                        .addGroup(panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_deparment_update, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_designation_update, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel_empUpdateLayout.setVerticalGroup(
            panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_empUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_empID_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_empId1))
                .addGap(28, 28, 28)
                .addGroup(panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_fname_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fname1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_lname_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_lname1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_designation_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_designation1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_empUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_deparment_update, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_department1))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        intFrame_employee_update.getContentPane().add(panel_empUpdate);
        panel_empUpdate.setBounds(30, 10, 470, 290);

        getContentPane().add(intFrame_employee_update);
        intFrame_employee_update.setBounds(0, 0, 1200, 680);
        try {
            intFrame_employee_update.setMaximum(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        intFrame_employee_search.setClosable(true);
        intFrame_employee_search.setTitle("Search Employee Details");
        intFrame_employee_search.setMaximumSize(new java.awt.Dimension(800, 500));
        intFrame_employee_search.setMinimumSize(new java.awt.Dimension(800, 500));
        intFrame_employee_search.setPreferredSize(new java.awt.Dimension(800, 500));
        intFrame_employee_search.setVisible(false);

        jScrollPane_tableContainer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(255, 0, 20))); // NOI18N

        btn_searchEmp.setText("Search Employee");
        btn_searchEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchEmpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout intFrame_employee_searchLayout = new javax.swing.GroupLayout(intFrame_employee_search.getContentPane());
        intFrame_employee_search.getContentPane().setLayout(intFrame_employee_searchLayout);
        intFrame_employee_searchLayout.setHorizontalGroup(
            intFrame_employee_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intFrame_employee_searchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(intFrame_employee_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane_tableContainer)
                    .addGroup(intFrame_employee_searchLayout.createSequentialGroup()
                        .addComponent(btn_searchEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        intFrame_employee_searchLayout.setVerticalGroup(
            intFrame_employee_searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, intFrame_employee_searchLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_searchEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane_tableContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        getContentPane().add(intFrame_employee_search);
        intFrame_employee_search.setBounds(0, 0, 1200, 680);
        try {
            intFrame_employee_search.setMaximum(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        intFrame_payroll.setClosable(true);
        intFrame_payroll.setTitle("Enter | Update Salary Details\n");
        intFrame_payroll.setMaximumSize(new java.awt.Dimension(800, 500));
        intFrame_payroll.setMinimumSize(new java.awt.Dimension(800, 500));
        intFrame_payroll.setName(""); // NOI18N
        intFrame_payroll.setPreferredSize(new java.awt.Dimension(1000, 600));
        intFrame_payroll.setVisible(false);

        panel_empDetails_payroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        lbl_empId_allowance.setText("Employee ID");

        txt_empId_allowance.setFocusable(false);

        lbl_fname_allowance.setText("First Name");

        lbl_lname_allowance.setText("Last Name");

        lbl_desig_allowance.setText("Designation");

        lbl_depart_allowance.setText("Department");

        lbl_sal_allowance.setText("Basic Salary");

        txt_fname_allowance.setFocusable(false);

        txt_lname_allowance.setFocusable(false);

        txt_desig_allowance.setFocusable(false);

        txt_depart_allowance.setFocusable(false);

        lbl_sal_allowance1.setText("Total Days");

        lbl_sal_allowance2.setText("Sal.Rate Daily");

        txt_totaldays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totaldaysActionPerformed(evt);
            }
        });

        txt_salratedaily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_salratedailyActionPerformed(evt);
            }
        });

        txt_basicsalary.setFocusable(false);
        txt_basicsalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_basicsalaryActionPerformed(evt);
            }
        });

        jLabel41.setText("Pay Date");

        jLabel42.setText("From");

        jLabel43.setText("To");

        fromcutofftxt.setText(" ");

        tocutofftxt.setText(" ");

        jLabel45.setText("Cutoff");

        javax.swing.GroupLayout panel_empDetails_payrollLayout = new javax.swing.GroupLayout(panel_empDetails_payroll);
        panel_empDetails_payroll.setLayout(panel_empDetails_payrollLayout);
        panel_empDetails_payrollLayout.setHorizontalGroup(
            panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_empDetails_payrollLayout.createSequentialGroup()
                .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(lbl_fname_allowance)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_fname_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                                .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(lbl_empId_allowance))
                                    .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lbl_lname_allowance)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_empId_allowance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_lname_allowance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(88, 88, 88))
                    .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_desig_allowance)
                            .addComponent(lbl_depart_allowance))
                        .addGap(18, 18, 18)
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_desig_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_depart_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(lbl_sal_allowance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_basicsalary, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lbl_sal_allowance1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_totaldays, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_sal_allowance2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_salratedaily, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(52, 52, 52)
                                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fromcutofftxt, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tocutofftxt, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panel_empDetails_payrollLayout.setVerticalGroup(
            panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_empId_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_empId_allowance))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_fname_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_fname_allowance))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_lname_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_lname_allowance))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_desig_allowance)
                            .addComponent(txt_desig_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_depart_allowance)
                            .addComponent(txt_depart_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_empDetails_payrollLayout.createSequentialGroup()
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_sal_allowance1)
                            .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_totaldays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_sal_allowance2)
                                .addComponent(txt_salratedaily, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_sal_allowance)
                            .addComponent(txt_basicsalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(tocutofftxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fromcutofftxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_empDetails_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        panel_salAllow_payroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salary Allowances", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(255, 0, 0))); // NOI18N
        panel_salAllow_payroll.setPreferredSize(new java.awt.Dimension(400, 550));

        lbl_travel.setText("Allowance");

        lbl_food.setText("Night Diff");

        lbl_performance.setText("Special Holiday");

        jLabel4.setText("Hours");

        jLabel5.setText("Amount");

        txt_nightdiffhours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nightdiffhoursActionPerformed(evt);
            }
        });
        txt_nightdiffhours.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nightdiffhoursKeyTyped(evt);
            }
        });

        txt_specialholidayhours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_specialholidayhoursActionPerformed(evt);
            }
        });

        txt_SHNDhours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SHNDhoursActionPerformed(evt);
            }
        });

        txt_nightdiffamount.setFocusable(false);

        txt_specialholidayamount.setFocusable(false);
        txt_specialholidayamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_specialholidayamountActionPerformed(evt);
            }
        });

        txt_SHNDamount.setFocusable(false);

        lbl_performance2.setText("SH ND");

        lbl_performance3.setText("Legal Holiday");

        txt_LegalHolidayhours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LegalHolidayhoursActionPerformed(evt);
            }
        });

        txt_LegalHolidayamount.setFocusable(false);

        lbl_performance4.setText("LH ND");

        txt_LHNDhours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LHNDhoursActionPerformed(evt);
            }
        });

        txt_LHNDamount.setFocusable(false);

        lbl_performance5.setText("Overtime");

        txt_overtimehours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_overtimehoursActionPerformed(evt);
            }
        });

        txt_overtimeamount.setFocusable(false);

        lbl_performance6.setText("OT Night Diff");

        txt_OTNDhours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_OTNDhoursActionPerformed(evt);
            }
        });

        txt_OTNDamount.setFocusable(false);

        lbl_performance7.setText("Leave/Others");

        lbl_performance8.setText("Adjustment");

        txt_leaveothersamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_leaveothersamountActionPerformed(evt);
            }
        });

        txt_adjustmentamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_adjustmentamountActionPerformed(evt);
            }
        });

        txt_allowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_allowanceActionPerformed(evt);
            }
        });

        jLabel14.setText("Amount");

        jLabel16.setText("Hours");

        jButton12.setText("Calculate");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_salAllow_payrollLayout = new javax.swing.GroupLayout(panel_salAllow_payroll);
        panel_salAllow_payroll.setLayout(panel_salAllow_payrollLayout);
        panel_salAllow_payrollLayout.setHorizontalGroup(
            panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_travel)
                            .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_food)
                                    .addComponent(lbl_performance))
                                .addGap(24, 24, 24)
                                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_specialholidayhours, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_nightdiffhours, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_SHNDhours, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_LegalHolidayhours, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_LHNDhours, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(lbl_performance2)
                    .addComponent(lbl_performance3)
                    .addComponent(lbl_performance4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                        .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_nightdiffamount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)
                            .addComponent(txt_specialholidayamount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SHNDamount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_LegalHolidayamount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_performance7)
                                    .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                                            .addComponent(lbl_performance5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_overtimehours, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                                            .addComponent(lbl_performance6)
                                            .addGap(23, 23, 23)
                                            .addComponent(txt_OTNDhours, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addGap(16, 16, 16))))
                    .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                        .addComponent(txt_LHNDamount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_performance8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_overtimeamount, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(txt_OTNDamount, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(txt_leaveothersamount, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(jLabel14)))
                    .addComponent(txt_adjustmentamount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton12)))
                .addGap(0, 40, Short.MAX_VALUE))
        );
        panel_salAllow_payrollLayout.setVerticalGroup(
            panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_salAllow_payrollLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_travel)
                    .addComponent(txt_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_food)
                    .addComponent(txt_nightdiffamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nightdiffhours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_performance)
                    .addComponent(txt_specialholidayhours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_specialholidayamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_performance2)
                    .addComponent(txt_SHNDhours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SHNDamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_performance3)
                    .addComponent(txt_LegalHolidayhours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_LegalHolidayamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_performance4)
                    .addComponent(txt_LHNDhours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_LHNDamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_performance8)
                    .addComponent(txt_adjustmentamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_salAllow_payrollLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_overtimehours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_overtimeamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_performance5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_OTNDhours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_OTNDamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_performance6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salAllow_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_leaveothersamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_performance7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addGap(209, 209, 209))
        );

        panel_salDeduct_payroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salary Deductions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(255, 0, 0))); // NOI18N
        panel_salDeduct_payroll.setPreferredSize(new java.awt.Dimension(500, 500));

        lbl_epf.setText("Late/Undertime");

        lbl_tax.setText("SSS");

        lbl_paye.setText("PHIL HEALTH");

        jLabel9.setText("Mins");

        jLabel10.setText("Amount");

        txt_lateundertimemins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lateundertimeminsActionPerformed(evt);
            }
        });

        txt_lateundertimeamount.setFocusable(false);

        lbl_paye1.setText("HDMF");

        lbl_paye2.setText("Other Deduction");

        txt_SSSamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SSSamountActionPerformed(evt);
            }
        });

        txt_PHILHEALTHamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_PHILHEALTHamountActionPerformed(evt);
            }
        });

        txt_HDMFamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HDMFamountActionPerformed(evt);
            }
        });

        txt_otherdeductionamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_otherdeductionamountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_salDeduct_payrollLayout = new javax.swing.GroupLayout(panel_salDeduct_payroll);
        panel_salDeduct_payroll.setLayout(panel_salDeduct_payrollLayout);
        panel_salDeduct_payrollLayout.setHorizontalGroup(
            panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_salDeduct_payrollLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_salDeduct_payrollLayout.createSequentialGroup()
                        .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_salDeduct_payrollLayout.createSequentialGroup()
                                .addComponent(lbl_epf)
                                .addGap(60, 60, 60)
                                .addComponent(txt_lateundertimemins, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_tax))
                        .addGap(17, 17, 17)
                        .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_SSSamount, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_lateundertimeamount)))
                    .addGroup(panel_salDeduct_payrollLayout.createSequentialGroup()
                        .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_paye1)
                            .addComponent(lbl_paye2)
                            .addComponent(lbl_paye))
                        .addGap(117, 117, 117)
                        .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_PHILHEALTHamount)
                            .addComponent(txt_HDMFamount)
                            .addComponent(txt_otherdeductionamount, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
                    .addGroup(panel_salDeduct_payrollLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel9)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_salDeduct_payrollLayout.setVerticalGroup(
            panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_salDeduct_payrollLayout.createSequentialGroup()
                .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_lateundertimemins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_lateundertimeamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_epf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SSSamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_tax))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_PHILHEALTHamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_paye))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_HDMFamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_paye1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salDeduct_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_otherdeductionamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_paye2))
                .addGap(50, 50, 50))
        );

        btn_search_payroll.setText("Search Employee");
        btn_search_payroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_payrollActionPerformed(evt);
            }
        });

        btn_add_payroll.setText("Add Salary Details");
        btn_add_payroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_payrollActionPerformed(evt);
            }
        });

        btn_exit_payroll.setText("Exit");
        btn_exit_payroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exit_payrollActionPerformed(evt);
            }
        });

        btn_update_payroll.setText("Update Salary Details");
        btn_update_payroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_payrollActionPerformed(evt);
            }
        });

        jLabel44.setText("TOTAL DEDUCTION");

        totaldeductiontxt.setFocusable(false);
        totaldeductiontxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totaldeductiontxtActionPerformed(evt);
            }
        });

        jButton18.setText("Clear");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton22.setText("Apply Default Deductions");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton21.setText("Edit Default Deductions");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel108.setText("TOTAL EARNINGS");

        jLabel109.setText("NET PAY");

        netpaytxt.setFocusable(false);
        netpaytxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netpaytxtActionPerformed(evt);
            }
        });

        jButton1.setText("Search Salary Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout intFrame_payrollLayout = new javax.swing.GroupLayout(intFrame_payroll.getContentPane());
        intFrame_payroll.getContentPane().setLayout(intFrame_payrollLayout);
        intFrame_payrollLayout.setHorizontalGroup(
            intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intFrame_payrollLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(intFrame_payrollLayout.createSequentialGroup()
                        .addComponent(panel_empDetails_payroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_exit_payroll, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(btn_add_payroll, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(btn_update_payroll, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_search_payroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(intFrame_payrollLayout.createSequentialGroup()
                        .addGroup(intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panel_salAllow_payroll, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(intFrame_payrollLayout.createSequentialGroup()
                                .addComponent(jLabel108)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalearningstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panel_salDeduct_payroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(intFrame_payrollLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(intFrame_payrollLayout.createSequentialGroup()
                                        .addComponent(jLabel44)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(totaldeductiontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(intFrame_payrollLayout.createSequentialGroup()
                                        .addComponent(jLabel109)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(netpaytxt, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        intFrame_payrollLayout.setVerticalGroup(
            intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intFrame_payrollLayout.createSequentialGroup()
                .addGroup(intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(intFrame_payrollLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel_empDetails_payroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(intFrame_payrollLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btn_search_payroll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_add_payroll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_update_payroll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_exit_payroll)))
                .addGroup(intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(intFrame_payrollLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(panel_salDeduct_payroll, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totaldeductiontxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel109)
                            .addComponent(netpaytxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(intFrame_payrollLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_salAllow_payroll, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(intFrame_payrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel108)
                            .addComponent(totalearningstxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(intFrame_payroll);
        intFrame_payroll.setBounds(0, 0, 1280, 720);
        try {
            intFrame_payroll.setMaximum(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        intFrame_print.setBackground(new java.awt.Color(255, 255, 255));
        intFrame_print.setTitle("Pay Slip");
        intFrame_print.setMaximumSize(new java.awt.Dimension(800, 500));
        intFrame_print.setMinimumSize(new java.awt.Dimension(800, 500));
        intFrame_print.setPreferredSize(new java.awt.Dimension(800, 500));
        intFrame_print.setVisible(false);

        panel_details.setBackground(new java.awt.Color(255, 255, 255));
        panel_details.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        panel_details.setToolTipText("");

        jLabel1.setText("Employee Name :");

        jLabel3.setText("Employee No.:");

        jLabel7.setText("Designation :");

        jLabel11.setText("Department :");

        jLabel48.setText("Cutoff Period:");

        jLabel49.setText("From");

        fromcutoffdatelabel.setText(" ");

        jLabel50.setText("To");

        jLabel59.setText("DAILY RATE:");

        jLabel60.setText("NUMBER OF DAYS:");

        jLabel47.setText("Payslip for");

        paydatelabel.setText(" ");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel58.setText("PARANAQUE CITY");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel57.setText("7-I A. MAYUGA STREET, MIAA ROAD, TAMBO, ");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel56.setText("D.O.T.A. AERO AVIATION SERVICES INC.");

        jLabel31.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel31.setText("Earnings");

        jLabel30.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel30.setText(" Amount (PHP)");
        jLabel30.setAlignmentY(0.0F);

        jLabel15.setText("Basic Salary");

        jLabel13.setText("Allowance");

        jLabel36.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel36.setText("Deductions");

        jLabel37.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel37.setText(" Amount (PHP)");

        jLabel24.setText("Late/Undertime");

        jLabel25.setText("SSS");

        jLabel26.setText("Philhealth");

        jLabel39.setText("HDMF");

        lbl_HDMF.setText(" ");

        jLabel40.setText("Other Deduction");

        jLabel33.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel33.setText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel34.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel34.setText("TOTAL DEDUCTIONS");

        lbl_pay_deduct.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N

        jLabel17.setText("Night Differential");

        jLabel19.setText("Special Holiday");

        jLabel2.setText("SH ND");

        jLabel18.setText("Legal Holiday");

        lbl_LegalHoliday.setText(" ");

        jLabel20.setText("LH ND");

        jLabel23.setText("Overtime");

        jLabel27.setText("OT ND");

        jLabel28.setText("Leave/Others");

        lbl_leaveothers.setText(" ");

        jLabel29.setText("Adjustment");

        lbl_adjustment.setText(" ");

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel22.setText("TOTAL EARNINGS");

        lbl_pay_gross.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel32.setText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        lbl_pay_net.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N

        jLabel38.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel38.setText("Net Pay :");

        jLabel61.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel61.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        dailyratelbl.setText(" ");

        numdayslbl.setText(" ");

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/forms/images/icontest1.png"))); // NOI18N
        jLabel35.setText(" ");

        javax.swing.GroupLayout panel_detailsLayout = new javax.swing.GroupLayout(panel_details);
        panel_details.setLayout(panel_detailsLayout);
        panel_detailsLayout.setHorizontalGroup(
            panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detailsLayout.createSequentialGroup()
                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel_detailsLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(26, 26, 26)
                                        .addComponent(lbl_pay_empId, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(270, 270, 270))
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_detailsLayout.createSequentialGroup()
                                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel11)
                                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                                .addComponent(jLabel48)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel49)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_pay_name, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_pay_desig, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                                .addComponent(lbl_pay_depart, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(182, 182, 182)
                                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel60)
                                                    .addComponent(jLabel59))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(dailyratelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(numdayslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                                .addComponent(fromcutoffdatelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel50)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tocutoffdatelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                                .addComponent(jLabel30)
                                .addGap(34, 34, 34)
                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_detailsLayout.createSequentialGroup()
                                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel38)
                                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGap(56, 56, 56))
                                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                                .addGap(110, 110, 110)
                                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbl_SSS, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbl_otherdeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbl_lateundertime, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbl_HDMF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbl_philhealth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbl_pay_deduct, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                                .addGap(109, 109, 109)
                                                .addComponent(lbl_pay_net, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panel_detailsLayout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addGap(152, 152, 152)
                                        .addComponent(jLabel37)))
                                .addGap(0, 54, Short.MAX_VALUE))
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel2)
                                    .addGroup(panel_detailsLayout.createSequentialGroup()
                                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27)
                                            .addComponent(jLabel18))
                                        .addGap(239, 239, 239)
                                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_LegalHoliday, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_OTND, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_OT, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_nightdiff, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_pay_basic, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panel_detailsLayout.createSequentialGroup()
                                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_detailsLayout.createSequentialGroup()
                                                    .addComponent(jLabel13)
                                                    .addGap(256, 256, 256))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_detailsLayout.createSequentialGroup()
                                                    .addComponent(jLabel19)
                                                    .addGap(230, 230, 230)))
                                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel20)
                                                    .addComponent(jLabel29)
                                                    .addComponent(jLabel28)
                                                    .addComponent(jLabel22))
                                                .addGap(191, 191, 191)))
                                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_leaveothers, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_adjustment, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_LHND, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(lbl_allowance, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                                .addComponent(lbl_SHND, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbl_specialholiday, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(lbl_pay_gross, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGap(342, 342, 342)
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paydatelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGap(296, 296, 296)
                                .addComponent(jLabel57))
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGap(311, 311, 311)
                                .addComponent(jLabel56))
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGap(374, 374, 374)
                                .addComponent(jLabel58))
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_detailsLayout.setVerticalGroup(
            panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_detailsLayout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(paydatelabel))))
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lbl_pay_empId, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59)
                        .addComponent(dailyratelbl)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(numdayslbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addComponent(lbl_pay_name, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_pay_desig, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_pay_depart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel48)
                                        .addComponent(jLabel49))
                                    .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tocutoffdatelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(fromcutoffdatelabel)
                                            .addComponent(jLabel50))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel30))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_detailsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(lbl_pay_basic, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_nightdiff, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26)))
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addComponent(lbl_SSS, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_philhealth, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_OT, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel39)
                                .addComponent(lbl_HDMF)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_OTND, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_lateundertime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_otherdeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(lbl_LegalHoliday)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20))
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addGap(0, 24, Short.MAX_VALUE)
                                .addComponent(lbl_LHND, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(lbl_specialholiday, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lbl_SHND, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lbl_allowance, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_detailsLayout.createSequentialGroup()
                                .addComponent(lbl_leaveothers)
                                .addGap(10, 10, 10)
                                .addComponent(lbl_adjustment)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_pay_gross, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_pay_deduct, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_detailsLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22)))
                .addGap(8, 8, 8)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(lbl_pay_net, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        btn_pay_search.setText("Search Employee ID");
        btn_pay_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pay_searchActionPerformed(evt);
            }
        });

        btn_pay_exit.setText("Exit");
        btn_pay_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pay_exitActionPerformed(evt);
            }
        });

        btn_pay_print.setText("Print Pay Slip");
        btn_pay_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pay_printActionPerformed(evt);
            }
        });

        jButton7.setText("Search Employee Name");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Capture Payslip 1");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Capture Payslip 2");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout intFrame_printLayout = new javax.swing.GroupLayout(intFrame_print.getContentPane());
        intFrame_print.getContentPane().setLayout(intFrame_printLayout);
        intFrame_printLayout.setHorizontalGroup(
            intFrame_printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intFrame_printLayout.createSequentialGroup()
                .addComponent(panel_details, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(intFrame_printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_pay_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_pay_print, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_pay_exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 188, Short.MAX_VALUE))
        );
        intFrame_printLayout.setVerticalGroup(
            intFrame_printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intFrame_printLayout.createSequentialGroup()
                .addGroup(intFrame_printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(intFrame_printLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pay_search)
                        .addGap(5, 5, 5)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9)
                        .addGap(7, 7, 7)
                        .addComponent(btn_pay_print)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pay_exit))
                    .addGroup(intFrame_printLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(intFrame_print);
        intFrame_print.setBounds(0, 0, 1200, 680);

        intFrame_leave.setClosable(true);
        intFrame_leave.setTitle("Leave Status | Apply Leave");
        intFrame_leave.setMaximumSize(new java.awt.Dimension(800, 500));
        intFrame_leave.setMinimumSize(new java.awt.Dimension(800, 500));
        intFrame_leave.setPreferredSize(new java.awt.Dimension(800, 500));
        intFrame_leave.setVisible(false);

        panel_empDetails_payroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        lbl_empId_allowance1.setText("Employee ID");

        txt_empId_leave.setFocusable(false);

        lbl_fname_allowance1.setText("First Name");

        lbl_lname_allowance1.setText("Last Name");

        lbl_desig_allowance1.setText("Designation");

        lbl_depart_allowance1.setText("Department");

        txt_fname_leave.setFocusable(false);

        txt_lname_leave.setFocusable(false);

        txt_desig_leave.setFocusable(false);

        txt_depart_leave.setFocusable(false);

        javax.swing.GroupLayout panel_empDetails_payroll1Layout = new javax.swing.GroupLayout(panel_empDetails_payroll1);
        panel_empDetails_payroll1.setLayout(panel_empDetails_payroll1Layout);
        panel_empDetails_payroll1Layout.setHorizontalGroup(
            panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_empDetails_payroll1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_empDetails_payroll1Layout.createSequentialGroup()
                        .addComponent(lbl_fname_allowance1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_fname_leave, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_empDetails_payroll1Layout.createSequentialGroup()
                        .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_empId_allowance1)
                            .addComponent(lbl_lname_allowance1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_empId_leave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_lname_leave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_depart_allowance1)
                    .addComponent(lbl_desig_allowance1))
                .addGap(60, 60, 60)
                .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_desig_leave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_depart_leave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );
        panel_empDetails_payroll1Layout.setVerticalGroup(
            panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_empDetails_payroll1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_empDetails_payroll1Layout.createSequentialGroup()
                        .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_empId_leave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_empId_allowance1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_fname_leave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_fname_allowance1)))
                    .addGroup(panel_empDetails_payroll1Layout.createSequentialGroup()
                        .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_desig_leave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_desig_allowance1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_depart_leave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_depart_allowance1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_empDetails_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_lname_leave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_lname_allowance1))
                .addGap(33, 33, 33))
        );

        panel_salAllow_payroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Leave Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        lbl_travel1.setText("Vacation Leave");

        lbl_food1.setText("Sick Leave");

        jLabel6.setText("Available Leave Count");

        jLabel8.setText("Apply Leave");

        txt_vl_count.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vl_countActionPerformed(evt);
            }
        });
        txt_vl_count.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_vl_countKeyTyped(evt);
            }
        });

        txt_sl_count.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sl_countActionPerformed(evt);
            }
        });

        txt_vl_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_vl_applyActionPerformed(evt);
            }
        });
        txt_vl_apply.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_vl_applyKeyTyped(evt);
            }
        });

        txt_sl_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sl_applyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_salAllow_payroll1Layout = new javax.swing.GroupLayout(panel_salAllow_payroll1);
        panel_salAllow_payroll1.setLayout(panel_salAllow_payroll1Layout);
        panel_salAllow_payroll1Layout.setHorizontalGroup(
            panel_salAllow_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_salAllow_payroll1Layout.createSequentialGroup()
                .addGroup(panel_salAllow_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_salAllow_payroll1Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel6))
                    .addGroup(panel_salAllow_payroll1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(panel_salAllow_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel_salAllow_payroll1Layout.createSequentialGroup()
                                .addComponent(lbl_travel1)
                                .addGap(170, 170, 170)
                                .addComponent(txt_vl_count, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_salAllow_payroll1Layout.createSequentialGroup()
                                .addComponent(lbl_food1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_sl_count, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(96, 96, 96)
                .addGroup(panel_salAllow_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_salAllow_payroll1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panel_salAllow_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_vl_apply, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sl_apply, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panel_salAllow_payroll1Layout.setVerticalGroup(
            panel_salAllow_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_salAllow_payroll1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel_salAllow_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_salAllow_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_salAllow_payroll1Layout.createSequentialGroup()
                        .addGroup(panel_salAllow_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_vl_count, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_travel1))
                        .addGap(18, 18, 18)
                        .addGroup(panel_salAllow_payroll1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_sl_count, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_food1)))
                    .addGroup(panel_salAllow_payroll1Layout.createSequentialGroup()
                        .addComponent(txt_vl_apply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_sl_apply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
        );

        btn_search_leave.setText("Search Employee");
        btn_search_leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_leaveActionPerformed(evt);
            }
        });

        btn_apply_leave.setText("Apply Leave");
        btn_apply_leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_apply_leaveActionPerformed(evt);
            }
        });

        btn_exit_leave.setText("Exit");
        btn_exit_leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exit_leaveActionPerformed(evt);
            }
        });

        btn_reset_leave.setText("Reset Leave");
        btn_reset_leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reset_leaveActionPerformed(evt);
            }
        });

        btn_update_leave.setText("Update Leave");
        btn_update_leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_leaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout intFrame_leaveLayout = new javax.swing.GroupLayout(intFrame_leave.getContentPane());
        intFrame_leave.getContentPane().setLayout(intFrame_leaveLayout);
        intFrame_leaveLayout.setHorizontalGroup(
            intFrame_leaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intFrame_leaveLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addGroup(intFrame_leaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(intFrame_leaveLayout.createSequentialGroup()
                        .addComponent(btn_search_leave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_apply_leave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_update_leave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_reset_leave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_exit_leave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel_empDetails_payroll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_salAllow_payroll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        intFrame_leaveLayout.setVerticalGroup(
            intFrame_leaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intFrame_leaveLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(panel_empDetails_payroll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_salAllow_payroll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(intFrame_leaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_search_leave)
                    .addComponent(btn_apply_leave)
                    .addComponent(btn_update_leave)
                    .addComponent(btn_reset_leave)
                    .addComponent(btn_exit_leave))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(intFrame_leave);
        intFrame_leave.setBounds(0, 0, 1200, 680);

        lbl_pms.setFont(new java.awt.Font("URW Palladio L", 1, 48)); // NOI18N
        lbl_pms.setForeground(new java.awt.Color(36, 121, 158));
        lbl_pms.setText("Payroll Management System");
        getContentPane().add(lbl_pms);
        lbl_pms.setBounds(210, 140, 710, 60);

        lbl_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/forms/images/DOAV_LOGO-removebg-preview.png"))); // NOI18N
        getContentPane().add(lbl_background);
        lbl_background.setBounds(270, 20, 1200, 700);

        menu_menuBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        menuBar_file.setText("   File   ");
        menuBar_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_fileActionPerformed(evt);
            }
        });

        menuBar_file_logout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuBar_file_logout.setText("Log Out");
        menuBar_file_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_file_logoutActionPerformed(evt);
            }
        });
        menuBar_file.add(menuBar_file_logout);

        menuBar_file_exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuBar_file_exit.setText("Exit");
        menuBar_file_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_file_exitActionPerformed(evt);
            }
        });
        menuBar_file.add(menuBar_file_exit);

        menu_menuBar.add(menuBar_file);

        menuBar_employee.setText("   Employee   ");
        menuBar_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_employeeActionPerformed(evt);
            }
        });

        menuBar_employee_new.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuBar_employee_new.setText("New Employee");
        menuBar_employee_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_employee_newActionPerformed(evt);
            }
        });
        menuBar_employee.add(menuBar_employee_new);

        menuBar_employee_update.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuBar_employee_update.setText("Update Employee");
        menuBar_employee_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_employee_updateActionPerformed(evt);
            }
        });
        menuBar_employee.add(menuBar_employee_update);

        menuBar_employee_delete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuBar_employee_delete.setText("Delete Employee");
        menuBar_employee_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_employee_deleteActionPerformed(evt);
            }
        });
        menuBar_employee.add(menuBar_employee_delete);

        menuBar_employee_search.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuBar_employee_search.setText("Search Employee");
        menuBar_employee_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_employee_searchActionPerformed(evt);
            }
        });
        menuBar_employee.add(menuBar_employee_search);

        menu_menuBar.add(menuBar_employee);

        menuBar_payroll.setText("   Payroll   ");
        menuBar_payroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBar_payrollMouseClicked(evt);
            }
        });
        menuBar_payroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_payrollActionPerformed(evt);
            }
        });

        menuBar_payroll_add.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuBar_payroll_add.setText("Edit Salary Details");
        menuBar_payroll_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_payroll_addActionPerformed(evt);
            }
        });
        menuBar_payroll.add(menuBar_payroll_add);

        jMenuItem1.setText("View Salary Details");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuBar_payroll.add(jMenuItem1);

        menu_menuBar.add(menuBar_payroll);

        menuBar_leave.setText("Leave");

        menuBar_leave_apply.setText("Apply Leave");
        menuBar_leave_apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_leave_applyActionPerformed(evt);
            }
        });
        menuBar_leave.add(menuBar_leave_apply);

        menu_menuBar.add(menuBar_leave);

        menuBar_paySlip.setText("     Pay Slip     ");

        menuBar_payslip_print.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuBar_payslip_print.setText("Print Pay Slip");
        menuBar_payslip_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBar_payslip_printActionPerformed(evt);
            }
        });
        menuBar_paySlip.add(menuBar_payslip_print);

        menu_menuBar.add(menuBar_paySlip);

        setJMenuBar(menu_menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Change Title bar Icon of the Form

    public void changeIcon() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/icon.png")));
    }

//Disable menu on form initialization    
    public void disableMenu() {

        menuBar_employee.setEnabled(false);
        menuBar_payroll.setEnabled(false);
        menuBar_paySlip.setEnabled(false);

    }

//Add radio buttons to a group
    public void addButtonGroup() {

       btnGroup_rd.add(rd_male);
       btnGroup_rd.add(rd_female);

    }

//clear new employee form    
    public void clearEmployeeNew() {
        txt_empID.setText(null);
        //txt_nic.setText(null);
        txt_fname.setText(null);
        txt_lname.setText(null);
        //txt_address.setText(null);
        //txt_city.setText(null);
        //txt_dob.setText(null);
        //txt_dateJoin.setText(null);
        txt_deparment.setText(null);
        txt_designation.setText(null);
        //txt_telHome.setText(null);
        //txt_telMobile.setText(null);
    }

//clear update employee form    
    public void clearEmployeeUpdate() {
        txt_empID_update.setText(null);
        //txt_nic_update.setText(null);
        txt_fname_update.setText(null);
        txt_lname_update.setText(null);
        //txt_address_update.setText(null);
        //txt_city_update.setText(null);
        //txt_dob_update.setText(null);
        //txt_dateJoin_update.setText(null);
        txt_deparment_update.setText(null);
        txt_designation_update.setText(null);
        //txt_telHome_update.setText(null);
        //txt_telMobile_update.setText(null);

    }

//clear new payroll details form    
    public void clearPayroll() {
        txt_empId_allowance.setText(null);
        txt_fname_allowance.setText(null);
        txt_lname_allowance.setText(null);
        txt_desig_allowance.setText(null);
        txt_depart_allowance.setText(null);
        //txt_salType_allowance.setText(null);
        txt_totaldays.setText(null);
        txt_salratedaily.setText(null);
        txt_basicsalary.setText(null);
        txt_allowance.setText(null);
        txt_nightdiffhours.setText(null);
        txt_nightdiffamount.setText(null);
        txt_specialholidayhours.setText(null);
        txt_specialholidayamount.setText(null);
        txt_SHNDhours.setText(null);
        txt_SHNDamount.setText(null);
        txt_LegalHolidayhours.setText(null);
        txt_LegalHolidayamount.setText(null);
        txt_LHNDhours.setText(null);
        txt_LHNDamount.setText(null);
        txt_overtimehours.setText(null);
        txt_overtimeamount.setText(null);
        txt_OTNDhours.setText(null);
        txt_OTNDamount.setText(null);
        txt_leaveothersamount.setText(null);
        txt_adjustmentamount.setText(null);
        txt_lateundertimemins.setText(null);
        //txt_lateundertimerate.setText(null);
        txt_lateundertimeamount.setText(null);
        txt_SSSamount.setText(null);
        txt_PHILHEALTHamount.setText(null);
        txt_HDMFamount.setText(null);
        txt_otherdeductionamount.setText(null);  
        txtDate1.setText(null);
        fromcutofftxt.setText(null);
        tocutofftxt.setText(null);
        totaldeductiontxt.setText(null);
    }
    public void clearPayrollnewEntry() {
        //txt_empId_allowance.setText(null);
        //txt_fname_allowance.setText(null);
        //txt_lname_allowance.setText(null);
        //txt_desig_allowance.setText(null);
        //txt_depart_allowance.setText(null);
        //txt_salType_allowance.setText(null);
        txt_totaldays.setText(String.valueOf(0.0));
        //txt_salratedaily.setText(null);
        txt_basicsalary.setText(String.valueOf(0.0));
        txt_allowance.setText(String.valueOf(0.0));
        txt_nightdiffhours.setText(String.valueOf(0.0));
        txt_nightdiffamount.setText(String.valueOf(0.0));
        txt_specialholidayhours.setText(String.valueOf(0.0));
        txt_specialholidayamount.setText(String.valueOf(0.0));
        txt_SHNDhours.setText(String.valueOf(0.0));
        txt_SHNDamount.setText(String.valueOf(0.0));
        txt_LegalHolidayhours.setText(String.valueOf(0.0));
        txt_LegalHolidayamount.setText(String.valueOf(0.0));
        txt_LHNDhours.setText(String.valueOf(0.0));
        txt_LHNDamount.setText(String.valueOf(0.0));
        txt_overtimehours.setText(String.valueOf(0.0));
        txt_overtimeamount.setText(String.valueOf(0.0));
        txt_OTNDhours.setText(String.valueOf(0.0));
        txt_OTNDamount.setText(String.valueOf(0.0));
        txt_leaveothersamount.setText(String.valueOf(0.0));
        txt_adjustmentamount.setText(String.valueOf(0.0));
        txt_lateundertimemins.setText(String.valueOf(0.0));
        //txt_lateundertimerate.setText(null);
        txt_lateundertimeamount.setText(String.valueOf(0.0));
        txt_SSSamount.setText(String.valueOf(0.0));
        txt_PHILHEALTHamount.setText(String.valueOf(0.0));
        txt_HDMFamount.setText(String.valueOf(0.0));
        txt_otherdeductionamount.setText(String.valueOf(0.0));  
        txtDate1.setText(String.valueOf(0.0));
        fromcutofftxt.setText(String.valueOf(0.0));
        tocutofftxt.setText(String.valueOf(0.0));
        totaldeductiontxt.setText(String.valueOf(0.0));
    }

//clear pay slip form
    public void clearPaySlip() {
        lbl_pay_empId.setText(null);
        lbl_pay_name.setText(null);
        lbl_pay_desig.setText(null);
        lbl_pay_depart.setText(null);
        lbl_pay_basic.setText(null);
        lbl_lateundertime.setText(null);
        lbl_philhealth.setText(null);
        lbl_SSS.setText(null);
        lbl_pay_gross.setText(null);
        lbl_pay_deduct.setText(null);
        lbl_pay_net.setText(null);
    }

//clear leave form    
    public void clearLeave() {
        txt_empId_leave.setText(null);
        txt_fname_leave.setText(null);
        txt_lname_leave.setText(null);
        txt_desig_leave.setText(null);
        txt_depart_leave.setText(null);
        txt_vl_count.setText(null);
        txt_vl_apply.setText(null);
        txt_sl_apply.setText(null);
        txt_sl_count.setText(null);

    }

//validate new employee fields    
    public boolean validateEmployeeNew() {

        if (txt_empID.getText().isEmpty()
                //|| txt_nic.getText().isEmpty()
                || txt_fname.getText().isEmpty()
                || txt_lname.getText().isEmpty()
           //     || txt_dob.getText().isEmpty()
                || txt_designation.getText().isEmpty()
                || txt_deparment.getText().isEmpty())
         //       || txt_dateJoin.getText().isEmpty()) 
        {
            return false;
        } else {
            return true;
        }

    }

//validate update employee form fields    
    public boolean validateEmployeeUpdate() {

        if (txt_empID_update.getText().isEmpty()
              //  || txt_nic_update.getText().isEmpty()
                || txt_fname_update.getText().isEmpty()
                || txt_lname_update.getText().isEmpty()
              //  || txt_dob_update.getText().isEmpty()
                || txt_designation_update.getText().isEmpty()
                || txt_deparment_update.getText().isEmpty()
              //  || txt_dateJoin_update.getText().isEmpty()
                ) {
            return false;
        } else {
            return true;
        }

    }

//hide frames on opening a new form    
    public void hideFrames() {

        intFrame_employee_new.setVisible(false);
        intFrame_employee_update.setVisible(false);
        intFrame_payroll.setVisible(false);
        intFrame_print.setVisible(false);
        intFrame_employee_search.setVisible(false);
        intFrame_leave.setVisible(false);

    }

//dialog box to get employee id    
    public String getEmpId() {

        return JOptionPane.showInputDialog("Enter Employee ID");

    }

    private void menuBar_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_fileActionPerformed

    }//GEN-LAST:event_menuBar_fileActionPerformed

    private void menuBar_file_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_file_exitActionPerformed

        System.exit(0);
    }//GEN-LAST:event_menuBar_file_exitActionPerformed

    private void menuBar_employee_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_employee_newActionPerformed

        hideFrames();
        intFrame_employee_new.setVisible(true);
        txt_empID.setText(objEmployee.setEmpIdField());
    }//GEN-LAST:event_menuBar_employee_newActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed

        intFrame_employee_new.setVisible(false);
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed

        if (!validateEmployeeNew()) {

            JOptionPane.showMessageDialog(null, "Fields Marked with * are Mandatory", "ERROR", 0);

        } else {

            objEmployee.setEmpId(Integer.parseInt(txt_empID.getText()));
            //objEmployee.setNic(txt_nic.getText());
            objEmployee.setFname(txt_fname.getText());
            objEmployee.setLname(txt_lname.getText());
            //objEmployee.setDob(txt_dob.getText());
            //objEmployee.setAddress(txt_address.getText());
           // objEmployee.setCity(txt_city.getText());
           // objEmployee.setTel_home(txt_telHome.getText());
           // objEmployee.setTel_mobile(txt_telMobile.getText());
            objEmployee.setDesignation(txt_designation.getText());
            objEmployee.setDepartment(txt_deparment.getText());
           // objEmployee.setDateOfJoining(txt_dateJoin.getText());
           // objEmployee.setSalType(combo_salType.getSelectedItem().toString());

           if (rd_male.isSelected()) {

                objEmployee.setGender(rd_male.getText());

            } else if (rd_female.isSelected()) {

                objEmployee.setGender(rd_female.getText());

            }

            if (objEmployee.insertEmployee()) {
                clearEmployeeNew();
            }

        }


    }//GEN-LAST:event_btn_addActionPerformed

    private void menuBar_file_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_file_logoutActionPerformed

        Login loginForm = new Login();
        loginForm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_menuBar_file_logoutActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed

        if (!validateEmployeeUpdate()) {

            JOptionPane.showMessageDialog(null, "Fields Marked with * are Mandatory", "ERROR", 0);

        } else {

            objEmployee.setEmpId(Integer.parseInt(txt_empID_update.getText()));
            //objEmployee.setNic(txt_nic_update.getText());
            objEmployee.setFname(txt_fname_update.getText());
            objEmployee.setLname(txt_lname_update.getText());
            //objEmployee.setDob(txt_dob_update.getText());
           // objEmployee.setAddress(txt_address_update.getText());
           // objEmployee.setCity(txt_city_update.getText());
           // objEmployee.setTel_home(txt_telHome_update.getText());
          //  objEmployee.setTel_mobile(txt_telMobile_update.getText());
            objEmployee.setDesignation(txt_designation_update.getText());
            objEmployee.setDepartment(txt_deparment_update.getText());
           // objEmployee.setDateOfJoining(txt_dateJoin_update.getText());
           // objEmployee.setSalType(combo_salType_update.getSelectedItem().toString());
           
           
           //objLeave.setVacationleave(Double.parseDouble(txt_vl_count.getText()));
           //sobjLeave.setSickleave(Double.parseDouble(txt_sl_count.getText()));

            if (objEmployee.updateEmployee() && objLeave.applyLeave()) {
                clearEmployeeUpdate();
            }
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_exit_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exit_updateActionPerformed

        intFrame_employee_update.setVisible(false);
    }//GEN-LAST:event_btn_exit_updateActionPerformed

    private void menuBar_employee_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_employee_updateActionPerformed

        hideFrames();
        intFrame_employee_update.setVisible(true);
    }//GEN-LAST:event_menuBar_employee_updateActionPerformed

    private void menuBar_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_employeeActionPerformed

    }//GEN-LAST:event_menuBar_employeeActionPerformed

    private void menuBar_employee_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_employee_deleteActionPerformed

        hideFrames();
        objEmployee.deleteEmployee(getEmpId());
    }//GEN-LAST:event_menuBar_employee_deleteActionPerformed

    private void btn_search_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_updateActionPerformed

        if (objEmployee.getEmployeeDetails(getEmpId())) {

            txt_empID_update.setText(String.valueOf(objEmployee.getEmpId()));
            //txt_nic_update.setText(objEmployee.getNic());
            txt_fname_update.setText(objEmployee.getFname());
            txt_lname_update.setText(objEmployee.getLname());
            //txt_dob_update.setText(objEmployee.getDob());
            //txt_address_update.setText(objEmployee.getAddress());
            //txt_city_update.setText(objEmployee.getCity());
            txt_designation_update.setText(objEmployee.getDesignation());
            txt_deparment_update.setText(objEmployee.getDepartment());
            //txt_telHome_update.setText(objEmployee.getTelHome());
           // txt_telMobile_update.setText(objEmployee.getTelMobile());
            //txt_dateJoin_update.setText(objEmployee.getDateOfJoining());
            //combo_salType_update.setSelectedItem(objEmployee.getSalType());
            //txt_vl_count1.setText(String.valueOf(objLeave.getVacationleave()));
            //txt_sl_count1.setText(String.valueOf(objLeave.getSickleave()));
            

        }
    }//GEN-LAST:event_btn_search_updateActionPerformed

    private void menuBar_employee_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_employee_searchActionPerformed
//populate table with employee details        
        hideFrames();
        intFrame_employee_search.setVisible(true);
        String sql = "SELECT * FROM employee";
        JTable empDetails = new JTable(objEmployee.getAllEmployeeDetails(objEmployee.getAllEmployeeDetails(sql)), objEmployee.getColumnNames(objEmployee.getAllEmployeeDetails(sql)));
        jScrollPane_tableContainer.setViewportView(empDetails);
    }//GEN-LAST:event_menuBar_employee_searchActionPerformed

    private void btn_searchEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchEmpActionPerformed

        String empId = getEmpId();
        String sql = "SELECT * FROM employee WHERE empId='" + empId + "'";
        JTable empDetails = new JTable(objEmployee.getAllEmployeeDetails(objEmployee.getAllEmployeeDetails(sql)), objEmployee.getColumnNames(objEmployee.getAllEmployeeDetails(sql)));
        jScrollPane_tableContainer.setViewportView(empDetails);
    }//GEN-LAST:event_btn_searchEmpActionPerformed

    private void btn_exit_payrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exit_payrollActionPerformed

        intFrame_payroll.setVisible(false);
    }//GEN-LAST:event_btn_exit_payrollActionPerformed

    private void btn_search_payrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_payrollActionPerformed
        SQLRun objSQLRun = new SQLRun();
        try {

            String sql = "SELECT * FROM defultdeduc WHERE id= 1";
            String sql2 = "SELECT * FROM defultdeduc WHERE id= 1";
            ResultSet resSet = objSQLRun.sqlQuery(sql);
            ResultSet resSet2 = objSQLRun.sqlQuery(sql2);

            if (resSet.next()) {
                this.defaultSSS = resSet.getDouble("defaultsss");//
                this.defaultPhilhealth = resSet.getDouble("defaultphilhealth");//
                this.defaultHDMF = resSet.getDouble("defaulthdmf");//

            } else if (resSet2.next()) {                
                this.defaultSSS = resSet.getDouble("defaultsss");//
                this.defaultPhilhealth = resSet.getDouble("defaultphilhealth");//
                this.defaultHDMF = resSet.getDouble("defaulthdmf");//

            } else {
                JOptionPane.showMessageDialog(null, "No data found", "ERROR", 0);
                
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);


        }
        if (objPayroll.getSalaryDetails(getEmpId())) {
            txt_empId_allowance.setText(String.valueOf(objPayroll.objEmployee.getEmpId()));
            txt_fname_allowance.setText(objPayroll.objEmployee.getFname());
            txt_lname_allowance.setText(objPayroll.objEmployee.getLname());
            txt_desig_allowance.setText(objPayroll.objEmployee.getDesignation());
            txt_depart_allowance.setText(objPayroll.objEmployee.getDepartment());
            //txt_salType_allowance.setText(objPayroll.objEmployee.getSalType());
            txt_totaldays.setText(String.valueOf(objPayroll.gettotalDays()));
            txt_salratedaily.setText(String.valueOf(objPayroll.getsalratedaily()));
            txt_basicsalary.setText(String.valueOf(objPayroll.getbasicSalary()));
            txt_allowance.setText(String.valueOf(objPayroll.getallowanceAmount()));
            txt_nightdiffhours.setText(String.valueOf(objPayroll.getnightDiffHours()));
            txt_nightdiffamount.setText(String.valueOf(objPayroll.getnightDiffAmount()));
            txt_specialholidayhours.setText(String.valueOf(objPayroll.getspecialHolidaydays()));
            txt_specialholidayamount.setText(String.valueOf(objPayroll.getspecialHolidayAmount()));
            txt_SHNDhours.setText(String.valueOf(objPayroll.getSHNDhours()));
            txt_SHNDamount.setText(String.valueOf(objPayroll.getSHNDAmount()));
            txt_LegalHolidayhours.setText(String.valueOf(objPayroll.getLegalHolidayDays()));
            txt_LegalHolidayamount.setText(String.valueOf(objPayroll.getLegalHolidayAmount()));
            txt_LHNDhours.setText(String.valueOf(objPayroll.getLHNDhours()));
            txt_LHNDamount.setText(String.valueOf(objPayroll.getLHNDamount()));
            txt_overtimehours.setText(String.valueOf(objPayroll.getOThours()));
            txt_overtimeamount.setText(String.valueOf(objPayroll.getOTamount()));
            txt_OTNDhours.setText(String.valueOf(objPayroll.getOTNDhours()));
            txt_OTNDamount.setText(String.valueOf(objPayroll.getOTNDamount()));
            txt_leaveothersamount.setText(String.valueOf(objPayroll.getleaveothersamount()));
            txt_adjustmentamount.setText(String.valueOf(objPayroll.getadjustmentamount()));
            txt_lateundertimemins.setText(String.valueOf(objPayroll.getlateundertimemins()));
            //txt_lateundertimerate.setText(String.valueOf(objPayroll.getlateundertimerate()));
            txt_lateundertimeamount.setText(String.valueOf(objPayroll.getlateundertimeamount()));
            
            txt_SSSamount.setText(String.valueOf(objPayroll.getSSSamount()));
            txt_PHILHEALTHamount.setText(String.valueOf(objPayroll.getphilhealthamount()));
            txt_HDMFamount.setText(String.valueOf(objPayroll.gethdmfamount()));
            txt_otherdeductionamount.setText(String.valueOf(objPayroll.getotherdeductionamount()));  
            txtDate1.setText(String.valueOf(objPayroll.getDate()));
            fromcutofftxt.setText(String.valueOf(objPayroll.getfromcutoffdate()));
            tocutofftxt.setText(String.valueOf(objPayroll.gettocutoffdate()));
             
            totaldeductiontxt.setText(String.valueOf(objPayroll.getTotalDeductions()));
        }
    }//GEN-LAST:event_btn_search_payrollActionPerformed

    private void txt_nightdiffhoursKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nightdiffhoursKeyTyped


    }//GEN-LAST:event_txt_nightdiffhoursKeyTyped

    private void txt_nightdiffhoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nightdiffhoursActionPerformed

        txt_nightdiffamount.setText(String.valueOf(objPayroll.calculatenightdiff(txt_nightdiffhours.getText(), txt_salratedaily.getText())));
    }//GEN-LAST:event_txt_nightdiffhoursActionPerformed

    private void txt_specialholidayhoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_specialholidayhoursActionPerformed

        txt_specialholidayamount.setText(String.valueOf(objPayroll.calculatespecialholiday(txt_specialholidayhours.getText(), txt_salratedaily.getText())));
    }//GEN-LAST:event_txt_specialholidayhoursActionPerformed

    private void txt_SHNDhoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SHNDhoursActionPerformed

        txt_SHNDamount.setText(String.valueOf(objPayroll.calculateshnd(txt_SHNDhours.getText())));
    }//GEN-LAST:event_txt_SHNDhoursActionPerformed

    private void txt_lateundertimeminsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lateundertimeminsActionPerformed

        
        //txt_lateundertimerate.setText(String.valueOf(objPayroll.calculaterate(txt_lateundertimemins.getText())));
        txt_lateundertimeamount.setText(String.valueOf(objPayroll.calculatelateundertime(txt_lateundertimemins.getText())));
    }//GEN-LAST:event_txt_lateundertimeminsActionPerformed

    private void btn_add_payrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_payrollActionPerformed
        
        try{
        objPayroll.objEmployee.setEmpId(Integer.parseInt(txt_empId_allowance.getText()));
        objPayroll.settotalDays(Integer.parseInt(txt_totaldays.getText()));
        objPayroll.setsalratedaily(Double.parseDouble(txt_salratedaily.getText()));
        objPayroll.setbasicSalary(Double.parseDouble(txt_basicsalary.getText()));
        objPayroll.setallowanceAmount(Double.parseDouble(txt_allowance.getText()));
        objPayroll.setnightDiffHours(Double.parseDouble(txt_nightdiffhours.getText()));
        objPayroll.setnightDiffAmount(Double.parseDouble(txt_nightdiffamount.getText()));
        objPayroll.setspecialHolidaydays(Double.parseDouble(txt_specialholidayhours.getText()));
        objPayroll.setspecialHolidayAmount(Double.parseDouble(txt_specialholidayamount.getText()));
        objPayroll.setSHNDhours(Double.parseDouble(txt_SHNDhours.getText()));
        objPayroll.setSHNDAmount(Double.parseDouble(txt_SHNDamount.getText()));
        objPayroll.setLegalHolidayDays(Double.parseDouble(txt_LegalHolidayhours.getText()));
        objPayroll.setLegalHolidayAmount(Double.parseDouble(txt_LegalHolidayamount.getText()));
        objPayroll.setLHNDhours(Double.parseDouble(txt_LHNDhours.getText()));
        objPayroll.setLHNDamount(Double.parseDouble(txt_LHNDamount.getText()));
        objPayroll.setOThours(Double.parseDouble(txt_overtimehours.getText()));
        objPayroll.setOTamount(Double.parseDouble(txt_overtimeamount.getText()));
        objPayroll.setOTNDhours(Double.parseDouble(txt_OTNDhours.getText()));
        objPayroll.setOTNDamount(Double.parseDouble(txt_OTNDamount.getText()));
        objPayroll.setleaveothersamount(Double.parseDouble(txt_leaveothersamount.getText()));
        //objPayroll.setleaveothersamount(objPayroll.calculateleave(objPayroll.objLeave.getVacationleave(), objPayroll.objLeave.getSickleave(),txt_salratedaily.getText()));
        objPayroll.setadjustmentamount(Double.parseDouble(txt_adjustmentamount.getText()));
        objPayroll.setlateundertimemins(Double.parseDouble(txt_lateundertimemins.getText()));
        //objPayroll.setlateundertimerate(Double.parseDouble(txt_lateundertimerate.getText()));
        objPayroll.setlateundertimeamount(Double.parseDouble(txt_lateundertimeamount.getText()));
        objPayroll.setSSSamount(Double.parseDouble(txt_SSSamount.getText()));
        objPayroll.setphilhealthamount(Double.parseDouble(txt_PHILHEALTHamount.getText()));
        objPayroll.sethdmfamount(Double.parseDouble(txt_HDMFamount.getText()));
        objPayroll.setotherdeductionamount(Double.parseDouble(txt_otherdeductionamount.getText()));
        objPayroll.setDate(txtDate1.getText());
        objPayroll.setfromcutoffdate(fromcutofftxt.getText());
        objPayroll.settocutoffdate(tocutofftxt.getText());
        objPayroll.setTotalDeductions(objPayroll.calculatetotaldeduction(txt_lateundertimeamount.getText(), txt_SSSamount.getText(), txt_PHILHEALTHamount.getText(), txt_HDMFamount.getText(), txt_otherdeductionamount.getText()));
        
        if (objPayroll.insertSalaryDetails()) {
            clearPayroll();
        }
        }
        catch (NumberFormatException e) {
        // Handle the exception caused by an empty string
        if (e.getMessage().equals("empty String")) {
        // Handle the empty string case here
        JOptionPane.showMessageDialog(null, "An input field was left empty. Please provide a valid number or put 0 if there is no entry for such field.", "ERROR", 0);
        } else {
        // Handle other NumberFormatException cases
         JOptionPane.showMessageDialog(null, "Invalid number format. Please provide a valid number.", "ERROR", 0);
        }
        }
    }//GEN-LAST:event_btn_add_payrollActionPerformed

    private void menuBar_payrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_payrollActionPerformed


    }//GEN-LAST:event_menuBar_payrollActionPerformed

    private void menuBar_payrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBar_payrollMouseClicked


    }//GEN-LAST:event_menuBar_payrollMouseClicked

    private void menuBar_payroll_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_payroll_addActionPerformed

        hideFrames();
        intFrame_payroll.setVisible(true);
    }//GEN-LAST:event_menuBar_payroll_addActionPerformed

    private void btn_pay_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pay_exitActionPerformed

        intFrame_print.setVisible(false);
    }//GEN-LAST:event_btn_pay_exitActionPerformed

    private void menuBar_payslip_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_payslip_printActionPerformed

        hideFrames();
        clearPaySlip();
        //intFrame_print.setVisible(true);
        jFrame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame1.show();
    }//GEN-LAST:event_menuBar_payslip_printActionPerformed

    private void btn_pay_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pay_searchActionPerformed
        jDialog2.setVisible(true);
        /*if (objPaySlip.getPayDetails(getEmpId())) {
            lbl_pay_empId.setText(String.valueOf(objPaySlip.objEmployee.getEmpId()));
            lbl_pay_name.setText(objPaySlip.objEmployee.getFname() + " " + objPaySlip.objEmployee.getLname());
            lbl_pay_desig.setText(objPaySlip.objEmployee.getDesignation());
            lbl_pay_depart.setText(objPaySlip.objEmployee.getDepartment());
            lbl_pay_basic.setText(String.valueOf(objPaySlip.getbasicpay()));
            lbl_allowance.setText(String.valueOf(objPaySlip.getallowanceAmount()));
            lbl_nightdiff.setText(String.valueOf(objPaySlip.getnightDiffAmount()));
            lbl_specialholiday.setText(String.valueOf(objPaySlip.getspecialHolidayAmount()));
            lbl_SHND.setText(String.valueOf(objPaySlip.getSHNDAmount()));
            lbl_LegalHoliday.setText(String.valueOf(objPaySlip.getLegalHolidayAmount()));
            lbl_LHND.setText(String.valueOf(objPaySlip.getLHNDamount()));
            lbl_OT.setText(String.valueOf(objPaySlip.getOTamount()));
            lbl_OTND.setText(String.valueOf(objPaySlip.getOTNDamount()));
            lbl_leaveothers.setText(String.valueOf(objPaySlip.getleaveothersamount()));
            lbl_adjustment.setText(String.valueOf(objPaySlip.getadjustmentamount()));
            lbl_lateundertime.setText(String.valueOf(objPaySlip.getlateundertimeamount()));
            lbl_SSS.setText(String.valueOf(objPaySlip.getSSSamount()));
            lbl_philhealth.setText(String.valueOf(objPaySlip.getphilhealthamount()));
            lbl_HDMF.setText(String.valueOf(objPaySlip.gethdmfamount()));
            lbl_otherdeduction.setText(String.valueOf(objPaySlip.getotherdeductionamount()));
            lbl_pay_gross.setText(String.valueOf(objPaySlip.getGrossPay()));
            lbl_pay_deduct.setText(String.valueOf(objPaySlip.getTotalDeductions()));
            lbl_pay_net.setText(String.valueOf(objPaySlip.getNetPay()));
            fromcutoffdatelabel.setText(String.valueOf(objPaySlip.getfromcutoffdate()));
            tocutoffdatelabel.setText(String.valueOf(objPaySlip.gettocutoffdate()));
            paydatelabel.setText(String.valueOf(objPaySlip.getDate()));
            
            
        
        }*/
    }//GEN-LAST:event_btn_pay_searchActionPerformed

    private void btn_pay_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pay_printActionPerformed
        //print frame (pay slip)        
        if (lbl_pay_net.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "No Pay Details to Print", "ERROR", 0);

        } 
        else {
            PageFormat preFormat = printJob.defaultPage();
            preFormat.setOrientation(PageFormat.PORTRAIT);
            PageFormat postFormat = printJob.pageDialog(preFormat);
            // Calculate dimensions for the two halves
                int halfWidth = image1.getWidth() / 2;
                int halfHeight = image1.getHeight() / 2;

                // Create rectangles for each half
                //Rectangle firstHalf = new Rectangle(0, halfHeight, halfWidth, halfHeight);
                //Rectangle secondHalf = new Rectangle(0, halfHeight, halfWidth, halfHeight);
            if (preFormat != postFormat) {
            try{
                // Set the printable content to the captured images
                printJob.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                        if (pageIndex == 0) {
                            Graphics2D g2d = (Graphics2D) graphics;
                            double pageWidth = pageFormat.getImageableWidth();
                            double pageHeight = pageFormat.getImageableHeight();
                            
                            // Scale images to fit the page
                            double scale = Math.min(pageWidth / halfWidth, pageHeight / (2 * halfHeight));
                            int scaledWidth = (int) (halfWidth * scale);
                            int scaledHeight = (int) (halfHeight * scale);

                            int yOffset = (int)((pageHeight - 2 * scaledHeight)/2);
                            // Draw black border around first image
                            g2d.setColor(Color.BLACK);
                            g2d.fillRect(0, yOffset, scaledWidth, scaledHeight);
                            // Draw the image over the black border
                            g2d.drawImage(image1, 2, yOffset + 2, scaledWidth - 4, scaledHeight - 4, null);

                            // Draw black border around second image
                            g2d.setColor(Color.BLACK);
                            g2d.fillRect(0, yOffset + scaledHeight + 100, scaledWidth, scaledHeight);
                            // Draw the image over the black border
                            g2d.drawImage(image2, 2, yOffset + scaledHeight + 102, scaledWidth - 4, scaledHeight - 4, null);
                            

                            return Printable.PAGE_EXISTS;
                        } else {
                            return Printable.NO_SUCH_PAGE;
                        }
                    }
                }, postFormat);

                // Show print dialog for final confirmation
                if (printJob.printDialog()) {
                    // Perform the printing
                    printJob.print();
                }
            } catch (PrinterException ex) {
                // Handle exceptions
                ex.printStackTrace();
                System.err.println("Error: " + ex.getMessage());
            }
            /*
            PrinterJob printJob = PrinterJob.getPrinterJob();
            PageFormat preFormat = printJob.defaultPage();
            preFormat.setOrientation(PageFormat.PORTRAIT);
            PageFormat postFormat = printJob.pageDialog(preFormat);

            if (preFormat != postFormat) {
                try {
                    // Create a Robot instance, which may throw AWTException
                    Robot robot = new Robot();

                    // Get the screen dimensions
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    Rectangle printArea = new Rectangle(-40,90,screenSize.width-380,screenSize.height-180);

                    // Capture a screenshot of the desired area
                    BufferedImage image = robot.createScreenCapture(printArea);

                    // Set the printable content to the captured image
                    printJob.setPrintable(new PrintableImage(image), postFormat);

                    // Show print dialog for final confirmation
                    if (printJob.printDialog()) {
                        // Perform the printing
                        printJob.print();
                    }
                } 
                catch (AWTException | PrinterException ex) {
                // Handle exceptions
                ex.printStackTrace();
                System.err.println("Error: " + ex.getMessage());
                }
            }
            */
            }
        }
    }//GEN-LAST:event_btn_pay_printActionPerformed

    private void txt_vl_countActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vl_countActionPerformed

    }//GEN-LAST:event_txt_vl_countActionPerformed

    private void txt_vl_countKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vl_countKeyTyped

    }//GEN-LAST:event_txt_vl_countKeyTyped

    private void txt_sl_countActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sl_countActionPerformed

    }//GEN-LAST:event_txt_sl_countActionPerformed

    private void btn_search_leaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_leaveActionPerformed

        if (objLeave.getLeaveDetails(getEmpId())) {

            txt_empId_leave.setText(String.valueOf(objLeave.objEmployee.getEmpId()));
            txt_fname_leave.setText(objLeave.objEmployee.getFname());
            txt_lname_leave.setText(objLeave.objEmployee.getLname());
            txt_desig_leave.setText(objLeave.objEmployee.getDesignation());
            txt_depart_leave.setText(objLeave.objEmployee.getDepartment());
            txt_vl_count.setText(String.valueOf(objLeave.getVacationleave()));
            txt_sl_count.setText(String.valueOf(objLeave.getSickleave()));
        }
    }//GEN-LAST:event_btn_search_leaveActionPerformed

    private void btn_apply_leaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_apply_leaveActionPerformed

        objLeave.objEmployee.setEmpId(Integer.parseInt(txt_empId_leave.getText()));
        objLeave.setVacationleave(Double.parseDouble(txt_vl_count.getText()) - Double.parseDouble(txt_vl_apply.getText()));
        objLeave.setSickleave(Double.parseDouble(txt_sl_count.getText()) - Double.parseDouble(txt_sl_apply.getText()));
        if (objLeave.applyLeave()) {
            clearLeave();
        }
    }//GEN-LAST:event_btn_apply_leaveActionPerformed

    private void btn_exit_leaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exit_leaveActionPerformed

        intFrame_leave.setVisible(false);
    }//GEN-LAST:event_btn_exit_leaveActionPerformed

    private void menuBar_leave_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBar_leave_applyActionPerformed

        hideFrames();
        intFrame_leave.setVisible(true);
    }//GEN-LAST:event_menuBar_leave_applyActionPerformed

    private void txt_vl_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_vl_applyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_vl_applyActionPerformed

    private void txt_vl_applyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_vl_applyKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_vl_applyKeyTyped

    private void txt_sl_applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sl_applyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sl_applyActionPerformed
    
    
    
    private void btn_update_payrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_payrollActionPerformed
        // TODO add your handling code here:
        try {
    objPayroll.objEmployee.setEmpId(Integer.parseInt(txt_empId_allowance.getText()));
        
        objPayroll.settotalDays(Integer.parseInt(txt_totaldays.getText()));
        objPayroll.setsalratedaily(Double.parseDouble(txt_salratedaily.getText()));
        objPayroll.setbasicSalary(objPayroll.calculatebasicsal(txt_totaldays.getText(), txt_salratedaily.getText()));
        //objPayroll.setbasicSalary(Double.parseDouble(txt_basicsalary.getText()));
        
        objPayroll.setallowanceAmount(Double.parseDouble(txt_allowance.getText())); 
        
        objPayroll.setnightDiffHours(Double.parseDouble(txt_nightdiffhours.getText()));
        objPayroll.setnightDiffAmount(objPayroll.calculatenightdiff(txt_nightdiffhours.getText(), txt_salratedaily.getText()));
        //objPayroll.setnightDiffAmount(Double.parseDouble(txt_nightdiffamount.getText()));
        objPayroll.setspecialHolidaydays(Double.parseDouble(txt_specialholidayhours.getText()));
        objPayroll.setspecialHolidayAmount(objPayroll.calculatespecialholiday(txt_specialholidayhours.getText(), txt_salratedaily.getText()));
        //objPayroll.setspecialHolidayAmount(Double.parseDouble(txt_specialholidayamount.getText()));
        objPayroll.setSHNDhours(Double.parseDouble(txt_SHNDhours.getText()));
        objPayroll.setSHNDAmount(objPayroll.calculateshnd(txt_SHNDhours.getText()));
        //objPayroll.setSHNDAmount(Double.parseDouble(txt_SHNDamount.getText()));
        objPayroll.setLegalHolidayDays(Double.parseDouble(txt_LegalHolidayhours.getText()));
        objPayroll.setLegalHolidayAmount(objPayroll.calculatelegalholiday(txt_LegalHolidayhours.getText(),txt_salratedaily.getText()));
        //objPayroll.setLegalHolidayAmount(Double.parseDouble(txt_LegalHolidayamount.getText()));
        objPayroll.setLHNDhours(Double.parseDouble(txt_LHNDhours.getText()));
        objPayroll.setLHNDamount(objPayroll.calculateLHND(txt_LHNDhours.getText()));
        //objPayroll.setLHNDamount(Double.parseDouble(txt_LHNDamount.getText()));
        objPayroll.setOThours(Double.parseDouble(txt_overtimehours.getText()));
        objPayroll.setOTamount(objPayroll.calculateOT(txt_overtimehours.getText(),txt_salratedaily.getText()));
        //objPayroll.setOTamount(Double.parseDouble(txt_overtimeamount.getText()));
        objPayroll.setOTNDhours(Double.parseDouble(txt_OTNDhours.getText()));
        objPayroll.setOTNDamount(objPayroll.calculateOTND(txt_OTNDhours.getText(),txt_salratedaily.getText()));
        //objPayroll.setOTNDamount(Double.parseDouble(txt_OTNDamount.getText()));
        objPayroll.setleaveothersamount(Double.parseDouble(txt_leaveothersamount.getText()));
        //objPayroll.setleaveothersamount(objPayroll.calculateleave(objPayroll.objLeave.getVacationleave(), objPayroll.objLeave.getSickleave(),txt_salratedaily.getText()));
            
        objPayroll.setadjustmentamount(Double.parseDouble(txt_adjustmentamount.getText()));
        objPayroll.setlateundertimemins(Double.parseDouble(txt_lateundertimemins.getText()));
        //objPayroll.setlateundertimerate(Double.parseDouble(txt_lateundertimerate.getText()));
        //objPayroll.setlateundertimeamount(objPayroll.calculatelateundertime(txt_lateundertimemins.getText(), objPayroll.calculaterate(txt_lateundertimemins.getText())));
        objPayroll.setTotalDeductions(objPayroll.calculatetotaldeduction(txt_lateundertimeamount.getText(), txt_SSSamount.getText(), txt_PHILHEALTHamount.getText(), txt_HDMFamount.getText(), txt_otherdeductionamount.getText()));
        objPayroll.setlateundertimeamount(Double.parseDouble(txt_lateundertimeamount.getText()));
        objPayroll.setSSSamount(Double.parseDouble(txt_SSSamount.getText()));
        objPayroll.setphilhealthamount(Double.parseDouble(txt_PHILHEALTHamount.getText()));
        objPayroll.sethdmfamount(Double.parseDouble(txt_HDMFamount.getText()));
        objPayroll.setotherdeductionamount(Double.parseDouble(txt_otherdeductionamount.getText()));
        objPayroll.setDate(txtDate1.getText());
        objPayroll.setfromcutoffdate(fromcutofftxt.getText());
        objPayroll.settocutoffdate(tocutofftxt.getText());
        if (objPayroll.updateSalaryDetails(txt_empId_allowance.getText(),txtDate1.getText())) {
            JOptionPane.showMessageDialog(null, "Salary details have been Updated", "", 1);
            clearPayroll();
        } else {
            JOptionPane.showMessageDialog(null, "Error occurred while Updating Salary Detals", "ERROR", 0);
        }
        } catch (NumberFormatException e) {
        // Handle the exception caused by an empty string
        if (e.getMessage().equals("empty String")) {
        // Handle the empty string case here
        JOptionPane.showMessageDialog(null, "An input field was left empty. Please provide a valid number or put 0 if there is no entry for such field.", "ERROR", 0);
        } else {
        // Handle other NumberFormatException cases
         JOptionPane.showMessageDialog(null, "Invalid number format. Please provide a valid number.", "ERROR", 0);
        }
        }
        

        
    }//GEN-LAST:event_btn_update_payrollActionPerformed

    private void txt_LegalHolidayhoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LegalHolidayhoursActionPerformed
        txt_LegalHolidayamount.setText(String.valueOf(objPayroll.calculatelegalholiday(txt_LegalHolidayhours.getText(),txt_salratedaily.getText())));
    }//GEN-LAST:event_txt_LegalHolidayhoursActionPerformed

    private void txt_LHNDhoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LHNDhoursActionPerformed
        txt_LHNDamount.setText(String.valueOf(objPayroll.calculateLHND(txt_LHNDhours.getText())));
    }//GEN-LAST:event_txt_LHNDhoursActionPerformed

    private void txt_overtimehoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_overtimehoursActionPerformed
        txt_overtimeamount.setText(String.valueOf(objPayroll.calculateOT(txt_overtimehours.getText(),txt_salratedaily.getText())));
    }//GEN-LAST:event_txt_overtimehoursActionPerformed

    private void txt_OTNDhoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_OTNDhoursActionPerformed
        txt_OTNDamount.setText(String.valueOf(objPayroll.calculateOTND(txt_OTNDhours.getText(),txt_salratedaily.getText())));
    }//GEN-LAST:event_txt_OTNDhoursActionPerformed

    private void txt_leaveothersamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_leaveothersamountActionPerformed
        txt_leaveothersamount.setText(String.valueOf(objPayroll.calculateleave(objPayroll.objLeave.getVacationleave(), objPayroll.objLeave.getSickleave(), txt_salratedaily.getText())));
    }//GEN-LAST:event_txt_leaveothersamountActionPerformed

    private void txt_adjustmentamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_adjustmentamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adjustmentamountActionPerformed

    private void txt_allowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_allowanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_allowanceActionPerformed

    private void txt_SSSamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SSSamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SSSamountActionPerformed

    private void txt_PHILHEALTHamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PHILHEALTHamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PHILHEALTHamountActionPerformed

    private void txt_HDMFamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_HDMFamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_HDMFamountActionPerformed

    private void txt_otherdeductionamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_otherdeductionamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_otherdeductionamountActionPerformed

    private void txt_totaldaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totaldaysActionPerformed
        txt_basicsalary.setText(String.valueOf(objPayroll.calculatebasicsal(txt_totaldays.getText(), txt_salratedaily.getText())));
    }//GEN-LAST:event_txt_totaldaysActionPerformed

    private void txt_salratedailyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_salratedailyActionPerformed
        txt_basicsalary.setText(String.valueOf(objPayroll.calculatebasicsal(txt_totaldays.getText(), txt_salratedaily.getText())));
    }//GEN-LAST:event_txt_salratedailyActionPerformed

    private void txt_basicsalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_basicsalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_basicsalaryActionPerformed

    private void txt_specialholidayamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_specialholidayamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_specialholidayamountActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       hideFrames();
       intFrame_cutoff.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btn_exit_payroll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exit_payroll1ActionPerformed
        intFrame_cutoff.setVisible(false);
    }//GEN-LAST:event_btn_exit_payroll1ActionPerformed

    private void txtDate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDate2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDate2ActionPerformed

    private void btn_update_leaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_leaveActionPerformed
        objLeave.objEmployee.setEmpId(Integer.parseInt(txt_empId_leave.getText()));
        objLeave.setVacationleave(Double.parseDouble(txt_vl_count.getText()));
        objLeave.setSickleave(Double.parseDouble(txt_sl_count.getText()));
        if (objLeave.applyLeave()) {
            clearLeave();
        }
    }//GEN-LAST:event_btn_update_leaveActionPerformed

    private void btn_reset_leaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reset_leaveActionPerformed
        double vacationLeave = 5.0;
        double sickLeave = 5.0;

        objLeave.setVacationleave(vacationLeave);
        objLeave.setSickleave(sickLeave);
        int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Reset Leave?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                if (objLeave.resetLeave()) {
                clearLeave();
                }
            } else if (result == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(frame, "Cancelled!");
            }
        
    }//GEN-LAST:event_btn_reset_leaveActionPerformed

    private void deletesalarydeets_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletesalarydeets_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deletesalarydeets_dateActionPerformed

    private void deletesalarydeets_empidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletesalarydeets_empidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deletesalarydeets_empidActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //jDialog1.setVisible(true);
        jDialog5.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String deletesalarydeetsempid = deletesalarydeets_empid.getText();
        String deletesalarydeetsdate = deletesalarydeets_date.getText();
        int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Delete Salary Details?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                objPayroll.deleteSalaryDetails(deletesalarydeetsempid,deletesalarydeetsdate);
            } else if (result == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(frame, "Cancelled!");
            }
        jDialog1.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String empId = getEmpId();
        txtDate2.setText(null);
        model = (DefaultTableModel) jTable1.getModel();
        String sql = "SELECT * FROM employee e,salary_details s WHERE e.empId=" + empId + " AND e.empId=s.empId";
        loadData(sql);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        model = (DefaultTableModel) jTable1.getModel();
        String sql = "SELECT * FROM employee e,salary_details s WHERE e.empId=s.empId";
        loadData(sql);
        txtDate2.setText(null);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void print_empid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_empid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_empid1ActionPerformed

    private void print_date1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_date1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_date1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String empid = print_empid1.getText();
        String date = print_date1.getText();
        if (objPaySlip.getPayDetails(empid, date)) {
            // Parse the input date string to a LocalDate object
            LocalDate fromcutoffdate = LocalDate.parse(String.valueOf(objPaySlip.getfromcutoffdate()));
            LocalDate tocutoffdate = LocalDate.parse(String.valueOf(objPaySlip.gettocutoffdate()));
            LocalDate paydate = LocalDate.parse(String.valueOf(objPaySlip.getDate()));

            // Define the output date format
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM. d, yyyy", Locale.ENGLISH);

            // Format the LocalDate object to the desired output format
            String formattedfromcutoffdate = fromcutoffdate.format(outputFormatter);
            String formattedtocutoffdate = tocutoffdate.format(outputFormatter);
            String formattedpaydate = paydate.format(outputFormatter);

            
            lbl_pay_empId1.setText(String.valueOf(objPaySlip.objEmployee.getEmpId()));
            // Input string
            String name = objPaySlip.objEmployee.getFname() + " " + objPaySlip.objEmployee.getLname();

            // Convert the string to uppercase
            String uppercasedName = name.toUpperCase();
            lbl_pay_name1.setText(uppercasedName);
            //lbl_pay_name1.setText(objPaySlip.objEmployee.getFname() + " " + objPaySlip.objEmployee.getLname());
            lbl_pay_desig1.setText(objPaySlip.objEmployee.getDesignation());
            lbl_pay_depart1.setText(objPaySlip.objEmployee.getDepartment());
            lbl_pay_basic1.setText(String.valueOf(objPaySlip.getbasicpay()));
            lbl_allowance1.setText(String.valueOf(objPaySlip.getallowanceAmount()));
            lbl_nightdiff1.setText(String.valueOf(objPaySlip.getnightDiffAmount()));
            lbl_specialholiday1.setText(String.valueOf(objPaySlip.getspecialHolidayAmount()));
            lbl_SHND1.setText(String.valueOf(objPaySlip.getSHNDAmount()));
            lbl_LegalHoliday1.setText(String.valueOf(objPaySlip.getLegalHolidayAmount()));
            lbl_LHND1.setText(String.valueOf(objPaySlip.getLHNDamount()));
            lbl_OT1.setText(String.valueOf(objPaySlip.getOTamount()));
            lbl_OTND1.setText(String.valueOf(objPaySlip.getOTNDamount()));
            lbl_leaveothers1.setText(String.valueOf(objPaySlip.getleaveothersamount()));
            lbl_adjustment1.setText(String.valueOf(objPaySlip.getadjustmentamount()));
            lbl_lateundertime1.setText(String.valueOf(objPaySlip.getlateundertimeamount()));
            lbl_SSS1.setText(String.valueOf(objPaySlip.getSSSamount()));
            lbl_philhealth1.setText(String.valueOf(objPaySlip.getphilhealthamount()));
            lbl_HDMF1.setText(String.valueOf(objPaySlip.gethdmfamount()));
            lbl_otherdeduction1.setText(String.valueOf(objPaySlip.getotherdeductionamount()));
            lbl_pay_gross1.setText(String.valueOf(objPaySlip.getGrossPay()));
            lbl_pay_deduct1.setText(String.valueOf(objPaySlip.getTotalDeductions()));
            lbl_pay_net1.setText(String.valueOf(objPaySlip.getNetPay()));
            
            fromcutoffdatelabel1.setText(formattedfromcutoffdate);
            //fromcutoffdatelabel.setText(String.valueOf(objPaySlip.getfromcutoffdate()));
            tocutoffdatelabel1.setText(formattedtocutoffdate);
            //tocutoffdatelabel.setText(String.valueOf(objPaySlip.gettocutoffdate()));
            
            paydatelabel1.setText(formattedpaydate);
            //paydatelabel1.setText(String.valueOf(objPaySlip.getDate()));
            numdayslbl1.setText(String.valueOf(objPaySlip.gettotalDays()));
            dailyratelbl1.setText(String.valueOf(objPaySlip.getsalratedaily()));
            
            

        }
        if (objPaySlip.getPayDetails(empid, date)) {
            // Parse the input date string to a LocalDate object
            LocalDate fromcutoffdate = LocalDate.parse(String.valueOf(objPaySlip.getfromcutoffdate()));
            LocalDate tocutoffdate = LocalDate.parse(String.valueOf(objPaySlip.gettocutoffdate()));

            // Define the output date format
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM. d, yyyy", Locale.ENGLISH);

            // Format the LocalDate object to the desired output format
            String formattedfromcutoffdate = fromcutoffdate.format(outputFormatter);
            String formattedtocutoffdate = tocutoffdate.format(outputFormatter);

            lbl_pay_empId.setText(String.valueOf(objPaySlip.objEmployee.getEmpId()));
            lbl_pay_name.setText(objPaySlip.objEmployee.getFname() + " " + objPaySlip.objEmployee.getLname());
            lbl_pay_desig.setText(objPaySlip.objEmployee.getDesignation());
            lbl_pay_depart.setText(objPaySlip.objEmployee.getDepartment());
            lbl_pay_basic.setText(String.valueOf(objPaySlip.getbasicpay()));
            lbl_allowance.setText(String.valueOf(objPaySlip.getallowanceAmount()));
            lbl_nightdiff.setText(String.valueOf(objPaySlip.getnightDiffAmount()));
            lbl_specialholiday.setText(String.valueOf(objPaySlip.getspecialHolidayAmount()));
            lbl_SHND.setText(String.valueOf(objPaySlip.getSHNDAmount()));
            lbl_LegalHoliday.setText(String.valueOf(objPaySlip.getLegalHolidayAmount()));
            lbl_LHND.setText(String.valueOf(objPaySlip.getLHNDamount()));
            lbl_OT.setText(String.valueOf(objPaySlip.getOTamount()));
            lbl_OTND.setText(String.valueOf(objPaySlip.getOTNDamount()));
            lbl_leaveothers.setText(String.valueOf(objPaySlip.getleaveothersamount()));
            lbl_adjustment.setText(String.valueOf(objPaySlip.getadjustmentamount()));
            lbl_lateundertime.setText(String.valueOf(objPaySlip.getlateundertimeamount()));
            lbl_SSS.setText(String.valueOf(objPaySlip.getSSSamount()));
            lbl_philhealth.setText(String.valueOf(objPaySlip.getphilhealthamount()));
            lbl_HDMF.setText(String.valueOf(objPaySlip.gethdmfamount()));
            lbl_otherdeduction.setText(String.valueOf(objPaySlip.getotherdeductionamount()));
            lbl_pay_gross.setText(String.valueOf(objPaySlip.getGrossPay()));
            lbl_pay_deduct.setText(String.valueOf(objPaySlip.getTotalDeductions()));
            lbl_pay_net.setText(String.valueOf(objPaySlip.getNetPay()));
            
            
            fromcutoffdatelabel.setText(formattedfromcutoffdate);
            //fromcutoffdatelabel.setText(String.valueOf(objPaySlip.getfromcutoffdate()));
            tocutoffdatelabel.setText(formattedtocutoffdate);
            //tocutoffdatelabel.setText(String.valueOf(objPaySlip.gettocutoffdate()));
            paydatelabel.setText(String.valueOf(objPaySlip.getDate()));
            numdayslbl.setText(String.valueOf(objPaySlip.gettotalDays()));
            dailyratelbl.setText(String.valueOf(objPaySlip.getsalratedaily()));
            
            
        
        }
        jDialog2.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void print_empfnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_empfnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_empfnameActionPerformed

    private void print_datevianameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_datevianameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_datevianameActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String empfname = print_empfname.getText();
        String emplname = print_emplname.getText();
        String date = print_datevianame.getText();
        if (objPaySlip.getPayDetailsvianame(empfname, emplname, date)) {
            lbl_pay_empId.setText(String.valueOf(objPaySlip.objEmployee.getEmpId()));
            lbl_pay_name.setText(objPaySlip.objEmployee.getFname() + " " + objPaySlip.objEmployee.getLname());
            lbl_pay_desig.setText(objPaySlip.objEmployee.getDesignation());
            lbl_pay_depart.setText(objPaySlip.objEmployee.getDepartment());
            lbl_pay_basic.setText(String.valueOf(objPaySlip.getbasicpay()));
            lbl_allowance.setText(String.valueOf(objPaySlip.getallowanceAmount()));
            lbl_nightdiff.setText(String.valueOf(objPaySlip.getnightDiffAmount()));
            lbl_specialholiday.setText(String.valueOf(objPaySlip.getspecialHolidayAmount()));
            lbl_SHND.setText(String.valueOf(objPaySlip.getSHNDAmount()));
            lbl_LegalHoliday.setText(String.valueOf(objPaySlip.getLegalHolidayAmount()));
            lbl_LHND.setText(String.valueOf(objPaySlip.getLHNDamount()));
            lbl_OT.setText(String.valueOf(objPaySlip.getOTamount()));
            lbl_OTND.setText(String.valueOf(objPaySlip.getOTNDamount()));
            lbl_leaveothers.setText(String.valueOf(objPaySlip.getleaveothersamount()));
            lbl_adjustment.setText(String.valueOf(objPaySlip.getadjustmentamount()));
            lbl_lateundertime.setText(String.valueOf(objPaySlip.getlateundertimeamount()));
            lbl_SSS.setText(String.valueOf(objPaySlip.getSSSamount()));
            lbl_philhealth.setText(String.valueOf(objPaySlip.getphilhealthamount()));
            lbl_HDMF.setText(String.valueOf(objPaySlip.gethdmfamount()));
            lbl_otherdeduction.setText(String.valueOf(objPaySlip.getotherdeductionamount()));
            lbl_pay_gross.setText(String.valueOf(objPaySlip.getGrossPay()));
            lbl_pay_deduct.setText(String.valueOf(objPaySlip.getTotalDeductions()));
            lbl_pay_net.setText(String.valueOf(objPaySlip.getNetPay()));
            fromcutoffdatelabel.setText(String.valueOf(objPaySlip.getfromcutoffdate()));
            tocutoffdatelabel.setText(String.valueOf(objPaySlip.gettocutoffdate()));
            paydatelabel.setText(String.valueOf(objPaySlip.getDate()));
            numdayslbl.setText(String.valueOf(objPaySlip.gettotalDays()));
            dailyratelbl.setText(String.valueOf(objPaySlip.getsalratedaily()));
            
            lbl_pay_empId1.setText(String.valueOf(objPaySlip.objEmployee.getEmpId()));
            lbl_pay_name1.setText(objPaySlip.objEmployee.getFname() + " " + objPaySlip.objEmployee.getLname());
            lbl_pay_desig1.setText(objPaySlip.objEmployee.getDesignation());
            lbl_pay_depart1.setText(objPaySlip.objEmployee.getDepartment());
            lbl_pay_basic1.setText(String.valueOf(objPaySlip.getbasicpay()));
            lbl_allowance1.setText(String.valueOf(objPaySlip.getallowanceAmount()));
            lbl_nightdiff1.setText(String.valueOf(objPaySlip.getnightDiffAmount()));
            lbl_specialholiday1.setText(String.valueOf(objPaySlip.getspecialHolidayAmount()));
            lbl_SHND1.setText(String.valueOf(objPaySlip.getSHNDAmount()));
            lbl_LegalHoliday1.setText(String.valueOf(objPaySlip.getLegalHolidayAmount()));
            lbl_LHND1.setText(String.valueOf(objPaySlip.getLHNDamount()));
            lbl_OT1.setText(String.valueOf(objPaySlip.getOTamount()));
            lbl_OTND1.setText(String.valueOf(objPaySlip.getOTNDamount()));
            lbl_leaveothers1.setText(String.valueOf(objPaySlip.getleaveothersamount()));
            lbl_adjustment1.setText(String.valueOf(objPaySlip.getadjustmentamount()));
            lbl_lateundertime1.setText(String.valueOf(objPaySlip.getlateundertimeamount()));
            lbl_SSS1.setText(String.valueOf(objPaySlip.getSSSamount()));
            lbl_philhealth1.setText(String.valueOf(objPaySlip.getphilhealthamount()));
            lbl_HDMF1.setText(String.valueOf(objPaySlip.gethdmfamount()));
            lbl_otherdeduction1.setText(String.valueOf(objPaySlip.getotherdeductionamount()));
            lbl_pay_gross1.setText(String.valueOf(objPaySlip.getGrossPay()));
            lbl_pay_deduct1.setText(String.valueOf(objPaySlip.getTotalDeductions()));
            lbl_pay_net1.setText(String.valueOf(objPaySlip.getNetPay()));
            fromcutoffdatelabel1.setText(String.valueOf(objPaySlip.getfromcutoffdate()));
            tocutoffdatelabel1.setText(String.valueOf(objPaySlip.gettocutoffdate()));
            paydatelabel1.setText(String.valueOf(objPaySlip.getDate()));
            numdayslbl1.setText(String.valueOf(objPaySlip.gettotalDays()));
            dailyratelbl1.setText(String.valueOf(objPaySlip.getsalratedaily()));
            
            

        }
        jDialog3.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void print_emplnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_emplnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_emplnameActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jDialog3.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

            try {
                // Create a Robot instance, which may throw AWTException
                Robot robot = new Robot();

                // Get the screen dimensions
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Rectangle printArea = new Rectangle(-40, 90, screenSize.width - 380, screenSize.height - 180);

                // Capture first screenshot
                image1 = robot.createScreenCapture(printArea);
            }catch (AWTException ex) {
                // Handle exceptions
                ex.printStackTrace();
                System.err.println("Error: " + ex.getMessage());
            }
       
        JFrame f=new JFrame();  
        JOptionPane.showMessageDialog(f,"Payslip 1 captured successfully");  
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

            try {
                // Create a Robot instance, which may throw AWTException
                Robot robot = new Robot();

                // Get the screen dimensions
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Rectangle printArea = new Rectangle(-40, 90, screenSize.width - 380, screenSize.height - 180);

                // Capture first screenshot
                image2 = robot.createScreenCapture(printArea);
            }catch (AWTException ex) {
                // Handle exceptions
                ex.printStackTrace();
                System.err.println("Error: " + ex.getMessage());
            }
        
        JFrame f=new JFrame();  
        JOptionPane.showMessageDialog(f,"Payslip 2 captured successfully");  
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        exportarExcel(jTable1);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        model2 = (DefaultTableModel) jTable1.getModel();
        String pydtsort = txt_pydt.getText();
        loadData("SELECT * FROM employee e,salary_details s WHERE e.empId=s.empId AND date = '" + pydtsort + "'");
        txtDate2.setText(null);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        txt_leaveothersamount.setText(String.valueOf(objPayroll.calculateleave(objPayroll.objLeave.getVacationleave(), objPayroll.objLeave.getSickleave(), txt_salratedaily.getText())));
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btn_pay_search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pay_search1ActionPerformed
        jDialog2.setVisible(true);
    }//GEN-LAST:event_btn_pay_search1ActionPerformed

    private void btn_pay_exit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pay_exit1ActionPerformed
        jFrame1.dispose();
    }//GEN-LAST:event_btn_pay_exit1ActionPerformed

    private void btn_pay_print1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pay_print1ActionPerformed
        //print frame (pay slip)        
        /*if (lbl_pay_net1.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "No Pay Details to Print", "ERROR", 0);

        } 
        else {*/
            PageFormat preFormat = printJob.defaultPage();
            preFormat.setOrientation(PageFormat.PORTRAIT);
            PageFormat postFormat = printJob.pageDialog(preFormat);

            if (preFormat != postFormat) {
            try{
                // Set the printable content to the captured images
                printJob.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                        if (pageIndex == 0) {
                            Graphics2D g2d = (Graphics2D) graphics;
                            double pageWidth = pageFormat.getImageableWidth();
                            double pageHeight = pageFormat.getImageableHeight();

                            // Determine the larger height between image1 and image2
                            int largerHeight = Math.max(image1.getHeight(), image2.getHeight());

                            // Scale factor based on the larger height
                            double scale = pageHeight / (2 * largerHeight);
                            double adjustedWidthScale = scale * 1.15;
                            double adjustedHeightScale = scale * 1;

                            // Calculate scaled dimensions for both images
                            int scaledWidth1 = (int) (image1.getWidth() * adjustedWidthScale);
                            int scaledHeight1 = (int) (image1.getHeight() * adjustedHeightScale);
                            int scaledWidth2 = (int) (image2.getWidth() * adjustedWidthScale);
                            int scaledHeight2 = (int) (image2.getHeight() * adjustedHeightScale);

                            // Calculate yOffset for image1 to position it at the top
                            int yOffset1 = (int)((pageHeight - scaledHeight1 - scaledHeight2 - 69) / 2); // Adjusted to account for spacing between images

                            // Draw image1 at the top and image2 at the bottom
                            g2d.drawImage(image1, 2, yOffset1 + 2, scaledWidth1 - 4, scaledHeight1 - 4, null);
                            g2d.drawImage(image2, 2, yOffset1 + scaledHeight1 + 50, scaledWidth2 - 4, scaledHeight2 - 4, null);


                            

                            return Printable.PAGE_EXISTS;
                        } else {
                            return Printable.NO_SUCH_PAGE;
                        }
                    }
                }, postFormat);

                // Show print dialog for final confirmation
                if (printJob.printDialog()) {
                    // Perform the printing
                    printJob.print();
                }
            } catch (PrinterException ex) {
                // Handle exceptions
                ex.printStackTrace();
                System.err.println("Error: " + ex.getMessage());
            }
            }
        
    }//GEN-LAST:event_btn_pay_print1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
       jDialog3.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        	try {
                // Create a Robot instance, which may throw AWTException
                Robot robot = new Robot();

                // Get the screen dimensions
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                //Rectangle printArea = new Rectangle(20, -140, screenSize.width - 350, screenSize.height + 100);
                Rectangle printArea = new Rectangle(-10, -169, screenSize.width - 300, screenSize.height + 120);

                // Capture first screenshot
                image1 = robot.createScreenCapture(printArea);
            }catch (AWTException ex) {
                // Handle exceptions
                ex.printStackTrace();
                System.err.println("Error: " + ex.getMessage());
            }
       
        JFrame f=new JFrame();  
        JOptionPane.showMessageDialog(f,"Payslip 1 captured successfully");  
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        try {
                // Create a Robot instance, which may throw AWTException
                Robot robot = new Robot();

                // Get the screen dimensions
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Rectangle printArea = new Rectangle(-10, 0, screenSize.width - 300, screenSize.height - 50);

                // Capture first screenshot
                image2 = robot.createScreenCapture(printArea);
            }catch (AWTException ex) {
                // Handle exceptions
                ex.printStackTrace();
                System.err.println("Error: " + ex.getMessage());
            }
        
        JFrame f=new JFrame();  
        JOptionPane.showMessageDialog(f,"Payslip 2 captured successfully"); 
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        clearPayrollnewEntry();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void totaldeductiontxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totaldeductiontxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totaldeductiontxtActionPerformed

    private void defSSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defSSSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_defSSSActionPerformed

    private void defHDMFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defHDMFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_defHDMFActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        SQLRun objSQLRun = new SQLRun();
        double updateSSS = Double.parseDouble(defSSS.getText());
        double updatePhilhealth = Double.parseDouble(defPhilhealth.getText());
        double updateHDMF = Double.parseDouble(defHDMF.getText());
        String sql = "UPDATE defultdeduc SET defaultsss = " + updateSSS + ", defaultphilhealth = " + updatePhilhealth + ", defaulthdmf = " + updateHDMF + "WHERE id = 1";
        int updated = objSQLRun.sqlUpdate(sql);
        if (updated > 0) {
            JOptionPane.showMessageDialog(frame, "Updated Successfully.");
        } else {
            JOptionPane.showMessageDialog(frame, "Update Failed.");
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void defPhilhealthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defPhilhealthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_defPhilhealthActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        jDialog4.setVisible(false);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        jDialog4.setVisible(true);
        SQLRun objSQLRun = new SQLRun();
        try {

            String sql = "SELECT * FROM defultdeduc WHERE id= 1";
            String sql2 = "SELECT * FROM defultdeduc WHERE id= 1";
            ResultSet resSet = objSQLRun.sqlQuery(sql);
            ResultSet resSet2 = objSQLRun.sqlQuery(sql2);

            if (resSet.next()) {
                this.defaultSSS = resSet.getDouble("defaultsss");//
                this.defaultPhilhealth = resSet.getDouble("defaultphilhealth");//
                this.defaultHDMF = resSet.getDouble("defaulthdmf");//

            } else if (resSet2.next()) {                
                this.defaultSSS = resSet.getDouble("defaultsss");//
                this.defaultPhilhealth = resSet.getDouble("defaultphilhealth");//
                this.defaultHDMF = resSet.getDouble("defaulthdmf");//

            } else {
                JOptionPane.showMessageDialog(null, "No data found", "ERROR", 0);
                
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);


        }
        defSSS.setText(String.valueOf(defaultSSS));
        defPhilhealth.setText(String.valueOf(defaultPhilhealth));
        defHDMF.setText(String.valueOf(defaultHDMF));
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        SQLRun objSQLRun = new SQLRun();
        try {

            String sql = "SELECT * FROM defultdeduc WHERE id= 1";
            String sql2 = "SELECT * FROM defultdeduc WHERE id= 1";
            ResultSet resSet = objSQLRun.sqlQuery(sql);
            ResultSet resSet2 = objSQLRun.sqlQuery(sql2);

            if (resSet.next()) {
                this.defaultSSS = resSet.getDouble("defaultsss");//
                this.defaultPhilhealth = resSet.getDouble("defaultphilhealth");//
                this.defaultHDMF = resSet.getDouble("defaulthdmf");//

            } else if (resSet2.next()) {                
                this.defaultSSS = resSet.getDouble("defaultsss");//
                this.defaultPhilhealth = resSet.getDouble("defaultphilhealth");//
                this.defaultHDMF = resSet.getDouble("defaulthdmf");//

            } else {
                JOptionPane.showMessageDialog(null, "No data found", "ERROR", 0);
                
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);


        }
        txt_SSSamount.setText(String.valueOf(defaultSSS));
        txt_PHILHEALTHamount.setText(String.valueOf(defaultPhilhealth));
        txt_HDMFamount.setText(String.valueOf(defaultHDMF));
    }//GEN-LAST:event_jButton22ActionPerformed

    private void netpaytxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netpaytxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_netpaytxtActionPerformed

    private void getsalarydetailsviadate_empid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getsalarydetailsviadate_empid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_getsalarydetailsviadate_empid1ActionPerformed

    private void getsalarydetailsviadate_date1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getsalarydetailsviadate_date1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_getsalarydetailsviadate_date1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        SQLRun objSQLRun = new SQLRun();
        try {

            String sql = "SELECT * FROM defultdeduc WHERE id= 1";
            String sql2 = "SELECT * FROM defultdeduc WHERE id= 1";
            ResultSet resSet = objSQLRun.sqlQuery(sql);
            ResultSet resSet2 = objSQLRun.sqlQuery(sql2);

            if (resSet.next()) {
                this.defaultSSS = resSet.getDouble("defaultsss");//
                this.defaultPhilhealth = resSet.getDouble("defaultphilhealth");//
                this.defaultHDMF = resSet.getDouble("defaulthdmf");//

            } else if (resSet2.next()) {                
                this.defaultSSS = resSet.getDouble("defaultsss");//
                this.defaultPhilhealth = resSet.getDouble("defaultphilhealth");//
                this.defaultHDMF = resSet.getDouble("defaulthdmf");//

            } else {
                JOptionPane.showMessageDialog(null, "No data found", "ERROR", 0);
                
            }
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error! Failed to Retrieve Data! Please Contact Your System Administrator!\n\n" + ex.getMessage(), "ERROR", 0);


        }
        String getSalaryDetailsViaDateEmpID = getsalarydetailsviadate_empid1.getText();
        String getSalaryDetailsViaDatePaydate = getsalarydetailsviadate_date1.getText();
        
        if (objPayroll.getSalaryDetailsViaDate(getSalaryDetailsViaDateEmpID,getSalaryDetailsViaDatePaydate)) {
            txt_empId_allowance.setText(String.valueOf(objPayroll.objEmployee.getEmpId()));
            txt_fname_allowance.setText(objPayroll.objEmployee.getFname());
            txt_lname_allowance.setText(objPayroll.objEmployee.getLname());
            txt_desig_allowance.setText(objPayroll.objEmployee.getDesignation());
            txt_depart_allowance.setText(objPayroll.objEmployee.getDepartment());
            //txt_salType_allowance.setText(objPayroll.objEmployee.getSalType());
            txt_totaldays.setText(String.valueOf(objPayroll.gettotalDays()));
            txt_salratedaily.setText(String.valueOf(objPayroll.getsalratedaily()));
            txt_basicsalary.setText(String.valueOf(objPayroll.getbasicSalary()));
            txt_allowance.setText(String.valueOf(objPayroll.getallowanceAmount()));
            txt_nightdiffhours.setText(String.valueOf(objPayroll.getnightDiffHours()));
            txt_nightdiffamount.setText(String.valueOf(objPayroll.getnightDiffAmount()));
            txt_specialholidayhours.setText(String.valueOf(objPayroll.getspecialHolidaydays()));
            txt_specialholidayamount.setText(String.valueOf(objPayroll.getspecialHolidayAmount()));
            txt_SHNDhours.setText(String.valueOf(objPayroll.getSHNDhours()));
            txt_SHNDamount.setText(String.valueOf(objPayroll.getSHNDAmount()));
            txt_LegalHolidayhours.setText(String.valueOf(objPayroll.getLegalHolidayDays()));
            txt_LegalHolidayamount.setText(String.valueOf(objPayroll.getLegalHolidayAmount()));
            txt_LHNDhours.setText(String.valueOf(objPayroll.getLHNDhours()));
            txt_LHNDamount.setText(String.valueOf(objPayroll.getLHNDamount()));
            txt_overtimehours.setText(String.valueOf(objPayroll.getOThours()));
            txt_overtimeamount.setText(String.valueOf(objPayroll.getOTamount()));
            txt_OTNDhours.setText(String.valueOf(objPayroll.getOTNDhours()));
            txt_OTNDamount.setText(String.valueOf(objPayroll.getOTNDamount()));
            txt_leaveothersamount.setText(String.valueOf(objPayroll.getleaveothersamount()));
            txt_adjustmentamount.setText(String.valueOf(objPayroll.getadjustmentamount()));
            txt_lateundertimemins.setText(String.valueOf(objPayroll.getlateundertimemins()));
            //txt_lateundertimerate.setText(String.valueOf(objPayroll.getlateundertimerate()));
            txt_lateundertimeamount.setText(String.valueOf(objPayroll.getlateundertimeamount()));
            
            txt_SSSamount.setText(String.valueOf(objPayroll.getSSSamount()));
            txt_PHILHEALTHamount.setText(String.valueOf(objPayroll.getphilhealthamount()));
            txt_HDMFamount.setText(String.valueOf(objPayroll.gethdmfamount()));
            txt_otherdeductionamount.setText(String.valueOf(objPayroll.getotherdeductionamount()));  
            txtDate1.setText(String.valueOf(objPayroll.getDate()));
            fromcutofftxt.setText(String.valueOf(objPayroll.getfromcutoffdate()));
            tocutofftxt.setText(String.valueOf(objPayroll.gettocutoffdate()));
             
            totaldeductiontxt.setText(String.valueOf(objPayroll.getTotalDeductions()));
        }
     jDialog5.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        jDialog6.show();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        //payslipNote = jTextArea1.getText();
        //payslipNote_lbl.setText(payslipNote);
               
        jDialog6.dispose();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void print_empid2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_empid2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_empid2ActionPerformed

    private void print_date2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_date2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_date2ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        
    }//GEN-LAST:event_jButton28ActionPerformed

    private void print_empid3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_empid3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_empid3ActionPerformed

    private void print_date3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_date3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_date3ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }
    class PrintableImage implements Printable {
        private BufferedImage image;

        PrintableImage(BufferedImage image) {
            this.image = image;
        }

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex > 0) {
                return NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            double scaleX = pageFormat.getImageableWidth() / image.getWidth();
            double scaleY = pageFormat.getImageableHeight() / image.getHeight();
            double scaleFactor = Math.min(scaleX, scaleY);
            int scaledWidth = (int) (image.getWidth() * scaleFactor);
            int scaledHeight = (int) (image.getHeight() * scaleFactor);

            // Center the image on the page
            int x = (int) ((pageFormat.getImageableWidth() - scaledWidth) / 2);
            int y = (int) ((pageFormat.getImageableHeight() - scaledHeight) / 2);

            g2d.drawImage(image, x, y, scaledWidth, scaledHeight, null);
            return PAGE_EXISTS;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup_rd;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_add_payroll;
    private javax.swing.JButton btn_apply_leave;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_exit_leave;
    private javax.swing.JButton btn_exit_payroll;
    private javax.swing.JButton btn_exit_payroll1;
    private javax.swing.JButton btn_exit_update;
    private javax.swing.JButton btn_pay_exit;
    private javax.swing.JButton btn_pay_exit1;
    private javax.swing.JButton btn_pay_print;
    private javax.swing.JButton btn_pay_print1;
    private javax.swing.JButton btn_pay_search;
    private javax.swing.JButton btn_pay_search1;
    private javax.swing.JButton btn_reset_leave;
    private javax.swing.JButton btn_searchEmp;
    private javax.swing.JButton btn_search_leave;
    private javax.swing.JButton btn_search_payroll;
    private javax.swing.JButton btn_search_update;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton btn_update_leave;
    private javax.swing.JButton btn_update_payroll;
    private javax.swing.JLabel dailyratelbl;
    private javax.swing.JLabel dailyratelbl1;
    private javax.swing.JTextField defHDMF;
    private javax.swing.JTextField defPhilhealth;
    private javax.swing.JTextField defSSS;
    private javax.swing.JTextField deletesalarydeets_date;
    private javax.swing.JTextField deletesalarydeets_empid;
    private javax.swing.JLabel fromcutoffdatelabel;
    private javax.swing.JLabel fromcutoffdatelabel1;
    private javax.swing.JTextField fromcutofftxt;
    private javax.swing.JTextField getsalarydetailsviadate_date1;
    private javax.swing.JTextField getsalarydetailsviadate_empid1;
    private javax.swing.JInternalFrame intFrame_cutoff;
    private javax.swing.JInternalFrame intFrame_employee_new;
    private javax.swing.JInternalFrame intFrame_employee_search;
    private javax.swing.JInternalFrame intFrame_employee_update;
    private javax.swing.JInternalFrame intFrame_leave;
    private javax.swing.JInternalFrame intFrame_payroll;
    private javax.swing.JInternalFrame intFrame_print;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JDialog jDialog6;
    private javax.swing.JDialog jDialog7;
    private javax.swing.JDialog jDialog8;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane_tableContainer;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_HDMF;
    private javax.swing.JLabel lbl_HDMF1;
    private javax.swing.JLabel lbl_LHND;
    private javax.swing.JLabel lbl_LHND1;
    private javax.swing.JLabel lbl_LegalHoliday;
    private javax.swing.JLabel lbl_LegalHoliday1;
    private javax.swing.JLabel lbl_OT;
    private javax.swing.JLabel lbl_OT1;
    private javax.swing.JLabel lbl_OTND;
    private javax.swing.JLabel lbl_OTND1;
    private javax.swing.JLabel lbl_SHND;
    private javax.swing.JLabel lbl_SHND1;
    private javax.swing.JLabel lbl_SSS;
    private javax.swing.JLabel lbl_SSS1;
    private javax.swing.JLabel lbl_adjustment;
    private javax.swing.JLabel lbl_adjustment1;
    private javax.swing.JLabel lbl_allowance;
    private javax.swing.JLabel lbl_allowance1;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_depart_allowance;
    private javax.swing.JLabel lbl_depart_allowance1;
    private javax.swing.JLabel lbl_department;
    private javax.swing.JLabel lbl_department1;
    private javax.swing.JLabel lbl_desig_allowance;
    private javax.swing.JLabel lbl_desig_allowance1;
    private javax.swing.JLabel lbl_designation;
    private javax.swing.JLabel lbl_designation1;
    private javax.swing.JLabel lbl_empId;
    private javax.swing.JLabel lbl_empId1;
    private javax.swing.JLabel lbl_empId_allowance;
    private javax.swing.JLabel lbl_empId_allowance1;
    private javax.swing.JLabel lbl_epf;
    private javax.swing.JLabel lbl_fname;
    private javax.swing.JLabel lbl_fname1;
    private javax.swing.JLabel lbl_fname_allowance;
    private javax.swing.JLabel lbl_fname_allowance1;
    private javax.swing.JLabel lbl_food;
    private javax.swing.JLabel lbl_food1;
    private javax.swing.JLabel lbl_lateundertime;
    private javax.swing.JLabel lbl_lateundertime1;
    private javax.swing.JLabel lbl_leaveothers;
    private javax.swing.JLabel lbl_leaveothers1;
    private javax.swing.JLabel lbl_lname;
    private javax.swing.JLabel lbl_lname1;
    private javax.swing.JLabel lbl_lname_allowance;
    private javax.swing.JLabel lbl_lname_allowance1;
    private javax.swing.JLabel lbl_nightdiff;
    private javax.swing.JLabel lbl_nightdiff1;
    private javax.swing.JLabel lbl_otherdeduction;
    private javax.swing.JLabel lbl_otherdeduction1;
    private javax.swing.JLabel lbl_pay_basic;
    private javax.swing.JLabel lbl_pay_basic1;
    private javax.swing.JLabel lbl_pay_deduct;
    private javax.swing.JLabel lbl_pay_deduct1;
    private javax.swing.JLabel lbl_pay_depart;
    private javax.swing.JLabel lbl_pay_depart1;
    private javax.swing.JLabel lbl_pay_desig;
    private javax.swing.JLabel lbl_pay_desig1;
    private javax.swing.JLabel lbl_pay_empId;
    private javax.swing.JLabel lbl_pay_empId1;
    private javax.swing.JLabel lbl_pay_gross;
    private javax.swing.JLabel lbl_pay_gross1;
    private javax.swing.JLabel lbl_pay_name;
    private javax.swing.JLabel lbl_pay_name1;
    private javax.swing.JLabel lbl_pay_net;
    private javax.swing.JLabel lbl_pay_net1;
    private javax.swing.JLabel lbl_paye;
    private javax.swing.JLabel lbl_paye1;
    private javax.swing.JLabel lbl_paye2;
    private javax.swing.JLabel lbl_performance;
    private javax.swing.JLabel lbl_performance2;
    private javax.swing.JLabel lbl_performance3;
    private javax.swing.JLabel lbl_performance4;
    private javax.swing.JLabel lbl_performance5;
    private javax.swing.JLabel lbl_performance6;
    private javax.swing.JLabel lbl_performance7;
    private javax.swing.JLabel lbl_performance8;
    private javax.swing.JLabel lbl_philhealth;
    private javax.swing.JLabel lbl_philhealth1;
    private javax.swing.JLabel lbl_pms;
    private javax.swing.JLabel lbl_sal_allowance;
    private javax.swing.JLabel lbl_sal_allowance1;
    private javax.swing.JLabel lbl_sal_allowance2;
    private javax.swing.JLabel lbl_specialholiday;
    private javax.swing.JLabel lbl_specialholiday1;
    private javax.swing.JLabel lbl_tax;
    private javax.swing.JLabel lbl_travel;
    private javax.swing.JLabel lbl_travel1;
    private javax.swing.JMenu menuBar_employee;
    private javax.swing.JMenuItem menuBar_employee_delete;
    private javax.swing.JMenuItem menuBar_employee_new;
    private javax.swing.JMenuItem menuBar_employee_search;
    private javax.swing.JMenuItem menuBar_employee_update;
    private javax.swing.JMenu menuBar_file;
    private javax.swing.JMenuItem menuBar_file_exit;
    private javax.swing.JMenuItem menuBar_file_logout;
    private javax.swing.JMenu menuBar_leave;
    private javax.swing.JMenuItem menuBar_leave_apply;
    private javax.swing.JMenu menuBar_paySlip;
    private javax.swing.JMenu menuBar_payroll;
    private javax.swing.JMenuItem menuBar_payroll_add;
    private javax.swing.JMenuItem menuBar_payslip_print;
    private javax.swing.JMenuBar menu_menuBar;
    private javax.swing.JTextField netpaytxt;
    private javax.swing.JLabel numdayslbl;
    private javax.swing.JLabel numdayslbl1;
    private javax.swing.JPanel panel_details;
    private javax.swing.JPanel panel_details1;
    private javax.swing.JPanel panel_empDetails;
    private javax.swing.JPanel panel_empDetails_payroll;
    private javax.swing.JPanel panel_empDetails_payroll1;
    private javax.swing.JPanel panel_empUpdate;
    private javax.swing.JPanel panel_salAllow_payroll;
    private javax.swing.JPanel panel_salAllow_payroll1;
    private javax.swing.JPanel panel_salDeduct_payroll;
    private javax.swing.JLabel paydatelabel;
    private javax.swing.JLabel paydatelabel1;
    private javax.swing.JLabel payslipNote_lbl;
    private javax.swing.JTextField print_date1;
    private javax.swing.JTextField print_date2;
    private javax.swing.JTextField print_date3;
    private javax.swing.JTextField print_datevianame;
    private javax.swing.JTextField print_empfname;
    private javax.swing.JTextField print_empid1;
    private javax.swing.JTextField print_empid2;
    private javax.swing.JTextField print_empid3;
    private javax.swing.JTextField print_emplname;
    private javax.swing.JRadioButton rd_female;
    private javax.swing.JRadioButton rd_male;
    private javax.swing.JLabel tocutoffdatelabel;
    private javax.swing.JLabel tocutoffdatelabel1;
    private javax.swing.JTextField tocutofftxt;
    private javax.swing.JTextField totaldeductiontxt;
    private javax.swing.JTextField totalearningstxt;
    private javax.swing.JTextField txtDate1;
    private javax.swing.JTextField txtDate2;
    private javax.swing.JTextField txt_HDMFamount;
    private javax.swing.JTextField txt_LHNDamount;
    private javax.swing.JTextField txt_LHNDhours;
    private javax.swing.JTextField txt_LegalHolidayamount;
    private javax.swing.JTextField txt_LegalHolidayhours;
    private javax.swing.JTextField txt_OTNDamount;
    private javax.swing.JTextField txt_OTNDhours;
    private javax.swing.JTextField txt_PHILHEALTHamount;
    private javax.swing.JTextField txt_SHNDamount;
    private javax.swing.JTextField txt_SHNDhours;
    private javax.swing.JTextField txt_SSSamount;
    private javax.swing.JTextField txt_adjustmentamount;
    private javax.swing.JTextField txt_allowance;
    private javax.swing.JTextField txt_basicsalary;
    private javax.swing.JTextField txt_deparment;
    private javax.swing.JTextField txt_deparment_update;
    private javax.swing.JTextField txt_depart_allowance;
    private javax.swing.JTextField txt_depart_leave;
    private javax.swing.JTextField txt_desig_allowance;
    private javax.swing.JTextField txt_desig_leave;
    private javax.swing.JTextField txt_designation;
    private javax.swing.JTextField txt_designation_update;
    private javax.swing.JTextField txt_empID;
    private javax.swing.JTextField txt_empID_update;
    private javax.swing.JTextField txt_empId_allowance;
    private javax.swing.JTextField txt_empId_leave;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JTextField txt_fname_allowance;
    private javax.swing.JTextField txt_fname_leave;
    private javax.swing.JTextField txt_fname_update;
    private javax.swing.JTextField txt_lateundertimeamount;
    private javax.swing.JTextField txt_lateundertimemins;
    private javax.swing.JTextField txt_leaveothersamount;
    private javax.swing.JTextField txt_lname;
    private javax.swing.JTextField txt_lname_allowance;
    private javax.swing.JTextField txt_lname_leave;
    private javax.swing.JTextField txt_lname_update;
    private javax.swing.JTextField txt_nightdiffamount;
    private javax.swing.JTextField txt_nightdiffhours;
    private javax.swing.JTextField txt_otherdeductionamount;
    private javax.swing.JTextField txt_overtimeamount;
    private javax.swing.JTextField txt_overtimehours;
    private javax.swing.JTextField txt_pydt;
    private javax.swing.JTextField txt_salratedaily;
    private javax.swing.JTextField txt_sl_apply;
    private javax.swing.JTextField txt_sl_count;
    private javax.swing.JTextField txt_specialholidayamount;
    private javax.swing.JTextField txt_specialholidayhours;
    private javax.swing.JTextField txt_totaldays;
    private javax.swing.JTextField txt_vl_apply;
    private javax.swing.JTextField txt_vl_count;
    // End of variables declaration//GEN-END:variables
}
