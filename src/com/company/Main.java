package com.company;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, Exception {
        Locale.setDefault(Locale.US);

        firstTask();//базовый,Вывести данные о пластинках, тираж которых превышает 10000 экземпляров.
         //secondTask();//средний,Вывести сведения о работниках, срок действия контракта которых истекает в течении 5-ти дней.

    }
    public static void firstTask() throws FileNotFoundException {

        boolean state = true;
        ArrayList<VinylRecord> catalog = new ArrayList<>();
        VinylRecords catalogX = new VinylRecords();
        catalogX.kryoLoad("file.bin");
       // catalogX.load("file.bin");
        catalog=catalogX.list;
        do {


            System.out.println("input a data\n" +
                    "string genre;\n" +
                    "string albumTitle;\n" +
                    "integer copies;");
            Scanner consoleinput = new Scanner(System.in);
            VinylRecord record = new VinylRecord();
            try {
                if (record.trySetArtist(consoleinput.next()) &&
                        record.trySetGenre(consoleinput.next()) &&
                        record.trySetAlbumTitle(consoleinput.next())
                        && record.trySetCopies(consoleinput.nextInt())) {
                    state = false;
                    catalog.add(record);
                    System.out.println("record is added");
                }
            } catch (Exception t) {
                System.out.println("record is not added");
                consoleinput.next();//clear buffer)))
            }

            boolean checkChoice = true;
            do {
                System.out.println("add records? yes or no");
                String temp = consoleinput.next();
                if (temp.equals("yes".intern())) {
                    state = true;
                    checkChoice = false;
                }
                if (temp.equals("no".intern())) checkChoice = state = false;

            } while (checkChoice);


        } while (state);
        for (VinylRecord temp : catalog) {
            if (temp.getCopies() > 10000) {
                System.out.println(temp.getArtist());
                System.out.println(temp.getGenre());
                System.out.println(temp.getAlbumTitle());
                System.out.println(temp.getCopies());
                catalogX.list.add(temp);
            }
        }
        catalogX.kryoSave("file.bin");
        //catalogX.save(catalogX.list,"file.bin");
    }
    public static void secondTask() throws FileNotFoundException {
        boolean state=true;
        ArrayList<Human> timetable = new ArrayList<>();
        Humans timetableX = new Humans();
       //timetables.load("file.bin");
        //timetables.kryoLoad("file.bin");
do {
    Human human = new Human();
    System.out.println("Date input in format 2002-05-14\n" +
            "String lastName;\n" +
            "     String position;\n" +
            "     LocalDate dateSignContract;\n" +
            "     LocalDate termContract;\n" +
            "     int salary;");
    Scanner consoleinput = new Scanner(System.in);
          try {
              if(human.trySetLastName(consoleinput.next())&&
                      human.trySetPosition(consoleinput.next())&&
                      human.trySetDateSignContract(consoleinput.next())&&
                      human.trySetTermContract(consoleinput.next())&&
                      human.getTermContract().isAfter(human.getDateSignContract())&&
                      human.trySetSalary(consoleinput.nextInt()))
              {
                  timetable.add(human);
                  state = false;
                  System.out.println("record added");
              }
              else
              {
                  state = true;
                  System.out.println("record is not added");
              }
          }catch (Exception ex)
          {
              state = true;
              System.out.println("record is not added");
          }

    boolean checkChoice = true;
    do {
        System.out.println("add records? yes or no");
        String temp = consoleinput.next();
        if (temp.equals("yes".intern())) {
            state = true;
            checkChoice = false;
        }
        if (temp.equals("no".intern())) checkChoice = state = false;

    } while (checkChoice);

}while (state);
        for (Human temp:timetable) {
            System.out.println(ChronoUnit.DAYS.between(temp.getTermContract(), temp.getDateSignContract()));//negative number????
            if(Math.abs(ChronoUnit.DAYS.between(temp.getTermContract(), LocalDate.now()))<=5)
            {
                System.out.println(temp.getLastName());
                System.out.println(temp.getPosition());
                System.out.println(temp.getDateSignContract());
                System.out.println(temp.getTermContract());
                System.out.println(temp.getSalary());
                timetableX.list.add(temp);
            }

        }
timetableX.kryoSave("file.bin");
        timetableX.save(timetableX.list,"file.bin");
    }

}
