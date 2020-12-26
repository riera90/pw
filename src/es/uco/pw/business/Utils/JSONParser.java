package es.uco.pw.business.Utils;

import com.mysql.jdbc.ResultSet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * The type Json parser.
 */
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

    /**
     * Instantiates a new Json parser.
     *
     * @param JSON the json
     */
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

    /**
     * Get error integer.
     *
     * @return the integer
     */
    public Integer getError(){
        return this.error;
    }

    /**
     * Get key string.
     *
     * @return the string
     */
    public String getKey(){
        if (this.JSONString.charAt(fieldStartIndex) == ',')
            return this.JSONString.substring(fieldStartIndex+1, this.keyValueSeparator);
        return this.JSONString.substring(fieldStartIndex, this.keyValueSeparator);
    }

    /**
     * Get value as int integer.
     *
     * @return the integer
     */
    public Integer getValueAsInt(){
        return Integer.parseInt(getValue());
    }

    /**
     * Get value as date date.
     *
     * @return the date
     */
    public Date getValueAsDate(){
        try {
            return new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(getValue());
        }catch (ParseException e){
            return null;
        }
    }

    /**
     * Get value as string string.
     *
     * @return the string
     */
    public String getValueAsString(){
        return this.JSONString.substring(keyValueSeparator+2, fieldEndIndex-1);
    }

    /**
     * Get value string.
     *
     * @return the string
     */
    public String getValue(){
        return this.JSONString.substring(keyValueSeparator+1, fieldEndIndex);
    }

    /**
     * Gets value as boolean.
     *
     * @return the value as boolean
     */
    public Boolean getValueAsBoolean() {
        return Boolean.valueOf(getValue());
    }

    /**
     * Gets value as integer linked list.
     *
     * @return the value as integer linked list
     */
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
        int separatorIndex = this.fieldEndIndex+1;
        for (;separatorIndex < this.JSONString.length(); separatorIndex++){
            if(this.JSONString.charAt(separatorIndex)=='"'){
                separatorIndex=this.JSONString.indexOf('"',separatorIndex+1);
                continue;
            }
            if(this.JSONString.charAt(separatorIndex)=='['){
                separatorIndex=this.JSONString.indexOf(']',separatorIndex+1);
                continue;
            }
            if (this.JSONString.charAt(separatorIndex)==',') {
                if (!this.JSONString.substring(this.fieldEndIndex, separatorIndex).contains(":"))
                {
                    this.error=END_OF_JSON_PASSED;
                    return -1;
                }
                return separatorIndex;
            }
        }
        this.error=END_OF_JSON;
        if (!this.JSONString.substring(this.fieldEndIndex, separatorIndex).contains(":"))
        {
            this.error=END_OF_JSON_PASSED;
            return -1;
        }
        return separatorIndex;
    }

    /**
     * Goto next field boolean.
     *
     * @return the boolean
     */
    public Boolean gotoNextField(){
        this.fieldStartIndex = this.fieldEndIndex;
        int fieldSeparatorIndex = getNextFieldSeparatorIndex();
        if (fieldSeparatorIndex == -1)
            return false;

        this.keyValueSeparator = this.JSONString.indexOf(':', fieldStartIndex);
        this.fieldEndIndex = fieldSeparatorIndex;

        return true;
    }

    /**
     * Get date as string string.
     *
     * @param date the date
     * @return the string
     */
    public static String getDateAsString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
        return dateFormat.format(date);
    }

    /**
     * Get local now date.
     *
     * @return the date
     */
    public static Date getNow(){
        return new Date();
    }
}
