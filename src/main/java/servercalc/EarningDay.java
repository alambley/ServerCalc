package servercalc;

public class EarningDay {
	private int month;
	private int day;
	private int year;
	private int secdate;
	private double money;
	private double hours;
	private String dayofweek;
	
	public EarningDay(){
		
	}
	
	public EarningDay(int initmonth, int initday, int inityear, double initmoney, double inithours){
		month = initmonth;
		day = initday;
		year = inityear;
		money = initmoney;
		hours = inithours;
		secdate = calcsecdate(initmonth, initday, inityear);
		dayofweek = DayOfWeek();
	}
	
	public int calcsecdate(int inputmonth, int inputday, int inputyear){
		int temp = inputday;
		switch(inputmonth){
		case 1:
            break;
        case 2:
        	temp += 31;
            break;
        case 3:
        	temp += 59;
            break;
        case 4:
        	temp += 90;
            break;
        case 5:
        	temp += 120;
            break;
        case 6:
        	temp += 151;
            break;
        case 7:
        	temp += 181;
            break;
        case 8:
        	temp += 212;
            break;
        case 9:
        	temp += 243;
            break;
        case 10:
        	temp += 273;
            break;
        case 11:
        	temp += 304;
            break;
        case 12:
        	temp += 334;
            break;
        default:			
		}
		if((inputyear % 4 == 0)&&(inputmonth > 2)){
			temp++;
		}
		return temp;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getYear() {
		return year;
	}

	public int getSecdate() {
		return secdate;
	}

	public double getMoney() {
		return money;
	}

	public double getHours() {
		return hours;
	}
	
	public String getDayofweek() {
		return dayofweek;
	}

	public String DayOfWeek()
	{
		int dayofweek;	   
	    String dayString;  
	    int tempyear = year, tempmonth = month, tempday = day;
	    int t[] = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        if(tempmonth < 3){
        	tempyear -= 1;
        }
        dayofweek = (tempyear + tempyear/4 - tempyear/100 + tempyear/400 + t[tempmonth-1] + tempday) % 7;    
	    switch(dayofweek)
	    {
	        case 0: dayString = "Sunday";
	        break;
	        case 1: dayString = "Monday";
	        break;
	        case 2: dayString = "Tuesday";
	        break;
	        case 3: dayString = "Wednesday";
	        break;
	        case 4: dayString = "Thursday";
	        break;
	        case 5: dayString = "Friday";
	        break;
	        case 6: dayString = "Saturday";
	        break;
	        default: dayString = "Something went wrong";   
	    }

	    return dayString;
	}
	
	public int integdayofweek(int tempmonth, int tempday, int tempyear){
		int dayofweek;	   
	    int t[] = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        if(tempmonth < 3){
        	tempyear -= 1;
        }
        dayofweek = (tempyear + tempyear/4 - tempyear/100 + tempyear/400 + t[tempmonth-1] + tempday) % 7;
	    return dayofweek;
	}
}
