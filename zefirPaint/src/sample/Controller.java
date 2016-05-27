package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private TextField pText;
    @FXML
    private TextField oxiText;
    @FXML
    private TextField blondText;
    @FXML
    private Button resultButton;
    @FXML
    private RadioButton firstRadioButton;
    @FXML
    private RadioButton secondRadioButton;
    @FXML
    private RadioButton thirdRadioButton;
    @FXML
    private Label resultText;
    @FXML
    private JFXCheckBox dryCheck;
    @FXML
    private ImageView banner;

    private int hairLength;
    private boolean dryIsChecked;

    public boolean checkIfDry() {
        if (dryCheck.isSelected()) {
            dryIsChecked = true;
        }
        return false;
    }

    public void getSelectedHairLength() {
        if (firstRadioButton.isSelected()) {
            setHairLength(1);
        }
        if (secondRadioButton.isSelected()) {
            setHairLength(2);
        }
        if (thirdRadioButton.isSelected()) {
            setHairLength(3);
        }
    }

    public double workPrice(Client client) {
        double tempPaint = client.getPaintingPrice() * getpText();
        double tempBlonder = client.getBlonderPrice() * getBlondText();
        double tempOxi = client.getOxyPrice() * getOxiText();
        return tempBlonder + tempOxi + tempPaint;
    }

    @Override
    public String toString() {
        return "\n "+dateNow() +
                "\n Client {" +
                "sum: " + resultText.getText() +
                ", paint: " + getpText() +
                ", oxy: " + getOxiText() +
                ", blond: " + getBlondText() +
                ", hair length: " + hairLength +
                ", drying: " + dryIsChecked +
                '}'+"\n..............---->>";
    }

    public void resultButtonClicked() {


        resultButton.setOnAction(event -> {
                    getSelectedHairLength();
                    checkIfDry();

                    Client client = new Client(hairLength, dryIsChecked);
                    checkIfDry();
                    Double priceOfWork = workPrice(client) + client.priceForHair() + client.hairDryPerMoney();
                    resultText.setText(priceOfWork.toString() + "0 hrn");
                    try {
                        FileUtils.writeStringToFile(new File("D://PaintZefir/paintdata.txt"), toString(), "windows-1250", true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        );
    }

    private String decodeCyrillic(String string) throws UnsupportedEncodingException {
        String value = "";
        try {
            byte bytes[] = string.getBytes("ISO-8859-1");
            value = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return value;
    }

    private String dateNow() {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        return df.format(dateobj);
    }

    public String validateValue(String string) {
        if (string.contains(",")) {
            return string.replace(',', '.');
        }
        return string;
    }

    public Double getpText() {
        Double textPaintAsDouble = Double.parseDouble(validateValue(pText.getText()));
        return textPaintAsDouble;
    }


    public void setHairLength(int hairLength) {
        this.hairLength = hairLength;
    }


    public Double getOxiText() {
        Double textOxiAsDouble = Double.parseDouble(validateValue(oxiText.getText()));
        return textOxiAsDouble;
    }


    public Double getBlondText() {
        Double textBlondAsDouble = Double.parseDouble(validateValue(blondText.getText()));
        return textBlondAsDouble;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
