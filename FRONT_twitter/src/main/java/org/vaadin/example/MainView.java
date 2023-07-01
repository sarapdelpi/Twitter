package org.vaadin.example;

import com.nimbusds.jose.shaded.gson.Gson;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")

public class MainView extends VerticalLayout {

    private static final String API_URL = "http://localhost:8085/";
    HttpRequest request;
    HttpClient cliente = HttpClient.newBuilder().build();
    HttpResponse response;

    final Gson gson = new Gson();
    ArrayList<Tweet> listaTweets = new ArrayList<>();
    Tweet AuxTweet = new Tweet();

    //Creo grid (uno por pestaña)
    Grid<Tweet> tweetGrid = new Grid<>(Tweet.class);

    //Creo boton para borrar tweet
    Button borrarTweet = new Button("Borrar Tweet");

    Dialog dialog = new Dialog();

    //Metodo para meter en una clase lo que me devuelve el API
    public Tweet TweetApi(){

        Tweet TweetJSon = new Tweet();

        String StringTweet = getData("Tweet", null);

        TweetJSon = gson.fromJson(StringTweet, Tweet.class);

        return TweetJSon;
    }

    //Metodo para llamar a post de API
    private void postData(String url1, String url2, Tweet AuxTweet) {
        try {

            String resource;

            if (url2 == null){

                resource = String.format(API_URL + url1);

                request = HttpRequest
                        .newBuilder(new URI(resource))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(""))
                        .build();

                response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            }
            else{

                resource = String.format(API_URL + url1 + "/" + url2);

                request = HttpRequest
                        .newBuilder(new URI(resource))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(AuxTweet.toString()))
                        .build();

                response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Metodo para llamar a get de API
    private String getData(String url1, String url2){
        try {
            String resource;

            if (url2 == null){
                resource = String.format(API_URL + url1);
            }
            else{
                resource = String.format(API_URL + url1 + "/" + url2);
            }

            request= HttpRequest
                    .newBuilder(new URI(resource))
                    .header("Content-Type","application/json")
                    .GET()
                    .build();
            response=cliente.send(request,HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (String) response.body();
    }

    private void putData(String url1, String url2, int Id, Tweet tweet){
        try {
            String resource = String.format(API_URL + url1 + "/" + url2 + "/" + Id);

                request= HttpRequest
                        .newBuilder(new URI(resource))
                        .header("Content-Type","application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(tweet.toString()))
                        .build();
                response=cliente.send(request,HttpResponse.BodyHandlers.ofString());


        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void deletetData(String url1, String url2, int Id, Tweet tweet){
        try {
            String resource = String.format(API_URL + url1 + "/" + url2 + "/" + Id);

                request= HttpRequest
                        .newBuilder(new URI(resource))
                        .header("Content-Type","application/json")
                        .DELETE()
                        .build();
                response=cliente.send(request,HttpResponse.BodyHandlers.ofString());



        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public MainView(@Autowired GreetService service) {

        // Use TextField for standard text input
        TextField textField = new TextField("Your name");
        textField.addThemeName("bordered");

        // Button click listeners can be defined as lambda expressions
        Button button = new Button("Say hello",
                e -> Notification.show(service.greet(textField.getValue())));

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        add(textField, button);
    }

}
