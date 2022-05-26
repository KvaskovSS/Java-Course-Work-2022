package org.HospitalConsoleApp;

import java.util.Scanner;

import static org.HospitalConsoleApp.rest.RequestSender.*;

public class Main {
    public Main(){

    }

    public static void main(String[] args){
        login();

        Scanner input = new Scanner(System.in);

        while (true){
            try{

                System.out.print("\n>> ");
                String command = input.nextLine();

                switch (command) {
                    case "patient"             -> getPeople();
                    case "diagnosis"           -> getDiagnosis();
                    case "ward"                -> getWards();
                    case "add patient"         -> addPatient();
                    case "add diagnosis"       -> addDiagnosis();
                    case "add ward"            -> addWard();
                    case "find patient by first name" -> findPatientByFirstName();
                    case "find patient by last name" -> findPatientByLastName();
                    case "find patient by pather name" -> findPatientByPatherName();
                    case "find patient by diagnosis name" -> findPatientByDiagnosisName();
                    case "find patient by ward name" -> findPatientByWardName();
                    case "edit patient"         -> editPatient();
                    case "edit diagnosis"           -> editDiagnosis();
                    case "edit ward"      -> editWard();
                    case "delete patient"       -> deletePatient();
                    case "delete diagnosis"         -> deleteDiagnosis();
                    case "delete ward"         -> deleteWard();
                    case "help"                -> help();
                    case "exit"                -> System.exit(0);
                    default -> System.out.println("Unknown command!\nType \"help\" to see commands' list");
                }


            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


    public static void login(){
        boolean loggedIn = false;
        while (!loggedIn) {
            Scanner in = new Scanner(System.in);
            System.out.println("----------AUTHORIZATION----------");
            System.out.print("Username: ");
            String username = in.next();
            System.out.print("Password: ");
            String password = in.next();

            loggedIn = authorize(username, password);
        }

    }

    public static void help(){
        System.out.println("---------------------------COMMANDS LIST---------------------------");
        System.out.println("patient - list patient");
        System.out.println("diagnosis - list diagnosis");
        System.out.println("ward - list wards");
        System.out.println("add patient - add patient");
        System.out.println("add diagnosis - add diagnosis ");
        System.out.println("add ward - add ward");
        System.out.println("find patient by first name - find patients by first name");
        System.out.println("find patient by lsat name - find patients by last name");
        System.out.println("find patient by pather name - find patients by pather name");
        System.out.println("find patient by diagnosis name - find patients by diagnosis name");
        System.out.println("find patient by ward name - find patients by ward name");
        System.out.println("edit patient - edit patient by ID");
        System.out.println("edit diagnosis - edit diagnosis by ID");
        System.out.println("edit ward - edit ward by ID");
        System.out.println("delete patient - delete patient by ID");
        System.out.println("delete diagnosis - delete diagnosis by ID");
        System.out.println("delete ward - delete ward by ID");
        System.out.println("exit - exit program");
        System.out.println("-------------------------------------------------------------------");
    }
}