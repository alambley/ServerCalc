package servercalc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import servercalc.EarningDay;

import java.io.*;


public class EarningReport {
	public class Stat{
		public String info;
		public int days;
		public double totalmoney;
		public double totalhour;
		public Stat(){
			info = "";
			days = 0;
			totalhour = 0;
			totalmoney = 0;
		}
	}
	private ArrayList<EarningDay> report = new ArrayList<EarningDay>();	
	public EarningReport(){
		String input;
		try{
			BufferedReader in = new BufferedReader(new FileReader("reportholder.txt")); 
			while(in.ready()){		
				input = in.readLine();
				StringTokenizer tokenizer = new StringTokenizer(input," "); 
				String initmoney = tokenizer.nextToken();
				String inithours = tokenizer.nextToken();
				String initmonth = tokenizer.nextToken();
				String initday = tokenizer.nextToken();
				String inityear = tokenizer.nextToken();
				EarningDay temp = new EarningDay(Integer.parseInt(initmonth), Integer.parseInt(initday), Integer.parseInt(inityear), Double.parseDouble(initmoney), Double.parseDouble(inithours));
				report.add(temp);
			}
			in.close();
		}catch(FileNotFoundException e){
			System.out.println("File couldn't be found");
		}catch(IOException e){
			System.out.println("IOException");
		}
	}
	
	public void add(EarningDay temp){
		boolean found = false;
		for(int findrep = 0; findrep < report.size(); findrep++){
			int tempmonth = report.get(findrep).getMonth();
			int tempday = report.get(findrep).getDay();
			int tempyear = report.get(findrep).getYear();
			if(tempmonth == temp.getMonth() && tempday == temp.getDay() && tempyear == temp.getYear()){
				found = true;
			}
		}
		if(!found){
			report.add(temp);
			for(int c1 = 1; c1 < report.size(); c1++){
				for(int c2 = c1; c2 > 0; c2--){
					if(report.get(c2).getYear() < report.get(c2-1).getYear() || report.get(c2).getSecdate() < report.get(c2-1).getSecdate() && report.get(c2).getYear() == report.get(c2-1).getYear()){
						Collections.swap(report, c2, c2-1);
					}
				}
			}
		}
	}
	
	public Stat all(){
		Stat temp = new Stat();
		for(int counter = 0; counter < report.size(); counter++){			
			String hold = report.get(counter).getDayofweek();
			temp.info += report.get(counter).getDayofweek();
			switch(hold){
				case("Sunday"):
					temp.info += "        ";
					break;
				case("Saturday"):
					temp.info += "      ";
					break;
				case("Monday"):
					temp.info += "      ";
				    break;
				case("Tuesday"):
					temp.info += "      ";
					break;
				case("Thursday"):
					temp.info += "    ";
					break;
				case("Friday"):
					temp.info += "          ";
					break;
			}
			temp.info += " ";
			temp.info += String.format("%-2d/%-2d/%-4d", report.get(counter).getMonth(), report.get(counter).getDay(), report.get(counter).getYear());
			temp.info += "   ";
			temp.info += String.format("$%-3.0f   %-2.2f", report.get(counter).getMoney(), report.get(counter).getHours());
			temp.info += "\n";
			temp.days++;
			temp.totalhour += report.get(counter).getHours();
			temp.totalmoney += report.get(counter).getMoney();			
		}
		return temp;
	}
	
	public Stat year(int tempyear){
		Stat temp = new Stat();
		for(int counter = 0; counter < report.size(); counter++){
			if(report.get(counter).getYear() == tempyear){
				String hold = report.get(counter).getDayofweek();
				temp.info += report.get(counter).getDayofweek();
				switch(hold){
					case("Sunday"):
						temp.info += "        ";
						break;
					case("Saturday"):
						temp.info += "      ";
						break;
					case("Monday"):
						temp.info += "      ";
					    break;
					case("Tuesday"):
						temp.info += "      ";
						break;
					case("Thursday"):
						temp.info += "    ";
						break;
					case("Friday"):
						temp.info += "          ";
						break;
				}
				temp.info += " ";
				temp.info += String.format("%-2d/%-2d/%-4d", report.get(counter).getMonth(), report.get(counter).getDay(), report.get(counter).getYear());
				temp.info += "   ";
				temp.info += String.format("$%-3.0f   %-2.2f", report.get(counter).getMoney(), report.get(counter).getHours());
				temp.info += "\n";
				temp.days++;
				temp.totalhour += report.get(counter).getHours();
				temp.totalmoney += report.get(counter).getMoney();	
			}
		}
		return temp;
	}
	
