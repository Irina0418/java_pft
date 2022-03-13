package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactDateGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public  String file;

    @Parameter(names = "-d", description = "Date format")
    public  String format;


    public static void main(String[] args) throws IOException {
        ContactDateGenerator generator = new ContactDateGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }

        generator.run();
        // int count = Integer.parseInt(args[0]);
        // File file = new File(args[1]);

    }

    private void run() throws IOException {
        List<ContactDate> contacts = generateContacts(count);
        if(format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        } else if(format.equals("xml")) {
            saveAsXML(contacts, new File(file));
        } else if(format.equals("json")) {
            saveAsJson(contacts, new File(file));
        }else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactDate> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXML(List<ContactDate> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        //xStream.alias("group", GroupDate.class);
        xStream.processAnnotations(ContactDate.class);
        String xml = xStream.toXML(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private  void saveAsCsv(List<ContactDate> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactDate contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                        contact.getAddress(), contact.getHome(), contact.getEmail()));
            }
        }
    }

    private  List<ContactDate> generateContacts(int count) {
        List <ContactDate> contacts = new ArrayList<ContactDate>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactDate().withFirstname(String.format("Firstname %s", i))
                                            .withLastname(String.format("Lastname %s", i))
                                            .withAddress(String.format("Address %s", i))
                                            .withHome(String.format("123456789 %s", i))
                                            .withEmail(String.format("Email %s", i)));
        }
        return contacts;
    }

}
