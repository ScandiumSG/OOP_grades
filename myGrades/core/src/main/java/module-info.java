module grades.core {
	requires javafx.base;

	exports grades.core;
	exports grades.persistance;

	opens grades.core;
	opens grades.persistance;
}
