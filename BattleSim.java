import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BattleSim {
	private static Scanner sc;
	public static void main(String[] args) {
		System.out.println("If aggressor doesn't want to risk all units, use [max units + 1].\nDefense must risk all units on space.");
		int agg = getAgg();
		int def = getDef();
		sc.close();
		int[] result = battle(agg,def);
		System.out.print("\n\n\n\n");
		if(result[1]>0) System.out.println("Defense holds with "+result[1]+" Units Remaining.\nAggressor Has 1 Unit remaining.");
		else System.out.println("Attack Succeeds. Aggressor has "+result[0]+" Units Remaining.");
	}
	public static int[] battle(int agg, int def) {
		int[] units = {agg,def};
		while(units[0]>1&&units[1]>0) {
			units = roll(units[0],units[1]);
			System.out.println("\nAggressor Has "+units[0]+" Units Remaining");
			System.out.println("Defense Has "+units[1]+" Units Remaining");
		}
		return units;
	}
	public static int[] roll(int agg,int def) {
		int adc, ddc;
		int al=0, dl = 0;
		ArrayList<Integer> aggDice = new ArrayList<>();
		ArrayList<Integer> defDice = new ArrayList<>();
		if(agg>3) adc=3;
		else adc=agg-1;
		
		if(def>=2&&agg>2) ddc=2;
		else ddc=1;
		
		//roll each aggressor die, store in list
		for(int i=0;i<adc;i++) {
			aggDice.add((int)(Math.random()*6)+1);
		}
		
		//roll each defense die, store in list
		for(int i=0;i<ddc;i++) {
			defDice.add((int)(Math.random()*6)+1);
		}
		//Sort dice descending
		aggDice= sort(aggDice);
		defDice= sort(defDice);
		
		//Print rolls
		System.out.println("\nAggressor Roll: ");
		for(int i=0;i<adc;i++) {
			System.out.print(aggDice.get(i));
		}
		System.out.println("\nDefense Roll: ");
		for(int i=0;i<ddc;i++) {
			System.out.print(defDice.get(i));
		}
		
		//compare highest dice, matches favor defense
		for(int i=0;i<ddc;i++) {
			if(aggDice.get(i)<=defDice.get(i)) {
				al++;
			}
			else {
				dl++;
			}
		}
		System.out.println("\n\nAggressor Loses "+al+" Units.");
		System.out.println("Defense Loses "+dl+" Units.");
		
		return new int[] {agg-al,def-dl};
	}
	public static ArrayList<Integer> sort(ArrayList<Integer> list){
		list.sort(Collections.reverseOrder());
		return list;	
	}
	public static int getAgg() {
		int a=0;
		sc = new Scanner(System.in);
		while(a<2) {
			System.out.print("Aggressor Units: ");
			a = sc.nextInt();
			if(a<2) {
				System.out.println("\nError: Aggressor must have at least two units.\n");
			}
		}
		return a;
	}
	public static int getDef() {
		int a=0;
		sc = new Scanner(System.in);
		while(a<1) {
			System.out.print("Defense Units: ");
			a = sc.nextInt();
			if(a<1) {
				System.out.println("\nError: Defense must have at least one unit.\n");
			}
		}
		return a;
	}
}
