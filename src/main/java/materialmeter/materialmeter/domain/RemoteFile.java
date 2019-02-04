package materialmeter.materialmeter.domain;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

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

    public RemoteFile() throws MalformedURLException, SmbException {
        user = "Teknoizo";
        password = "123456789";
        path = "smb://192.168.34.49/DataBase/";
        System.out.println(user + "  " + password + "  " + path);
        auth = new NtlmPasswordAuthentication("", user, password);
        System.out.println("completed ...nice !");
    }

    public SmbFile[] getSmbFiles() throws MalformedURLException, SmbException {
        return new SmbFile(path, auth).listFiles();
    }


    public String getPath() throws IOException, ParserConfigurationException, SAXException {

            System.out.println(getSmbFiles()[getSmbFiles().length -4]);
            SmbFile file = new SmbFile(String.valueOf(getSmbFiles()[getSmbFiles().length -4]));
            System.out.println(file.getDate() + "/" + LocalDateTime.now());

        InputStream is = file.getInputStream();
        System.out.println(is);

        documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(is);
        String ConsumptionC1 = document.getElementsByTagName("ConsumptionC1").item(0).getTextContent();
        String ConsumptionC2 = document.getElementsByTagName("ConsumptionC2").item(0).getTextContent();
        System.out.println(ConsumptionC1);


        return ConsumptionC1;

    }

}
