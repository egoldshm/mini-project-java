package primitives;

import java.awt.Color;

/**
 * @author sasegal
 *
 */

public abstract class Util {
	// It is binary, equivalent to ~1/1,000,000,000,000 in decimal (12 digits)
		private static final double ACCURACY = 0.001;

		// double store format (bit level):
		// 1 bit sign, 11 bits exponent, 53 bits (52 stored) normalized mantissa
		// the number is m+2^e where 1<=m<2
		// NB: exponent is stored "normalized" (i.e. always positive by adding 1023)
		private static int getExp(double num) {
			// 1. doubleToRawLongBits: "convert" the stored number to set of bits
			// 2. Shift all 52 bits to the right (removing mantissa)
			// 3. Zero the sign of number bit by mask 0x7FF
			// 4. "De-normalize" the exponent by subtracting 1023
			return (int)((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
		}

		public static double usubtract(double lhs, double rhs) {
			int lhsExp = getExp(lhs);
			int rhsExp = getExp(rhs);

			// if other is too small relatively to our coordinate
			// return the original coordinate
			if (rhsExp - lhsExp < ACCURACY) return lhs;

			// if our coordinate is too small relatively to other
			// return negative of other coordinate
			if (lhsExp - rhsExp < ACCURACY) return -rhs;

			double result = lhs - rhs;
			int resultExp = getExp(result);
			// if the result is relatively small - tell that it is zero
			return resultExp - lhsExp < ACCURACY ? 0.0 : result;
		}

		public static double uadd(double lhs, double rhs) {
			int lhsExp = getExp(lhs);
			int rhsExp = getExp(rhs);

			// if other is too small relatively to our coordinate
			// return the original coordinate
			if (rhsExp - lhsExp < ACCURACY) return lhs;

			// if our coordinate is too small relatively to other
			// return other coordinate
			if (lhsExp - rhsExp < ACCURACY) return rhs;

			double result = lhs + rhs;
			int resultExp = getExp(result);
			// if the result is relatively small - tell that it is zero
			return resultExp - lhsExp < ACCURACY ? 0.0 : result;
		}

		public static double uscale(double lhs, double factor) {
			double deltaExp = getExp(factor - 1);
			return deltaExp < ACCURACY ? lhs : lhs * factor;
		}

		public static boolean isZero(double number) {
			return Math.abs(/*getExp()*/number) < ACCURACY;
		}

		public static boolean isOne(double number) {
			return getExp(number - 1) < ACCURACY;
		}

		public static double alignZero(double number) {
			return getExp(number) < ACCURACY ? 0.0 : number;
		}
		
		
		public static Color addColors(Color...C)
		{
			//the function averages colors
			int r = 0;
			int g = 0;
			int b = 0;
			for(Color c:C)
			{
				r +=c.getRed();
				g += c.getGreen();
				b += c.getBlue();
			}
			r /= C.length;
			g /= C.length;
			b /= C.length;
			return new Color(r, g, b);
		}
		
		/**
		 * @param c Color to be scaled
		 * @param scale number to scale color by 
		 * @return scaled color
		 */
		public static Color brightness(Color c, double scale) {
			//the function scales colors by a number
		    int r = Math.min(255, (int) (c.getRed() * scale));
		    int g = Math.min(255, (int) (c.getGreen() * scale));
		    int b = Math.min(255, (int) (c.getBlue() * scale));
		    return new Color(r,g,b);
		}

}
