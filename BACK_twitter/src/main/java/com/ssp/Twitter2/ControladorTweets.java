package com.ssp.Twitter2;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorTweets {

    ArrayList<Tweet>  listaTweets = new ArrayList<>();

    //Post para enseñar todos los tweets
    @PostMapping(path = "/Tweets") //Llama a todo el fichero
    public ResponseEntity<ArrayList<Tweet>> nuevoTweet() { //Es tipo array porque el post es de todos los tweets

        LectorJson Leer = new LectorJson();
        ArrayList<Tweet> listadoTweets= new ArrayList<>();
        listadoTweets=Leer.LecturaJSONsimple();

        this.listaTweets = listadoTweets;

        return new ResponseEntity<ArrayList<Tweet>>(this.listaTweets, HttpStatus.CREATED);
    }

    @PostMapping(path = "/Tweets/Tweet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Tweet>> nuevoUsuario(@RequestBody Tweet nuevotweet) {//Llama solo a Datos

        this.listaTweets.add(nuevotweet);

        return new ResponseEntity<ArrayList<Tweet>>(this.listaTweets, HttpStatus.CREATED);
    }


    //Get para enseñar todos los tweets
    @GetMapping("/Tweets")
    public ArrayList<Tweet> GetTodosTweets(){return this.listaTweets;} //Es tipo array porque el get es de todos los tweets


    //Get para enseñar un solo tweet por su id
    @GetMapping("/Tweets/{id}")
    public  ResponseEntity<Tweet> GetPrestamos(@PathVariable int id){

        Tweet auxDatos = new Tweet();

        for (int i = 0; i < listaTweets.size(); i++) {
            if (listaTweets.get(i).getId() == id) {
                auxDatos = listaTweets.get(i);
                return new ResponseEntity<Tweet>(auxDatos, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/Tweets/{id}")
    public ResponseEntity<ArrayList<Tweet>> PutDato(@RequestBody Tweet tweet, @PathVariable int id){// como el tipo de respuesta es un array porque quiero que me devuelva todos los tweets
        for (int i = 0; i < listaTweets.size(); i++){
            if(listaTweets.get(i).getId() == id){

                listaTweets.set(i, tweet);

                return new ResponseEntity<ArrayList<Tweet>>(this.listaTweets, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/Tweets/{id}")
    public ResponseEntity<ArrayList<Tweet>> DeleteDato(@PathVariable int id){
        for (int i = 0; i < listaTweets.size(); i++){
            if(listaTweets.get(i).getId() == id){
                listaTweets.remove(i);
                return new ResponseEntity<ArrayList<Tweet>>(this.listaTweets, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
