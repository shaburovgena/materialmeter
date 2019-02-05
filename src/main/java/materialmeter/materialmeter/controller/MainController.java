package materialmeter.materialmeter.controller;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import materialmeter.materialmeter.domain.LocalFile;
import materialmeter.materialmeter.domain.RemoteFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

@Route("")
public class MainController extends VerticalLayout implements KeyNotifier {

    private final Text text = new Text("");
    private final VerticalLayout layout = new VerticalLayout();
    private LocalFile localFile;
    private RemoteFile remoteFile;

    @Autowired
    public MainController() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        remoteFile = new RemoteFile();

        Document document = remoteFile.getFile();
        Node n = document.getFirstChild();
        NodeList nl = n.getChildNodes();
        Node an, an2;

        for (int i = 0; i < nl.getLength(); i++) {
            an = nl.item(i);
            if (an.getNodeType() == Node.ELEMENT_NODE) {
                NodeList nl2 = an.getChildNodes();

                for (int i2 = 0; i2 < nl2.getLength(); i2++) {
                    an2 = nl2.item(i2);
                    //Имя тега
                    String name = an2.getNodeName().replace("#text", "");
                    add(new HorizontalLayout(new Text((name + "  " + an2.getTextContent()))));
                    System.out.println(an2.getNodeName() + ": type (" + an2.getNodeType() + "):");
                    //Содержимое тега
                    System.out.println(an2.getTextContent());
                }
            }
        }

    }
}
