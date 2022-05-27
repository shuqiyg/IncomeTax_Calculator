import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {	
		double brackets2009[][] = {{8350, 33950, 82250, 171550, 372950, 372951},
				{16700, 67900, 137050, 208850, 372950, 372951},
				{8350, 33950, 68525, 104425, 186475, 186475},
				{11950, 45500, 117450, 190200, 372950, 372951}};

		double brackets2020[][] = {{9875, 40125, 85525, 163300, 207350, 518400, 518401},
					 {19750, 80250, 171050, 326600, 414700, 622050, 622051},
					 {9875, 40125, 85525, 163300, 207350, 311025, 311026},
					 {14100, 53700, 85500, 163300, 207350, 518400, 518401}};

		double [] rates2009 = {.10, .15, .25, .28, .33, .35};
		double [] rates2020 = {.10, .12, .22, .24, .32, .35, .37};
		IncomeTax incTax2009 = null;
		IncomeTax incTax2020 = null;
		Scanner input = new Scanner(System.in);
		boolean exit = false;
		
		do {
			System.out.println("Please choose one of the 3 Options: ");
			System.out.println("1. Compute personal income tax\n2. Print the tax tables for taxable incomes(within range)\n3. Exit\n");
			int option = input.nextInt();
			switch(option) {
			
			case 1:
				int status = -1;
				boolean exit1 = false;
				while(!exit1) {
					System.out.println("\nWhat's your filing status\n0 - Single filer\n1 - Married filing jointly or qualifying widow(er)\n2 - Married filing separately\n3 - Head of household\n");
					status = input.nextInt();
					if(status <= 3 && status >=0) {
						exit1 = true;
					}else {
						System.out.println("\nInvalid Status, please try again.....\n");
					}
				}
				
				
				System.out.println("\n\nWhat's your annual income?\n");
				double income = input.nextDouble();
				incTax2009 = new IncomeTax(status, brackets2009, rates2009, income);
				incTax2020 = new IncomeTax(status, brackets2020, rates2020, income);
				System.out.println("\nCalculating your Income Tax, please wait......");
				
				try {
					TimeUnit.SECONDS.sleep(3); // imitate real time process
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("\n***************************************************\n");
				System.out.println("\nYour income tax for 2009 is: " + incTax2009.getIncomeTax() + "\n\nYour income tax for 2020 is: " + incTax2020.getIncomeTax());
				System.out.println("\n***************************************************\n");
				break;
				
			case 2:
				if(incTax2009 == null || incTax2020 == null) {
					System.out.println("\nPlease Enter your Status and Income using Option1 before printing out Tax Tables\n");
					break;
				}
				System.out.print("Please Enter the amount From(with intervals $1,000 - e.g.1000)$ ");
//				System.out.println("From: ");
				double from = input.nextDouble();
				System.out.print("Please Enter the amount From(with intervals $1,000 - e.g.20000)$ ");
				double to = input.nextDouble();
				if(from < 0 || to > 10000000 || (from > to)) {
					System.out.println("\nInvalid Amount Range, Please try again....\n");
					break;
				}
				System.out.println("2009 tax tables for taxable income from "+ from +" to " +to + "\n--------------------------------------------------------------------------"
						+ "\nTaxable   Single   Married Joint   Married   Head of\nIncome             or Qualifying   Separate  a House\n                   Widow(er)                  ");
				incTax2009.printTaxTable((int)from, (int)to); //call the method using the input range
				System.out.println("2020 tax tables for taxable income from "+ from +" to " +to + "\n--------------------------------------------------------------------------"
						+ "\nTaxable   Single   Married Joint   Married   Head of\nIncome             or Qualifying   Separate  a House\n                   Widow(er)                  ");
				incTax2020.printTaxTable((int)from, (int)to); 
				break;
				
			case 3:
				exit = true;
				System.out.println("\nGood Bye!");
				break;
				
			default:
				System.out.println("\nInvalid option, try again......\n");
			}
		}while(!exit);
	}
}
