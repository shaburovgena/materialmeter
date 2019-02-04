package materialmeter.materialmeter.domain;

import jcifs.smb.SmbFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class LocalFile {
    private File file;
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Document document;
    private String usr;
    private String pwd;

    public LocalFile() throws ParserConfigurationException, IOException, SAXException {

        file = new File("\\Users\\shaburov\\IdeaProjects\\materialmeter\\src\\main\\resources\\userdata.xml");
        documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(file);
        usr = document.getElementsByTagName("user").item(0).getTextContent();
        pwd = document.getElementsByTagName("password").item(0).getTextContent();

    }

    public String getUsr() {
        return usr;
    }

    public String getPwd() {
        return pwd;
    }


}
