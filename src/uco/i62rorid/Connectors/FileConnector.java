package uco.i62rorid.Connectors;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class FileConnector {
    private String path;

    public FileConnector(String filepath){
        this.path = filepath;

        try {
            File file = new File(this.path);
            if (!file.exists()) {
                file.createNewFile();
                //System.out.println("File created: " + file.getName());
            } else {
                //System.out.println("File " + file.getName() + " already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private Integer getIdFromLine(String line){
        return Integer.parseInt(line.substring(line.indexOf(':')+1, line.indexOf(',')));
    }

    public LinkedList<String> readAll(){
        LinkedList<String> list = new LinkedList<>();
        try {
            File file = new File(this.path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                list.add(reader.nextLine().trim());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;
    }

    public String read(int id){
        try {
            File file = new File(this.path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (getIdFromLine(line) == id){
                    reader.close();
                    return line.trim();
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "";
    }

    public LinkedList<String> getLineByField(String key, String value){
        try {
            LinkedList<String> list = new LinkedList<>();
            File file = new File(this.path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if(line.contains(key)){
                    int fieldSeparator = line.indexOf(key)+key.length();
                    int fieldDeliminator;
                    if (line.substring(fieldSeparator).contains(",")){
                        fieldDeliminator = line.indexOf(',',fieldSeparator);
                    }else{
                        fieldDeliminator = line.length()-1;
                    }
                    String readValue = line.substring(fieldSeparator+1,fieldDeliminator);
                    if (readValue.equals(value)){
                        list.add(line.trim());
                    }
                }
            }
            reader.close();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

    public LinkedList<String> getLineByFieldLike(String key, String value){
        try {
            LinkedList<String> list = new LinkedList<>();
            File file = new File(this.path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if(line.contains(key)){
                    int fieldSeparator = line.indexOf(key)+key.length();
                    int fieldDeliminator;
                    if (line.substring(fieldSeparator).contains(",")){
                        fieldDeliminator = line.indexOf(',',fieldSeparator);
                    }else{
                        fieldDeliminator = line.length()-1;
                    }
                    String readValue = line.substring(fieldSeparator+1,fieldDeliminator);
                    if (readValue.contains(value)){
                        list.add(line.trim());
                    }
                }
            }
            reader.close();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

    public Boolean append(String line){
        try {
            FileWriter writer = new FileWriter(this.path, true);
            writer.write(line+'\n');
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public Boolean update(String line){
        try {
            File inFile = new File(this.path);
            File tmpFile = new File("tmpfile.dat");

            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile));

            int id = getIdFromLine(line);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (id == getIdFromLine(trimmedLine)) {
                    //System.out.println("UPDATING");
                    writer.write(line + '\n');
                    continue;
                }
                writer.write(currentLine + '\n');
            }
            writer.close();
            reader.close();
            return tmpFile.renameTo(inFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public Boolean delete(int id){
        try {
            File inFile = new File(this.path);
            File tmpFile = new File("tmpfile.dat");

            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile));

            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if(id == getIdFromLine(trimmedLine)) continue;
                writer.write(currentLine + '\n');
            }
            writer.close();
            reader.close();
            return tmpFile.renameTo(inFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }
}
