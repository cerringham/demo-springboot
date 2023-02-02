package it.proactivity.demospringboot.json;

import it.proactivity.demospringboot.dto.CompanyDto;
import org.json.JSONArray;
import org.json.JSONObject;

public class DemoJson {

    public static void main(String[] args) {
        JSONObject myObject = createJsonObject();
        System.out.println(myObject);

        myObject = fromStringToJSONObject(createString());
        System.out.println(myObject);

        myObject = fromCompanyDTOToJSONObject();
        System.out.println(myObject);
    }

    private static JSONObject createJsonObject() {
        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", 22);
        jo.put("city", "chicago");

        JSONObject anotherJo = new JSONObject();
        anotherJo.put("booleanValue", Boolean.TRUE);
        jo.put("joBooleanVale", anotherJo);

        return jo;
    }

    private static JSONObject fromStringToJSONObject(String aString) {
        return new JSONObject(aString);
    }

    /*
    {
        "name": "firt post project",
            "endDate": "2023-12-31",
            "reportingId": "123456"
    }
    */
    private static String createString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append( "\"name\": \"firt post project\",");
        sb.append( "\"endDate\": \"2023-12-31\"");
        sb.append("}");

        return sb.toString();
    }

    private static JSONObject fromCompanyDTOToJSONObject() {
        CompanyDto companyDto = new CompanyDto(12l, "company name");
        return new JSONObject(companyDto);
    }
}
