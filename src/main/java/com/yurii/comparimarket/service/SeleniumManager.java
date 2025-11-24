package com.yurii.comparimarket.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SeleniumManager {
    ChromeOptions co;
    WebDriver driver;
    public SeleniumManager(){
        co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        co.addArguments("--headless");
        driver = new ChromeDriver(co);
    }
    public List<String> getAttributes(Element webElement){
        List<String> output = new ArrayList<>();
        String text;
        text = webElement.child(3).getElementsByAttribute("title").attr("title");
        if(text.equals("")){
            text = webElement.child(4).getElementsByAttribute("title").attr("title");
            output.add(text);

            text = webElement.child(4).getElementsByAttribute("title").attr("href");
            output.add(text);
            try {
                text = webElement.child(6).child(1).text();
            }catch (IndexOutOfBoundsException e){
                try {
                    text = webElement.child(6).text();
                }catch (IndexOutOfBoundsException a){
                    return null;
                }
            }

            output.add(text);
        }else{
            output.add(text);
            text = webElement.child(3).getElementsByAttribute("title").attr("href");
            output.add(text);
            try {
                text = webElement.child(5).child(1).text();
            }catch (IndexOutOfBoundsException e){
                try {
                    text = webElement.child(5).text();
                }catch (IndexOutOfBoundsException a){
                    return null;
                }
            }
            output.add(text);
        }

        return output;
    }

    public List<String> getFromSilpo(String item){
        driver.get("https://silpo.ua/search?find="+item);

        Document doc = Jsoup.parse(driver.getPageSource());
        Elements elements = doc.getElementsByAttributeValue("class","products-list ng-star-inserted");        

        List<String> outputAttributes = new ArrayList<>();
        String itemName = elements.get(0).getElementsByAttributeValue("class","product-card__title").text();
        String itemPrice = elements.get(0).getElementsByAttributeValue("class","product-card-price__displayPrice").text();
        outputAttributes.add(itemName);
        outputAttributes.add(itemPrice);
        return outputAttributes;
    }
    
    public List<String> getFromATB(String item){
        driver.get("https://www.atbmarket.com/sch?lang=uk&location=1154&query="+item+"&sort=price");

        Document doc = Jsoup.parse(driver.getPageSource());
        Elements elements = doc.getElementsByAttributeValue("class","\r\n"
        		+ "            catalog-item\r\n"
        		+ "    js-product-container\r\n"
        		+ "                        \r\n"
        		+ "    \r\n"
        		+ "");        

        List<String> outputAttributes = new ArrayList<>();
        String itemName = elements.get(0).getElementsByAttributeValue("class","catalog-item__title wbh-86").get(0).child(0).text();
        String itemPrice = elements.get(0).getElementsByAttributeValue("class","product-price__top").attr("value");
        outputAttributes.add(itemName);
        outputAttributes.add(itemPrice);
        return outputAttributes;
    }
    
    public List<String> getFromVarus(String item){
        driver.get("https://varus.ua/search?q="+item+"&sort=price.asc");

        Document doc = Jsoup.parse(driver.getPageSource());
        Elements elements = doc.getElementsByAttributeValue("class","sf-product-card__wrapper m-category-list__item");        

        List<String> outputAttributes = new ArrayList<>();
        String itemName = elements.get(0).getElementsByAttributeValue("class","sf-product-card__title").text();
        String itemPrice = elements.get(0).getElementsByAttributeValue("class","sf-price__regular").text();
        outputAttributes.add(itemName);
        outputAttributes.add(itemPrice);
        return outputAttributes;
        
    }
    //TODO
    public List<String> getFromOther(String item, String siteURL){
        
        driver.get(siteURL+"/ua/search/?text="+item+"&sort=cheap");

        Document doc = Jsoup.parse(driver.getPageSource());
        
        List<String> outputAttributes = new ArrayList<>();
        String itemName = doc.getElementsByAttributeValueMatching("class","__title").text();
        String itemPrice = doc.getElementsByAttributeValueMatching("class","price").text();
        outputAttributes.add(itemName);
        outputAttributes.add(itemPrice);
        return outputAttributes;

    }
}
