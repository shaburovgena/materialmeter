package materialmeter.materialmeter.controller;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import materialmeter.materialmeter.domain.LocalFile;
import materialmeter.materialmeter.domain.RemoteFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Route("")
public class MainController extends VerticalLayout implements KeyNotifier {

    private final Text text = new Text("");
    private LocalFile localFile;
private RemoteFile remoteFile;
    @Autowired
    public MainController() throws IOException, SAXException, ParserConfigurationException {
        localFile = new LocalFile();
        remoteFile = new RemoteFile();


        add(text);
        text.setText(String.valueOf(remoteFile.getPath()));
    }
}
