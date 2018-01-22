package com.jivesoftware.rest.box;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.xml.sax.SAXException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
/**
 * Created by hodkashtan on 25/12/2017.
 */
@Path("xpo")
public class HodMockController {
    private static String BOX_TOKENS_FILE_NAME = "spoTestTokens.json";

    @GET
    @Produces({MediaType.TEXT_HTML})
    @Path("/get")
    public String getTokens() throws IOException {


        return "get request is working";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/get/sec")
    public String getSecondLevel() throws IOException {

        return "get sec request is working";
    }


    @POST
    @Produces({MediaType.TEXT_HTML})
    @Path("/J59")
    public String J59(String xmlBody) throws UnsupportedEncodingException {

        String body = URLDecoder.decode(Jsoup.parse(xmlBody, "", Parser.xmlParser()).toString() , "UTF-8" );
        int start = body.indexOf("int_in")+7;
        String int_in=body.substring(start);
        String sessionId=body.substring(10,body.indexOf("&"));
        Document doc = Jsoup.parse(int_in, "", Parser.xmlParser());

        String personId = doc.getElementsByTag("id").text();
        String cardNo = doc.getElementsByTag("cardNo").text();
        String cardBin = cardNo.substring(0,5);
        String last4Digit = cardNo.substring(12);
        String cardMask = cardBin + "******" + last4Digit;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String response =
                "<ashrait>\n" +
                "<response>\n" +
                "  <command>doDeal</command>\n" +
                "  <dateTime>"+sdf.toString()+"</dateTime>\n" +
                "  <requestId/>\n" +
                "  <tranId>"+generateRandomDigitsString(8)+"</tranId>\n" +
                "  <result>000</result>\n" +
                "  <message>Permitted transaction.</message>\n" +
                "  <userMessage>Permitted transaction.</userMessage>\n" +
                "  <additionalInfo/>\n" +
                "  <version>1001</version>\n" +
                "  <language>Eng</language>\n" +
                "  <doDeal>\n" +
                "   <status>000</status>\n" +
                "   <statusText>Permitted transaction.</statusText>\n" +
                "   <terminalNumber>0963626</terminalNumber>\n" +
                "   <cardId>"+generateRandomDigitsString(16)+"</cardId>\n" +
                "   <cardBin>"+cardBin+"</cardBin>\n" +
                "   <cardMask>"+cardMask+"</cardMask>\n" +
                "   <cardLength>16</cardLength>\n" +
                "   <cardNo>xxxxxxxxxxxx"+last4Digit+"</cardNo>\n" +
                "   <cardName/>\n" +
                "   <cardExpiration>0518</cardExpiration>\n" +
                "   <cardType code=\"0\">Local</cardType>\n" +
                "   <extendedCardType>Credit</extendedCardType>\n" +
                "   <creditCompany code=\"11\">Isracard</creditCompany>\n" +
                "   <cardBrand code=\"1\">Mastercard</cardBrand>\n" +
                "   <cardAcquirer code=\"1\">Isracard</cardAcquirer>\n" +
                "   <serviceCode>000</serviceCode>\n" +
                "   <transactionType code=\"02\">AuthDebit</transactionType>\n" +
                "   <creditType code=\"1\">RegularCredit</creditType>\n" +
                "   <currency code=\"1\">ILS</currency>\n" +
                "   <transactionCode code=\"50\">Phone</transactionCode>\n" +
                "   <total>1134</total>\n" +
                "   <balance/>\n" +
                "   <starTotal>0</starTotal>\n" +
                "   <firstPayment/>\n" +
                "   <periodicalPayment/>\n" +
                "   <numberOfPayments/>\n" +
                "   <clubId/>\n" +
                "   <clubCode>0</clubCode>\n" +
                "   <validation code=\"49\">AutoCommNotifyObligo</validation>\n" +
                "   <commReason code=\"B\"/>\n" +
                "   <idStatus code=\"0\">Absent</idStatus>\n" +
                "   <cvvStatus code=\"0\">Absent</cvvStatus>\n" +
                "   <authSource code=\"1\">Shva</authSource>\n" +
                "   <authNumber>"+generateRandomDigitsString(7)+"</authNumber>\n" +
                "   <fileNumber>20</fileNumber>\n" +
                "   <slaveTerminalNumber>000</slaveTerminalNumber>\n" +
                "   <slaveTerminalSequence>032</slaveTerminalSequence>\n" +
                "   <creditGroup>32</creditGroup>\n" +
                "   <pinKeyIn>0</pinKeyIn>\n" +
                "   <pfsc>0</pfsc>\n" +
                "   <eci>0</eci>\n" +
                "   <cavv code=\"0\">NoObligoNotification</cavv>\n" +
                "   <user>666767863746</user>\n" +
                "   <addonData>944</addonData>\n" +
                "   <supplierNumber>0071506</supplierNumber>\n" +
                "   <id/>\n" +
                "   <shiftId1/>\n" +
                "   <shiftId2/>\n" +
                "   <shiftId3/>\n" +
                "   <shiftTxnDate/>\n" +
                "   <authAmount>40000</authAmount>\n" +
                "  </doDeal>\n" +
                "</response>\n" +
                "</ashrait>";

        return response;
    }

    @POST
    @Produces({MediaType.TEXT_HTML})
    @Path("/Relay")
    public String appCallsCC(String xmlBody) throws IOException, ParserConfigurationException, SAXException {

        String body = URLDecoder.decode(Jsoup.parse(xmlBody, "", Parser.xmlParser()).toString() , "UTF-8" );
        int start = body.indexOf("int_in")+7;
        String int_in=body.substring(start);
        String sessionId=body.substring(10,body.indexOf("&"));
        Document doc = Jsoup.parse(int_in, "", Parser.xmlParser());

        String personId = doc.getElementsByTag("id").text();
        String cardNo = doc.getElementsByTag("cardNo").text();
        String cardBin = cardNo.substring(0,5);
        String last4Digit = cardNo.substring(12);
        String cardMask = cardBin + "******" + last4Digit;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String response = "<?xml version='1.0' encoding='ISO-8859-8'?>\n" +
                "<ashrait>\n" +
                "    <response>\n" +
                "        <command>doDeal</command>\n" +
                "        <dateTime>"+sdf.toString()+"</dateTime>\n" +
                "        <requestId></requestId>\n" +
                "        <tranId>"+generateRandomDigitsString(8)+"</tranId>\n" +
                "        <result>000</result>\n" +
                "        <message>עסקה תקינה</message>\n" +
                "        <userMessage>עסקה תקינה</userMessage>\n" +
                "        <additionalInfo></additionalInfo>\n" +
                "        <version>1000</version>\n" +
                "        <language>Heb</language>\n" +
                "        <doDeal>\n" +
                "            <status>000</status>\n" +
                "            <statusText>עסקה תקינה</statusText>\n" +
                "            <terminalNumber>0963626</terminalNumber>\n" +
                "            <cardId>"+generateRandomDigitsString(16)+"</cardId>\n" +
                "            <cardBin>"+cardBin+"</cardBin>\n" +
                "            <cardMask>"+cardMask+"</cardMask>\n" +
                "            <cardLength>16</cardLength>\n" +
                "            <cardNo>xxxxxxxxxxxx"+last4Digit+"</cardNo>\n" +
                "            <cardName>laC )ויזה(</cardName>\n" +
                "            <cardExpiration>1120</cardExpiration>\n" +
                "            <cardType code=\"0\">Local</cardType>\n" +
                "            <extendedCardType>Credit</extendedCardType>\n" +
                "            <creditCompany code=\"20\">Visa</creditCompany>\n" +
                "            <cardBrand code=\"2\">Visa</cardBrand>\n" +
                "            <cardAcquirer code=\"6\">Alphacard</cardAcquirer>\n" +
                "            <serviceCode>000</serviceCode>\n" +
                "            <transactionType code=\"02\">AuthDebit</transactionType>\n" +
                "            <creditType code=\"1\">RegularCredit</creditType>\n" +
                "            <currency code=\"1\">ILS</currency>\n" +
                "            <transactionCode code=\"50\">Phone</transactionCode>\n" +
                "            <total>0</total>\n" +
                "            <balance></balance>\n" +
                "            <starTotal>0</starTotal>\n" +
                "            <firstPayment></firstPayment>\n" +
                "            <periodicalPayment></periodicalPayment>\n" +
                "            <numberOfPayments></numberOfPayments>\n" +
                "            <clubId></clubId>\n" +
                "            <clubCode></clubCode>\n" +
                "            <validation code=\"5\">Verify</validation>\n" +
                "            <commReason code=\"\"></commReason>\n" +
                "            <idStatus code=\"3\">NotValidated</idStatus>\n" +
                "            <cvvStatus code=\"3\">NotValidated</cvvStatus>\n" +
                "            <authSource code=\"3\">VoiceMail</authSource>\n" +
                "            <authNumber>"+generateRandomDigitsString(7)+"</authNumber>\n" +
                "            <fileNumber>29</fileNumber>\n" +
                "            <slaveTerminalNumber>001</slaveTerminalNumber>\n" +
                "            <slaveTerminalSequence>001</slaveTerminalSequence>\n" +
                "            <creditGroup></creditGroup>\n" +
                "            <pinKeyIn></pinKeyIn>\n" +
                "            <pfsc></pfsc>\n" +
                "            <eci></eci>\n" +
                "            <cavv code=\" \"></cavv>\n" +
                "            <user>33</user>\n" +
                "            <addonData></addonData>\n" +
                "            <supplierNumber>0300012</supplierNumber>\n" +
                "            <intIn>Bxxxxxxx5270C0D011150E7779319J5TxxxxUxxxY036683183X33</intIn>\n" +
                "            <intOt>0000xxxxxxxxxxxxxxx5270260005xxxx3300000000        000000002021 150  3777931900000000000000000029001001     (הזיו) Cal0                 33</intOt>\n" +
                "            <id>"+personId+"</id>\n" +
                "            <shiftId1></shiftId1>\n" +
                "            <shiftId2></shiftId2>\n" +
                "            <shiftId3></shiftId3>\n" +
                "            <shiftTxnDate></shiftTxnDate>\n" +
                "            <authAmount></authAmount>\n" +
                "        </doDeal>\n" +
                "    </response>\n" +
                "</ashrait>";
        return response;
    }

    private String getTestResource(String resource) {

        return "/opt/dealroom/" + resource;
    }

    private String generateRandomDigitsString(int lengh){
        String ALPHA_NUMERIC_STRING = "0123456789";
        StringBuilder builder = new StringBuilder();
            while (lengh-- != 0) {
                int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            }
            return builder.toString();
        }
    }
////
////    @POST
////    @Path("/post")
////    public void saveTokens(String tokens) throws IOException {
////        //do shit
////    }
//
//    private String getTestResource(String resource) {
//
//        return "/opt/dealroom/" + resource;
//    }





/**
 private static String BOX_TOKENS_FILE_NAME = "spoTestTokens.json";

 @GET
 @Produces({MediaType.APPLICATION_JSON})
 @Path("/getTokens")
 public String getTokens() throws IOException {

 FileInputStream fileReader = null;
 try {
 fileReader = new FileInputStream(getTestResource(BOX_TOKENS_FILE_NAME));
 String tokensJson = IOUtils.toString(fileReader, "utf-8");
 return tokensJson;
 }
 finally {
 if (fileReader != null)
 IOUtils.closeQuietly(fileReader);
 }
 }

 @POST
 @Path("/saveTokens")
 public void saveTokens(String tokens) throws IOException {

 FileWriter fileWriter = null;
 try {
 fileWriter = new FileWriter(getTestResource(BOX_TOKENS_FILE_NAME));
 fileWriter.write(tokens);
 } finally {
 if (fileWriter != null)
 IOUtils.closeQuietly(fileWriter);
 }
 }

 private String getTestResource(String resource) {

 return "/opt/dealroom/" + resource;
 }
 }
 */