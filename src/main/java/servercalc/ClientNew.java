package servercalc;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import servercalc.EarningReport;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.ibm.icu.impl.CalendarAstronomer.Horizon;

public class ClientNew {

	protected Shell shlServercalc;
	private Text useramountmade;
	private Text userhoursworked;
	private Text showuser;
	private Text daysworked;
	private Text moneymade;
	private Text hoursworked;
	private Text averagehourly;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ClientNew window = new ClientNew();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlServercalc.open();
		shlServercalc.layout();
		while (!shlServercalc.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	EarningReport report = new EarningReport();

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlServercalc = new Shell();
		shlServercalc.setSize(522, 402);
		shlServercalc.setText("ServerCalc");
		
		Label lblAmountMade = new Label(shlServercalc, SWT.NONE);
		lblAmountMade.setBounds(10, 13, 86, 15);
		lblAmountMade.setText("Amount Made");
		
		useramountmade = new Text(shlServercalc, SWT.BORDER);
		useramountmade.setBounds(96, 10, 76, 21);
		
		Label lblHoursWorked = new Label(shlServercalc, SWT.NONE);
		lblHoursWorked.setBounds(10, 31, 76, 15);
		lblHoursWorked.setText("Hours Worked");
		
		userhoursworked = new Text(shlServercalc, SWT.BORDER);
		userhoursworked.setBounds(96, 31, 76, 21);
		
		final DateTime usercalendar = new DateTime(shlServercalc, SWT.BORDER | SWT.CALENDAR);
		usercalendar.setBounds(0, 52, 233, 151);
		
		Button btnAddRecord = new Button(shlServercalc, SWT.NONE);
		btnAddRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(useramountmade.getText() == ""){				
				}
				else if(userhoursworked.getText() == ""){
				}
				else{
					try{
						double initmoney = Double.parseDouble(useramountmade.getText());
						double inithours = Double.parseDouble(userhoursworked.getText());
						EarningDay temp = new EarningDay(usercalendar.getMonth() + 1, usercalendar.getDay(), usercalendar.getYear(), initmoney, inithours);
						report.add(temp);
						report.save();
						hoursworked.setText(Double.toString(inithours));
						moneymade.setText(String.format("$%.0f", initmoney));
						averagehourly.setText(String.format("$%.2f", initmoney/inithours));
					}catch(NumberFormatException r){							
					}finally{
						useramountmade.setText("");
						userhoursworked.setText("");
					}
				}
			}
		});
		btnAddRecord.setBounds(177, 18, 75, 25);
		btnAddRecord.setText("Add Record");
		
		showuser = new Text(shlServercalc, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		showuser.setBounds(265, 10, 238, 348);
		
		Label lblNewLabel = new Label(shlServercalc, SWT.NONE);
		lblNewLabel.setBounds(19, 243, 69, 15);
		lblNewLabel.setText("Days Worked");
		
		Label lblMoneyMade = new Label(shlServercalc, SWT.NONE);
		lblMoneyMade.setBounds(12, 270, 76, 15);
		lblMoneyMade.setText("Money Made");
		
		Label lblHoursWorked_1 = new Label(shlServercalc, SWT.NONE);
		lblHoursWorked_1.setBounds(12, 297, 76, 15);
		lblHoursWorked_1.setText("Hours Worked");
		
		Label lblAverageHourly = new Label(shlServercalc, SWT.NONE);
		lblAverageHourly.setBounds(10, 324, 86, 15);
		lblAverageHourly.setText("Average Hourly");
		
		daysworked = new Text(shlServercalc, SWT.BORDER | SWT.READ_ONLY);
		daysworked.setBounds(102, 240, 76, 21);
		
		moneymade = new Text(shlServercalc, SWT.BORDER | SWT.READ_ONLY);
		moneymade.setBounds(102, 267, 76, 21);
		
		hoursworked = new Text(shlServercalc, SWT.BORDER | SWT.READ_ONLY);
		hoursworked.setBounds(102, 294, 76, 21);
		
		averagehourly = new Text(shlServercalc, SWT.BORDER | SWT.READ_ONLY);
		averagehourly.setBounds(102, 321, 76, 21);
		
		Button btnAll = new Button(shlServercalc, SWT.NONE);
		btnAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				showuser.setText("");
				double temphours = report.all().totalhour;
				double tempmoney = report.all().totalmoney;
				showuser.setText(report.all().info);
				daysworked.setText(Integer.toString(report.all().days));
				hoursworked.setText(Double.toString(temphours));
				moneymade.setText(String.format("$%.0f", tempmoney));
				averagehourly.setText(String.format("$%2.2f", tempmoney/temphours));				
			}
		});
		btnAll.setBounds(184, 333, 75, 25);
		btnAll.setText("All");
		
		Button btnYear = new Button(shlServercalc, SWT.NONE);
		btnYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				showuser.setText("");
				double temphours = report.year(usercalendar.getYear()).totalhour;
				double tempmoney = report.year(usercalendar.getYear()).totalmoney;
				showuser.setText(report.year(usercalendar.getYear()).info);
				daysworked.setText(Integer.toString(report.year(usercalendar.getYear()).days));
				hoursworked.setText(Double.toString(temphours));
				moneymade.setText(String.format("$%.0f", tempmoney));
				averagehourly.setText(String.format("$%2.2f", tempmoney/temphours));		
			}
		});
		btnYear.setBounds(184, 302, 75, 25);
		btnYear.setText("Year");
		
		Button btnMonth = new Button(shlServercalc, SWT.NONE);
		btnMonth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				showuser.setText("");
				double temphours = report.month(usercalendar.getMonth() + 1, usercalendar.getYear()).totalhour;
				double tempmoney = report.month(usercalendar.getMonth() + 1, usercalendar.getYear()).totalmoney;
				showuser.setText(report.month(usercalendar.getMonth() + 1, usercalendar.getYear()).info);
				daysworked.setText(Integer.toString(report.month(usercalendar.getMonth() + 1, usercalendar.getYear()).days));
				hoursworked.setText(Double.toString(temphours));
				moneymade.setText(String.format("$%.0f", tempmoney));
				averagehourly.setText(String.format("$%2.2f", tempmoney/temphours));	
			}
		});
		btnMonth.setBounds(184, 271, 75, 25);
		btnMonth.setText("Month");
		
		Button btnWeek = new Button(shlServercalc, SWT.NONE);
		btnWeek.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				showuser.setText("");
				double temphours = report.week(usercalendar.getDay(), usercalendar.getMonth() + 1, usercalendar.getYear()).totalhour;
				double tempmoney = report.week(usercalendar.getDay(), usercalendar.getMonth() + 1, usercalendar.getYear()).totalmoney;
				showuser.setText(report.week(usercalendar.getDay(), usercalendar.getMonth() + 1, usercalendar.getYear()).info);
				daysworked.setText(Integer.toString(report.week(usercalendar.getDay(), usercalendar.getMonth() + 1, usercalendar.getYear()).days));
				hoursworked.setText(Double.toString(temphours));
				moneymade.setText(String.format("$%.0f", tempmoney));
				averagehourly.setText(String.format("$%2.2f", tempmoney/temphours));	
			}
		});
		btnWeek.setBounds(184, 240, 75, 25);
		btnWeek.setText("Week");
		
		Button btnClear = new Button(shlServercalc, SWT.NONE);
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				showuser.setText("");
				daysworked.setText("");
				hoursworked.setText("");
				moneymade.setText("");
				averagehourly.setText("");
			}
		});
		btnClear.setBounds(184, 209, 75, 25);
		btnClear.setText("Clear");

	}

}
