package com.resto;

import com.resto.classes.Alkohol;
import com.resto.classes.Menu;
import com.resto.classes.Tellimus;
import com.resto.classes.Toit;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


public class Kasutajaliides extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String currentDirectory = System.getProperty("user.dir");
        List<Menu> menu = loeMenu(currentDirectory + "/src/main/resources/com/resto/menu.txt");
        Tellimus tellimus = new Tellimus();
        VBox juur = new VBox();
        juur.setPadding(new Insets(10));
        juur.setSpacing(10);

        Image pilt = new Image("file:"+currentDirectory+"/src/main/resources/com/resto/img/resto.JPG");
        ImageView pildiVaade = new ImageView(pilt);
        pildiVaade.setPreserveRatio(true);
        pildiVaade.setFitHeight(585);
        Text tekst = new Text("Tere tulemast restorani!");
        tekst.setFont(new Font(30));

        Button nupp = new Button("MENÜÜ");
        nupp.setFont(new Font(20));

        juur.getChildren().addAll(pildiVaade, tekst, nupp);
        juur.setAlignment(Pos.CENTER);

        Scene stseen = new Scene(juur, 900, 700);

        nupp.setOnAction(event1 -> {
            VBox juur2 = new VBox();
            juur2.setPadding(new Insets(10));
            juur2.setSpacing(30);

            Image pilt2 = new Image("file:"+currentDirectory+"/src/main/resources/com/resto/img/menu.JPEG");
            ImageView pildiVaade2 = new ImageView(pilt2);
            pildiVaade2.setFitHeight(585);
            pildiVaade2.setPreserveRatio(true);

            Button nupp2 = new Button("OLEN VALMIS TELLIMA");
            nupp2.setFont(new Font(20));
            juur2.getChildren().addAll(pildiVaade2, nupp2);
            juur2.setAlignment(Pos.CENTER);

            Scene stseen2 = new Scene(juur2, 900, 700);

            primaryStage.setScene(stseen2);

            nupp2.setOnAction(event2 -> {
                //stseen 3
                String[] teenindajad = new String[]{"Anneli", "Kärt", "Merike", "Triin"};
                String[] pildiTee = new String[]{"file:"+currentDirectory+"/src/main/resources/com/resto/img/waitress1.JPEG","file:"+currentDirectory+"/src/main/resources/com/resto/img/waitress2.JPEG","file:"+currentDirectory+"/src/main/resources/com/resto/img/waitress3.JPEG","file:"+currentDirectory+"/src/main/resources/com/resto/img/waitress4.JPEG"};

                Random random = new Random();
                int suvalineNr = random.nextInt(0, 4); // Genereeri suvaline indeks massiivis
                String teenindaja = teenindajad[suvalineNr];
                String pildiTeeValitud = pildiTee[suvalineNr];

                Image pilt3 = new Image(pildiTeeValitud);
                ImageView pildiVaade3 = new ImageView(pilt3);
                pildiVaade3.setFitHeight(585);
                pildiVaade3.setPreserveRatio(true);

                VBox juur3 = new VBox();
                juur3.setPadding(new Insets(10));
                juur3.setSpacing(10);

                Text tekst3 = new Text("Olen teie teenindaja " + teenindaja + "! Mida saan teile pakkuda?");
                tekst3.setFont(new Font(20));

                TextField sisestus3 = new TextField();
                sisestus3.setMaxWidth(200);
                sisestus3.setFont(new Font(17));
                sisestus3.setAlignment(Pos.BOTTOM_CENTER);

                juur3.getChildren().addAll(pildiVaade3, tekst3, sisestus3);
                juur3.setAlignment(Pos.CENTER);

                Scene stseen3 = new Scene(juur3, 900, 700);

                primaryStage.setScene(stseen3);

                //nupp pärastiseks
                Button nupp3 = new Button("EDASI");
                nupp3.setFont(new Font(20));

                // boolean, et ei peaks kogu aeg dokumenti küsima :)
                AtomicBoolean dokumentKüsitud = new AtomicBoolean(false);

                sisestus3.setOnAction(event3 -> {
                    //stseen 4
                    // boolean, et kontrollida kas roog on menüüs olemas, et vajadusel veateade anda
                    boolean roogOlemas = false;
                    String soovitudRoog = sisestus3.getText();

                    //veateade
                    if (soovitudRoog == null || soovitudRoog.trim().isEmpty()) {
                        Alert teade = new Alert(Alert.AlertType.ERROR);
                        teade.setTitle("Viga");
                        teade.setHeaderText("Vigane sisestus");
                        teade.setContentText("Te ei sisestanud midagi!");
                        teade.showAndWait();
                        return;
                    }

                    //otsime menüüst toidu
                    for (Menu roog : menu) {
                        // kui tegemist on alkoholiga ja dokumenti ei ole veel küsitud
                        if (roog.getRoog().equals(soovitudRoog) && roog.kasOnAlkohol() && !dokumentKüsitud.get()) {
                            // roog on olemas
                            roogOlemas = true;

                            VBox juur4 = new VBox();
                            juur4.setPadding(new Insets(10));
                            juur4.setSpacing(10);

                            Text tekst4 = new Text("Tegemist on alkohoolse joogiga! Palun sisestage oma isikukood");
                            tekst4.setFont(new Font(20));

                            TextField sisestus4 = new TextField();
                            sisestus4.setMaxWidth(200);
                            sisestus4.setFont(new Font(17));
                            sisestus4.setAlignment(Pos.BOTTOM_CENTER);

                            juur4.getChildren().addAll(pildiVaade3, tekst4, sisestus4);
                            juur4.setAlignment(Pos.CENTER);

                            Scene stseen4 = new Scene(juur4, 900, 700);

                            primaryStage.setScene(stseen4);

                            sisestus4.setOnAction(event4 -> {
                                String isikukood = sisestus4.getText();
                                //kontrollime, kas isikukood on numbriliselt kirjutatud, kui ei ss veateade
                                try {
                                    Long.parseLong((isikukood)); //kuna isikukood on nii suur number, siis kasutame longi
                                } catch (NumberFormatException v) {
                                    Alert teade = new Alert(Alert.AlertType.ERROR);
                                    teade.setTitle("Viga");
                                    teade.setHeaderText("Vigane sisestus");
                                    teade.setContentText("Teie vastus ei ole esitatud numbriliselt!");
                                    teade.showAndWait();
                                    return;
                                }

                                String[] list = isikukood.split("");

                                //kontroll, kas on ikkagi sisestatud 11-tähemärgiline isikukood.
                                if (!(list.length == 11)) {
                                    Alert teade = new Alert(Alert.AlertType.ERROR);
                                    teade.setTitle("Viga");
                                    teade.setHeaderText("Vigane sisestus");
                                    teade.setContentText("Isikukood on 11 tähemärgi pikkune!");
                                    teade.showAndWait();
                                    return;
                                }

                                int esimenenr = Integer.parseInt(list[0]);
                                int teinenr = Integer.parseInt(list[1] + "" + list[2]);

                                // stseen 5 olenevalt juhust
                                VBox juur5 = new VBox();
                                juur5.setPadding(new Insets(10));
                                juur5.setSpacing(10);

                                // meie kontrollime ainult aasta järgi vanust
                                if (esimenenr >= 5 && teinenr >= 05) {
                                    Text tekst5 = new Text("Olete liiga noor!");
                                    tekst5.setFont(new Font(20));

                                    juur5.getChildren().addAll(pildiVaade3, tekst5, nupp3);
                                    juur5.setAlignment(Pos.CENTER);

                                    Scene stseen5 = new Scene(juur5, 900, 700);

                                    primaryStage.setScene(stseen5);
                                    //kuna on liiga noor, siis tellimusele me rooga ei lisa
                                } else {
                                    // dokument nüüd küsitud ja korras - vahetame booleani ära
                                    dokumentKüsitud.set(true);

                                    Text tekst5 = new Text("Aitäh! Korras!");
                                    tekst5.setFont(new Font(20));

                                    juur5.getChildren().addAll(pildiVaade3, tekst5, nupp3);
                                    juur5.setAlignment(Pos.CENTER);

                                    Scene stseen5 = new Scene(juur5, 900, 700);

                                    primaryStage.setScene(stseen5);

                                    //piisavalt vana, lisame tellimusele
                                    tellimus.lisaRoog(roog);
                                }
                            });
                        } else if (roog.getRoog().equals(sisestus3.getText())) {
                            roogOlemas = true;
                            tellimus.lisaRoog(roog);

                            nupp3.fire(); //fire-me "EDASI" nupu ilma lisa stseeni kasutamata
                        }

                        nupp3.setOnAction(event5 -> {
                            //stseen 6
                            VBox juur6 = new VBox();
                            juur6.setPadding(new Insets(10));
                            juur6.setSpacing(10);

                            Text tekst4 = new Text("Kas saan teile veel midagi pakkuda?");
                            tekst4.setFont(new Font(20));

                            Button jah = new Button("JAH");
                            Button ei = new Button("EI");
                            jah.setFont(new Font(20));
                            ei.setFont(new Font(20));
                            HBox nupud = new HBox();
                            nupud.setSpacing(10);
                            nupud.setAlignment(Pos.CENTER);
                            nupud.getChildren().addAll(jah, ei);

                            juur6.getChildren().addAll(pildiVaade2, tekst4, nupud);
                            juur6.setAlignment(Pos.CENTER);

                            Scene stseen6 = new Scene(juur6, 900, 700);

                            primaryStage.setScene(stseen6);

                            jah.setOnAction(event6 -> {
                                //stseen 7
                                VBox juur7 = new VBox();
                                juur7.setPadding(new Insets(10));
                                juur7.setSpacing(10);

                                Text tekst7 = new Text("Mida soovite tellida?");
                                tekst7.setFont(new Font(20));

                                //kuna sisestus on seotud eelneva eventiga, siis viib see meid tagasi ja tekitab nö tsükli
                                juur7.getChildren().addAll(pildiVaade3, tekst7, sisestus3);
                                juur7.setAlignment(Pos.CENTER);

                                Scene stseen7 = new Scene(juur7, 900, 700);

                                primaryStage.setScene(stseen7);
                            });
                            ei.setOnAction(event7 -> {
                                VBox juur8 = new VBox();
                                juur8.setPadding(new Insets(10));
                                juur8.setSpacing(10);

                                Image pilt8 = new Image("file:./pildid/resto.jpg");
                                ImageView pildiVaade8 = new ImageView(pilt8);
                                pildiVaade8.setPreserveRatio(true);

                                Button nupp8 = new Button("KUTSUN TEENINDAJA");
                                nupp8.setFont(new Font(20));

                                Text tekst8 = new Text("Soovikorral kutsuge teenindaja!");
                                tekst8.setFont(new Font(20));

                                //kuna sisestus on seotud eelneva eventiga, siis viib see meid tagasi ja tekitab nö tsükli
                                juur8.getChildren().addAll(pildiVaade8, tekst8, nupp8);
                                juur8.setAlignment(Pos.CENTER);

                                Scene stseen8 = new Scene(juur8, 900, 700);

                                primaryStage.setScene(stseen8);

                                nupp8.setOnAction(event8 -> {
                                    VBox juur9 = new VBox();
                                    juur9.setPadding(new Insets(10));
                                    juur9.setSpacing(10);

                                    Text tekst9 = new Text("Kas saan teile veel midagi pakkuda või soovite arvet?");
                                    tekst9.setFont(new Font(20));

                                    Button tellinUuesti= new Button("SOOVIN VEEL TELLIDA");
                                    Button arve = new Button("SOOVIN ARVET");
                                    arve.setFont(new Font(20));
                                    tellinUuesti.setFont(new Font(20));
                                    HBox nupud1= new HBox();
                                    nupud1.setSpacing(10);
                                    nupud1.setAlignment(Pos.CENTER);
                                    nupud1.getChildren().addAll(tellinUuesti, arve);

                                    juur9.getChildren().addAll(pildiVaade3, tekst9, nupud1);
                                    juur9.setAlignment(Pos.CENTER);

                                    Scene stseen9 = new Scene(juur9, 900, 700);

                                    primaryStage.setScene(stseen9);

                                    tellinUuesti.setOnAction(event9 -> {
                                        jah.fire();
                                    });

                                    arve.setOnAction(event10 -> {
                                        tellimus.võtabArve();
                                        try {
                                            kirjutaTšekk(tellimus.tšekk());
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                        VBox juur10 = new VBox();
                                        juur10.setPadding(new Insets(10));
                                        juur10.setSpacing(10);

                                        Text tekst10 = new Text(tellimus.tšekk() + "\n\nKas soovite teenindajale jootraha jätta?");
                                        tekst10.setFont(new Font(20));

                                        TextField sisestus10 = new TextField();
                                        sisestus10.setFont(new Font(20));
                                        sisestus10.setAlignment(Pos.BOTTOM_CENTER);
                                        Button eiTipp = new Button("EI");
                                        eiTipp.setFont(new Font(20));

                                        HBox nupud2= new HBox();
                                        nupud2.setSpacing(10);
                                        nupud2.setAlignment(Pos.CENTER);
                                        nupud2.getChildren().addAll(sisestus10,eiTipp);

                                        juur10.getChildren().addAll( tekst10, nupud2);
                                        juur10.setAlignment(Pos.CENTER);

                                        Scene stseen10 = new Scene(juur10, 900, 700);

                                        primaryStage.setScene(stseen10);

                                        sisestus10.setOnAction(event11 -> {

                                            try {
                                                Integer.parseInt(sisestus10.getText()); //kuna isikukood on nii suur number, siis kasutame longi
                                            } catch (NumberFormatException v) {
                                                Alert teade = new Alert(Alert.AlertType.ERROR);
                                                teade.setTitle("Viga");
                                                teade.setHeaderText("Vigane sisestus");
                                                teade.setContentText("Teie vastus ei ole esitatud numbriliselt!");
                                                teade.showAndWait();
                                                return;
                                            }
                                            eiTipp.fire();
                                        });
                                        eiTipp.setOnAction(event12 -> {
                                            VBox juur11 = new VBox();
                                            juur11.setPadding(new Insets(10));
                                            juur11.setSpacing(10);

                                            Text tekst11 = new Text("Teie teenindaja " + teenindaja + " tänab teid! Kohtumiseni!");
                                            tekst11.setFont(new Font(20));

                                            Button nupp11 = new Button("LAHKU RESTORANIST");
                                            nupp11.setFont(new Font(20));

                                            juur11.getChildren().addAll(pildiVaade3, tekst11, nupp11);
                                            juur11.setAlignment(Pos.CENTER);

                                            Scene stseen11 = new Scene(juur11, 900, 700);

                                            primaryStage.setScene(stseen11);

                                            nupp11.setOnAction(event13 -> {
                                                primaryStage.close();
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    }
                    // kui sellist valikut menüüs ei ole, siis veateade
                    if (!roogOlemas) {
                        Alert teade = new Alert(Alert.AlertType.ERROR);
                        teade.setTitle("Viga");
                        teade.setHeaderText("Vigane sisestus");
                        teade.setContentText("Sellist valikut meie menüüs ei ole!");
                        teade.showAndWait();
                    }
                });
            });
        });


        primaryStage.setTitle("Restoran");

        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(700);
        primaryStage.setScene(stseen);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static List<Menu> loeMenu(String failinimi) throws Exception {
        List<Menu> valikud = new ArrayList<>();
        File file = new File(failinimi);
        try (Scanner failiLugeja = new Scanner(file, "UTF-8")) {
            while (failiLugeja.hasNextLine()) {
                String rida = failiLugeja.nextLine();
                String[] osad = rida.split(";");
                String roog = osad[0];
                String tahis = osad[1];
                double hind = Double.parseDouble(osad[2]);
                if (osad.length == 4) {
                    Alkohol alkohol = new Alkohol(roog, tahis, hind, true);
                    valikud.add(alkohol);
                } else {
                    Toit uusroog = new Toit(roog, tahis, hind);
                    valikud.add(uusroog);
                }
            }
        }
        return valikud;
    }

    public void kirjutaTšekk(String tellimus) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String[] osad = tellimus.split("\n");
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currentDirectory + "/src/main/resources/com/resto/tšekk.txt"), "UTF-8"))) {
            for (String s : osad) {
                bw.write(s);
                bw.newLine();
            }
        }
    }
}

