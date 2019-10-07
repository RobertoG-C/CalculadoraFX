package dad.javafx;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complejo {
	private DoubleProperty real;
	private DoubleProperty imaginario;
 
	public Complejo() {

		this.real=new SimpleDoubleProperty(this,"real");
		this.imaginario=new SimpleDoubleProperty(this,"imaginario");
}

	public DoubleProperty realProperty() {
		return this.real;
	}
	

	public double getReal() {
		return this.realProperty().get();
	}
	

	public void setReal( double real) {
		this.realProperty().set(real);
	}
	

	public DoubleProperty imaginarioProperty() {
		return this.imaginario;
	}
	

	public double getImaginario() {
		return this.imaginarioProperty().get();
	}
	

	public void setImaginario(double imaginario) {
		this.imaginarioProperty().set(imaginario);
	}
	


		
}
