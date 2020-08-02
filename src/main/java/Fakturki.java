import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Fakturki extends Application {

    private Scene addNew;
    private Button exit;
    private Scene scene;
    private Button exitAdd;
    private Button add;
    private TextField name;
    private TextField nip;
    private TextField adress;
    private int width;
    private int hight;
    private Label nameLabel;
    private Label nipLabel;
    private Label adressLabel;
    private Button addNewDataButton;
    private ArrayList<NIP> information;
    private ArrayList<Product> shoppingList;
    private ChoiceBox<NIP> choiceBox ;
    private Group addBox;
    private Label yourNip;
    private Label buyerNip;
    private int choice;
    private Button deleteChoice;
    private BufferedReader reader;
    private String loadNip;
    private String loadName;
    private String loadAdress;
    private ChoiceBox<NIP> choiceBoxbuyer;
    private int choiceChange;
    private int choiceBuyer;
    private Label buyerName;
    private Label buyerAdress;
    private Label sellerNip;
    private Label sellerName;
    private Label sellerAdress;
    private Label data;
    private GregorianCalendar calendar;
    private TextField productName;
    private TextField productPrice;
    private TextField productQuantity;
    private Button addProduct;
    private ListView<Product> listView;
    private int choseFromList;
    private Button deleteProduct;
    private TextField nameData;
    private Button saveData;
    private ChoiceBox<String> payment;
    private int paymentInt;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.show();
        primaryStage.setTitle("Fakturki");
        primaryStage.setFullScreenExitHint("");
        this.hight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.width = Toolkit.getDefaultToolkit().getScreenSize().width;
        calendar = new GregorianCalendar();
        information = new ArrayList<NIP>();
        shoppingList = new ArrayList<Product>();

        try{
            reader = new BufferedReader(new FileReader("nipList"));
            loadNip = "blebleble";
                while(loadNip !=null){
                    loadNip = reader.readLine();
                    loadName = reader.readLine();
                    loadAdress = reader.readLine();
                    if(loadNip !=null)
                    information.add(new NIP(loadNip,loadName,loadAdress));
            }
            reader.close();
        }catch (IOException ex){ System.out.println(ex.getMessage());}

        choiceBox = new ChoiceBox<>();
        choiceBox.setPrefSize(100,20);
        choiceBox.setLayoutX(10);
        choiceBox.setLayoutY(hight-30);
        choiceBox.getItems().addAll(information);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                choice = t1.intValue();
                sellerAdress.setText("Adres: " + information.get(choice).getAdress());
                sellerNip.setText("NIP sprzedawcy : " + information.get(choice).getNip());
                sellerName.setText("Nazwa firmy : " +information.get(choice).getName());
            }
        });

        choiceBoxbuyer = new ChoiceBox<>();
        choiceBoxbuyer.setPrefSize(100,20);
        choiceBoxbuyer.setLayoutX(250);
        choiceBoxbuyer.setLayoutY(hight-30);
        choiceBoxbuyer.getItems().addAll(information);
        choiceBoxbuyer.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                choiceBuyer = t1.intValue();
                buyerAdress.setText("Adres: " + information.get(choiceBuyer).getAdress());
                buyerNip.setText("NIP nabywcy : " + information.get(choiceBuyer).getNip());
                buyerName.setText("Nazwa firmy : " +information.get(choiceBuyer).getName());
            }
        });




        deleteChoice = new Button("usun wybrany NIP");
        deleteChoice.setPrefSize(120,20);
        deleteChoice.setLayoutX(120);
        deleteChoice.setLayoutY(hight-30);
        deleteChoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    choiceChange = choice;
                    choiceBox.getItems().remove(choice);
                    choiceBoxbuyer.getItems().remove(choiceChange);
                    information.remove(choiceChange);

                }catch (Exception e){
                    e.getMessage();}

            }
        });

        yourNip = new Label("Twoj NIP");
        yourNip.setTextFill(Color.WHITE);
        yourNip.setPrefSize(200,20);
        yourNip.setLayoutX(10);
        yourNip.setLayoutY(hight-55);

        buyerNip = new Label("NIP nabywcy");
        buyerNip.setTextFill(Color.WHITE);
        buyerNip.setPrefSize(200,20);
        buyerNip.setLayoutX(250);
        buyerNip.setLayoutY(hight-55);

        this.exit = new Button("Wyjscie");
        exit.setPrefSize(80,20);
        exit.setLayoutX(width-90);
        exit.setLayoutY(hight-30);

        add = new Button("dodaj nowe dane");
        add.setPrefSize(150,20);
        add.setLayoutX(width-250);
        add.setLayoutY(hight-30);

        if(information.isEmpty())
            sellerAdress = new Label("Adres: " +"brak danych");
            else sellerAdress = new Label("Adres: " + information.get(0).getAdress());
        sellerAdress.setTextFill(Color.WHITE);
        sellerAdress.setPrefSize(200,20);
        sellerAdress.setLayoutX(width/2-220);
        sellerAdress.setLayoutY(hight/2-100);

        if(information.isEmpty())
            sellerNip = new Label("NIP sprzedawcy : " +"brak danych");
        else sellerNip = new Label("NIP sprzedawcy : " + information.get(0).getNip());
        sellerNip.setTextFill(Color.WHITE);
        sellerNip.setPrefSize(200,20);
        sellerNip.setLayoutX(width/2-220);
        sellerNip.setLayoutY(hight/2-150);

        if(information.isEmpty())
            sellerName = new Label("Nazwa firmy : " +"brak danych");
        else sellerName = new Label("Nazwa firmy : " + information.get(0).getName());
        sellerName.setTextFill(Color.WHITE);
        sellerName.setPrefSize(200,20);
        sellerName.setLayoutX(width/2-220);
        sellerName.setLayoutY(hight/2-125);

        if(information.isEmpty())
            buyerAdress = new Label("Adres: " +"brak danych");
        else buyerAdress = new Label("Adres: " + information.get(0).getAdress());
        buyerAdress.setTextFill(Color.WHITE);
        buyerAdress.setPrefSize(200,20);
        buyerAdress.setLayoutX(width/2+20);
        buyerAdress.setLayoutY(hight/2-100);

        if(information.isEmpty())
            buyerNip = new Label("NIP nabywcy : " +"brak danych");
        else buyerNip = new Label("NIP nabywcy : " + information.get(0).getNip());
        buyerNip.setTextFill(Color.WHITE);
        buyerNip.setPrefSize(200,20);
        buyerNip.setLayoutX(width/2+20);
        buyerNip.setLayoutY(hight/2-150);

        if(information.isEmpty())
            buyerName = new Label("Nazwa firmy : " +"brak danych");
        else buyerName = new Label("Nazwa firmy : " + information.get(0).getName());
        buyerName.setTextFill(Color.WHITE);
        buyerName.setPrefSize(200,20);
        buyerName.setLayoutX(width/2+20);
        buyerName.setLayoutY(hight/2-125);

        data = new Label("Data: "+calendar.get(Calendar.DATE)+ " " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.YEAR));
        data.setPrefSize(120,20);
        data.setLayoutX(width-130);
        data.setTextFill(Color.WHITE);
        data.setLayoutY(20);

        productName = new TextField("");
        productName.setPromptText("Wprowadz nazwe przedmiotu");
        productName.setPrefSize(150,20);
        productName.setLayoutX(width/2-230);
        productName.setLayoutY(hight/2);

        productPrice = new TextField("");
        productPrice.setPromptText("cene produktu netto");
        productPrice.setPrefSize(150,20);
        productPrice.setLayoutX(width/2-75);
        productPrice.setLayoutY(hight/2);

        productQuantity = new TextField("");
        productQuantity.setPromptText("ilosc");
        productQuantity.setPrefSize(80,20);
        productQuantity.setLayoutX(width/2+80);
        productQuantity.setLayoutY(hight/2);

        addProduct = new Button("Dodaj");
        addProduct.setPrefSize(65, 20);
        addProduct.setLayoutX(width/2+165);
        addProduct.setLayoutY(hight/2);
        addProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!productName.getText().isEmpty()&& !productPrice.getText().isEmpty()&& !productQuantity.getText().isEmpty()
                        && (productPrice.getText().matches("\\d+")|| productPrice.getText().matches("\\d+\\.\\d{0,2}+") )
                        && productQuantity.getText().matches("\\d+")) {
                    shoppingList.add(new Product(productName.getText(), Integer.parseInt(productQuantity.getText()), Double.parseDouble(productPrice.getText())));
                    listView.getItems().add(shoppingList.get(shoppingList.size() - 1));
                    productName.clear();
                    productQuantity.clear();
                    productPrice.clear();
                }
            }
        });
        listView = new ListView<>();
        listView.getItems().addAll(shoppingList);
        listView.setPrefSize(700 , 300);
        listView.setLayoutX(hight/2-20);
        listView.setLayoutY(width/2-300);
        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                choseFromList = t1.intValue();
            }
        });

        deleteProduct = new Button("usun zaznaczony produkt");
        deleteProduct.setPrefSize(200, 20);
        deleteProduct.setLayoutY(hight/2 + 350);
        deleteProduct.setLayoutX(width/2-100);
        deleteProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    int chose2 = choseFromList;
                    listView.getItems().remove(choseFromList);
                    shoppingList.remove(chose2);

                }catch (Exception e){
                    e.getMessage();}

            }
        });

        nameData = new TextField();
        nameData.setPrefSize(200,20);
        nameData.setLayoutY(hight-30);
        nameData.setLayoutX(width/2-250);
        nameData.setPromptText("wpisz nazwe dokumentu");

        String[] paymentS = {"karta", "gotowka"};
        payment = new ChoiceBox<>();
        payment.getItems().addAll(paymentS);
        payment.setPrefSize(70,20);
        payment.setLayoutY(hight-30);
        payment.setLayoutX(width/2-30);
        payment.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                paymentInt = t1.intValue();
            }
        });

        saveData = new Button("zapisz dokument");
        saveData.setPrefSize(110,20);
        saveData.setLayoutY(hight-30);
        saveData.setLayoutX(width/2+60);
        saveData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    PrintWriter writer = new PrintWriter(new FileWriter(nameData.getText() + ".faktura"));
                    writer.print(data.getText());
                    writer.println();
                    writer.print("Sposob platnosci: " + paymentS[paymentInt]);
                    writer.println();
                    writer.print("Sprzedawca:");
                    writer.println();
                    writer.print("NIP: " );
                    writer.println();
                    writer.print(information.get(choice).getNip());
                    writer.println();
                    writer.print("Nazwa firmy: " );
                    writer.println();
                    writer.print(information.get(choice).getName());
                    writer.println();
                    writer.print("Adres: "  );
                    writer.println();
                    writer.print(information.get(choice).getAdress());
                    writer.println();
                    writer.print("Nabywca:");
                    writer.println();
                    writer.print("NIP: " );
                    writer.println();
                    writer.print(information.get(choiceBuyer).getNip());
                    writer.println();
                    writer.print("Nazwa firmy: " );
                    writer.println();
                    writer.print(information.get(choiceBuyer).getName());
                    writer.println();
                    writer.print("Adres: "  );
                    writer.println();
                    writer.print(information.get(choiceBuyer).getAdress());
                    writer.println();
                    for(int i = 0 ; i<shoppingList.size();i++) {
                        writer.print(shoppingList.get(i).toString());
                        writer.println();
                    }

                    writer.close();
                }catch (IOException ex){ System.out.println(ex.getMessage());}
            }
        });

        Group root = new Group();
        root.getChildren().add(exit);
        root.getChildren().add(nameData);
        root.getChildren().add(saveData);
        root.getChildren().addAll(payment);
        root.getChildren().add(add);
        root.getChildren().add(data);
        root.getChildren().add(addProduct);
        root.getChildren().add(choiceBox);
        root.getChildren().add(choiceBoxbuyer);
        root.getChildren().add(yourNip);
        root.getChildren().add(sellerName);
        root.getChildren().add(productName);
        root.getChildren().add(productPrice);
        root.getChildren().add(productQuantity);
        root.getChildren().add(listView);
        root.getChildren().add(sellerNip);
        root.getChildren().add(deleteProduct);
        root.getChildren().add(sellerAdress);
        root.getChildren().add(buyerNip);
        root.getChildren().add(buyerAdress);
        root.getChildren().add(buyerName);
        root.getChildren().add(deleteChoice);
        scene = new Scene(root);
        scene.setFill(Color.web("#696969"));
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    PrintWriter writer = new PrintWriter(new FileWriter("nipList"));
                    for(int i = 0 ; i<information.size();i++) {
                        writer.print(information.get(i).getNip());
                        writer.println();
                        writer.print(information.get(i).getName());
                        writer.println();
                        writer.print(information.get(i).getAdress());
                        writer.println();
                    }
                    writer.close();
                }catch (IOException ex){ System.out.println(ex.getMessage());}
                primaryStage.close();
            }
        });

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                primaryStage.setScene(addNew);
                primaryStage.setFullScreen(true);
                addBox.getChildren().add(choiceBox);
                addBox.getChildren().add(yourNip);
                addBox.getChildren().add(deleteChoice);
            }
        });

                exitAdd = new Button("Wyjscie");
                exitAdd.setPrefSize(80,20);
                exitAdd.setLayoutX(width-90);
                exitAdd.setLayoutY(hight-30);

                name = new TextField();
                name.setPrefSize(200, 20);
                name.setLayoutX((int)(width/2-100));
                name.setLayoutY((int)(hight/2-40));
                name.setPromptText("Wprowadz nazwe firmy");

                nip = new TextField();
                nip.setPrefSize(200, 20);
                nip.setLayoutX((int)(width/2-100));
                nip.setLayoutY((int)(hight/2));
                nip.setPromptText("Wprowadz 10 cyfrowy nip");



                adress = new TextField();
                adress.setPrefSize(200, 20);
                adress.setLayoutX((int)(width/2-100));
                adress.setLayoutY((int)(hight/2+40));
                adress.setPromptText("Wprowadz adres firmy");

                nameLabel = new Label("Nazwa: ");
                nameLabel.setPrefSize(60, 20);
                nameLabel.setLayoutX((int)(width/2-160));
                nameLabel.setTextFill(Color.WHITE);
                nameLabel.setLayoutY((int)(hight/2-40));

                nipLabel = new Label("Nip: ");
                nipLabel.setPrefSize(60, 20);
                nipLabel.setLayoutX((int)(width/2-160));
                nipLabel.setTextFill(Color.WHITE);
                nipLabel.setLayoutY((int)(hight/2));

                adressLabel = new Label("Adres: ");
                adressLabel.setPrefSize(60, 20);
                adressLabel.setTextFill(Color.WHITE);
                adressLabel.setLayoutX((int)(width/2-160));
                adressLabel.setLayoutY((int)(hight/2+40));

                addNewDataButton = new Button("dodaj");
                addNewDataButton.setPrefSize(60, 20);
                addNewDataButton.setLayoutX((int)(width/2-30));
                addNewDataButton.setLayoutY((int)(hight-30));

                addBox = new Group();
                this.addNew = new Scene(addBox);
                addBox.getChildren().add(exitAdd);
                addBox.getChildren().add(name);
                addBox.getChildren().add(nip);
                addBox.getChildren().add(adress);
                addBox.getChildren().add(nameLabel);
                addBox.getChildren().add(nipLabel);
                addBox.getChildren().add(addNewDataButton);
                addBox.getChildren().add(adressLabel);
                addNew.setFill(Color.web("#696969"));


        exitAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(scene);
                primaryStage.setFullScreen(true);
                root.getChildren().add(choiceBox);
                root.getChildren().add(yourNip);
                root.getChildren().add(deleteChoice);
            }
        });
        addNewDataButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!nip.getText().isEmpty()&&!name.getText().isEmpty()&&!adress.getText().isEmpty()
                && nip.getText().matches("\\d{10}")) {
                    information.add(new NIP(nip.getText(), name.getText(), adress.getText()));
                    nip.clear();
                    name.clear();
                    adress.clear();
                    choiceBox.getItems().add(information.get(information.size() - 1));
                    choiceBoxbuyer.getItems().add(information.get(information.size() - 1));
                }
            }
        });

    }
}