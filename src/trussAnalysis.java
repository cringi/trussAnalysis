/**
 * Truss Analysis
 * Brandon Berney
 * November 23 2015
 * [I have no idea what this is]
 */

import java.util.Scanner;
public class trussAnalysis {
	private static double
		// User variables
		centerLoad,
		sectionWeight,
		structuralPairWeight,

		// Weights
		roadWeight,
		structuralWeight,

		// Forces
		pierForce,
		up_force_e_via_ed,
		up_force_d_via_de,
		up_force_d_via_cd,
		up_force_c_via_cd,
		up_force_c_via_bc,
		up_force_b_via_bc,
		up_force_b_via_ab,
		up_force_a_via_ab,
		right_force_a_via_ac,
		right_force_c_via_ac,
		right_force_c_via_ce,
		right_force_c_via_bc,
		right_force_b_via_bd,
		right_force_d_via_bd,
		right_force_d_via_df,

		// Tensions
		tension_a_in_ab,
		tension_b_in_bc,
		tension_c_in_cd,
		tension_d_in_de,

		tension_a_in_ac,
		tension_c_in_ce,
		tension_b_in_bd,
		tension_d_in_df;

    public static void main(String[] args) {
    	// Testing
    	centerLoad = 20000;
    	sectionWeight = 2000;
    	structuralPairWeight = 500;

    	doEquations();
    	System.out.println(returnResults());
    }

    private static void getInputs() {
    	Scanner input = new Scanner(System.in);
    	System.out.print("Center load in lbs: ");
    	centerLoad = input.nextDouble();

    	System.out.print("Weight of one section of road in lbs: ");
    	sectionWeight = input.nextDouble();

    	System.out.print("Weight of structural pair in lbs: ");
    	structuralPairWeight = input.nextDouble();
    }

    private static void doEquations() {
    	double mathMagic = (1/Math.sqrt(3));

    	roadWeight = 0; // FIGURE THIS OUT
    	up_force_e_via_ed = 0.5 * (centerLoad + roadWeight);
    	up_force_d_via_de = -up_force_e_via_ed - structuralWeight;
    	up_force_d_via_cd = -up_force_d_via_de + structuralWeight;
    	up_force_c_via_cd = -up_force_d_via_cd - structuralWeight;
    	up_force_c_via_bc = -up_force_c_via_cd + roadWeight;
    	up_force_b_via_bc = -up_force_c_via_bc - structuralWeight;
    	up_force_b_via_ab = -up_force_b_via_bc + (0.5 * structuralWeight);
    	up_force_a_via_ab = -up_force_b_via_ab - structuralWeight;
    	pierForce = -up_force_a_via_ab + (0.5 * roadWeight);

    	tension_a_in_ab = getTension(true, up_force_a_via_ab);
    	tension_b_in_bc = getTension(false, up_force_b_via_bc);
    	tension_c_in_cd = getTension(true, up_force_c_via_cd);
    	tension_d_in_de = getTension(false, up_force_d_via_de);

    	right_force_a_via_ac = -mathMagic * up_force_a_via_ab;
    	right_force_c_via_ac = -right_force_a_via_ac;
    	right_force_c_via_ce = right_force_a_via_ac + mathMagic
    		* (up_force_c_via_bc - up_force_c_via_cd);
    	right_force_b_via_bd = -mathMagic * (up_force_b_via_ab - up_force_b_via_bc);
    	right_force_d_via_bd = -right_force_b_via_bd;
    	right_force_d_via_df = right_force_b_via_bd - mathMagic * (up_force_d_via_cd - up_force_d_via_de);

    	tension_a_in_ac = right_force_a_via_ac;
    	tension_b_in_bd = right_force_b_via_bd;
    	tension_c_in_ce = right_force_c_via_ce;
    	tension_d_in_df = right_force_d_via_df;
    }

    private static double getTension(boolean isPositive, double variable) {
    	int negate = 1;
    	double mathMagic = (2/Math.sqrt(3));
    	if (isPositive)
    		negate = 1;
    	else
    		negate = -1;
    	return (negate * mathMagic) * variable;
    }

    private static String returnResults() {
    	String ourResults = "";
    	return ourResults;
    }
}
