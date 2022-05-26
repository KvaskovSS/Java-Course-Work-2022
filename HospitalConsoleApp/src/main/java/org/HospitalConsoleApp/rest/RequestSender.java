package org.HospitalConsoleApp.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.HospitalConsoleApp.model.DiagnosisPostModel;
import org.HospitalConsoleApp.model.PeoplePostModel;
import org.HospitalConsoleApp.model.WardPostModel;
import org.HospitalConsoleApp.model.dialogModels.DiagnosisIdModel;
import org.HospitalConsoleApp.model.dialogModels.PeopleIdModel;
import org.HospitalConsoleApp.model.dialogModels.WardIdModel;
import org.HospitalConsoleApp.model.getModels.Diagnosis;
import org.HospitalConsoleApp.model.getModels.People;
import org.HospitalConsoleApp.model.getModels.Ward;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class RequestSender {
    private static String base64Credentials;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final String HOST_URL = "http://localhost:8080";
    private static final Scanner consoleIn = new Scanner(System.in);

    public static boolean authorize(String username, String password){
        try {
            String plainCreds = username + ":" + password;
            byte[] base64CredsBytes = Base64.getEncoder().encode(plainCreds.getBytes());
            setBase64Credentials(new String(base64CredsBytes));

            headers.add("Authorization", "Basic " + base64Credentials);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(HOST_URL + "/auth/login", HttpMethod.GET, request, String.class);

            System.out.println(response.getBody());
            return true;
        }catch (Exception exception){
            System.out.println("###INVALID CREDENTIALS###");
            return false;
        }
    }

    private static void printAllDiagnosis(List<Diagnosis> diagnosisList){
        System.out.println("---------------------------------All diagnosis---------------------------------");
        System.out.printf("%2s  %-20s", "№", "Name");
        System.out.println();

        for (int i = 0; i < diagnosisList.size();i++){
            System.out.printf("%2s. %-20s", i + 1, diagnosisList.get(i).getName());
            System.out.println();
        }
    }

    private static void printAllWards(List<Ward> wardList){
        System.out.println("---------------------------------All wards---------------------------------");
        System.out.printf("%2s  %-20s %-20s ", "№", "Name", "Max count");
        System.out.println();

        for(int i = 0; i < wardList.size();i++){
            System.out.printf("%2s  %-20s %-20s", i + 1, wardList.get(i).getName(), wardList.get(i).getMaxCount());
            System.out.println();
        }
    }

    private static void printAllPeople(List<People> peopleList){
        System.out.println("---------------------------------All people---------------------------------");
        System.out.printf("%2s  %-20s %-20s %-20s %-20s %-20s", "№", "First name", "Last name", "Pather name", "Ward name", "Diagnosis name");
        System.out.println();

        for(int i = 0; i < peopleList.size();i++){
            System.out.printf("%2s  %-20s %-20s %-20s %-20s %-20s", i + 1, peopleList.get(i).getFirstName(), peopleList.get(i).getLastName(), peopleList.get(i).getPatherName(), peopleList.get(i).getWardName(),peopleList.get(i).getDiagnosisName());
            System.out.println();
        }
    }



    public static void setBase64Credentials(String base64Credentials) {
        RequestSender.base64Credentials = base64Credentials;
    }

    public static String getBase64Credentials() {
        return base64Credentials;
    }

    private static void diagnosisListIdDialog(){

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/diagnosis/list", HttpMethod.GET, request, String.class);
        List<DiagnosisIdModel> diagnosisIdModels = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<DiagnosisIdModel>>(){}.getType());

        System.out.println("---------------------------------Diagnosis---------------------------------");
        System.out.printf("%2s     %-10s %-20s ", "№", "ID", "Name");
        System.out.println();

        for (int i = 0; i < diagnosisIdModels.size(); i++) {
            System.out.printf("%2d.    %-10d %-20s", i + 1, diagnosisIdModels.get(i).getId() ,diagnosisIdModels.get(i).getName());
            System.out.println();
        }
    }

    private static void wardListIdDialog(){
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/wards/list", HttpMethod.GET, request, String.class);
        List<WardIdModel> wardIdModels = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<WardIdModel>>(){}.getType());

        System.out.println("---------------------------------Wards---------------------------------");
        System.out.printf("%2s     %-10s %-20s %-20s", "№", "ID", "Name", "Max count");
        System.out.println();

        for (int i = 0; i < wardIdModels.size(); i++) {
            System.out.printf("%2d.    %-10d %-20s %-20s", i + 1, wardIdModels.get(i).getId() , wardIdModels.get(i).getName(), wardIdModels.get(i).getMaxCount());
            System.out.println();
        }
    }

    private static void peopleListIdDialog(){
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/people/list", HttpMethod.GET, request, String.class);
        List<PeopleIdModel> peopleIdModels = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<PeopleIdModel>>(){}.getType());

        System.out.println("---------------------------------All people---------------------------------");
        System.out.printf("%2s  %-20s %-20s %-20s %-20s %-20s %-20s", "№", "ID","First name", "Last name", "Pather name", "Ward name", "Diagnosis name");
        System.out.println();


        for(int i = 0; i < peopleIdModels.size(); i++){
            System.out.printf("%2s  %-20s %-20s %-20s %-20s %-20s %-20s ", i + 1, peopleIdModels.get(i).getId(),peopleIdModels.get(i).getFirstName(),peopleIdModels.get(i).getLastName(),peopleIdModels.get(i).getPatherName(), peopleIdModels.get(i).getWardName(), peopleIdModels.get(i).getDiagnosisName());
            System.out.println();
        }
    }

    public static void getDiagnosis(){
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/diagnosis/list", HttpMethod.GET, request, String.class);
        List<Diagnosis> diagnosisList = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<Diagnosis>>(){}.getType());

        if(diagnosisList.size() < 1){
            System.out.println("There are no diagnosis!");
            return;
        }

        printAllDiagnosis(diagnosisList);
    }

    public static void getWards(){
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/wards/list", HttpMethod.GET, request, String.class);
        List<Ward> wardList = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<Ward>>(){}.getType());

        if(wardList.size() < 1){
            System.out.println("There are no wards!");
            return;
        }

        printAllWards(wardList);
    }

    public static void getPeople(){
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/people/list", HttpMethod.GET, request, String.class);
        List<People> peopleList = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<People>>(){}.getType());

        if(peopleList.size() < 1){
            System.out.println("There are no people!");
            return;
        }

        printAllPeople(peopleList);
    }

    public static void addDiagnosis(){
        try {
            System.out.println("Adding new diagnosis");
            System.out.print("Name of diagnosis : ");
            String name = consoleIn.next();

            DiagnosisPostModel diagnosisPostModel = new DiagnosisPostModel(name);
            String json =GSON.toJson(diagnosisPostModel);

            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(json, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(HOST_URL + "/diagnosis/addDiagnosis",  request, String.class);
            System.out.println("Diagnosis added");
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void addWard(){
        try {
            System.out.println("Adding new ward");
            System.out.print("Name of ward : ");
            String name = consoleIn.next();
            System.out.print("Max count of patients in ward : ");
            Integer maxCount = consoleIn.nextInt();

            WardPostModel wardPostModel = new WardPostModel(name,maxCount);
            String json = GSON.toJson(wardPostModel);

            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(json, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(HOST_URL + "/wards/addWard",  request, String.class);
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void addPatient(){
        try {
            System.out.println("Adding patient");
            System.out.print("First name : ");
            String firstName = consoleIn.next();
            System.out.print("Last name : ");
            String lastName = consoleIn.next();
            System.out.print("Pather name : ");
            String patherName = consoleIn.next();

            diagnosisListIdDialog();
            System.out.print("Choose diagnosis's ID to be assigned : ");
            long diagnosisId = consoleIn.nextLong();

            wardListIdDialog();
            System.out.print("Choose ward's Id to be assigned  : ");
            long wardId = consoleIn.nextLong();

            PeoplePostModel peoplePostModel = new PeoplePostModel(firstName,lastName,patherName);
            String json = GSON.toJson(peoplePostModel);

            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(json, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(HOST_URL + "/people/addPeople?diagId=" + diagnosisId + "&wardId=" + wardId, request, String.class);
            System.out.println(responseEntity);


        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    //Searching funcs
    public static void findPatientByFirstName(){
        try {
            System.out.println("Enter patient's first name you want find to : ");
            String firstName = consoleIn.next();

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/people/findByFirstName?firstName=" + firstName, HttpMethod.GET, request, String.class);
            System.out.println(responseEntity);
            List<People> peopleList = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<People>>(){}.getType());

            printAllPeople(peopleList);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void findPatientByLastName(){
        try {
            System.out.println("Enter patient's last name you want find to : ");
            String lastName = consoleIn.next();

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/people/findByLastName?lastName=" + lastName, HttpMethod.GET, request, String.class);
            List<People> peopleList = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<People>>(){}.getType());

            printAllPeople(peopleList);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void findPatientByPatherName(){
        try {
            System.out.println("Enter patient's pather name you want find to : ");
            String patherName = consoleIn.next();

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/people/findByPatherName?patherName=" + patherName, HttpMethod.GET, request, String.class);
            List<People> peopleList = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<People>>(){}.getType());

            printAllPeople(peopleList);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void findPatientByDiagnosisName(){
        try {
            System.out.println("Search patient's by diagnosis name");
            diagnosisListIdDialog();
            System.out.println("Choose name of diagnosis : ");
            String diagName = consoleIn.next();

            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/people/findByDiagnosis?diagName=" + diagName, HttpMethod.GET, request, String.class);
            List<People> peopleList = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<People>>(){}.getType());
            printAllPeople(peopleList);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void findPatientByWardName(){
        try {
            System.out.println("Search patient's by ward name");
            wardListIdDialog();
            System.out.println("Choose name of ward : ");
            String wardName = consoleIn.next();
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(HOST_URL + "/people/findByWard?wardName=" + wardName, HttpMethod.GET, request, String.class);
            List<People> peopleList = GSON.fromJson(responseEntity.getBody(), new TypeToken<List<People>>(){}.getType());
            printAllPeople(peopleList);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }


    //Editing func
    public static void editDiagnosis(){
        try {
            System.out.println("Editing diagnosis");
            diagnosisListIdDialog();
            System.out.println("Chose diagnosis's ID you want edit to : ");
            long diagId = consoleIn.nextLong();

            System.out.println("Enter new details");
            System.out.print("Name of diagnosis : ");
            String name = consoleIn.next();

            Diagnosis diagnosis = new Diagnosis(name);
            String json = GSON.toJson(diagnosis);

            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(json, headers);
            restTemplate.exchange(HOST_URL + "/diagnosis/edit/" + diagId, HttpMethod.PUT, request, String.class);

            System.out.println("Edited successfully!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void editWard() {
        try {
            System.out.println("Editing ward");
            wardListIdDialog();
            System.out.println("Chose ward's ID you want edit to : ");
            long wardId = consoleIn.nextLong();

            System.out.println("Enter new details");
            System.out.print("Name of ward : ");
            String name = consoleIn.next();
            System.out.print("Max count of patients in ward : ");
            int maxCount = consoleIn.nextInt();

            Ward ward = new Ward(name, maxCount);
            String json = GSON.toJson(ward);

            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(json, headers);
            restTemplate.exchange(HOST_URL + "/wards/edit/" + wardId, HttpMethod.PUT, request, String.class);
            System.out.println("Edited successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editPatient(){
        try {
            System.out.println("Editing patient");
            peopleListIdDialog();
            System.out.println("Chose patient's ID you want edit to : ");
            long peopleId = consoleIn.nextLong();

            System.out.println("Enter new details");
            System.out.print("First name : ");
            String firstName = consoleIn.next();
            System.out.print("Last name : ");
            String lastName = consoleIn.next();
            System.out.print("Pather name : ");
            String patherName = consoleIn.next();

            PeoplePostModel people = new PeoplePostModel(firstName, lastName, patherName);
            String json = GSON.toJson(people);

            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(json, headers);
            restTemplate.exchange(HOST_URL + "/people/edit/" + peopleId, HttpMethod.PUT, request, String.class);
            System.out.println("Edited successfully!");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //deleting func
    public static void deleteDiagnosis(){
        try {
            System.out.println("Deleting diagnosis");
            diagnosisListIdDialog();
            System.out.println("Chose diagnosis Id you want delete to : ");
            long diagId = consoleIn.nextLong();

            HttpEntity<String> request = new HttpEntity<>(headers);
            restTemplate.exchange(HOST_URL + "/diagnosis/" + diagId, HttpMethod.DELETE, request, String.class);

            System.out.println("Diagnosis deleted successfully!");
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void deleteWard(){
        try {
            System.out.println("Deleting ward");
            wardListIdDialog();
            System.out.println("Chose ward's Id you want delete to : ");
            long wardId = consoleIn.nextLong();

            HttpEntity<String> request = new HttpEntity<>(headers);
            restTemplate.exchange(HOST_URL + "/wards/" + wardId, HttpMethod.DELETE, request, String.class);

            System.out.println("Ward deleted successfully!");
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static void deletePatient(){
        try {
            System.out.println("Deleting patient");
            peopleListIdDialog();
            System.out.println("Chose patient's Id you want delete to : ");
            long peopleId = consoleIn.nextLong();

            HttpEntity<String> request = new HttpEntity<>(headers);
            restTemplate.exchange(HOST_URL + "/people/" + peopleId, HttpMethod.DELETE, request, String.class);

            System.out.println("Patient deleted successfully!");
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}

