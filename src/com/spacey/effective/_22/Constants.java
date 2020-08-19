package com.spacey.effective._22;

/**
 * 
 * Putting static members into an interface (and implementing that interface) is
 * a bad practice and there is even a name for it, the Constant Interface
 * Antipattern, see Effective Java, Item 17:
 * 
 * The constant interface pattern is a poor use of interfaces. That a class uses
 * some constants internally is an implementation detail. Implementing a
 * constant interface causes this implementation detail to leak into the class's
 * exported API. It is of no consequence to the users of a class that the class
 * implements a constant interface. In fact, it may even confuse them. Worse, it
 * represents a commitment: if in a future release the class is modified so that
 * it no longer needs to use the constants, it still must implement the
 * interface to ensure binary compatibility. If a nonfinal class implements a
 * constant interface, all of its subclasses will have their namespaces polluted
 * by the constants in the interface.
 * 
 * There are several constant interfaces in the java platform libraries, such as
 * java.io.ObjectStreamConstants. These interfaces should be regarded as
 * anomalies and should not be emulated.
 * 
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 
 * To avoid some pitfalls of the constant interface (because you can't prevent
 * people from implementing it), a proper class with a private constructor
 * should be preferred (example borrowed from Wikipedia):
 * 
 * @author Farooque
 *
 */
public final class Constants {

	private Constants() {
		// restrict instantiation
	}

	public static final double PI = 3.14159;
	public static final double PLANCK_CONSTANT = 6.62606896e-34;
}