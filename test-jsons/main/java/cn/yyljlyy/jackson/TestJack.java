package cn.yyljlyy.jackson;

import cn.yyljlyy.bean.CommonBean;
import cn.yyljlyy.bean.Group;
import com.alibaba.fastjson.JSON;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by j on 2015/4/2.
 */
public class TestJack implements Runnable{
    int times;
    int next;
    Group group;
    public TestJack(int n,int t){
        next=n;
        times=t;
    }
    private void buildJsonString() {
        Group group = new Group();
        for (int i = 0; i < times; i++) {
            CommonBean commonBean = new CommonBean();
            commonBean.setId(i);
            commonBean.setAddress("adrees" + i);
            commonBean.setDoubleValue(i);
            commonBean.setTelephone((long) 11111111 + i);
            commonBean.setName("test" + i);
            commonBean.setVendor("class" + i);
            commonBean.setImageUrl("img/class.png" + i);
            commonBean.setCreator("niles" + i);
            commonBean.setInfo("Test user in group" + i);
            group.addcommonBean(commonBean);
        }
    }

    public void testJackson(){
        try {
            System.err.println("start compare...");

            ObjectMapper mapper = new ObjectMapper();

            long t0 = 0;
            long t1 = System.currentTimeMillis();
            String str = null;

            t0 = t1;
            str = JSON.toJSONString(group);
            t1 = System.currentTimeMillis();
            System.out.println("fastjson serialize object:" + (t1 - t0));

            t0 = t1;
            Group group0 = JSON.parseObject(str, Group.class);
            t1 = System.currentTimeMillis();
            System.out.println("fastjson parse object:" + (t1 - t0));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0;i<next;i++){
            buildJsonString();
            testJackson();
        }
    }
}
