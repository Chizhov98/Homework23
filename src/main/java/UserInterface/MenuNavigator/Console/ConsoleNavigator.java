package UserInterface.MenuNavigator.Console;


import Dao.AutomobileDao;
import Entity.Auto;
import UserInterface.MenuNavigator.MenuNavigator;
import UserInterface.menu.Console.RussianConsoleMenu;
import UserInterface.menu.Menu;
import org.hibernate.type.LocalDateType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleNavigator implements MenuNavigator {
    private final Scanner IN = new Scanner(System.in);
    private RussianConsoleMenu menu = new RussianConsoleMenu();
    private AutomobileDao autoDao = new AutomobileDao();

    @Override
    public void mainMenu() {
        menu.mainMenu();
        switch (menuScanner()) {
            case 1:
                createMenu();
                break;
            case 2:
                updateMenu();
                break;
            case 3:
                getAutoMenu();
                break;
            case 4:
                deleteMenu();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                menu.notExistChose();
                mainMenu();
        }
    }


    @Override
    public void getAutoMenu() {
        menu.getMenu();
        Auto auto;
        List<Auto> autos;
        switch (menuScanner()) {
            case 1:
                menu.enterId();
                int id = menuScanner();
                auto = autoDao.getAutoById(id);
                if (auto != null) {
                    System.out.println(auto.toString());
                } else {
                    menu.autoIsNotExist();
                }
                break;
            case 2:
                autos = autoDao.getAllAuto();
                if (autos.size() > 0) {
                    for (int i = 0; i < autos.size(); i++) {
                        if (autos.get(i) != null) {
                            System.out.println(autos.get(i).toString());
                        }
                    }
                } else {
                    menu.autoIsNotExist();
                }
                break;
            case 3:
                menu.enterTitle();
                String title = IN.nextLine();
                autos = autoDao.getAutoByTitle(title.trim());
                if (autos.size() > 0) {
                    for (int i = 0; i < autos.size(); i++) {
                        if (autos.get(i) != null) {
                            System.out.println(autos.get(i).toString());
                        }
                    }
                } else {
                    menu.autoIsNotExist();
                }
                break;
            case 4:
                menu.enterPriseToReadMenu("minimal");
                int min = menuScanner();
                menu.enterPriseToReadMenu("maximum");
                int max = menuScanner();
                autos = autoDao.getAllAutoInPriceRange(min, max);
                if (autos.size() > 0) {
                    for (int i = 0; i < autos.size(); i++) {
                        if (autos.get(i) != null) {
                            System.out.println(autos.get(i).toString());
                        }
                    }
                } else {
                    menu.autoIsNotExist();
                }
                break;
            case 0:
                break;
            default:
                menu.notExistChose();
        }
        mainMenu();

    }

    @Override
    public void updateMenu() {
        LocalDate date;
        menu.enterId();
        int id = menuScanner();
        boolean stop = true;
        Auto auto = autoDao.getAutoById(id);
        menu.updateMenu();
        while (stop) {
            switch (menuScanner()) {
                case 1:
                    menu.enterTitle();
                    auto.setTitle(IN.nextLine());
                    break;
                case 2:
                    menu.enterPrise();
                    auto.setPrice(menuScanner());
                    break;
                case 3:
                    menu.enterYearOfManufacture();
                    auto.setYearOfManufacture(dateConverter(IN.nextLine()));
                    break;
                case 4:
                    menu.enterLastSellingDate();
                    auto.setLastSellDate(dateConverter(IN.nextLine()));
                    break;
                case 5:
                    menu.enterFuelVolume();
                    auto.setFuelVolume(IN.nextByte());
                    break;
                case 0:
                    stop = false;
                default:
                    menu.notExistChose();
            }
        }
        autoDao.editAuto(auto);
        mainMenu();
    }

    private LocalDate dateConverter(String str) {
        String[] strArr = str.trim().split("/");
        int[] arr = new int[3];
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = Integer.valueOf(strArr[i]);
        }
        System.out.println(Arrays.toString(arr));
        return LocalDate.of(arr[2],arr[1],arr[0]);
    }

    @Override
    public void createMenu() {
        Auto auto = new Auto();
        menu.enterTitle();
        auto.setTitle(IN.nextLine());
        menu.enterPrise();
        auto.setPrice(menuScanner());
        menu.enterYearOfManufacture();
        auto.setYearOfManufacture(dateConverter(IN.nextLine()));
        menu.enterLastSellingDate();
        auto.setLastSellDate(dateConverter(IN.nextLine()));
        menu.enterFuelVolume();
        auto.setFuelVolume(IN.nextByte());

        autoDao.createAuto(auto);
        mainMenu();
    }

    @Override
    public void deleteMenu() {
        menu.deleteMenu();
        switch (menuScanner()) {
            case 1:
                menu.enterId();
                autoDao.deleteAuto(menuScanner());
                break;
            case 2:
                autoDao.deleteAllAuto();
                break;
            case 0:
                break;
            default:
                deleteMenu();
        }
        mainMenu();
    }

    public int menuScanner() {
        int result = IN.nextInt();
        IN.nextLine();
        return result;
    }
}
