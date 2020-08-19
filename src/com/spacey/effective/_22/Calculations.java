package com.spacey.effective._22;

import static com.spacey.effective._22.Constants.PLANCK_CONSTANT;
import static com.spacey.effective._22.Constants.PI;

/**
 * And to access the constants without having to fully qualify them (i.e.
 * without having to prefix them with the class name), use a static import
 * (since Java 5):
 * 
 * @author Farooque
 *
 */
public class Calculations {

	public double getReducedPlanckConstant() {
		return PLANCK_CONSTANT / (2 * PI);
	}
}