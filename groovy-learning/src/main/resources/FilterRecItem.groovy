import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class FilterRecItem {

     String filter(String dataJson,String paramsJson){
         JsonSlurper jsonSlurper = new JsonSlurper();
         List<Map<String,String>> recItems = jsonSlurper.parseText(dataJson);
         Map<String,String> params = jsonSlurper.parseText(paramsJson);
        Iterator<Map<String,String>> iterator = recItems.iterator();
         String userPin = params.get("userPin");
        while (iterator.hasNext()){
            Map<String,String> recItem = iterator.next();
            if(recItem.get("id").equals(userPin)){
                iterator.remove();
            }
        }
        return JsonOutput.toJson(recItemData);
    }
}
