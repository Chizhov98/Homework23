package UserInterface.menu.Console;

import UserInterface.menu.Menu;

public class RussianConsoleMenu implements Menu {
    @Override
    public void mainMenu() {
        System.out.println("Здраствуйте, ввыберите пожалуйста один из пунктов");
        System.out.println("1. Добавить запись в базу данных");
        System.out.println("2. Изменить запись в базе данных");
        System.out.println("3. Получить данные из базы");
        System.out.println("4. Удалить данные из базы");
        System.out.println("0. Выйти из приложения");
    }

    @Override
    public void deleteMenu() {
        System.out.println("1. Удалить всех\n" +
                "2. Удалить выбраного");
    }

    @Override
    public void updateMenu() {
        System.out.println("Что вы хотите изменить " +
                "1. Марку авто\n" +
                "2. Цену\n" +
                "3. Год выпуска\n" +
                "4. Дату последней продажи\n" +
                "5. Разход топлива на (100км)\n" +
                "0. Ничего (вернуться в главное меню)");
    }

    @Override
    public void getMenu() {
        System.out.println("1. Получить авто по ID\n" +
                "2. Получить все авто из базы данных \n" +
                "3. Получить авто по марке\n" +
                "4. Получить все авто в диапазоне цены\n" +
                "0. Вернуться в главное меню");
    }

    public void notExistChose() {
        System.out.println("Такого варианта ответа нет");
    }

    public void autoIsNotExist() {
        System.out.println("Такого авто нет в базе");
    }


    public void deleteDataMenu() {
        System.out.println("1. Удалить авто по ID\n" +
                "2. Удалить все авто из базы данных \n" +
                "0. Вернуться в главное меню");
    }

    public void enterId() {
        System.out.println(" Пожалуйста введите ID Автомобиля который вам нужен");
    }

    public void enterTitle() {
        System.out.println("Введите марку автомобиля");
    }

    public void enterPriseToReadMenu(String str) {
        System.out.println("Введите " + str + " цену");
    }

    public void enterPrise() {
        System.out.println("Введите цену");
    }

    public void enterYearOfManufacture() {
        System.out.println("Введите дату выпуска автомобиля(в формате дд/мм/гг)");
    }

    public void enterLastSellingDate() {
        System.out.println("Введитиете дату последней продажи авто(в формате дд/мм/гг)");
    }

    public void enterFuelVolume() {
        System.out.println("Введите разход какой топлива на 100км");
    }


}
