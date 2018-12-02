package com.example.demotravel.service.impl;

import com.example.demotravel.service.UserService;
import com.example.demotravel.dataobject.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private RestTemplate restTemplate;



    @Override
    public User getById(String id) {
        String apiURL="http://120.79.142.173:8080/Entity/U190df2ec26f589/travelling/Test/"+id;
        User user=restTemplate.getForObject(apiURL,User.class);
        return user;
    }



    @Override
    public List<User> getByProperty(String property,String value){
        String apiURL="http://120.79.142.173:8080/Entity/U190df2ec26f589/travelling/Test/?Test."+property+"="+value;
      //  System.out.println("*************"+apiURL);
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(apiURL,String.class);
        String jsonstr=responseEntity.getBody();
        JSONObject pa=JSONObject.fromObject(jsonstr);
        jsonstr=pa.getString("Test");
        List<User> userList=(List<User>) JSONArray.toList(JSONArray.fromObject(jsonstr), User.class);
        return userList;
    }

    @Override
    public boolean save(User user) {
        String apiURL="http://120.79.142.173:8080/Entity/U190df2ec26f589/travelling/Test/";
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(apiURL, user, User.class);
        if(responseEntity!=null)
            return true;
        else
        return false;
    }

    @Override
    public void delete(String id) {
        String apiURL="http://120.79.142.173:8080/Entity/U190df2ec26f589/travelling/Test/"+id;
        restTemplate.delete(apiURL);
    }

    @Override
    public void put(String id, User user) {
        String apiURL="http://120.79.142.173:8080/Entity/U190df2ec26f589/travelling/Test/"+id;
        restTemplate.put(apiURL,user);
    }
}
