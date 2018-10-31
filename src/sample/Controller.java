package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btn_Count;

    @FXML
    private TextField tf_total;

    @FXML
    private ComboBox<String> cb_room;

    @FXML
    private CheckBox chb_breakfast;

    @FXML
    private CheckBox chb_safe;

    @FXML
    private DatePicker dp_in;

    @FXML
    private DatePicker dp_out;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_Count.setOnAction(e -> {
            double costDay;

            if(cb_room.getValue().equals("Стандарт")){
                costDay = 2000;
            }else if(cb_room.getValue().equals("Эконом")){
                costDay = 1500;
            }else {
                costDay = 3000;
            }

            if(chb_breakfast.isSelected()){
                costDay += 300;
            }
            if(chb_safe.isSelected()){
                costDay += 50;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if(dp_in.getValue() != null && dp_out.getValue() != null){
//                try {
//                    Date dateIn = dateFormat.parse(dp_in.getValue().toString());
//                    Date dateOut = dateFormat.parse(dp_out.getValue().toString());
//                    long msIn = dateIn.getTime();
//                    long msOut = dateOut.getTime();
//                    if (msOut > msIn){
//                        int days = (int)((msOut - msIn) / 86400000 );
//                        double result = days * costDay;
//                        tf_total.setText(String.valueOf(result));
//
//
//                    }
//                    else {
//                        tf_total.setText("Error: incorrect dates");
//                    }
//                } catch (ParseException e1) {
//                    tf_total.setText("Error: incorrect dates");
//                }

                LocalDate dateIn = dp_in.getValue();
                LocalDate dateOut = dp_out.getValue();
                Period delta = Period.between(dateIn, dateOut);
                int days = delta.getDays();
                if (days > 0){
                    double result = days * costDay;
                    tf_total.setText(String.valueOf(result));
                }else {
                    tf_total.setText("Error: incorrect dates");
                }
            }else {
                tf_total.setText("Error: incorrect dates");
            }
        });

        ObservableList<String> list = FXCollections.observableArrayList("Стандарт", "Эконом", "Люкс");

        cb_room.setItems(list);

        cb_room.setValue("Стандарт");
    }
}