package ru.job4j.optimizationxml;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
//import java.sql.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for create and perform xml document from data of database.
 * @author atrifonov.
 * @version 1.
 * @since 24.10.2017.
 */
public class Performer {
    /**
     * Count items for insert into table of data base.
     */
    private static final int BUFFER_SIZE = 100000;
    /**
     * Path to base.
     */
    private String url;
    /**
     * Count of numbers.
     */
    private int count;

    /**
     * Set value of url.
     * @param url new value of url
     */
    public void setUrl(String url) {
        this.url = String.format("%s%s", "jdbc:sqlite:", url);
    }

    /**
     * Set value of count.
     * @param count new value of count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Paste in base numbers, create xml document and perform xml document in another xml document.
     */
    public void doWork() {
        if (url == null && count == 0) {
            System.out.println("url of base and number count wasn't initialized");
            return;
        }

        Connection conn = null;
        Statement statmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(this.url);
            conn.setAutoCommit(false);
            statmt = conn.createStatement();

            statmt.execute("CREATE TABLE if NOT EXISTS 'TEST' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'FIELD' INTEGER);");
            statmt.execute("DELETE FROM 'TEST';");

            try {
                fillBase(statmt);
                conn.commit();
            } catch (Exception ex) {
                conn.rollback();
            }

            createFirstXMLDoc(statmt);
            InputStream is = getClass().getResourceAsStream("make_attribute.xsl");
            StreamSource styleSource = new StreamSource(is);
            Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
            t.transform(new StreamSource("1.xml"), new StreamResult("2.xml"));
            System.out.printf("%s = %d", "sum", evaluateSumAtrb());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && statmt != null) {
                    statmt.close();
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    /**
     * Fill base item from 1 to this.count.
     * @param statmt PreparedStatement for execute static SQL statement.
     * @throws SQLException SQLException.
     */
    private void fillBase(Statement statmt) throws SQLException {
        int countBuffers = this.count / BUFFER_SIZE;
        StringBuilder sb;
        String command = "INSERT INTO 'TEST' ('FIELD') VALUES";
        if (countBuffers != 0) {

            for (int i = 0; i < countBuffers; i++) {
                sb = itemsSequence(i * BUFFER_SIZE + 1, BUFFER_SIZE * (i + 1));
                statmt.addBatch(String.format("%s %s", command, sb.toString()));
            }

            if (this.count % BUFFER_SIZE != 0) {
                sb = itemsSequence(1 + countBuffers * BUFFER_SIZE, this.count);
                statmt.addBatch(String.format("%s %s", command, sb.toString()));
            }

            int[] countExec = statmt.executeBatch();
            for (int x: countExec) {
                if (x <= 0) {
                    throw new SQLException();
                }
            }
        } else {
            sb = itemsSequence(1, this.count);
            statmt.execute(String.format("%s %s", command, sb.toString()));
        }
    }

    /**
     * Create StringBuilder that is sequence many items.
     * @param start first item in sequence.
     * @param end last item in sequence.
     * @return StringBuilder is sequence items.
     */
    private StringBuilder itemsSequence(int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append("(");
            sb.append(i);
            sb.append(")");
            if (i != end) {
                sb.append(",");
            }
        }
        sb.append(";");
        return sb;
    }

    /**
     * Create xml document with values from data base.
     * @param statmt Statement for execute static SQL statement.
     * @throws SQLException Exception.
     * @throws JAXBException Exception.
     */
    private void createFirstXMLDoc(Statement statmt) throws SQLException, JAXBException {
        ResultSet resSet = statmt.executeQuery("SELECT * FROM 'TEST';");
        List<Entry> entryList = new LinkedList<>();
        while (resSet.next()) {
            int field = resSet.getInt("FIELD");
            //entryList.add(new Entry(field));
        }
        File file = new File(String.format("%s%s", System.getProperty("user.dir"), "\\1.xml"));
        Entries entries = new Entries(entryList);
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(entries, file);
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        if (resSet != null) {
            resSet.close();
        }

    }

    /**
     * Evaluate sum of values attributes in file xml.
     * @return sum.
     */
    private long evaluateSumAtrb() {
        File file = new File(String.format("%s%s", System.getProperty("user.dir"), "\\2.xml"));
        long sum = 0;
        try {
            FileInputStream in = new FileInputStream(file);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            while (parser.hasNext()) {
                int event = parser.next();
                if (event == XMLStreamConstants.START_ELEMENT && parser.getLocalName().equals("entry")) {
                    Integer fieldValue = Integer.parseInt(parser.getAttributeValue(null, "field"));
                    sum += fieldValue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }
}
