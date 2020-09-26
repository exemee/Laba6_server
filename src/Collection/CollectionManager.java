package Collection;

import BasicClasses.Organization;
import BasicClasses.Worker;

import java.lang.reflect.Array;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionManager {
    private static ArrayDeque<Worker> arrayDeque;
    private static ZonedDateTime creationDate;
    private static CollectionManager collectionManager;
    private List<String> res = new ArrayList<String>();

    public static ArrayDeque<Worker> getArrayDeque() {
        return arrayDeque;
    }

    public void initArray(){
        if(arrayDeque == null){
            arrayDeque = new ArrayDeque<>();
            creationDate = ZonedDateTime.now();
        }
    }

    public static CollectionManager getCollectionManager() {
        if (collectionManager == null) {
            collectionManager = new CollectionManager();
        }
        return collectionManager;
    }

    public void add(Worker worker){
        arrayDeque.add(worker);
    }

    public void removeById(Integer ID){
        arrayDeque.forEach(worker -> {
            if (worker.getId().equals(ID)) { arrayDeque.remove(worker); }
        });
    }

    public String show(){
        String info = arrayDeque
                .stream()
                .map(CollectionUtils::display)
                .collect(Collectors.joining(", ")    );
        if (info.equals("")) { info = "На данный момент коллекция пуста."; }
        return info;
    }

    public String info(){
        String info = "";
        info += "Тип коллекции – " + arrayDeque.getClass().getName() + "\n";
        info += "Дата инициализации коллекции – " + creationDate + "\n";
        info += "Количество элементов в коллекции – " + arrayDeque.size() + "\n";
        info += "_________________________________________________________\n";
        return info;
    }

    public void update(Worker workerToUpdate, Integer ID){
        arrayDeque.forEach( worker -> {
            if (worker.getId().equals(ID)) {
                worker.setName(workerToUpdate.getName());
                worker.setPosition(workerToUpdate.getPosition());
                worker.setStatus(workerToUpdate.getStatus());
                worker.setCoordinates(workerToUpdate.getCoordinates());
                worker.setOrganization(workerToUpdate.getOrganization());
            }
        });
    }

    public void clear(){
        arrayDeque.clear();
    }

    public String removeFirst(){
        if (arrayDeque.size() > 0) {
            arrayDeque.removeFirst();
            return "Удален первый элемент коллекции";}
        else { return "Коллекция пуста."; }
    }

    public String removeGreater(Worker worker){
        res.clear();
        arrayDeque.forEach(arrayWorker -> {
            if (arrayWorker.compareTo(worker) > 0) {
                appendToList(arrayWorker.getId().toString());
                arrayDeque.remove(arrayWorker);
            }
        });

        if (res.isEmpty()) return "Таких элементов не найдено";
        return "Из коллекции удалены элементы с ID: " + res.toString().replaceAll("[\\[\\]]", "");
    }

    public String addIfMax(Worker workerToAdd) {
        if (arrayDeque.size() > 0 ) {
            Worker worker = arrayDeque.stream().max(Comparator.comparing(Worker::getId)).get();
            if (workerToAdd.getId() > worker.getId()) {
                arrayDeque.add(workerToAdd);
                return "Элемент добавили в коллекцию.";
            } else return "Элемент не добавлен. Он меньше максимального.";
        } else return "Коллекция пуста.";
    }

    public static void addJsonObject(Worker worker) {
        worker.setId(IDGenerator.generateID(worker.getId()));
        arrayDeque.add(worker);
    }
    public void appendToList(String o){
        res.add(o);
    }

    public String count_greater_than_organization(Organization organizationToCompare) {
        int count = (int) CollectionManager.getArrayDeque().stream()
                .filter(worker -> worker.getOrganization()
                        .compareTo(organizationToCompare) > 0)
                .count();
        return "Количество элементов в коллекции превышающее заданный: " + count;
    }

    public String sumOfSalary() {
        double sumSalary = CollectionManager.getArrayDeque().stream().mapToDouble(Worker::getSalary).sum();
        return "Размер зарплаты всех работников: " + sumSalary;
    }

    public String minByName() {
        if (CollectionManager.getArrayDeque().size() > 0) {
            return CollectionUtils.display(CollectionManager.getArrayDeque().stream()
                    .min(Comparator.comparing(Worker::getName)).get());
        } else return "Коллекция пуста.";
    }

}
