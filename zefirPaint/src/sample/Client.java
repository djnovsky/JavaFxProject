package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by novsky on 21.03.2016.
 */
public class Client implements Serializable {

    private int hairLength;
    private double paintingPrice;
    private double oxyPrice;
    private double blonderPrice;
    private boolean dryIsChecked;

    public Client(int hairLength, boolean dryIsChecked) {
        readProperties();
        this.hairLength = hairLength;
        this.dryIsChecked = dryIsChecked;
    }


    public double hairDryPerMoney() {
        if (dryIsChecked == true) {
            if (getHairLength() == 1) {
                return 0;
            }
            if (getHairLength() == 2) {
                return 20;
            }
            if (getHairLength() == 3) {
                return 40;
            }
        }
        return 0;
    }


    public double priceForHair() {
        if (hairLength == 1) {
            return 180;
        }
        if (hairLength == 2) {
            return 220;
        }
        if (hairLength == 3) {
            return 260;
        }
        return 0;
    }

    public int getHairLength() {
        return hairLength;
    }

    public void readProperties() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("D://PaintZefir/prices.properties")) {
            properties.load(fileInputStream);
            setPaintingPrice(Double.parseDouble(properties.getProperty("pricePaint")));
            setOxyPrice(Double.parseDouble(properties.getProperty("priceOxi")));
            setBlonderPrice(Double.parseDouble(properties.getProperty("priceBlond")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getPaintingPrice() {
        return paintingPrice;
    }

    public void setPaintingPrice(double paintingPrice) {
        this.paintingPrice = paintingPrice;
    }

    public double getOxyPrice() {
        return oxyPrice;
    }

    public void setOxyPrice(double oxyPrice) {
        this.oxyPrice = oxyPrice;
    }

    public double getBlonderPrice() {
        return blonderPrice;
    }

    public void setBlonderPrice(double blonderPrice) {
        this.blonderPrice = blonderPrice;
    }

}