	public Stat month (int tempmonth, int tempyear){
		Stat temp = new Stat();
		for(int counter = 0; counter < report.size(); counter++){
			if(report.get(counter).getYear() == tempyear && report.get(counter).getMonth() == tempmonth){
				String hold = report.get(counter).getDayofweek();
				temp.info += report.get(counter).getDayofweek();
				switch(hold){
					case("Sunday"):
						temp.info += "        ";
						break;
					case("Saturday"):
						temp.info += "      ";
						break;
					case("Monday"):
						temp.info += "      ";
					    break;
					case("Tuesday"):
						temp.info += "      ";
						break;
					case("Thursday"):
						temp.info += "    ";
						break;
					case("Friday"):
						temp.info += "          ";
						break;
				}
				temp.info += " ";
				temp.info += String.format("%-2d/%-2d/%-4d", report.get(counter).getMonth(), report.get(counter).getDay(), report.get(counter).getYear());
				temp.info += "   ";
				temp.info += String.format("$%-3.0f   %-2.2f", report.get(counter).getMoney(), report.get(counter).getHours());
				temp.info += "\n";
				temp.days++;
				temp.totalhour += report.get(counter).getHours();
				temp.totalmoney += report.get(counter).getMoney();	
			}
		}
		return temp;
	}
	
	public Stat week(int tempday, int tempmonth, int tempyear){
		Stat temp = new Stat();
		EarningDay dummy = new EarningDay();
		int secretdate = dummy.calcsecdate(tempmonth, tempday, tempyear);
		int dayofweek = dummy.integdayofweek(tempmonth, tempday, tempyear);
		secretdate -= dayofweek;
		for(int counter = 0; counter < report.size(); counter++){
			if(report.get(counter).getSecdate() >= secretdate && report.get(counter).getSecdate() <= secretdate + 6 && report.get(counter).getYear() == tempyear){
				String hold = report.get(counter).getDayofweek();
				temp.info += report.get(counter).getDayofweek();
				switch(hold){
					case("Sunday"):
						temp.info += "        ";
						break;
					case("Saturday"):
						temp.info += "      ";
						break;
					case("Monday"):
						temp.info += "      ";
					    break;
					case("Tuesday"):
						temp.info += "      ";
						break;
					case("Thursday"):
						temp.info += "    ";
						break;
					case("Friday"):
						temp.info += "          ";
						break;
				}
				temp.info += " ";
				temp.info += String.format("%-2d/%-2d/%-4d", report.get(counter).getMonth(), report.get(counter).getDay(), report.get(counter).getYear());
				temp.info += "   ";
				temp.info += String.format("$%-3.0f   %-2.2f", report.get(counter).getMoney(), report.get(counter).getHours());
				temp.info += "\n";
				temp.days++;
				temp.totalhour += report.get(counter).getHours();
				temp.totalmoney += report.get(counter).getMoney();
			}
		}
		return temp;
	}
	
	public void save(){
		try{
		PrintWriter out = new PrintWriter(new FileWriter("reportholder.txt")); 
		for(int counter = 0; counter < report.size(); counter++){
			out.print(report.get(counter).getMoney() + " " +
					  report.get(counter).getHours() + " " +
					  report.get(counter).getMonth() + " " +
					  report.get(counter).getDay() + " " +
					  report.get(counter).getYear() + "\n");
		}
		out.close();
		}catch(IOException e){
			System.out.println("IOException");
		}
	}
}
