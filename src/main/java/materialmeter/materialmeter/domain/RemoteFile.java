package materialmeter.materialmeter.domain;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

public class RemoteFile {

    private String user;
    private String password;
    private String path;
    private SmbFile[] smbFile;
    private NtlmPasswordAuthentication auth;

    private File file;
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;
    private String pathFile;

    public RemoteFile() throws MalformedURLException, SmbException {
        user = "Teknoizo";
        password = "123456789";
        path = "smb://192.168.34.49/DataBase/";
        auth = new NtlmPasswordAuthentication("", user, password);
    }

    public SmbFile[] getSmbFiles() throws MalformedURLException, SmbException {
        return new SmbFile(path, auth).listFiles();
    }

    public void getTag(Document document) throws XPathExpressionException {

        Node n = document.getFirstChild();
        NodeList nl = n.getChildNodes();
        Node an, an2;

        for (int i = 0; i < nl.getLength(); i++) {
            an = nl.item(i);
            if (an.getNodeType() == Node.ELEMENT_NODE) {
                NodeList nl2 = an.getChildNodes();

                for (int i2 = 1; i2 < nl2.getLength(); i2++) {
                    an2 = nl2.item(i2);
                    //Имя тега
                    System.out.println(an2.getNodeName() + ": type (" + an2.getNodeType() + "):");
                    //Содержимое тега
                    System.out.println(an2.getTextContent());
                }
            }
        }

    }

    public Document getFile() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

        String ConsumptionC1 = "";
        for (int i = 1; i < getSmbFiles().length; i++) {
            pathFile = String.valueOf(getSmbFiles()[getSmbFiles().length - i]);
            if (pathFile.endsWith("xml")) {
                SmbFile file = new SmbFile(pathFile);
                InputStream is = file.getInputStream();
                System.out.println(pathFile);
                documentBuilderFactory = DocumentBuilderFactory
                        .newInstance();
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
                document = documentBuilder.parse(is);

                ConsumptionC1 = document.getElementsByTagName("ConsumptionC1").item(0).getTextContent();

                String ConsumptionC2 = document.getElementsByTagName("ConsumptionC2").item(0).getTextContent();
                System.out.println(ConsumptionC1);
                getTag(document);
                return document;

            }
        }


        return document;

    }

}
