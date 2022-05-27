
public class IncomeTax {
	static final int SINGLE_FILER = 0;
	static final int MARRIED_JOINTLY_OR_QUALIFYING_WIDOW = 1;
	static final int MARRIED_SEPARATELY = 2;
	static final int HEAD_OF_HOUSEHOLD = 3;
	
	int filingStatus; // 0-single filer 1-married filing jointly or qualifying widow(er) 2- married filing separately 3-head of household
	double intervals[][]; //income brackets
	double [] rates; //for different brackets
	double taxableIncome;
	double incomeTax;
	
	IncomeTax(){
		double []rates = {};
		double[][]inter = {};
		setStatus(-1);
		setIncome(0);
		setRates(rates);
		setIntervals(inter);
	}
	
	IncomeTax(int fStatus, double[][] intervals, double []rates, double taxableIncome){
		setStatus(fStatus);
		setIncome(taxableIncome);
		setRates(rates);
		setIntervals(intervals);
	}
	public void setStatus(int input) {
		this.filingStatus = input;
	}
	
	public void setIncome(double income) {
		this.taxableIncome = income;
	}
	
	public void setRates(double[] rates) {
		this.rates = rates;
	}
	
	public void setIntervals(double [][] intervals) {
		this.intervals = intervals;
	}
	
	public double getRate(double txIncome, int status) {
		return 0;
	}
	
	public double getTaxableIncome() {
		return taxableIncome;
	}
	public double getIncomeTax() {  // for generating one single income tax
		if(filingStatus < 0 || filingStatus > 3) return 0;
		double incomeTax = 0;
		for(int i = 0; i < intervals[filingStatus].length; i++) {
			if(i == 0) {
				if(taxableIncome > intervals[filingStatus][i]) {
					incomeTax += intervals[filingStatus][i]* rates[i];
				}else {
					incomeTax += taxableIncome * rates[i];
					break;
				}
			}else if(i == intervals[filingStatus].length -1){
				incomeTax += (taxableIncome - intervals[filingStatus][i]) * rates[i];
				
			}
			else{
				if(taxableIncome > intervals[filingStatus][i]) {
					incomeTax += (intervals[filingStatus][i]- intervals[filingStatus][i-1]) * rates[i];	
				}else {		
					incomeTax += (taxableIncome - intervals[filingStatus][i-1])* rates[i];	
					break;
				}
			}		
		}
		return incomeTax;
	}
	
	public double getIncomeTax(double Income, int Status) {//for printing out the table of all different tax brackets 
		if(Status < 0 || Status > 3) return 0;
		double incomeTax = 0;
		for(int i = 0; i < intervals[Status].length; i++) {
			if(i == 0) {
				if(Income > intervals[Status][i]) {
					incomeTax += intervals[Status][i]* rates[i];
				}else {
					incomeTax += Income * rates[i];
					break;
				}
			}else if(i == intervals[Status].length -1){
				incomeTax += (Income - intervals[Status][i]) * rates[i];
				
			}
			else{
				if(Income > intervals[Status][i]) {
					incomeTax += (intervals[Status][i]- intervals[Status][i-1]) * rates[i];	
				}else {		
					incomeTax += (Income - intervals[Status][i-1])* rates[i];	
					break;
				}
			}		
		}
		return incomeTax;
	}
	
	public void printTaxTable(int from, int to){ //require income range from user to print the taxes for different brackets
		int row = (to - from)/ 1000 + 1;
		int column = intervals.length;
		for(int i = 0; i < row; i++) {  //use nested for loops to build a presentable table using getIncomeTax method to calculate total tax for  each bracket
			System.out.printf("%-9d", (from + i * 1000));
			for(int j = 0; j < column; j++) {
				double tax = getIncomeTax(from + i*1000, j);
				System.out.printf("%-10.2f  ", tax);			
			}
			System.out.println("");
		}
		System.out.println("");
	}	
}
