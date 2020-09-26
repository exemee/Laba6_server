package Collection;

import BasicClasses.Worker;

public class CollectionUtils {

    public static boolean checkExist(Integer ID) {
        for (Worker worker:CollectionManager.getArrayDeque()) {
            if(worker.getId().equals(ID)) return true;
        }
        return false;
    }

    static String display(Worker worker){
        String info = "";
        info = "ID элемента коллекции - " + worker.getId() + "\n" + "Имя рабочего - " + worker.getName() + "\n" + "Должность рабочего - " + worker.getPosition() + "\n" + "Статус рабочего - " + worker.getStatus() + "\n" + "Координата x рабочего - " + worker.getCoordinates().getX() + "\n" + "Координата y рабочего - " + worker.getCoordinates().getY() + "\n" + "Имя организации - " + worker.getOrganization().getFullName() + "\n" + "Годовой оборот организации - " + worker.getOrganization().getAnnualTurnover() + "\n" + "Количество сотрудников организации - " + worker.getOrganization().getEmployeesCount() + "\n" + "Количество сотрудников организации - " + worker.getOrganization().getEmployeesCount() + "\n" + "Улица организации - " + worker.getOrganization().getPostalAddress().getStreet() + "\n" + "Почтовый индекс организации - " + worker.getOrganization().getPostalAddress().getZipCode() + "\n" + "Координата X организации в городе - " + worker.getOrganization().getPostalAddress().getTown().getX() + "\n" + "Координата Y организации в городе - " + worker.getOrganization().getPostalAddress().getTown().getY() + "\n" + "Координата Z организации в городе - " + worker.getOrganization().getPostalAddress().getTown().getZ() + "\n" + "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n";
        return info;
    }
}
