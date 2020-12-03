/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author Jeanca
 */
public class Person {

    private  String url;
    private double angle;
    private double[] posicion;

    public Person(int i,double angle) {
        this.angle = angle;
        double positionX = Simulacion.CENTERX + Simulacion.separacion_personas * cos(angle);
        double positionY = Simulacion.CENTERY + Simulacion.separacion_personas * sin(angle);
        posicion = new double[]{positionX, positionY};
        url = "p" + i;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double[] getPosition() {
        return posicion;
    }

    public void setPosition(double[] position) {
        this.posicion = position;
    }

    public boolean isOcupied() {
        return true;
    }

    public String getUrl() {
        return url;
    }

   

}
