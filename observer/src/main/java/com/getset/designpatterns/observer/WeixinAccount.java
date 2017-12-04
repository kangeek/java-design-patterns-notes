package com.getset.designpatterns.observer;

public class WeixinAccount implements Subscriber {
    private String accountName;

    public WeixinAccount(String accountName) {
        this.accountName = accountName;
    }

    public void update(Publisher publisher) {
        System.out.println(accountName + "的微信收到了来自" + publisher + "的推送文章： " + publisher.getMessage());
    }
}
