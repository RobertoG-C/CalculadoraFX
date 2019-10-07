package dad.javafx;

import com.sun.javafx.binding.SelectBinding.AsString;
import com.sun.scenario.effect.impl.prism.PrImage;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {

	private VBox leftVbox,centerVbox;
	private HBox realHbox,imaginarioHbox,resulHbox;
	private TextField aTextField,bTextField,cTextField,dTextField,resultField1,resultField2;
	private ComboBox<String> operadorCombo;
	
	private StringProperty  operador;
	private Complejo a,b,c;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		a=new Complejo();
		b=new Complejo();
		c=new Complejo();
		operador=new SimpleStringProperty();


				
		aTextField=new TextField();
		aTextField.setMaxWidth(50);
		
		bTextField=new TextField();
		bTextField.setMaxWidth(50);
		
		cTextField=new TextField();
		cTextField.setMaxWidth(50);
		
		dTextField=new TextField();
		dTextField.setMaxWidth(50);
		
		resultField1=new TextField();
		resultField1.setMaxWidth(50);
		resultField1.setEditable(false);
		
		resultField2=new TextField();
		resultField2.setMaxWidth(50);
		resultField2.setEditable(false);
		
		operadorCombo=new ComboBox<String>();
		operadorCombo.getItems().addAll("+","-","*","/");
		

		
		realHbox=new HBox(5,aTextField,new Label("+"),bTextField,new Label("i"));
		imaginarioHbox=new HBox(5,cTextField,new Label("+"),dTextField,new Label("i"));
		resulHbox=new HBox(5,resultField1,new Label("+"),resultField2,new Label("i"));
		
		
		leftVbox=new VBox(operadorCombo);
		leftVbox.setAlignment(Pos.CENTER);
		centerVbox=new VBox(5,realHbox,imaginarioHbox,new Separator(),resulHbox);
		centerVbox.setAlignment(Pos.CENTER);
		
		HBox root=new HBox(5,leftVbox,centerVbox);
		root.setAlignment(Pos.CENTER);
		
		Scene scene=new Scene(root,320,200);
		
		primaryStage.setTitle("Calculadora");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Bindings.bindBidirectional(aTextField.textProperty(), a.realProperty(),new NumberStringConverter());
		Bindings.bindBidirectional(bTextField.textProperty(), a.imaginarioProperty(),new NumberStringConverter());
		Bindings.bindBidirectional(cTextField.textProperty(), b.realProperty(),new NumberStringConverter());
		Bindings.bindBidirectional(dTextField.textProperty(), b.imaginarioProperty(),new NumberStringConverter());
	

		resultField1.textProperty().bind(c.realProperty().asString());
		resultField2.textProperty().bind(c.imaginarioProperty().asString());

		operador.bind(operadorCombo.getSelectionModel().selectedItemProperty());
		operador.addListener((o, ov, nv) -> onOperadorAction(nv));
		operadorCombo.getSelectionModel().select(0);
		
	}
	private void onOperadorAction(String nv) {
		
		c.realProperty().unbind();
		c.imaginarioProperty().unbind();
		
		switch (nv) {
		case "+":
			c.realProperty().bind(a.realProperty().add(b.realProperty()));
			c.imaginarioProperty()
					.bind(a.imaginarioProperty().add(b.imaginarioProperty()));
			
			break;
		case "-":
			c.realProperty().bind(a.realProperty().subtract(b.realProperty()));
			c.imaginarioProperty()
					.bind(a.imaginarioProperty().subtract(b.imaginarioProperty()));

			break;
		case "*":
			c.realProperty().bind(a.realProperty().multiply(b.realProperty())
					.subtract(a.imaginarioProperty().multiply(b.imaginarioProperty())));
			c.imaginarioProperty()
					.bind(a.realProperty().multiply(b.imaginarioProperty())
							.add(b.realProperty().multiply(a.imaginarioProperty())));
		
			break;
		case "/":
			c.realProperty()
					.bind((a.realProperty().multiply(b.realProperty())
							.add(a.imaginarioProperty().multiply(b.imaginarioProperty()))).divide(
									b.realProperty().multiply(b.realProperty()).add(b
											.imaginarioProperty().multiply(b.imaginarioProperty()))));
			c.imaginarioProperty()
					.bind((a.imaginarioProperty().multiply(b.realProperty())
							.subtract(a.realProperty().multiply(b.imaginarioProperty()))).divide(
									b.realProperty().multiply(b.realProperty()).add(b
											.imaginarioProperty().multiply(b.imaginarioProperty()))));
			break;
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

}
