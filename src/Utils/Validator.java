package Utils;

import BasicClasses.Organization;
import BasicClasses.Position;
import BasicClasses.Status;
import BasicClasses.Worker;

import java.util.Arrays;

public class Validator {
    public static boolean checkExistStatus(String text){
        return Arrays.stream(Status.values()).anyMatch((status -> status.name().equals(text)));
    }

    public static boolean checkExistPosition(String text){
        return Arrays.stream(Position.values()).anyMatch((status -> status.name().equals(text)));
    }
    public static boolean validateWorker(Worker worker) {
        return worker.getId() != null &&
                ( worker.getName() != null && !worker.getName().equals("")) &&
                worker.getCoordinates().getX() != null &&
                worker.getCoordinates().getY() != null &&
                worker.getSalary() > 0 &&
               validateOrganization(worker.getOrganization());
    }

    public static boolean validateOrganization(Organization organization){
         return organization.getAnnualTurnover() > 0 &&
                 organization.getEmployeesCount() > 0 &&
                 organization.getPostalAddress().getStreet() != null &&
                 organization.getPostalAddress().getZipCode() != null &&
                 organization.getPostalAddress().getTown().getX() != null &&
                 organization.getPostalAddress().getTown().getY() != null &&
                 organization.getPostalAddress().getTown().getZ() != null;
    }

}
