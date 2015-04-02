package cn.yyljlyy.bean;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by j on 2015/4/2.
 */
public class Group {

    private Map<String, CommonBean> commonBeans = new Hashtable<String, CommonBean>();

    private Date updateTime = null;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Map<String, CommonBean> getcommonBeans() {
        return commonBeans;
    }

    public void setcommonBeans(Map<String, CommonBean> commonBeans) {
        this.commonBeans = commonBeans;
    }

    public void addcommonBean(CommonBean CommonBean) {
        commonBeans.put(CommonBean.getName(), CommonBean);
    }

    public CommonBean getcommonBean(String name) {
        return commonBeans.get(name);
    }
}
