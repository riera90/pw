package uco.i62rorid.Utils;

import java.util.LinkedList;

public class JSONParser {
    private static final Integer NOT_A_JSON = 1;
    private static final Integer INCORRECT_TYPE = 2;
    private static final Integer EMPTY_JSON = 3;
    private static final Integer END_OF_JSON = 4;
    private static final Integer END_OF_JSON_PASSED = 5;
    private String JSONString;
    private Integer error;
    private Integer fieldStartIndex;
    private Integer fieldEndIndex;
    private Integer keyValueSeparator;

    public JSONParser(String JSON) {
        this.error = 0;
        this.fieldStartIndex = 0;
        this.fieldEndIndex = 0;
        this.keyValueSeparator = 0;
        if ((!JSON.startsWith("{")) || (!JSON.endsWith("}")))
        {
            this.JSONString = "";
            this.error = NOT_A_JSON;
            return;
        }
        this.JSONString = JSON.substring(JSON.indexOf('{')+1, JSON.lastIndexOf('}'));
        if (this.JSONString.charAt(0)==',')
            this.JSONString = JSON.substring(1);
    }

    public Integer getError(){
        return this.error;
    }

    public String getKey(){
        if (this.JSONString.charAt(fieldStartIndex) == ',')
            return this.JSONString.substring(fieldStartIndex+1, this.keyValueSeparator);
        return this.JSONString.substring(fieldStartIndex, this.keyValueSeparator);
    }

    public Integer getValueAsInt(){
        return Integer.parseInt(getValue());
    }

    public String getValueAsString(){
        return this.JSONString.substring(keyValueSeparator+2, fieldEndIndex-1);
    }

    public String getValue(){
        return this.JSONString.substring(keyValueSeparator+1, fieldEndIndex);
    }

    public Boolean getValueAsBoolean() {
        return Boolean.valueOf(getValue());
    }

    public LinkedList<Integer> getValueAsIntegerLinkedList() {
        String value = this.getValue();
        value = value.substring(1,value.length()-1);
        LinkedList<Integer> list = new LinkedList<>();
        if (value.length() > 0) {
            if (value.contains(", ")) {
                for (String element : value.split(", ")) {
                    list.add(Integer.parseInt(element));
                }
            }else{
                list.add(Integer.parseInt(value));
            }
        }
        return list;
    }

    private Integer getNextFieldSeparatorIndex(){
        //System.out.println(this.JSONString);
        //System.out.println("0123456789012345678901234567890");
        //System.out.println("          1         2         3");
        int separatorIndex = this.fieldEndIndex+1;
        for (;separatorIndex < this.JSONString.length(); separatorIndex++){
            //System.out.println("evaluating "+separatorIndex);
            if(this.JSONString.charAt(separatorIndex)=='"'){
                //System.out.println("\" found, jumping");
                separatorIndex=this.JSONString.indexOf('"',separatorIndex+1);
                continue;
            }
            if(this.JSONString.charAt(separatorIndex)=='['){
                //System.out.println("[ found, jumping");
                separatorIndex=this.JSONString.indexOf(']',separatorIndex+1);
                continue;
            }
            if (this.JSONString.charAt(separatorIndex)==',') {
                //System.out.println("separator: " + separatorIndex + "->" + this.JSONString.charAt(separatorIndex));
                if (!this.JSONString.substring(this.fieldEndIndex, separatorIndex).contains(":"))
                {
                    this.error=END_OF_JSON_PASSED;
                    //System.out.println("END_OF_JSON_PASSED");
                    return -1;
                }
                return separatorIndex;
            }
        }
        this.error=END_OF_JSON;
        //System.out.println("END_OF_JSON");
        if (!this.JSONString.substring(this.fieldEndIndex, separatorIndex).contains(":"))
        {
            this.error=END_OF_JSON_PASSED;
            //System.out.println("END_OF_JSON_PASSED");
            return -1;
        }
        return separatorIndex;
    }

    public Boolean gotoNextField(){
        this.fieldStartIndex = this.fieldEndIndex;
        int fieldSeparatorIndex = getNextFieldSeparatorIndex();
        if (fieldSeparatorIndex == -1)
            return false;

        this.keyValueSeparator = this.JSONString.indexOf(':', fieldStartIndex);
        this.fieldEndIndex = fieldSeparatorIndex;

        return true;
    }
}
