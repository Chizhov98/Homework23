package Entity;

import Enams.GearType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "automobiles")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "manufacture_date")
    private int yearOfManufacture;

    @Column(name = "sell_date")
    private String lastSellDate;

    @Column(name = "gear type")
    private GearType typeOfGear;

    @Column(name = "fuel volume")
    private double fuelVolume;

    public Auto(String title, int price, int yearOfManufacture, String lastSellDate, GearType typeOfGear, double fuelVolume) {
        this.title = title;
        this.price = price;
        this.yearOfManufacture = yearOfManufacture;
        this.lastSellDate = lastSellDate;
        this.typeOfGear = typeOfGear;
        this.fuelVolume = fuelVolume;
    }

}
