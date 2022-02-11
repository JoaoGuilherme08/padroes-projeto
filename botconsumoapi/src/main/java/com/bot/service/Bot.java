package com.bot.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Bot {
    static String webService = "http://localhost:8083/userorder";
    static int codigoSucesso = 200;

    public static void executar(String token) throws IOException {
        int x = 0;
        while (x < 1000) {

            x++;
            DecimalFormat df = new DecimalFormat("#.##");

            JSONObject json = new JSONObject();
            int id_user = new Random().nextInt(10) + 1;
            int id_stock = new Random().nextInt(10) + 1;
            int volume = new Random().nextInt(50) + 1;
            Double price = Double.parseDouble(df.format(Math.random() * 100).replace(",",
                    "."));
            int type = (Math.random() <= 0.5) ? 1 : 2;
            String stock_name = "";
            String stock_symbol = "";

            try {

                File file = new File("src/main/java/com/bot/stocks/stocks.xml");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(file);
                document.getDocumentElement().normalize();
                NodeList nList = document.getElementsByTagName("stock");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                        if (id_stock == Integer
                                .parseInt(eElement.getElementsByTagName("id").item(0).getTextContent())) {
                            stock_name = eElement.getElementsByTagName("name").item(0).getTextContent();
                            stock_symbol = eElement.getElementsByTagName("simbol").item(0).getTextContent();

                            json.put("idUser", id_user);
                            json.put("idStock", id_stock);
                            json.put("volume", volume);
                            json.put("price", price);
                            json.put("type", type);
                            json.put("stockName", stock_name);
                            json.put("stockSymbol", stock_symbol);
                            json.put("status", 1);

                            // System.out.print(json);
                            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

                            try {
                                HttpPost request = new HttpPost("http://localhost:8083/userorder");
                                StringEntity params = new StringEntity(json.toString());
                                request.addHeader("Content-Type", "application/json");
                                request.addHeader("Authorization", token);
                                request.setEntity(params);
                                System.out.print(httpClient.execute(request));
                            } catch (Exception ex) {
                                System.out.println(ex);
                            } finally {
                                httpClient.close();
                            }
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
}
