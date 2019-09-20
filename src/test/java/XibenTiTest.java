import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@Feature("我是feature XibenTiTest")
public class XibenTiTest {

    @BeforeMethod
    public static void setUp(){
        RestAssured.baseURI ="http://172.18.89.11";
    }

    @Test(description = "查询所有的实体词")
    @Story("story-查询所有的实体词1")
    @Description("主要测试shitici")
    public void test实体词(){
        JSONObject body=new JSONObject();
        body.put("pageNo",1);
        body.put("pageSize",1000);
        body.put("queryStr","");
        body.put("superClassId","思想概念");

        String content=given().contentType("application/json").body(body).when().post("/refining/rest/ont/pageIndividualByClass").
                then().extract().response().body().asString();
       // System.out.println(content);
               // body("statusCode",equalTo(200)).log().all().body("data.totalHits",equalTo(518));
        List<String> nameList = (List<String>) JSONPath.read(content, "$.data.source[*].individualName");
        for(String oname:nameList){
            System.out.println(oname);
            given().log().all().contentType("application/json").queryParam("name",oname).when().get("/qa/rest/qa/getGraphByName").
                    then().body("nodes.id",hasItem(oname)).log().all();
        }

    }

    @Test
    @Story("story-hshahhh")
    @Description("主要测试hhhhhh")
    public void test2(){
        System.out.println("hahahaha");
    }
}
