/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jeanca
 */
public class Person {

    private ImageView persona_imagen;
    private boolean alive;

    public ImageView getPersona_imagen() {
        return persona_imagen;
    }

    public void setPersona_imagen(Image persona_imagen) {
        this.persona_imagen.setImage(persona_imagen);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Person(ImageView persona_imagen, boolean alive) {
        this.persona_imagen = persona_imagen;
        this.alive = alive;
    }

}
